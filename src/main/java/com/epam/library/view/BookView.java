package com.epam.library.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.data.domain.Page;

import com.epam.library.domain.book.Author;
import com.epam.library.domain.book.Book;
import com.epam.library.domain.book.Genre;

public class BookView {

    private String name;
    private String genres;
    private String authors;
    private String number;
    private Long id;

    public BookView(Book book) {
        this.id = book.getId();
        this.number = book.getNumber().toString();
        this.name = book.getBookName();
        
        StringJoiner sj = new StringJoiner(", ");
        for (Genre genre : book.getGenres()) {
            sj.add(genre.getGenreName());
        }
        this.genres = sj.toString();
        
        sj = new StringJoiner(", ");
        for (Author author : book.getAuthors()) {
            sj.add(author.getName());
        }
        this.authors = sj.toString();
    }

    public static Collection<BookView> of(Page<Book> all) {
        List<BookView> views = new ArrayList<>(all.getSize());
        for (Book book : all) {
            views.add(new BookView(book));
        }
        return views;
    }

    public String getName() {
        return name;
    }

    public String getGenres() {
        return genres;
    }

    public String getAuthors() {
        return authors;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
}
