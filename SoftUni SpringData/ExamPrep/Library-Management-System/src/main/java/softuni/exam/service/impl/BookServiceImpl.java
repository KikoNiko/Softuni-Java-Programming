package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookSeedDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.enums.GenreType;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private static final String FILE_PATH = "src/main/resources/files/json/books.json";
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder sb = new StringBuilder();
        BookSeedDto[] bookSeedDtos = this.gson.fromJson(readBooksFromFile(), BookSeedDto[].class);
        for (BookSeedDto bookSeedDto : bookSeedDtos) {
            Optional<Book> optionalBook = this.bookRepository.findByTitle(bookSeedDto.getTitle());
            if (!validationUtil.isValid(bookSeedDto) || optionalBook.isPresent()) {
                sb.append("Invalid book").append(System.lineSeparator());
                continue;
            }
            Book book = this.modelMapper.map(bookSeedDto, Book.class);
            book.setGenre(GenreType.valueOf(bookSeedDto.getGenre()));
            this.bookRepository.saveAndFlush(book);
            sb.append(String.format("Successfully imported book %s - %s%n", book.getAuthor(), book.getTitle()));
        }
        return sb.toString().trim();
    }
}
