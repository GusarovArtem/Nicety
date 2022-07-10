package ua.nicety.http.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.Event;
import ua.nicety.database.entity.Schedule;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.service.schedule.ScheduleService;

@Component
@RequiredArgsConstructor
public class EventCreateEditMapper implements Mapper<EventCreateEditDto, Event> {

    private final ScheduleService scheduleService;

    @Override
    public Event map(EventCreateEditDto object) {
        Event event = new Event();
        copy(object, event);

        return event;
    }
    public Event map(EventCreateEditDto fromObject, Event toObject) {
        copy(fromObject, toObject);
        return toObject;
    }


    private void copy(EventCreateEditDto object, Event event) {
        event.setName(object.getName());
        event.setDescription(object.getDescription());
        event.setSmiles(object.getSmiles());
        event.setColor(object.getColor());
        event.setDay(object.getDay());
        event.setTime(object.getTime());
        event.setSchedule(getSchedule(object.getScheduleId()));
    }

    private Schedule getSchedule(String id){
        return scheduleService.getById(id);
    }
}