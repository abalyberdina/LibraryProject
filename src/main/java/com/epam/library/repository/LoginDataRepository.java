package com.epam.library.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.LoginData;
import com.epam.library.domain.UserType;

public interface LoginDataRepository extends JpaRepository<LoginData, Long> {
    LoginData findByLogin(String login);

    LoginData findByLoginAndPassword(String login, String password);

    List<LoginData> findByUserType(UserType userType);

    LoginData findByLogin(String login, Sort sort);

    LoginData findByLoginAndPassword(String login, String password, Sort sort);

    List<LoginData> findByUserType(UserType userType, Sort sort);
}
