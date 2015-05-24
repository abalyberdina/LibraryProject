package com.epam.library.repository.book;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.book.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenreName(String name);

//    List<Genre> findByParentGenre(Genre parent);

    Genre findByGenreName(String name, Sort sort);

//    List<Genre> findByParentGenre(Genre parent, Sort sort);
}
