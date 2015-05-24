package com.epam.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.library.domain.book.Author;
import com.epam.library.domain.book.Book;
import com.epam.library.domain.book.Genre;
import com.epam.library.form.ManageBookForm;
import com.epam.library.form.adapter.ManageBookFormAdapter;
import com.epam.library.repository.book.AuthorRepository;
import com.epam.library.repository.book.BookRepository;
import com.epam.library.repository.book.GenreRepository;
import com.epam.library.support.exception.AuthorNotFoundException;
import com.epam.library.support.exception.GenreNotFoundException;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    private ManageBookFormAdapter adapter;

    @Autowired
    public BookService(BookRepository bookRep, AuthorRepository authRep, GenreRepository genreRep,
            ManageBookFormAdapter manageBookFormAdapter) {
        this.bookRepository = bookRep;
        this.authorRepository = authRep;
        this.genreRepository = genreRep;
        this.adapter = manageBookFormAdapter;
    }

    @Transactional
    public Page<Book> getPagedBooks(Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "bookName");
        Page<Book> books = bookRepository.findAll(request);
        for (Book book : books) {
            book.getAuthors().size();
            book.getGenres().size();
        }
        return books;
    }

    public List<Book> getAllLazy() {
        return bookRepository.findAll();
    }

    @Transactional
    public Page<Book> searchPagedBooks(String bookname, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "bookName");
        Page<Book> books = bookRepository.findByBookNameIgnoreCaseContaining(bookname, request);
        for (Book book : books) {
            book.getAuthors().size();
            book.getGenres().size();
        }
        return books;
    }

    @Transactional
    public List<Book> getAllEager() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            book.getAuthors().size();
            book.getGenres().size();
        }

        return books;
    }

    public Book addBook(ManageBookForm form) throws AuthorNotFoundException, GenreNotFoundException {
        Book book = adapter.createBookFromManagingBookForm(form);
        List<Author> authors = book.getAuthors();
        List<Author> tempAuthor = null;
        for (int i = 0; i < authors.size(); i++) {
            tempAuthor = authorRepository.findByNameIgnoreCase(authors.get(i).getName());
            if (tempAuthor == null || tempAuthor.size() == 0) {
                throw new AuthorNotFoundException("Author " + authors.get(i).getName()
                        + " not exist");
            } else {
                authors.set(i, tempAuthor.get(0));
            }
        }
        List<Genre> genres = book.getGenres();
        Genre tempGenre = null;
        for (int i = 0; i < genres.size(); i++) {
            tempGenre = genreRepository.findByGenreName(genres.get(i).getGenreName());
            if (tempGenre == null) {
                throw new GenreNotFoundException("Genre " + genres.get(i).getGenreName()
                        + " not exist");
            } else {
                genres.set(i, tempGenre);
            }
        }
        book.setAuthors(authors);
        book.setGenres(genres);
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.delete(bookId);
    }

    public Book findBookByIDLazy(Long bookId) {
        return bookRepository.findOne(bookId);
    }

    @Transactional
    public Book findBookByIDEager(Long bookId) {
        Book book = bookRepository.findOne(bookId);
        book.getGenres().size();
        book.getAuthors().size();
        return book;
    }

    public Book updateBook(Long bookId, ManageBookForm manageBookForm)
            throws AuthorNotFoundException, GenreNotFoundException {
        Book book = adapter.createBookFromManagingBookForm(manageBookForm);
        book.setId(bookId);
        List<Author> authors = book.getAuthors();
        List<Author> tempAuthor = null;
        for (int i = 0; i < authors.size(); i++) {
            tempAuthor = authorRepository.findByNameIgnoreCase(authors.get(i).getName());
            if (tempAuthor == null || tempAuthor.size() == 0) {
                throw new AuthorNotFoundException("Author " + authors.get(i).getName()
                        + " not exist");
            } else {
                authors.set(i, tempAuthor.get(0));
            }
        }
        List<Genre> genres = book.getGenres();
        Genre tempGenre = null;
        for (int i = 0; i < genres.size(); i++) {
            tempGenre = genreRepository.findByGenreName(genres.get(i).getGenreName());
            if (tempGenre == null) {
                throw new GenreNotFoundException("Genre " + genres.get(i).getGenreName()
                        + " not exist");
            } else {
                genres.set(i, tempGenre);
            }
        }
        book.setAuthors(authors);
        book.setGenres(genres);
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}
