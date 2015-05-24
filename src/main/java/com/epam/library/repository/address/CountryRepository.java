package com.epam.library.repository.address;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.address.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
    Country findByCountryNameIgnoreCase(String countryName);
    
    Country findByCountryNameIgnoreCase(String countryName, Sort sort);
}
