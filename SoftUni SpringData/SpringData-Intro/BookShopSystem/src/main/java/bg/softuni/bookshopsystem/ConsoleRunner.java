package bg.softuni.bookshopsystem;

import bg.softuni.bookshopsystem.services.AuthorService;
import bg.softuni.bookshopsystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, CategoryService categoryService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.authorService.seedAuthors();
//        this.categoryService.seedCategory();
    }
}
