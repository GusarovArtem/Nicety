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
import ua.nicety.database.repository.UserRepository;
import ua.nicety.http.dto.UserCreateEditDto;
import ua.nicety.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;


    //  Show all users
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/allUsers";
    }

    //  Show user
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "users/show";
    }


    //  User registration
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
        model.addAttribute("user", userRepository.findById(id));
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