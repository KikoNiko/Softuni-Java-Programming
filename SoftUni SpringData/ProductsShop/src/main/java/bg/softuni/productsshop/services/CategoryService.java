package bg.softuni.productsshop.services;

import bg.softuni.productsshop.services.dtos.exports.CategoryByProductsDto;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<CategoryByProductsDto> getAllByProductCount();

    void printCategoriesByProducts();
}
