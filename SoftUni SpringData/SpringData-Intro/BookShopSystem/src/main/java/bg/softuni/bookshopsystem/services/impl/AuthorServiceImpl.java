package bg.softuni.bookshopsystem.services.impl;

import bg.softuni.bookshopsystem.domain.entities.Author;
import bg.softuni.bookshopsystem.repositories.AuthorRepository;
import bg.softuni.bookshopsystem.services.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final String FILE_PATH = "src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(r -> !r.isBlank())
                    .forEach(r -> {
                        String[] tokens = r.split(" ");
                        this.authorRepository.saveAndFlush(new Author(tokens[0], tokens[1]));
                    });
        }
    }

}
