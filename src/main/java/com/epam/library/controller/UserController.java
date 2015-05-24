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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.library.domain.User;
import com.epam.library.domain.UserType;
import com.epam.library.form.AdminsEditUserForm;
import com.epam.library.form.EditUserForm;
import com.epam.library.form.RegistrationForm;
import com.epam.library.service.UserService;
import com.epam.library.support.SecurityUserDetails;
import com.epam.library.view.GeneralUserView;
import com.epam.library.view.UserView;

@Controller
public class UserController {
    private UserService service;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService us, PasswordEncoder encoder) {
        this.service = us;
        this.passwordEncoder = encoder;
    }

    @RequestMapping(value = "/login", method = { GET, HEAD })
    public String showLogin() {
        return "auth/login";
    }
    
    @RequestMapping(value = "/registration", method = { GET, HEAD })
    public String showRegistration(ModelMap model) {
        RegistrationForm regForm = new RegistrationForm();
        model.put("registerForm", regForm);
        return "auth/registration";
    }

    @RequestMapping(value = "/registration", method = POST)
    public String doRegistration(HttpServletRequest request,
            @Valid @ModelAttribute("registerForm") RegistrationForm regForm, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                service.addUser(regForm, UserType.READER);
                request.getSession().setAttribute("successAction", "success.user.registration");
                return "redirect:/";
            } catch (JpaSystemException ignored) {
                result.rejectValue("username", "error.user.logintaken");
            }
        }
        return "auth/registration";
    }

    @RequestMapping(value = "/myprofile", method = { GET, HEAD })
    public String showProfile(ModelMap model, @AuthenticationPrincipal SecurityUserDetails sud) {
        User user = service.findUserByLogin(sud.getUsername());
        UserView view = new UserView(user);
        model.put("profile", view);
        return "auth/myprofile";
    }

    @RequestMapping(value = "/myprofile/edit", method = { GET, HEAD })
    public String showEditForm(ModelMap model, @AuthenticationPrincipal SecurityUserDetails sud) {
        EditUserForm editUserForm = new EditUserForm(service.findUserByLogin(sud.getUsername()));
        model.put("editUserForm", editUserForm);
        return "auth/edituserdata";
    }

    @RequestMapping(value = "/myprofile/edit", method = POST)
    public String showEditForm(HttpServletRequest request,
            @Valid @ModelAttribute("editUserForm") EditUserForm editUserForm, BindingResult result,
            @AuthenticationPrincipal SecurityUserDetails sud) {
        if (!result.hasErrors()) {
            if (!passwordEncoder.matches(editUserForm.getPassword(), sud.getPassword())) {
                result.rejectValue("password", "error.user.passwordisincorrect");
                return "auth/edituserdata";
            }
            service.updateUser(sud.getUsername(), editUserForm);
            request.getSession().setAttribute("successAction", "success.user.update");
            return "redirect:/myprofile";
        }
        return "auth/edituserdata";
    }

    @RequestMapping(value = "/myprofile/delete", method = { GET, HEAD })
    public String deleteProfile(HttpServletRequest request,
            @AuthenticationPrincipal SecurityUserDetails sud) {
        try {
            service.deleteUser(sud.getUsername());
            request.getSession().setAttribute("successAction", "success.user.delete");
            return "redirect:/logout";

        } catch (JpaSystemException ignored) {
            request.getSession().setAttribute("dangerError", "error.profile.remove");
            return "redirect:/myprofile";
        }
        
    }

    @RequestMapping(value = "/users/all", method = { GET, HEAD })
    public String showAllUsers(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize,
            @RequestParam(value = "query", defaultValue = "") String query) {
        pagesize = checkPageSize(pagesize);
        Page<User> page = service.searchPagedUsers(query.trim(), pageNumber, pagesize);
        Collection<GeneralUserView> views = GeneralUserView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("users", views);
        model.addAttribute("query", query);
        return "users/allusers";
    }

    @RequestMapping(value = "/users/readers", method = { GET, HEAD })
    public String showAllReaders(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        pagesize = checkPageSize(pagesize);
        Page<User> page = service
                .getPagedUsersOfConcreteType(UserType.READER, pageNumber, pagesize);
        Collection<GeneralUserView> views = GeneralUserView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("users", views);

        model.put("usertype", UserType.READER);
        return "users/allusersofconcretetype";
    }

    @RequestMapping(value = "/users/librarians", method = { GET, HEAD })
    public String showAllLibrarians(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        pagesize = checkPageSize(pagesize);
        Page<User> page = service.getPagedUsersOfConcreteType(UserType.LIBRARIAN, pageNumber,
                pagesize);
        Collection<GeneralUserView> views = GeneralUserView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("users", views);
        model.put("usertype", UserType.LIBRARIAN);
        return "users/allusersofconcretetype";
    }

    @RequestMapping(value = "/users/administrators", method = { GET, HEAD })
    public String showAllAdmins(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        pagesize = checkPageSize(pagesize);
        Page<User> page = service.getPagedUsersOfConcreteType(UserType.ADMINISTRATOR, pageNumber,
                pagesize);
        Collection<GeneralUserView> views = GeneralUserView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("users", views);
        model.put("usertype", UserType.ADMINISTRATOR);
        return "users/allusersofconcretetype";
    }

    @RequestMapping(value = "/users/add/{userType}", method = { GET, HEAD })
    public String addNewUser(ModelMap model, @PathVariable("userType") String userType) {
        RegistrationForm regForm = new RegistrationForm();
        model.put("registerForm", regForm);
        return "auth/registration";
    }

    @RequestMapping(value = "/users/add/{userType}", method = POST)
    public String doAddNewUser(HttpServletRequest request,
            @PathVariable("userType") String userType,
            @Valid @ModelAttribute("registerForm") RegistrationForm regForm, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                service.addUser(regForm, UserType.valueOf(userType.toUpperCase()));
                request.getSession().setAttribute("successAction", "success.user.add");
                return "redirect:/users/all";
            } catch (JpaSystemException ignored) {
                result.rejectValue("username", "error.user.logintaken");
            }
        }
        return "auth/registration";
    }

    @RequestMapping(value = "/users/edit/{userId}", method = { GET, HEAD })
    public String editUser(ModelMap model, @PathVariable("userId") Long userId) {
        EditUserForm regForm = new EditUserForm(service.findUserByLoginDataId(userId));
        model.put("editUserForm", regForm);
        return "users/adminedituserdata";
    }

    @RequestMapping(value = "/users/edit/{userId}", method = POST)
    public String doEditUser(HttpServletRequest request, @PathVariable("userId") Long userId,
            @Valid @ModelAttribute("editUserForm") AdminsEditUserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "users/adminedituserdata";
        }
        service.updateUser(userId, form);
        request.getSession().setAttribute("successAction", "success.user.update");

        return "redirect:/users/all";
    }

    @RequestMapping(value = "/users/remove/{userId}", method = { GET, HEAD })
    public String delteUser(HttpServletRequest request, @PathVariable("userId") Long userId,
            @AuthenticationPrincipal SecurityUserDetails sud) {
        try {
            long currId = service.findUserByLogin(sud.getUsername()).getId();
            service.deleteUser(userId);
            request.getSession().setAttribute("successAction", "success.user.delete");
            if (userId == currId) {
                return "redirect:/logout";
            }
        } catch (JpaSystemException ignored) {
            request.getSession().setAttribute("dangerError", "error.user.remove");
        }
        return "redirect:/users/all";
    }

    private Integer checkPageSize(Integer pagesize) {
        if (pagesize > 50) {
            pagesize = 50;
        }
        if (pagesize <= 0) {
            pagesize = 20;
        }
        return pagesize;
    }

    private void formPageBoundaries(ModelMap model, Integer pagesize, Page<User> page) {
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("pageTotal", page.getTotalPages());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("size", pagesize);
    }
}
