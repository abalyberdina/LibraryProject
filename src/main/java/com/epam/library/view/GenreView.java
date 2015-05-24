package com.epam.library.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

import com.epam.library.domain.book.Genre;

public class GenreView {
    private String name;
    private String parentGenreName;
    private Long id;

    public GenreView(Genre genre) {
        this.name = genre.getGenreName();
        this.id = genre.getId();
        // if (genre.getParentGenre() != null) {
        // this.parentGenreName = genre.getParentGenre().getGenreName();
        // }
    }

    public static Collection<GenreView> of(Page<Genre> all) {
        List<GenreView> views = new ArrayList<>(all.getSize());
        for (Genre genre : all) {
            views.add(new GenreView(genre));
        }
        return views;
    }

    public String getName() {
        return name;
    }

    public String getParentGenreName() {
        return parentGenreName;
    }

    public Long getId() {
        return id;
    }
}
