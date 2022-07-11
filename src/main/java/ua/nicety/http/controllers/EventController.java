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
import ua.nicety.database.entity.BaseEvent;
import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.Schedule;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.service.event.EventService;
import ua.nicety.service.event.EventUtil;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/{event-type}-events")
public class EventController {

    private final EventUtil eventUtil;

    //  Create event
    @GetMapping("/new")
    public String create(
            @PathVariable(value="scheduleId") String scheduleId,
            @PathVariable(value="event-type") String eventType,
            @ModelAttribute("event") EventCreateEditDto event,
            Model model
    ) {
        Optional.ofNullable(scheduleId).ifPresent(event::setScheduleId);

        if (eventType.equals("common")) {
            model.addAttribute("days", Day.values());
        }

        model.addAttribute("event", event);
        return "events/" + eventType +  "/new";
    }

    @PostMapping("/new")
    public String add(
            @PathVariable(value="scheduleId") String scheduleId,
            @PathVariable(value="event-type") String eventType,
            @ModelAttribute @Validated EventCreateEditDto event,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("event", event);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedules/" + scheduleId  +"/" + eventType + "-events/new";
        }
        event.setScheduleId(scheduleId);

        EventService<? extends BaseEvent, ?> eventService = eventUtil.getEventService(eventType);

        eventService.create(event);
        return "redirect:/schedules/" + scheduleId + "/" + eventType + "-events";
    }

    //  Edit & update event
    @GetMapping("/{id}/edit")
    public String edit(
            Model model,
            @PathVariable("scheduleId") String scheduleId,
            @PathVariable("event-type") String eventType,
            @PathVariable("id") Long id
    ) {
        EventService<? extends BaseEvent, ?> eventService = eventUtil.getEventService(eventType);

        BaseEvent event = eventService.findById(id).orElseThrow();

        Schedule schedule = new Schedule();
        schedule.setId(scheduleId);
        event.setSchedule(schedule);

        if (eventType.equals("common")) {
            model.addAttribute("days", Day.values());
        }

        model.addAttribute("event", event);
        return "events/" + eventType +  "/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable(value="scheduleId") String scheduleId,
            @PathVariable(value="event-type") String eventType,
            @PathVariable(value="id") Long id,
            @Valid EventCreateEditDto event,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("event", event);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedules/" + scheduleId + "/" + eventType + "-events/"  + id + "/edit" ;
        }

        event.setScheduleId(scheduleId);
        EventService<? extends BaseEvent, ?> eventService = eventUtil.getEventService(eventType);

        eventService.update(id, event);

        return "redirect:/schedules/" + scheduleId + "/" + eventType + "-events";
    }

    //  Delete event
    @PostMapping("/{id}")
    public String delete(
            @PathVariable("scheduleId") String scheduleId,
            @PathVariable("event-type") String eventType,
            @PathVariable("id") Long id
    ) {
        EventService<? extends BaseEvent, ?> eventService = eventUtil.getEventService(eventType);
        if (!eventService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/schedules/" + scheduleId + "/" + eventType + "-events";
    }
}