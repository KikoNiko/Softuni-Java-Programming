package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllGoldenEditionTitlesWithCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal p1, BigDecimal p2);

    List<Book> findAllNotReleasedInGivenYear(int year);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<String> findAllByTitleContainingIgnoreCase(String string);

    List<String> findAllByAuthorLastNameStartingWIth(String string);

    int findCountOfAllByTitleLengthGreaterThan(int number);

    long getAllBookCopiesByAuthor(Author author);

    int deleteAllByCopiesLessThan(Integer copies);
}
