package ua.nicety.http.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nicety.database.dao.EventDAO;
import ua.nicety.database.model.Event;

import javax.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventDAO eventDAO;

    @Autowired
    public EventController(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    //  Show all schedule events
    @GetMapping("/{id}")
    public String scheduleEvents(
            @PathVariable("id") Event event,
            Model model) {
        model.addAttribute("user", eventDAO.allScheduleEvents(event.getScheduleId()));

        return "events/scheduleEvents";
    }




    //  Create new Schedule
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

        eventDAO.save(event);
        return "redirect:/events";
    }

    //  Edit & update schedule
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("event", eventDAO.show(id));
        return "event/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("schedule") @Valid Event event,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "events/edit";

        eventDAO.update(id, event);
        return "redirect:/events";
    }

    //  Delete schedule
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        eventDAO.delete(id);
        return "redirect:/events";
    }
}
