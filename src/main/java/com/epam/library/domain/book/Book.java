package com.epam.library.domain.book;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
    @SequenceGenerator(name = "BOOK_SEQ", sequenceName = "BOOK_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "BOOK_NAME", unique = true, nullable = false)
    private String bookName;

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR", joinColumns = { @JoinColumn(name = "ID_BOOK", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_AUTHOR", nullable = false) })
    private List<Author> authors;

    // @ManyToOne
    // @JoinColumn(name = "ID_GENRE")
    @ManyToMany
    @JoinTable(name = "BOOK_GENRES", joinColumns = { @JoinColumn(name = "ID_BOOK", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_GENRE", nullable = false) })
    private List<Genre> genres;

    @Column(name = "NUM_AVAILABLE", nullable = false, columnDefinition="number(19,0) check (NUM_AVAILABLE between 0 and 4294967295)")
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
