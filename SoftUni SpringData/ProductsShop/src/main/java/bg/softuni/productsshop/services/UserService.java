package bg.softuni.productsshop.services;

import bg.softuni.productsshop.services.dtos.exports.UserProductsDto;
import bg.softuni.productsshop.services.dtos.exports.UserSoldProductsDto;

import java.io.FileNotFoundException;
import java.util.Set;

public interface UserService {
    void seedUsers() throws FileNotFoundException;

    UserSoldProductsDto getSellerByFirstAndLastName(String firstName, String lastName);

    void printSeller(String firstName, String lastName);

    Set<UserSoldProductsDto> getAllSellers();

    void printAllSellers();

}
