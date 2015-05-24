package com.epam.library.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.library.domain.book.Author;
import com.epam.library.domain.book.Book;
import com.epam.library.domain.book.Genre;
import com.epam.library.repository.book.AuthorRepository;
import com.epam.library.repository.book.BookRepository;
import com.epam.library.repository.book.GenreRepository;

//@Controller
public class FillController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    
    @Autowired
    public FillController(AuthorRepository authorRepository, BookRepository bookRepository,
            GenreRepository genreRepository) {
        super();
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }
    
    @RequestMapping("/fill")
    public @ResponseBody String a() {
        int howMany = 20;
        int start = 100;
        
        // Genres
        List<Genre> genres = new LinkedList<>();
        for(int i = start; i < start + howMany; ++i) {
            Genre genre = new Genre();
            genre.setGenreName("Genre " + i);
            genres.add(genre);
        }
        genreRepository.save(genres);
        
        // Authors
        List<Author> authors = new LinkedList<>();
        for(int i = start; i < start + howMany; ++i) {
            Author author = new Author();
            author.setName("Author name " + i);
            author.setPseudonym("Pseudonym " + i);
            author.setYearOfBirth("" + (1900 + i));
            authors.add(author);
        }
        authorRepository.save(authors);
        
        Collection<Book> books = new LinkedList<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = start; i < start + howMany; ++i) {
            Book book = new Book();
            book.setBookName("Book Name " + i);
            book.setNumber(i);
            
            int authorCount = random.nextInt(3) + 1;
            int genreCount = random.nextInt(3) + 1;
            
            Collections.shuffle(authors, random);
            Collections.shuffle(genres, random);
            
            book.setAuthors(authors.subList(0, authorCount));
            book.setGenres(genres.subList(0, genreCount));

            books.add(book);
        }
        bookRepository.save(books);
        return "a";
    }
}
