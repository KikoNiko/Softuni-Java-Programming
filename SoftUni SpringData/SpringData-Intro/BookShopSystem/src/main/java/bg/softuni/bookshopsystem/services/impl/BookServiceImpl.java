package bg.softuni.bookshopsystem.services.impl;

import bg.softuni.bookshopsystem.repositories.BookRepository;
import bg.softuni.bookshopsystem.services.BookService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookServiceImpl implements BookService {
    private static final String FILE_PATH = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedBooks() throws IOException {

    }
}
