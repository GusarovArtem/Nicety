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
public class EventReadMapper implements Mapper<Event, EventReadDto> {

    @Override
    public EventReadDto map(Event object) {
        return EventReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .color(object.getColor())
                .description(object.getDescription())
                .smiles(object.getSmiles())
                .day(object.getDay())
                .time(object.getTime())
                .build();
    }
}