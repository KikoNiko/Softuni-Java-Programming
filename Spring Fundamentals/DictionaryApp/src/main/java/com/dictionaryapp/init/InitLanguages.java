package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitLanguages implements CommandLineRunner {
    private final LanguageRepository languageRepository;

    public InitLanguages(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (languageRepository.count() == 0) {
            for (LanguageEnum value : LanguageEnum.values()) {
                String description = null;
                switch (value) {
                    case FRENCH:
                        description = "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.";
                        break;
                    case GERMAN:
                        description = "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.";
                        break;
                    case ITALIAN:
                        description = "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.";
                        break;
                    case SPANISH:
                        description = "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.";
                        break;
                }
                Language language = new Language(value, description);
                languageRepository.save(language);
            }

        }
    }
}
