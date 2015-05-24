package com.epam.library.form;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.epam.library.domain.book.Book;

public class ManageBookForm {

    @NotBlank
    @Length(max = 50)
    private String bookName;
    @NotBlank
    private String authors;
    @NotBlank
    private String genres;

    @NotBlank
    @Min(value = 0)
    private String numOfBooks;

    public ManageBookForm() {

    }

    public ManageBookForm(Book book) {
        this.bookName = book.getBookName();
        this.numOfBooks = book.getNumber().toString();
        StringBuilder sb = new StringBuilder(book.getGenres().get(0).getGenreName());
        for (int i = 1; i < book.getGenres().size(); i++) {
            sb.append(", ");
            sb.append(book.getGenres().get(i).getGenreName());
        }
        genres = sb.toString();
        sb = new StringBuilder(book.getAuthors().get(0).getName());
        for (int i = 1; i < book.getAuthors().size(); i++) {
            sb.append(", ");
            sb.append(book.getAuthors().get(i).getName());
            sb.append(" ");
            if (book.getAuthors().get(i).getPseudonym() != null) {
                sb.append("(");
                sb.append(book.getAuthors().get(i).getPseudonym());
                sb.append(")");
            }
            sb.append(" ");
            if (book.getAuthors().get(i).getYearOfBirth() != null) {
                sb.append("-");
                sb.append(book.getAuthors().get(i).getYearOfBirth());
            }
        }
        authors = sb.toString();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getNumOfBooks() {
        return numOfBooks;
    }

    public void setNumOfBooks(String numOfBooks) {
        this.numOfBooks = numOfBooks;
    }

}
