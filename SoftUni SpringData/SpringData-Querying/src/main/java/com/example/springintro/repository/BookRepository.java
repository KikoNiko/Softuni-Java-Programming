package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal p1, BigDecimal p2);

    @Query("FROM Book WHERE YEAR(releaseDate) != :year")
    List<Book> findAllNotReleasedInGivenYear(int year);

    List<Book> findAllByTitleContainingIgnoreCase(String string);

    @Query("FROM Book WHERE author.lastName LIKE :string%")
    List<Book> findAllByAuthorLastNameStartingWIth(String string);

    @Query("FROM Book WHERE LENGTH(title) > :number")
    List<Book> findAllByTitleLengthGreaterThan(int number);

    List<Book> findAllByAuthorOrderByCopiesDesc(Author author);

    @Transactional
    int deleteAllByCopiesLessThan(Integer copies);
}
