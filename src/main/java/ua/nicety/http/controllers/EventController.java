package ua.nicety.http.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.database.entity.Event;

import javax.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

//  Show schedule event
    @GetMapping("/{id}")
    public String scheduleEvent(
            @PathVariable("id") Event event,
            Model model
    ) {
        model.addAttribute("user", eventRepository.findById(event.getId()));

        return "events/scheduleEvent";
    }


    //  Create new Event
    @GetMapping("/new")
    public String newEvent(Model model, Event event) {
        model.addAttribute("event", event);

        return "events/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("event") @Valid Event event,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "events/new";

        eventRepository.save(event);
        return "redirect:/events";
    }

    //  Edit & update event
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("event", eventRepository.findById(id));
        return "event/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("schedule") @Valid Event event,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "events/edit";

//        eventRepository.update(id, event);
        return "redirect:/events";
    }

    //  Delete event
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        eventRepository.deleteById(id);
        return "redirect:/events";
    }
}
