package com.epam.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.library.domain.address.Address;
import com.epam.library.domain.address.City;
import com.epam.library.domain.address.Country;
import com.epam.library.domain.address.Street;
import com.epam.library.repository.address.AddressRepository;
import com.epam.library.repository.address.CityRepository;
import com.epam.library.repository.address.CountryRepository;
import com.epam.library.repository.address.StreetRepository;

@Service
public class AddressService {

    private AddressRepository addressRepository;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;
    private StreetRepository streetRepository;

    @Autowired
    public AddressService(AddressRepository addressRep, CountryRepository countryRep,
            CityRepository cityRep, StreetRepository streetRep) {
        this.addressRepository = addressRep;
        this.countryRepository = countryRep;
        this.cityRepository = cityRep;
        this.streetRepository = streetRep;
    }

    @Transactional
    public Address addAddress(Address address) {
        Country country = countryRepository.findByCountryNameIgnoreCase(address.getCountry()
                .getCountryName());
        if (country == null) {
            country = countryRepository.save(address.getCountry());
        }
        address.setCountry(country);
        City city = cityRepository.findByCityNameIgnoreCase(address.getCity().getCityName());
        if (city == null) {
            city = cityRepository.save(address.getCity());
        }
        address.setCity(city);
        Street street = streetRepository.findByStreetNameIgnoreCase(address.getStreet()
                .getStreetName());
        if (street == null) {
            street = streetRepository.save(address.getStreet());
        }
        address.setStreet(street);
        return addressRepository.save(address);
    }
}
