package ua.nicety.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

//  What we can
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

//  Select common, goal and meeting schedule
    @GetMapping("/main")
    public String showMainPage() {
        return "main";
    }

}
