package ua.nicety.http.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.nicety.database.dao.UserDAO;
import ua.nicety.http.dto.UserCreateEditDto;
import ua.nicety.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;
    private final UserService userService;


//  Show all users
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.showAll());
        return "users/allUsers";
    }

//  Show user
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDAO.findById(id));
        return "users/show";
    }

//  Create new user
    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") UserCreateEditDto user) {
        return "users/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute @Validated UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        userService.create(user);
        return "redirect:/login";
    }



//  Edit & update user
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userDAO.findById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid UserCreateEditDto user,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "users/edit";

        if (!userService.update(id, user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }

//  Delete user
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
}