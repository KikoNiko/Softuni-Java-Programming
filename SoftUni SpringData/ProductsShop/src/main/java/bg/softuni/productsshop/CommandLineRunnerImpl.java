package bg.softuni.productsshop;

import bg.softuni.productsshop.services.CategoryService;
import bg.softuni.productsshop.services.ProductService;
import bg.softuni.productsshop.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.userService.seedUsers();
        this.productService.seedProducts();
        // this.productService.printAllProductsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        // this.userService.printSeller("Carl", "Lawson");
        // this.userService.printAllSellers();
        // this.categoryService.printCategoriesByProducts();
    }
}
