package ua.nicety.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nicety.database.dao.ScheduleDAO;
import ua.nicety.database.model.Schedule;
import ua.nicety.database.model.User;
import ua.nicety.service.MailService;

import javax.validation.Valid;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleDAO scheduleDAO;
    private final MailService mailService;

    @PostMapping(value = "/mail")
    public String sendPdfViaEmail(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {

        mailService.sendEmail(user.getUsername());
        return "redirect:/main";
    }


//  Show all user schedules
    @GetMapping()
    public String userSchedules(User user, Model model) {
        model.addAttribute("schedules", scheduleDAO.allUserSchedules(user.getId()));

        return "schedules/userSchedules";
    }

  //  Show user schedule
    @GetMapping("/{id}")
    public String userSchedule(
            @PathVariable("id") Schedule schedule,
            Model model) {
        model.addAttribute("schedules", scheduleDAO.show(schedule.getId()));

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

        scheduleDAO.save(schedule);

        return "redirect:/schedules";
    }

//  Edit & update schedule
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("schedule", scheduleDAO.show(id));

        return "schedules/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("schedule") @Valid Schedule schedule,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "schedules/edit";

        scheduleDAO.update(id, schedule);

        return "redirect:/schedules";
    }

//  Delete schedule
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        scheduleDAO.delete(id);
        return "redirect:/schedules";
    }
}

