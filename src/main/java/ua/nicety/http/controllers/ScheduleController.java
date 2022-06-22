package ua.nicety.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.database.repository.ScheduleRepository;
import ua.nicety.service.MailService;

import javax.validation.Valid;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;
    private final MailService mailService;

    @PostMapping(value = "/mail")
    public String sendPdfViaEmail(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        mailService.sendEmail(user.getUsername());
        return "redirect:/main";
    }


//  Show all user schedules
    @GetMapping()
    public String userSchedules(User user, Model model) {
        model.addAttribute("schedules", scheduleRepository.findAllByAuthor(user));

        return "schedules/userSchedules";
    }

  //  Show user schedule
    @GetMapping("/{id}")
    public String userSchedule(
            @PathVariable("id") Schedule schedule,
            Model model) {
        model.addAttribute("schedules", scheduleRepository.findById(schedule.getId()));

        return "schedules/userSchedule";
    }

//  Create new Schedule
    @GetMapping("/new")
    public String newSchedule(Model model, Schedule schedule) {
        model.addAttribute("schedule", schedule);

        return "schedules/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("schedule") @Valid Schedule schedule,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "schedules/new";

        scheduleRepository.save(schedule);

        return "redirect:/schedules";
    }

//  Edit & update schedule
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("schedule", scheduleRepository.findById(id));

        return "schedules/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("schedule") @Valid Schedule schedule,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "schedules/edit";

//        scheduleRepository.update(id, schedule);

        return "redirect:/schedules";
    }

//  Delete schedule
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        scheduleRepository.deleteById(id);
        return "redirect:/schedules";
    }
}

