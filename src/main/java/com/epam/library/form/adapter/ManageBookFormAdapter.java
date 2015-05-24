package com.epam.library.form.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.library.domain.book.Author;
import com.epam.library.domain.book.Book;
import com.epam.library.domain.book.Genre;
import com.epam.library.form.ManageBookForm;

@Service
public class ManageBookFormAdapter {

    public Book createBookFromManagingBookForm(ManageBookForm manageBookForm) {
        Book book = new Book();
        book.setBookName(manageBookForm.getBookName());
        book.setNumber(Integer.parseInt(manageBookForm.getNumOfBooks()));
        List<Genre> genres = new ArrayList<>();
        Genre temp = null;
        for (String str : manageBookForm.getGenres().split(",")) {
            temp = new Genre();
            temp.setGenreName(str.trim());
            genres.add(temp);
        }
        List<Author> authors = new ArrayList<>();
        Author author = null;
        for (String str : manageBookForm.getAuthors().split(",")) {
            author = new Author();
            author.setName(str.trim());
            authors.add(author);
        }
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

    // public List<Author> createAuthorFromManagingBookForm(ManageBookForm
    // manageBookForm) {
    // List<Author> authors = new ArrayList<>();
    // String[] authorData = manageBookForm.getAuthors().split(", ");
    // for(String author : authorData) {
    //
    // }
    // }
}
