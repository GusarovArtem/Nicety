package ua.nicety.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nicety.DAO.UserDAO;
import ua.nicety.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

//  Show all users
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.showAll());
        return "users/allUsers";
    }

//  Show user
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "users/show";
    }

//  Create new user
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";

        userDAO.save(user);
        return "redirect:/users";
    }

//  Edit & update user
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userDAO.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "users/edit";

        userDAO.update(id, user);
        return "redirect:/users";
    }

//  Delete user
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userDAO.delete(id);
        return "redirect:/users";
    }
}