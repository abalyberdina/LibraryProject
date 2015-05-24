package com.epam.library.repository.book;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.epam.library.domain.book.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameIgnoreCase(String name);

    List<Author> findByYearOfBirthIgnoreCase(String yearOfBirth);

    Author findByPseudonymIgnoreCase(String pseudonym);

    Author findByNameAndYearOfBirthAllIgnoreCase(String name, String yearOfBirth);

    List<Author> findByNameIgnoreCase(String name, Sort sort);

    List<Author> findByYearOfBirthIgnoreCase(String yearOfBirth, Sort sort);

    Author findByPseudonymIgnoreCase(String pseudonym, Sort sort);

    Author findByNameAndYearOfBirthAllIgnoreCase(String name, String yearOfBirth, Sort sort);
    
    @Modifying
    @Query("update Author a set a.name = ?1 where a.id = ?2")
    Author setFixedNameFor(String name, Integer id);
    
    @Modifying
    @Query("update Author a set a.pseudonym = ?1 where a.id = ?2")
    Author setFixedPseudonymFor(String pseudonym, Integer id);
   
    @Modifying
    @Query("update Author a set a.yearOfBirth = ?1 where a.id = ?2")
    Author setFixedYearOfBirthFor(String yearOfBirth, Integer id);
}
