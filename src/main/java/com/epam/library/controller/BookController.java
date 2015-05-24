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

import com.epam.library.domain.book.Book;
import com.epam.library.form.ManageBookForm;
import com.epam.library.service.BookService;
import com.epam.library.support.exception.AuthorNotFoundException;
import com.epam.library.support.exception.GenreNotFoundException;
import com.epam.library.view.BookView;

@Controller
public class BookController {
    private BookService service;

    @Autowired
    public BookController(BookService bookService) {
        this.service = bookService;
    }

    @RequestMapping(value = "/books/all", method = { GET, HEAD })
    public String showAll(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize,
            @RequestParam(value = "query", defaultValue = "") String name) {
        if (pagesize > 50) {
            pagesize = 50;
        }
        if (pagesize <= 0) {
            pagesize = 20;
        }
        Page<Book> page = service.searchPagedBooks(name.trim(), pageNumber, pagesize);
        Collection<BookView> views = BookView.of(page);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("pageTotal", page.getTotalPages());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("size", pagesize);
        model.addAttribute("query", name);
        model.put("books", views);
        return "books/booklist";
    }

    @RequestMapping(value = "books/add", method = { GET, HEAD })
    public String showAddBook(ModelMap model) {
        ManageBookForm manageBookForm = new ManageBookForm();
        model.put("addBookForm", manageBookForm);
        return "books/managebook";
    }

    @RequestMapping(value = "books/add", method = POST)
    public String doAddBook(HttpServletRequest request,
            @Valid @ModelAttribute("addBookForm") ManageBookForm manageBookForm,
            BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                service.addBook(manageBookForm);
                request.getSession().setAttribute("successAction", "success.book.add");
                return "redirect:/books/all";
            } catch (AuthorNotFoundException exception) {
                model.put("authornotexist", exception.getMessage() + ". Please add author.");
                return "books/managebook";
            } catch (GenreNotFoundException exception) {
                model.put("genrenotexist", exception.getMessage() + ". Please add genre.");
                return "books/managebook";
            } catch (JpaSystemException ignored) {
                result.rejectValue("bookName", "error.book.alreadyexists");
            }
        }
        return "books/managebook";
    }

    @RequestMapping(value = "/books/remove/{bookId}", method = { GET, HEAD })
    public String removeBook(HttpServletRequest request, @PathVariable("bookId") Long bookId) {
        try {
            service.deleteBook(bookId);
            request.getSession().setAttribute("successAction", "success.book.delete");
        } catch (JpaSystemException ignored) {
            request.getSession().setAttribute("dangerError", "error.book.remove");
        }
        return "redirect:/books/all";
    }

    @RequestMapping(value = "/books/update/{bookId}", method = { GET, HEAD })
    public String updateBook(@PathVariable("bookId") Long bookId, ModelMap model) {
        ManageBookForm form = new ManageBookForm(service.findBookByIDEager(bookId));
        model.put("addBookForm", form);

        return "books/managebook";
    }

    @RequestMapping(value = "/books/update/{bookId}", method = POST)
    public String doUpdateBook(HttpServletRequest request, @PathVariable("bookId") Long bookId,
            @Valid @ModelAttribute("addBookForm") ManageBookForm manageBookForm,
            BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                service.updateBook(bookId, manageBookForm);
                request.getSession().setAttribute("successAction", "success.book.update");
                return "redirect:/books/all";
            } catch (AuthorNotFoundException exception) {
                model.put("authornotexist", exception.getMessage() + ". Please add author.");
                return "books/managebook";
            } catch (GenreNotFoundException exception) {
                model.put("genrenotexist", exception.getMessage() + ". Please add genre.");
                return "books/managebook";
            } catch (JpaSystemException ignored) {
                result.rejectValue("bookName", "error.book.alreadyexists");
            }
        }
        return "books/managebook";
    }

}
