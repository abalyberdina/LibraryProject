package com.epam.library.form.adapter;

import org.springframework.stereotype.Service;

import com.epam.library.domain.book.Genre;
import com.epam.library.form.ManageGenreForm;

@Service
public class ManageGenreFormAdapter {
    public Genre createGenreFromManageGenreForm(ManageGenreForm form) {
        Genre genre = new Genre();
        genre.setGenreName(form.getName().trim());
//        if (form.getParentGenre().trim().length() != 0) {
//            Genre parentGenre = new Genre();
//            parentGenre.setGenreName(form.getParentGenre());
//            genre.setParentGenre(parentGenre);
//        }
        return genre;
    }
}
