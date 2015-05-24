package com.epam.library.form.adapter;

import org.springframework.stereotype.Service;

import com.epam.library.domain.book.Author;
import com.epam.library.form.ManageAuthorForm;

@Service
public class ManageAuthorFormAdapter {

    public Author createAuthorFromManageAuthorForm(ManageAuthorForm manageAuthorForm) {
        Author author = new Author();
        author.setName(manageAuthorForm.getName().trim());
        if (manageAuthorForm.getPseudonym() != null
                && manageAuthorForm.getPseudonym().trim().length() > 0) {
            author.setPseudonym(manageAuthorForm.getPseudonym().trim());
        }
        if (manageAuthorForm.getYearOfBirth() != null
                && manageAuthorForm.getYearOfBirth().trim().length() > 0) {
            author.setYearOfBirth(manageAuthorForm.getYearOfBirth().trim());
        }
        return author;
    }

}
