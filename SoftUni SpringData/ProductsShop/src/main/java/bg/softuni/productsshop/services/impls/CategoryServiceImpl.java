package bg.softuni.productsshop.services.impls;

import bg.softuni.productsshop.data.entities.Category;
import bg.softuni.productsshop.data.entities.Product;
import bg.softuni.productsshop.data.repositories.CategoryRepository;
import bg.softuni.productsshop.services.CategoryService;
import bg.softuni.productsshop.services.dtos.exports.CategoryByProductsDto;
import bg.softuni.productsshop.services.dtos.imports.CategorySeedDto;
import bg.softuni.productsshop.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String FILE_PATH = "src/main/resources/json/categories.json";

    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            //String jsonContent = new String(Files.readAllBytes(Path.of(FILE_PATH)));
            //CategorySeedDto[] categorySeedDtos = this.gson.fromJson(jsonContent, CategorySeedDto[].class);

            CategorySeedDto[] categorySeedDtos =
                    this.gson.fromJson(new FileReader(FILE_PATH), CategorySeedDto[].class);

            for (CategorySeedDto dto : categorySeedDtos) {
                if (!this.validationUtil.isValid(dto)) {
                    this.validationUtil.getViolations(dto)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }

                Category category = this.modelMapper.map(dto, Category.class);
                this.categoryRepository.saveAndFlush(category);
            }
        }
    }

    @Override
    public Set<CategoryByProductsDto> getAllByProductCount() {
        return this.categoryRepository.findAllByProductsOrderByProductsCount()
                .stream()
                .map(c -> {
                    CategoryByProductsDto dto = this.modelMapper.map(c, CategoryByProductsDto.class);
                    int productsCount = c.getProducts().size();
                    dto.setProductCount(productsCount);
                    BigDecimal totalRevenue = c.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).get();
                    dto.setAveragePrice(totalRevenue.divide(BigDecimal.valueOf(productsCount), MathContext.DECIMAL32));
                    dto.setTotalRevenue(totalRevenue);

                    return dto;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    @Override
    public void printCategoriesByProducts() {
        System.out.println(this.gson.toJson(this.getAllByProductCount()));
    }
}
