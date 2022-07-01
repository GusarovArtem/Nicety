package ua.nicety.http.mapper.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.Schedule;
import ua.nicety.http.dto.read.ScheduleReadDto;
import ua.nicety.http.mapper.Mapper;

@Component
@RequiredArgsConstructor
public class ScheduleReadMapper implements Mapper<Schedule, ScheduleReadDto> {

    @Override
    public ScheduleReadDto map(Schedule object) {
        return ScheduleReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .author(object.getAuthor())
                .build();
    }
}