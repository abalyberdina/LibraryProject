package com.epam.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.epam.library.domain.book.Author;
import com.epam.library.form.ManageAuthorForm;
import com.epam.library.form.adapter.ManageAuthorFormAdapter;
import com.epam.library.repository.book.AuthorRepository;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private ManageAuthorFormAdapter adapter;

    @Autowired
    public AuthorService(AuthorRepository authorRep, ManageAuthorFormAdapter manageAuthorFormAdapter) {
        this.authorRepository = authorRep;
        this.adapter = manageAuthorFormAdapter;
    }

    public Page<Author> getPagedAuthors(Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC, "name");
        return authorRepository.findAll(request);
    }

    public Author addAuthor(ManageAuthorForm form) {
        Author author = adapter.createAuthorFromManageAuthorForm(form);
        return authorRepository.save(author);
    }

    // public Author updateAuthor(Author author) {
    // return authorRepository.
    // }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public void deleteAuthor(Long id) {
        authorRepository.delete(id);
    }

    public Author updateAuthor(Long id, ManageAuthorForm form) {
        Author author = adapter.createAuthorFromManageAuthorForm(form);
        author.setId(id);
        return authorRepository.save(author);
    }

    public Author findAuthorByID(Long id) {
        return authorRepository.findOne(id);
    }
}
