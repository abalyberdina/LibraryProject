package com.epam.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(Exception.class)
    public String handlingAuthorNotFoundException(HttpServletRequest req, Exception exception) {
        exception.printStackTrace();
        return "error";
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", exception);
//        mav.setViewName("home");
//        return mav;
//        model.put("authornotexist", exception.getMessage() + ". Please add author.");
//        return "home";
    }

//    @ExceptionHandler(Da.class)
//    public ModelAndView handlingGenreNotFoundException(HttpServletRequest req, Exception exception) {
//        model.put("genrenotexist", exception.getMessage() + ". Please add genre.");
//        return "genres/all";
//    }
}
