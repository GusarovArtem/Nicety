package ua.nicety.http.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.Event;
import ua.nicety.database.entity.Schedule;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.EventReadDto;
import ua.nicety.service.interfaces.ScheduleService;

@Component
@RequiredArgsConstructor
public class EventReadMapper implements Mapper<Event, EventReadDto> {

    @Override
    public EventReadDto map(Event object) {
        return EventReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .color(object.getColor())
                .day(object.getDay())
                .description(object.getDescription())
                .smiles(object.getSmiles())
                .time(object.getTime())
                .build();
    }
}