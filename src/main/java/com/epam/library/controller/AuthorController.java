package com.epam.library.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.library.domain.book.Author;
import com.epam.library.form.ManageAuthorForm;
import com.epam.library.service.AuthorService;
import com.epam.library.view.AuthorView;

@Controller
public class AuthorController {

    private AuthorService service;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.service = authorService;
    }

    @RequestMapping(value = "/authors/add", method = { GET, HEAD })
    public String showAddAuthor(ModelMap model) {
        ManageAuthorForm form = new ManageAuthorForm();
        model.put("manageAuthorForm", form);
        return "books/authors/manageauthor";
    }

    @RequestMapping(value = "/authors/add", method = POST)
    public String addAuthor(
            HttpServletRequest request,
            @Valid @ModelAttribute("manageAuthorForm") ManageAuthorForm manageAuthorForm,
            BindingResult result) {
        if (result.hasErrors()) {
            return "books/authors/manageauthor";
        }
        service.addAuthor(manageAuthorForm);
        request.getSession().setAttribute("successAction", "success.author.add");
        return "redirect:/authors/all";
    }

    @RequestMapping(value = "/authors/all", method = { GET, HEAD })
    public String showAllAuthors(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        if (pagesize > 50) {
            pagesize = 50;
        }
        if (pagesize <= 0) {
            pagesize = 20;
        }
        Page<Author> page = service.getPagedAuthors(pageNumber, pagesize);
        Collection<AuthorView> views = AuthorView.of(page);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("pageTotal", page.getTotalPages());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("size", pagesize);
        model.put("authors", views);
        return "books/authors/authorlist";
    }

    @RequestMapping(value = "/authors/remove/{authorId}", method = { GET, HEAD })
    public String removeAuthor(HttpServletRequest request, @PathVariable("authorId") Long authorId) {
        try {
            service.deleteAuthor(authorId);
            request.getSession().setAttribute("successAction", "success.author.delete");
        } catch (JpaSystemException ignored) {
            request.getSession().setAttribute("dangerError", "error.author.remove");
        }
        return "redirect:/authors/all";
    }

    @RequestMapping(value = "/authors/update/{authorId}", method = { GET, HEAD })
    public String updateAuthor(@PathVariable("authorId") Long authorId, ModelMap model) {
        ManageAuthorForm form = new ManageAuthorForm(service.findAuthorByID(authorId));
        model.put("manageAuthorForm", form);
        return "books/authors/manageauthor";
    }

    @RequestMapping(value = "/authors/update/{authorId}", method = POST)
    public String doUpdateAuthor(
            HttpServletRequest request,
            @PathVariable("authorId") Long authorId,
            @Valid @ModelAttribute("manageAuthorForm") ManageAuthorForm manageAuthorForm,
            BindingResult result) {
        if (result.hasErrors()) {
            return "books/authors/manageauthor";
        }
        service.updateAuthor(authorId, manageAuthorForm);
        request.getSession().setAttribute("successAction", "success.author.update");
        return "redirect:/authors/all";
    }
}
