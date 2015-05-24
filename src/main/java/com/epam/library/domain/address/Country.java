package com.epam.library.domain.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class Country {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRY_SEQ")
    @SequenceGenerator(name = "COUNTRY_SEQ", sequenceName = "COUNTRY_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "COUNTRY_NAME", nullable = false, length = 50, unique = true)
    private String countryName;

    public Country() {

    }

    public Country(String name) {
        this.countryName = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
