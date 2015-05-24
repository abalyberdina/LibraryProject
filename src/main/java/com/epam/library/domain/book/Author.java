package com.epam.library.domain.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQ")
    @SequenceGenerator(name = "AUTHOR_SEQ", sequenceName = "AUTHOR_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "AUTHOR_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "PSEUDONYM", length = 50)
    private String pseudonym;

    @Column(name = "YEAR_OF_BIRTH", length = 15)
    private String yearOfBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
