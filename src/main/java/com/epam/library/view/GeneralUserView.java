package com.epam.library.view;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.Page;

import com.epam.library.domain.User;
import com.epam.library.domain.address.Address;

public class GeneralUserView {
    private Long id;
    private String name;
    private String email;
    private String contactPhone;
    private String address;
    private String role;

    public GeneralUserView(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getFirstName()).append(" ").append(user.getLastName());
        this.name = sb.toString();
        sb = new StringBuilder();
        Address address = user.getAddress();
        sb.append(address.getCountry().getCountryName()).append(", ")
                .append(address.getCity().getCityName()).append(", ")
                .append(address.getStreet().getStreetName()).append(" ").append(address.getHouse())
                .append(" - ")
                .append(address.getApartment() != null ? address.getApartment() : " ");
        this.address = sb.toString();
        this.contactPhone = user.getContactPhone() != null ? user.getContactPhone() : " ";
        this.role = user.getLoginData().getUserType().name();
        this.email = user.getLoginData().getLogin();
        this.id = user.getId();
    }

    public static Collection<GeneralUserView> of(Page<User> all) {
        Collection<GeneralUserView> views = new ArrayList<>(all.getSize());
        for (User user : all) {
            views.add(new GeneralUserView(user));
        }
        return views;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }
}
