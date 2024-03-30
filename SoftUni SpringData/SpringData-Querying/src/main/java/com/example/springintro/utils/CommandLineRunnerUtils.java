package com.example.springintro.utils;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class CommandLineRunnerUtils {
    private static final String PRINT_TITLE_AND_PRICE_FORMAT = "%s - $%.2f%n";

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public CommandLineRunnerUtils(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    private void printAuthorAndBookCopies(String firstName, String lastName) {
        Author author = authorService.getAuthorByFirstNameAndLastName(firstName, lastName);
        long copies = bookService.getAllBookCopiesByAuthor(author);
        System.out.println(firstName + " " + lastName + " - " + copies);
    }
    private void printAllTitlesAndAuthorsWhereAuthorLastNameStartsWith(String string) {
        this.bookService.findAllByAuthorLastNameStartingWIth(string)
                .forEach(System.out::println);
    }
    private void printAllTitlesContaining(String string) {
        bookService.findAllByTitleContainingIgnoreCase(string)
                .forEach(System.out::println);
    }
    private void printAllAuthorsWithFirstNameEndingWith(String string) {
        authorService.findAllByFirstNameEndsWith(string)
                .forEach(System.out::println);
    }
    private void printBookTitleEditionTypeAndPriceByReleaseDateBefore(LocalDate localDate) {
        bookService.findAllByReleaseDateBefore(localDate)
                .forEach(b ->
                        System.out.println(b.toStringFormatTitleEditionTypeAndPrice()));
    }

    private void printBooksNotReleasedInGivenYear(int year) {
        bookService
                .findAllNotReleasedInGivenYear(year)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void printBookTitlesAndPricesByPriceLowerThanAndPriceGreaterThan(BigDecimal p1, BigDecimal p2) {
        List<Book> filteredBooks = bookService.findAllByPriceLessThanOrPriceGreaterThan(p1, p2);
        for (Book book : filteredBooks) {
            System.out.printf(PRINT_TITLE_AND_PRICE_FORMAT,
                    book.getTitle(),
                    book.getPrice().doubleValue());
        }
    }
    private void printAllGoldenEditionBooks() {
            bookService
                    .findAllGoldenEditionTitlesWithCopiesLessThan(EditionType.GOLD, 5000)
                .forEach(System.out::println);
    }
    private void printAllBookTitlesByAgeRestrictionType(AgeRestriction ageRestriction) {
        bookService
                .findAllBooksByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
