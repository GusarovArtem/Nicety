package ua.nicety.http.mapper.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.BaseEvent;
import ua.nicety.database.entity.Event;
import ua.nicety.database.entity.Goal;
import ua.nicety.database.entity.Meeting;
import ua.nicety.http.dto.read.EventReadDto;
import ua.nicety.http.mapper.Mapper;

@Component
@RequiredArgsConstructor
public class EventReadMapper<E extends BaseEvent> implements Mapper<E, EventReadDto> {

    private final Class<E> eventType;
    
    @Override
    public EventReadDto map(BaseEvent object) {

        EventReadDto eventReadDto = EventReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .color(object.getColor())
                .description(object.getDescription())
                .smiles(object.getSmiles())
                .build();

        if (eventType.getName().equals("Event")) {
            object = new Event();
            EventReadDto.builder()
                    .day(((Event) object).getDay())
                    .time(((Event) object).getTime())
                    .build();
        } else if (eventType.getName().equals("Goal")) {
            object = new Goal();
            EventReadDto.builder()
                    .dateTime(((Goal) object).getDateTime())
                    .build();
        } else {
            object = new Meeting();
            EventReadDto.builder()
                    .dateTime(((Meeting) object).getDateTime())
                    .notify(((Meeting) object).isNotify())
                    .build();
        }
        return eventReadDto;
    }
}