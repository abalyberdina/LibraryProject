package com.epam.library.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.epam.library.domain.book.Author;

public class ManageAuthorForm {

    @NotBlank
    @Length(max = 50)
    private String name;

    @Length(max = 50)
    private String pseudonym;

    @Length(max = 50)
    private String yearOfBirth;

    public ManageAuthorForm() {

    }

    public ManageAuthorForm(Author author) {
        this.name = author.getName();
        this.pseudonym = author.getPseudonym();
        this.yearOfBirth = author.getYearOfBirth();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
