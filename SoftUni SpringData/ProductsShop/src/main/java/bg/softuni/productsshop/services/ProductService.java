package bg.softuni.productsshop.services;

import bg.softuni.productsshop.services.dtos.exports.ProductInRangeDto;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Set;

public interface ProductService {
    void seedProducts() throws FileNotFoundException;

    Set<ProductInRangeDto> getAllProductsInRange(BigDecimal from, BigDecimal to);
    void printAllProductsInRange(BigDecimal from, BigDecimal to);


}
