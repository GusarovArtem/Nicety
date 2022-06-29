package ua.nicety.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.database.repository.ScheduleRepository;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.service.MailService;
import ua.nicety.service.ScheduleServiceImpl;
import ua.nicety.service.interfaces.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;
    private final ScheduleServiceImpl scheduleService;
    private final MailService mailService;

    @PostMapping(value = "/mail")
    public String sendPdfViaEmail(@AuthenticationPrincipal UserDetails user) {
        mailService.sendEmail(user.getUsername());
        return "redirect:/main";
    }


//  Show all user schedules
    @GetMapping
    public String userSchedules(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        final User user = userService.getByEmail(userDetails.getUsername());
        model.addAttribute("schedules", scheduleRepository.findAllByAuthor(user));

        return "schedules/userSchedules";
    }

  //  Show user schedule
    @GetMapping("/{id}")
    public String userSchedule(
            @PathVariable("id") Schedule schedule,
            Model model) {
        model.addAttribute("schedule", schedule);
        return "schedules/userSchedule";
    }

//  Create new Schedule
    @GetMapping("/new")
    public String newSchedule(@ModelAttribute("schedule") ScheduleCreateEditDto schedule) {

        return "schedules/new";
    }

    @PostMapping("/new")
    public String create(
            @AuthenticationPrincipal UserDetails userDetails,
            @Validated ScheduleCreateEditDto schedule,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schedule", schedule);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedules/new";
        }
        final User user = userService.getByEmail(userDetails.getUsername());
        schedule.setAuthor(user);
        scheduleService.create(schedule);

        return "redirect:/main";
    }

    //  Edit & update schedule
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") String id) {
        model.addAttribute("schedule", scheduleRepository.findById(id).get());

        return "schedules/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid ScheduleCreateEditDto schedule,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @PathVariable(value="id") String id
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schedule", schedule);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedules/"+ id + "/edit";
        }

        final User user = userService.getByEmail(userDetails.getUsername());
        schedule.setAuthor(user);

        if (!scheduleService.update(id, schedule)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/schedules/" + id;
    }

//  Delete schedule
    @PostMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        scheduleRepository.deleteById(id);
        return "redirect:/main";
    }
}

