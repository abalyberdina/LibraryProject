package com.epam.library.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.epam.library.domain.book.Genre;

public class ManageGenreForm {

    @NotBlank
    @Length(max = 50)
    private String name;

//    @NotBlank(message = "{error.parentgenrename.mustbefilled}")
//    @Length(max = 50)
//    private String parentGenre;

    public ManageGenreForm() {

    }

    public ManageGenreForm(Genre genre) {
        this.name = genre.getGenreName();
//        this.parentGenre = genre.getParentGenre().getGenreName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public String getParentGenre() {
//        return parentGenre;
//    }
//
//    public void setParentGenre(String parentGenre) {
//        this.parentGenre = parentGenre;
//    }

}
