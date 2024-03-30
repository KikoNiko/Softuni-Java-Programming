package bg.softuni.productsshop.services.impls;

import bg.softuni.productsshop.data.entities.Product;
import bg.softuni.productsshop.data.entities.User;
import bg.softuni.productsshop.data.repositories.ProductRepository;
import bg.softuni.productsshop.data.repositories.UserRepository;
import bg.softuni.productsshop.services.UserService;
import bg.softuni.productsshop.services.dtos.exports.ProductSoldDto;
import bg.softuni.productsshop.services.dtos.exports.UserProductsDto;
import bg.softuni.productsshop.services.dtos.imports.UserSeedDto;
import bg.softuni.productsshop.services.dtos.exports.UserSoldProductsDto;
import bg.softuni.productsshop.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String FILE_PATH = "src/main/resources/json/users.json";

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        if (userRepository.count() == 0) {
            UserSeedDto[] userSeedDtos =
                    this.gson.fromJson(new FileReader(FILE_PATH), UserSeedDto[].class);

            for (UserSeedDto dto : userSeedDtos) {
                if (!this.validationUtil.isValid(dto)) {
                    this.validationUtil.getViolations(dto)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }

                this.userRepository.saveAndFlush(this.modelMapper.map(dto, User.class));
            }
        }
    }

    @Override
    public UserSoldProductsDto getSellerByFirstAndLastName(String firstName, String lastName) {
        Set<ProductSoldDto> soldProducts =
                this.productRepository.findAllBySellerFirstNameAndSellerLastNameAndBuyerIsNotNull(firstName, lastName)
                        .stream()
                        .map(p -> this.modelMapper.map(p, ProductSoldDto.class))
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        User seller = this.userRepository.findByFirstNameAndLastNameOrderByLastName(firstName, lastName);
        UserSoldProductsDto dto = this.modelMapper.map(seller, UserSoldProductsDto.class);
        dto.setSoldProducts(soldProducts);
        return dto;
    }


    @Override
    public void printSeller(String firstName, String lastName) {
        System.out.println(this.gson.toJson(this.getSellerByFirstAndLastName(firstName, lastName)));
    }

    @Override
    public Set<UserSoldProductsDto> getAllSellers() {
        Set<UserSoldProductsDto> sellers = new LinkedHashSet<>();
        Set<Product> soldProducts = this.productRepository.findAllByBuyerIsNotNull();
        for (Product product : soldProducts) {
            String firstName = product.getSeller().getFirstName();
            String lastName = product.getSeller().getLastName();

            if (sellers
                    .stream()
                    .noneMatch(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))) {
                UserSoldProductsDto seller = this.getSellerByFirstAndLastName(firstName, lastName);
                sellers.add(seller);
            }

        }
        return sellers;
    }

    @Override
    public void printAllSellers() {
        System.out.println(this.gson.toJson(this.getAllSellers()));
    }



}
