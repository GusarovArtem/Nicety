package ua.nicety.http.mapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.*;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.service.schedule.ScheduleService;

@Component
@RequiredArgsConstructor
public class EventCreateEditMapper<E extends BaseEvent> implements Mapper<EventCreateEditDto, E> {

    private final ScheduleService scheduleService;
    private final Class<E> eventType;

    @Override
    @SneakyThrows
    public E map(EventCreateEditDto object) {
        E event = eventType.newInstance();
        copy(object, event);

        return event;
    }
    public E map(EventCreateEditDto fromObject, E toObject) {
        copy(fromObject, toObject);
        return toObject;
    }


    @SneakyThrows
    private void copy(EventCreateEditDto object, BaseEvent event) {
        if (eventType.getName().equals("Event")) {
            event =  new Event();
            ((Event) event).setDay(object.getDay());
            ((Event) event).setTime(object.getTime());
        } else if (eventType.getName().equals("Goal")) {
            event = new Goal();
            ((Goal) event).setDateTime(object.getDateTime());
        } else {
            event = new Meeting();
            ((Meeting) event).setNotify(object.isNotify());
            ((Meeting) event).setDateTime(object.getDateTime());
        }

        event.setName(object.getName());
        event.setDescription(object.getDescription());
        event.setSmiles(object.getSmiles());
        event.setColor(object.getColor());
        event.setSchedule(getSchedule(object.getScheduleId()));
    }

    private Schedule getSchedule(String id){
        return scheduleService.getById(id);
    }
}