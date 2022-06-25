package ua.nicety.http.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.Event;
import ua.nicety.http.dto.EventCreateEditDto;

@Component
@RequiredArgsConstructor
public class EventCreateEditMapper implements Mapper<EventCreateEditDto, Event> {

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
        event.setSchedule(object.getSchedule());
    }
}