package com.epam.library.domain.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
public class City {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITY_SEQ")
    @SequenceGenerator(name = "CITY_SEQ", sequenceName = "CITY_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CITY_NAME", nullable = false, length = 50, unique = true)
    private String cityName;

    public City() {
        
    }
    
    public City(String name) {
        cityName = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
