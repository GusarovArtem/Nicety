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
import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.Event;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.service.EventServiceImpl;
import ua.nicety.service.interfaces.EventService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/events")
public class EventController {

    private final EventService eventService;


    //  Show schedule event
    @GetMapping("/{id}")
    public String scheduleEvent(
            @PathVariable("id") Event event,
            Model model,
            @PathVariable String scheduleId) {
        model.addAttribute("user", eventService.findById(event.getId()));

        return "events/scheduleEvent";
    }

    //  Create event
    @GetMapping("/new")
    public String create(
            @PathVariable(value="scheduleId") String scheduleId,
            EventCreateEditDto event, Model model
    ) {
        Optional.ofNullable(scheduleId).ifPresent(event::setScheduleId);

        model.addAttribute("days", Day.values());
        model.addAttribute("event", event);
        return "events/new";
    }

    @PostMapping("/new")
    public String add(
            @ModelAttribute @Validated EventCreateEditDto event,
            @PathVariable(value="scheduleId") String scheduleId,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("event", event);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedules/" + scheduleId  +"/events/new";
        }
        event.setScheduleId(scheduleId);

        eventService.create(event);
        return "redirect:/schedules/" + scheduleId;
    }

    //  Edit & update event
    @GetMapping("/{id}/edit")
    public String edit(
            Model model,
            @PathVariable("scheduleId") String scheduleId,
            @PathVariable("id") Long id
    ) {

        Event event =  eventService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Schedule schedule = new Schedule();
        schedule.setId(scheduleId);
        event.setSchedule(schedule);

        model.addAttribute("days", Day.values());
        model.addAttribute("event", event);

        return "events/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable(value="scheduleId") String scheduleId,
            @PathVariable(value="id") Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid EventCreateEditDto event,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("event", event);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedules/"+ scheduleId + "/events/" + id;
        }

        event.setScheduleId(scheduleId);

        eventService.update(id, event)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return "redirect:/schedules/" + scheduleId;
    }

    //  Delete schedule
    @PostMapping("/{id}")
    public String delete(
            @PathVariable("scheduleId") String scheduleId,
            @PathVariable("id") Long id
    ) {

        if (!eventService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/schedules/" + scheduleId;
    }
}