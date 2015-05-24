package com.epam.library.repository.address;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.address.City;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityNameIgnoreCase(String name);

    City findByCityNameIgnoreCase(String name, Sort sort);
}
