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
import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.Event;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.service.EventServiceImpl;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;
    private final EventServiceImpl eventService;


//  Show schedule event
    @GetMapping("/{id}")
    public String scheduleEvent(
            @PathVariable("id") Event event,
            Model model
    ) {
        model.addAttribute("user", eventRepository.findById(event.getId()));

        return "events/scheduleEvent";
    }

//  Create event
    @GetMapping("/new")
    public String create(@RequestParam(required = false) String scheduleId, EventCreateEditDto event, Model model) {
        Optional.ofNullable(scheduleId).ifPresent(event::setScheduleId);


        model.addAttribute("days", Day.values());
        model.addAttribute("event", event);
        return "events/new";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute @Validated EventCreateEditDto event,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("event", event);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/events/new";
        }
        eventService.create(event);
        return "redirect:/main";
    }

    //  Edit & update event
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("event", eventRepository.findById(id));
        return "events/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute @Validated EventCreateEditDto event,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("event", event);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/events/{id}/edit";
        }

        if (!eventService.update(id, event)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/main";
    }

    //  Delete event
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        eventRepository.deleteById(id);
        return "redirect:/main";
    }
}
