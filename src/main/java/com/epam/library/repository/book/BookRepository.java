package com.epam.library.repository.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.book.Author;
import com.epam.library.domain.book.Book;
import com.epam.library.domain.book.Genre;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByBookNameIgnoreCaseContaining(String bookname, Pageable pageable);

    List<Book> findByAuthors(Author author);

    List<Book> findByBookNameIgnoreCase(String name);

    List<Book> findByGenres(Genre genre);

    List<Book> findByAuthorsAndBookNameIgnoreCase(Author author, String name);

    List<Book> findByAuthorsAndGenres(Author author, Genre genre);

    List<Book> findByGenresAndBookNameIgnoreCase(Genre genre, String name);

    List<Book> findByAuthors(Author author, Sort sort);

    List<Book> findByBookNameIgnoreCase(String name, Sort sort);

    List<Book> findByGenres(Genre genre, Sort sort);

    List<Book> findByAuthorsAndBookNameIgnoreCase(Author author, String name, Sort sort);

    List<Book> findByAuthorsAndGenres(Author author, Genre genre, Sort sort);

    List<Book> findByGenresAndBookNameIgnoreCase(Genre genre, String name, Sort sort);

    // @Query("SELECT p FROM Person p JOIN FETCH p.roles WHERE p.id = (:id)")
    // Book getOne(Long id);
}
