package com.epam.library.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.library.domain.LoginData;
import com.epam.library.domain.User;
import com.epam.library.domain.UserType;
import com.epam.library.form.AdminsEditUserForm;
import com.epam.library.form.EditUserForm;
import com.epam.library.form.RegistrationForm;
import com.epam.library.form.adapter.UserFormAdapter;
import com.epam.library.repository.LoginDataRepository;
import com.epam.library.repository.UserRepository;

@Service
public class UserService {
    private UserFormAdapter adapter;
    private UserRepository userRepository;
    private LoginDataRepository loginDataRepository;
    private AddressService addressService;

    @Autowired
    public UserService(UserRepository userRep, LoginDataRepository loginRep,
            UserFormAdapter adapter, AddressService addressService) {
        this.userRepository = userRep;
        this.loginDataRepository = loginRep;
        this.adapter = adapter;
        this.addressService = addressService;
    }

    public Page<User> getPagedUsers(Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "firstName");
        return userRepository.findAll(request);
    }
    
    public Page<User> searchPagedUsers(String query, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "firstName");
        return userRepository.findByLoginData_LoginIgnoreCaseContaining(query, request);
    }

    public Page<User> getPagedUsersOfConcreteType(UserType userType, Integer pageNumber,
            Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "firstName");
        return userRepository.findByLoginData_UserType(userType, request);
    }

    public User findById(Long userId) {
        return userRepository.getOne(userId);
    }

    @Transactional
    public User addUser(RegistrationForm regForm, UserType userType) {
        LoginData logindata = adapter.createLoginDataFromRegistrationForm(regForm, userType);

        User user = adapter.createUserFromRegistrationForm(regForm);
        user.setAddress(addressService.addAddress(adapter
                .createAddressFromRegistrationForm(regForm)));
        user.setLoginData(loginDataRepository.save(logindata));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLoginData_LoginIgnoreCase(login);
    }

    @Transactional
    public void deleteUser(String login) {
        User user = userRepository.findByLoginData_LoginIgnoreCase(login);
        userRepository.delete(user);
        loginDataRepository.delete(user.getLoginData());
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findOne(userId);
        userRepository.delete(user);
        loginDataRepository.delete(user.getLoginData());
    }

    @Transactional
    public User updateUser(String login, EditUserForm form) {
        User old = userRepository.findByLoginData_LoginIgnoreCase(login);
        User user = adapter.createUserFromEditUserForm(form);
        user.setId(old.getId());
        user.setLoginData(old.getLoginData());
        user.setAddress(addressService.addAddress(user.getAddress()));
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, AdminsEditUserForm form) {
        User old = userRepository.findOne(id);
        User user = adapter.createUserFromEditUserForm(form);
        user.setId(old.getId());
        user.setLoginData(old.getLoginData());
        user.setAddress(addressService.addAddress(user.getAddress()));
        return userRepository.save(user);
    }

    @Transactional
    public Collection<User> getUserOfType(UserType userType) {
        return userRepository.findByLoginData_UserType(userType);
    }

    public User findUserByLoginDataId(Long userId) {
        return userRepository.findByLoginData_Id(userId);
    }
}
