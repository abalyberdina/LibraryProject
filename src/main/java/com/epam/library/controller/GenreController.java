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

import com.epam.library.domain.book.Genre;
import com.epam.library.form.ManageGenreForm;
import com.epam.library.service.GenreService;
import com.epam.library.view.GenreView;

@Controller
public class GenreController {

    private GenreService service;

    @Autowired
    public GenreController(GenreService service) {
        this.service = service;
    }

    @RequestMapping(value = "/genres/add", method = { GET, HEAD })
    public String showAddGenre(ModelMap model) {
        ManageGenreForm form = new ManageGenreForm();
        model.put("manageGenreForm", form);
        return "books/genres/managegenre";
    }

    @RequestMapping(value = "/genres/add", method = POST)
    public String addGenre(
            HttpServletRequest request, 
            @Valid @ModelAttribute("manageGenreForm") ManageGenreForm manageGenreForm,
            BindingResult result) {
        if (!result.hasErrors()) {
            try {
                service.addGenre(manageGenreForm);
                request.getSession().setAttribute("successAction", "success.genre.add");
                return "redirect:/genres/all";
            } catch (JpaSystemException ignored) {
                result.rejectValue("name", "error.genre.alreadyexists");
            }
        }
        return "books/genres/managegenre";

    }

    @RequestMapping(value = "/genres/all", method = { GET, HEAD })
    public String showAllGenres(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        if (pagesize > 50) {
            pagesize = 50;
        }
        if (pagesize <= 0) {
            pagesize = 20;
        }
        Page<Genre> page = service.getPagedGenres(pageNumber, pagesize);
        Collection<GenreView> views = GenreView.of(page);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("pageTotal", page.getTotalPages());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("size", pagesize);

        model.put("genres", views);
        return "books/genres/genrelist";
    }

    @RequestMapping(value = "genres/remove/{genreId}", method = { GET, HEAD })
    public String removeGenre(HttpServletRequest request, @PathVariable("genreId") Long genreId) {
        try{
        service.deleteGenreById(genreId);
        request.getSession().setAttribute("successAction", "success.genre.delete");

        }catch(JpaSystemException ignored) {
            request.getSession().setAttribute("dangerError", "error.genre.remove");
        }
        return "redirect:/genres/all";
    }

    @RequestMapping(value = "/genres/update/{genreId}", method = { GET, HEAD })
    public String updateAuthor(@PathVariable("genreId") Long genreId, ModelMap model) {
        ManageGenreForm form = new ManageGenreForm(service.findGenreByID(genreId));
        model.put("manageGenreForm", form);
        return "books/genres/managegenre";
    }

    @RequestMapping(value = "/genres/update/{genreId}", method = POST)
    public String doUpdateAuthor(
            HttpServletRequest request, 
            @PathVariable("genreId") Long genreId,
            @Valid @ModelAttribute("manageGenreForm") ManageGenreForm manageGenreForm,
            BindingResult result) {
        if (!result.hasErrors()) {
            try {
                service.updateGenre(genreId, manageGenreForm);
                request.getSession().setAttribute("successAction", "success.genre.update");

                return "redirect:/genres/all";
            } catch (JpaSystemException ignored) {
                result.reject("name", "This genre already exists");
            }
        }
        return "books/genres/managegenre";
    }
    // @ExceptionHandler(Exception.class)
    // public ModelAndView handlingGenreNotFoundException(Exception exception) {
    // ModelAndView mav = new ModelAndView();
    // mav.addObject("exception", "This genre already exists");
    // mav.setViewName("books/genres/managegenre");
    // return mav;
    // }
}
