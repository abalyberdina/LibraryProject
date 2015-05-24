package com.epam.library.repository.address;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.address.Address;
import com.epam.library.domain.address.City;
import com.epam.library.domain.address.Country;
import com.epam.library.domain.address.Street;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCountry(Country country);

    List<Address> findByCity(City city);

    List<Address> findByStreet(Street street);

    List<Address> findByCountryAndCity(Country country, City city);

    List<Address> findByCityAndStreet(City city, Street street);

    List<Address> findByCountryAndCityAndStreet(Country country, City city,
            Street street);

    List<Address> findByCountryAndCityAndStreetAndHouseIgnoreCase(
            Country country, City city, Street street, String house);

    List<Address> findByCountry(Country country, Sort sort);

    List<Address> findByCity(City city, Sort sort);

    List<Address> findByStreet(Street street, Sort sort);

    List<Address> findByCountryAndCity(Country country, City city, Sort sort);

    List<Address> findByCityAndStreet(City city, Street street, Sort sort);

    List<Address> findByCountryAndCityAndStreet(Country country, City city,
            Street street, Sort sort);

    List<Address> findByCountryAndCityAndStreetAndHouseIgnoreCase(
            Country country, City city, Street street, String house, Sort sort);
}
