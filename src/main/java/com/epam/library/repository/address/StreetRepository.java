package com.epam.library.repository.address;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.address.Street;

public interface StreetRepository extends JpaRepository<Street, Long> {
    Street findByStreetNameIgnoreCase(String streetName);
    
    Street findByStreetNameIgnoreCase(String streetName, Sort sort);
}
