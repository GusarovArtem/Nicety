package ua.nicety.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

//  What the site can
    @GetMapping("/")
    public String home() {
        return "home";
    }

//  Select common, goal and meeting schedule
    @GetMapping("/main")
    public String main() {
        return "main";
    }

}
