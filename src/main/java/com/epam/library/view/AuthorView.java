package com.epam.library.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

import com.epam.library.domain.book.Author;

public class AuthorView {
    private Long id;
    private String name;
    private String pseudonym;
    private String yearOfBirth;

    public AuthorView(Author author) {
        this.name = author.getName();
        this.pseudonym = author.getPseudonym();
        this.yearOfBirth = author.getYearOfBirth();
        this.id = author.getId();
    }

    public static Collection<AuthorView> of(Page<Author> all) {
        List<AuthorView> views = new ArrayList<>(all.getSize());
        for (Author aut : all) {
            views.add(new AuthorView(aut));
        }

        return views;
    }

    public String getName() {
        return name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public Long getId() {
        return id;
    }
}
