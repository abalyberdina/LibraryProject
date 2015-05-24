package com.epam.library.domain.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "STREET")
public class Street {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STREET_SEQ")
    @SequenceGenerator(name = "STREET_SEQ", sequenceName = "STREET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "STREET_NAME", nullable = false, length = 50, unique = true)
    private String streetName;

    public Street() {

    }

    public Street(String name) {
        streetName = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

}
