package ua.nicety.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.database.repository.ScheduleRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.service.MailService;
import ua.nicety.service.ScheduleServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleServiceImpl scheduleService;
    private final MailService mailService;

    @PostMapping(value = "/mail")
    public String sendPdfViaEmail(@AuthenticationPrincipal UserDetails user) {
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
    public String create(@ModelAttribute @Validated ScheduleCreateEditDto schedule,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schedule", schedule);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedule/new";
        }
        scheduleService.create(schedule);

        return "redirect:/schedules";
    }

//  Edit & update schedule
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("schedule", scheduleRepository.findById(id));

        return "schedules/edit";
    }

    @PatchMapping("/{id}/edit")
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

