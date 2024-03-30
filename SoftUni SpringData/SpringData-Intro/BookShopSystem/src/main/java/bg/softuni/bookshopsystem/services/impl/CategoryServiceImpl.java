package bg.softuni.bookshopsystem.services.impl;

import bg.softuni.bookshopsystem.domain.entities.Category;
import bg.softuni.bookshopsystem.repositories.CategoryRepository;
import bg.softuni.bookshopsystem.services.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String FILE_PATH = "src/main/resources/files/categories.txt";
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategory() throws IOException {
        if (this.categoryRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(r -> !r.isBlank())
                    .forEach(r -> this.categoryRepository.saveAndFlush(new Category(r)));
        }
    }

}
