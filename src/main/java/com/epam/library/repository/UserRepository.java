package com.epam.library.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.LoginData;
import com.epam.library.domain.User;
import com.epam.library.domain.UserType;
import com.epam.library.domain.address.Address;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLoginData_UserType(UserType type);
    
    User findByLoginData_Id(Long id);

    User findByLoginData_LoginIgnoreCase(String login);
    
    List<User> findByFirstNameIgnoreCase(String firstName);

    List<User> findByLastNameIgnoreCase(String lastName);

    List<User> findByFirstNameAndFirstNameAllIgnoreCase(String firstName, String lastName);

    List<User> findByAddress(Address address);

    List<User> findByFirstNameIgnoreCase(String firstName, Sort sort);

    List<User> findByLastNameIgnoreCase(String lastName, Sort sort);

    List<User> findByFirstNameAndFirstNameAllIgnoreCase(String firstName, String lastName, Sort sort);

    List<User> findByAddress(Address address, Sort sort);

    User findByLoginData(LoginData loginData);

    Page<User> findByLoginData_UserType(UserType type, Pageable pageable);
    
    Page<User> findByLoginData_LoginIgnoreCaseContaining(String login, Pageable pageable);
}
