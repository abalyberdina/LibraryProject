package com.epam.library.form.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.library.domain.LoginData;
import com.epam.library.domain.User;
import com.epam.library.domain.UserType;
import com.epam.library.domain.address.Address;
import com.epam.library.domain.address.City;
import com.epam.library.domain.address.Country;
import com.epam.library.domain.address.Street;
import com.epam.library.form.AdminsEditUserForm;
import com.epam.library.form.EditUserForm;
import com.epam.library.form.RegistrationForm;

@Service
public class UserFormAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUserFromRegistrationForm(RegistrationForm regForm) {
        User user = new User();
        user.setFirstName(regForm.getFirstName().trim());
        user.setLastName(regForm.getLastName().trim());
        user.setContactPhone(regForm.getContactPhone());
        return user;
    }

    public LoginData createLoginDataFromRegistrationForm(RegistrationForm regForm, UserType userType) {
        LoginData loginData = new LoginData();
        loginData.setLogin(regForm.getUsername());
        loginData.setPassword(passwordEncoder.encode(regForm.getPassword()));
        loginData.setUserType(userType);
        return loginData;
    }

    public Address createAddressFromRegistrationForm(RegistrationForm regForm) {
        Address address = new Address();
        address.setCountry(new Country(regForm.getCountry().trim()));
        address.setCity(new City(regForm.getCity().trim()));
        address.setStreet(new Street(regForm.getStreet().trim()));
        address.setHouse(regForm.getHouse().trim());
        address.setApartment(regForm.getAppartment().trim());
        return address;
    }

    public User createUserFromEditUserForm(EditUserForm editUserForm) {
        User user = new User();
        user.setFirstName(editUserForm.getFirstName().trim());
        user.setLastName(editUserForm.getLastName().trim());
        user.setContactPhone(editUserForm.getContactPhone());
        Address address = new Address();
        address.setCountry(new Country(editUserForm.getCountry().trim()));
        address.setCity(new City(editUserForm.getCity().trim()));
        address.setStreet(new Street(editUserForm.getStreet().trim()));
        address.setHouse(editUserForm.getHouse().trim());
        address.setApartment(editUserForm.getAppartment().trim());
        user.setAddress(address);
        return user;
    }
    
    public User createUserFromEditUserForm(AdminsEditUserForm editUserForm) {
        User user = new User();
        user.setFirstName(editUserForm.getFirstName().trim());
        user.setLastName(editUserForm.getLastName().trim());
        user.setContactPhone(editUserForm.getContactPhone());
        Address address = new Address();
        address.setCountry(new Country(editUserForm.getCountry().trim()));
        address.setCity(new City(editUserForm.getCity().trim()));
        address.setStreet(new Street(editUserForm.getStreet().trim()));
        address.setHouse(editUserForm.getHouse().trim());
        address.setApartment(editUserForm.getAppartment().trim());
        user.setAddress(address);
        return user;
    }
}
