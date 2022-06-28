package ua.nicety.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.service.interfaces.UserService;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByEmail(userDetails.getUsername());

        if(user.getSchedules().size() == 1 ) {
            Schedule schedule = user.getSchedules().stream().findAny().get();
            return "redirect:/schedules/" + schedule.getId();
//      Select the schedule
        } else if (user.getSchedules().size() > 1 ) {
            return "redirect:/schedules/";
        }

        return "redirect:/schedules/new";
    }
}
