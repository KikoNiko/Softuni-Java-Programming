package bg.softuni.productsshop.services.impls;

import bg.softuni.productsshop.data.entities.Category;
import bg.softuni.productsshop.data.entities.Product;
import bg.softuni.productsshop.data.entities.User;
import bg.softuni.productsshop.data.repositories.CategoryRepository;
import bg.softuni.productsshop.data.repositories.ProductRepository;
import bg.softuni.productsshop.data.repositories.UserRepository;
import bg.softuni.productsshop.services.ProductService;
import bg.softuni.productsshop.services.dtos.exports.ProductInRangeDto;
import bg.softuni.productsshop.services.dtos.imports.ProductSeedDto;
import bg.softuni.productsshop.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String FILE_PATH = "src/main/resources/json/products.json";

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedProducts() throws FileNotFoundException {
        if (productRepository.count() == 0) {
            ProductSeedDto[] productSeedDtos =
                    this.gson.fromJson(new FileReader(FILE_PATH), ProductSeedDto[].class);

            for (ProductSeedDto dto : productSeedDtos) {
                if (!this.validationUtil.isValid(dto)) {
                    this.validationUtil.getViolations(dto)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }

                Product product = this.modelMapper.map(dto, Product.class);
                product.setBuyer(getRandomUser(true));
                product.setSeller(getRandomUser(false));
                product.setCategories(getRandomCategories());

                this.productRepository.saveAndFlush(product);
            }
        }
    }

    @Override
    public Set<ProductInRangeDto> getAllProductsInRange(BigDecimal from, BigDecimal to) {
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(from, to)
                .stream()
                .map(p -> {
                    ProductInRangeDto dto = this.modelMapper.map(p, ProductInRangeDto.class);
                    dto.setSeller(String.format("%s %s",
                            p.getSeller().getFirstName(),
                            p.getSeller().getLastName()));
                    return dto;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    @Override
    public void printAllProductsInRange(BigDecimal from, BigDecimal to) {
        System.out.println(this.gson.toJson(this.getAllProductsInRange(from, to)));
    }


    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        int randomCount = ThreadLocalRandom.current().nextInt(1,4);
        for (int i = 0; i < randomCount ; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, this.categoryRepository.count() + 1);
            categories.add(this.categoryRepository.findById(randomId).get());
        }

        return categories;
    }

    private User getRandomUser(boolean isBuyer) {
        long randomId = ThreadLocalRandom.current().nextLong(1, this.userRepository.count() + 1);
        return isBuyer && randomId % 4 == 0 ? null : this.userRepository.findById(randomId).get();
    }
}
