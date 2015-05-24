package com.epam.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.library.domain.book.Genre;
import com.epam.library.form.ManageGenreForm;
import com.epam.library.form.adapter.ManageGenreFormAdapter;
import com.epam.library.repository.book.GenreRepository;

@Service
public class GenreService {
    private GenreRepository genreRepository;
    private ManageGenreFormAdapter adapter;

    @Autowired
    public GenreService(GenreRepository genreRep, ManageGenreFormAdapter manageGenreFormAdapter) {
        this.genreRepository = genreRep;
        this.adapter = manageGenreFormAdapter;
    }

    public Page<Genre> getPagedGenres(Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "genreName");
        return genreRepository.findAll(request);
    }

    @Transactional
    public Genre addGenre(ManageGenreForm form) {
        Genre genre = adapter.createGenreFromManageGenreForm(form);
        // if (genre.getParentGenre() != null
        // && genre.getParentGenre().getGenreName().trim().length() != 0) {
        // Genre parent =
        // genreRepository.findByGenreName(genre.getParentGenre().getGenreName());
        // if (parent == null) {
        // parent = genreRepository.save(genre.getParentGenre());
        // }
        // genre.setParentGenre(parent);
        // }
        return genreRepository.save(genre);
    }

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    public void deleteGenreById(Long id) {
        genreRepository.delete(id);
    }

    public Genre findGenreByID(Long genreId) {
        return genreRepository.findOne(genreId);
    }

    public Genre updateGenre(Long genreId, ManageGenreForm manageGenreForm) {
        Genre genre = adapter.createGenreFromManageGenreForm(manageGenreForm);
        // genre.setParentGenre(genre.getParentGenre());
        genre.setId(genreId);
        return genreRepository.save(genre);
    }
}
