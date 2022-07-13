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
import ua.nicety.database.entity.User;
import ua.nicety.database.entity.event.BaseEvent;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.http.dto.read.EventReadDto;
import ua.nicety.http.dto.read.ScheduleReadDto;
import ua.nicety.service.event.CommonEventService;
import ua.nicety.service.event.EventService;
import ua.nicety.service.event.EventUtil;
import ua.nicety.service.mail.MailService;
import ua.nicety.service.mail.PdfGeneratorService;
import ua.nicety.service.schedule.ScheduleService;
import ua.nicety.service.user.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final EventUtil eventUtil;

    private final UserService userService;
    private final CommonEventService commonEventService;
    private final ScheduleService scheduleService;
    private final MailService mailService;
    private final PdfGeneratorService pdfGeneratorService;

    @GetMapping(value = "/{id}/{event-type}-events/mail")
    public String sendPdfViaEmail(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable("id") String scheduleId,
            @PathVariable("event-type") String eventType
    ) {

        ScheduleReadDto schedule = scheduleService.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Map<Day, List<EventReadDto>> mapEvents = commonEventService.getMapEvents(scheduleId);
        pdfGeneratorService.generatePdf(mapEvents, schedule.getName());
        //mailService.sendEmail(user.getUsername());
        return "redirect:/schedules/" + scheduleId + "/" + eventType + "-events";
    }


//  Show all common-schedules
    @GetMapping("/common-events")
    public String showAllCommonSchedules(
            @AuthenticationPrincipal UserDetails userDetails,
            Model model
    ) {

        Map<String, Map<Day, List<EventReadDto>>> mapSchedules = new HashMap<>();
        List<ScheduleReadDto> schedules = scheduleService.findAllByAuthor(userService.getByEmail(userDetails.getUsername()));

        schedules.forEach(schedule -> {
            Map<Day, List<EventReadDto>> mapEvents = commonEventService.getMapEvents(schedule.getId());
            mapSchedules.put(schedule.getId(), mapEvents);
        });

        model.addAttribute("mapSchedules", mapSchedules);
        model.addAttribute("schedules", schedules);

        return "schedules/common/userSchedules";
    }

  //  Show schedule
    @GetMapping("/{id}/{event-type}-events")
    public String showSchedule(
            @PathVariable("id") String scheduleId,
            @PathVariable("event-type") String eventType,
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {

        ScheduleReadDto schedule = scheduleService.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        EventService<? extends BaseEvent, ?> eventService = eventUtil.getEventService(eventType);
        Map<?, ? extends List<?>> mapEvents;

        if (filter != null && !filter.isEmpty()) {
            mapEvents = eventService.findAllByName(filter, schedule.getId());
        } else {
            mapEvents = eventService.getMapEvents(schedule.getId());
        }

        model.addAttribute("mapEvents", mapEvents);
        model.addAttribute("schedule", schedule);

        return "schedules/"+ eventType +"/userSchedule";
    }

//  Create new Schedule
    @GetMapping("/new/{event-type}-events")
    public String newSchedule(
            @ModelAttribute("schedule") ScheduleCreateEditDto schedule,
            @PathVariable("event-type") String eventType
    ) {

        return "schedules/" + eventType + "/new";
    }

    @PostMapping("/new/{event-type}-events")
    public String create(
            @AuthenticationPrincipal UserDetails userDetails,
            @Validated ScheduleCreateEditDto schedule,
            @PathVariable("event-type") String eventType,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schedule", schedule);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedules/new/" + eventType + "-events";
        }
        final User user = userService.getByEmail(userDetails.getUsername());
        schedule.setAuthor(user);
        scheduleService.create(schedule);

        return "redirect:/main";
    }

    //  Edit & update schedule
    @GetMapping("/{id}/edit/{event-type}-events")
    public String edit(
            Model model,
            @PathVariable("id") String id,
            @PathVariable("event-type") String eventType
    ) {

        ScheduleReadDto schedule =  scheduleService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("schedule", schedule);

        return "schedules/" + eventType  + "/edit";
    }

    @PostMapping("/{id}/edit/{event-type}-events")
    public String update(
            @AuthenticationPrincipal UserDetails userDetails,
            @Validated ScheduleCreateEditDto schedule,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @PathVariable(value="id") String id,
            @PathVariable(value="event-type") String eventType
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schedule", schedule);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/schedules/" + id + "/edit/" + eventType + "-events";
        }

        final User user = userService.getByEmail(userDetails.getUsername());
        schedule.setAuthor(user);

        scheduleService.update(id, schedule)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return "redirect:/schedules/" + id + "/" + eventType + "-events";
    }

    //  Delete schedule
    @PostMapping("/{id}")
    public String delete(
            @PathVariable("id") String id
    ) {

        if (!scheduleService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/main";
    }
}

