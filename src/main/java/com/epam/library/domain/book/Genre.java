package com.epam.library.domain.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GENRE")
public class Genre {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENRE_SEQ")
    @SequenceGenerator(name = "GENRE_SEQ", sequenceName = "GENRE_SEQ", allocationSize = 1)
    private Long id;
    
    @Column(name = "GENRE_NAME", nullable = false, length = 50, unique = true)
    private String genreName;
//    
//    @ManyToOne
//    @JoinColumn(name = "PARENT_GENRE")
//    private Genre parentGenre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

//    public Genre getParentGenre() {
//        return parentGenre;
//    }
//
//    public void setParentGenre(Genre parentGenre) {
//        this.parentGenre = parentGenre;
//    }
}
