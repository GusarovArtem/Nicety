package ua.nicety.http.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.nicety.database.entity.Event;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.UserCreateEditDto;
import ua.nicety.database.entity.Role;
import ua.nicety.database.entity.User;

import java.util.Optional;

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
        event.setDay(object.getDay());
        event.setTime(object.getTime());
    }
}