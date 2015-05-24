package com.epam.library.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.epam.library.domain.LoginData;
import com.epam.library.domain.User;
import com.epam.library.support.FieldMatch;

@FieldMatch.List({ @FieldMatch(first = "password", second = "passwordConfirmation") })
public class RegistrationForm {

    @NotBlank
    @Length(max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String username;

    @NotBlank
    @Length(min = 4, max = 50)
    private String password;

    @NotBlank
    @Length(min = 4, max = 50)
    private String passwordConfirmation;

    @NotBlank
    @Length(max = 50)
    private String firstName;

    @NotBlank
    @Length(max = 50)
    private String lastName;

    @NotBlank
    @Length(max = 50)
    private String country;

    @NotBlank
    @Length(max = 50)
    private String city;

    @NotBlank
    @Length(max = 50)
    private String street;

    @NotBlank
    @Length(max = 50)
    private String house;

    @Length(max = 50)
    private String appartment;

    @Length(max = 20)
    @Pattern(regexp = "(^\\+(?:[0-9] ?){6,14}[0-9]$)?")
    private String contactPhone;

    public RegistrationForm() {

    }

    public RegistrationForm(LoginData loginData, User user) {
        this.username = loginData.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.country = user.getAddress().getCountry().getCountryName();
        this.city = user.getAddress().getCity().getCityName();
        this.street = user.getAddress().getStreet().getStreetName();
        this.house = user.getAddress().getHouse();
        this.appartment = user.getAddress().getApartment();
        this.contactPhone = user.getContactPhone();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }
}
