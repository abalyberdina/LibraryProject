package com.epam.library.view;

import com.epam.library.domain.User;
import com.epam.library.domain.UserType;

public class UserView {

    private String firstName;
    private String lastName;
    private String login;
    private String country;
    private String city;
    private String street;
    private String house;
    private String appartment;
    private String contactPhone;
    private UserType userType;

    public UserView(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getLoginData().getLogin();
        this.country = user.getAddress().getCountry().getCountryName();
        this.city = user.getAddress().getCity().getCityName();
        this.street = user.getAddress().getStreet().getStreetName();
        this.house = user.getAddress().getHouse();
        this.appartment = user.getAddress().getApartment();
        this.contactPhone = user.getContactPhone();
        this.userType = user.getLoginData().getUserType();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getAppartment() {
        return appartment;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public UserType getUserType() {
        return userType;
    }
}
