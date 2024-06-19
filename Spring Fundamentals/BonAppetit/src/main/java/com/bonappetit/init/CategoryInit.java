package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryInit implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategoryInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            String description = "";
            for (CategoryName cat : CategoryName.values()) {
                switch (cat) {
                    case MAIN_DISH:
                        description = "Heart of the meal, substantial and satisfying; main dish delights taste buds";
                        break;
                    case DESSERT:
                        description = "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.";
                        break;
                    case COCKTAIL:
                        description = "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.";
                        break;
                }
                Category category = new Category(cat, description);
                categoryRepository.save(category);
            }
        }
    }
}
