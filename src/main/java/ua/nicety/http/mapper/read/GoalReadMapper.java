package ua.nicety.http.mapper.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.Event;
import ua.nicety.database.entity.Goal;
import ua.nicety.http.dto.read.EventReadDto;
import ua.nicety.http.dto.read.GoalReadDto;
import ua.nicety.http.mapper.Mapper;

@Component
@RequiredArgsConstructor
public class GoalReadMapper implements Mapper<Goal, GoalReadDto> {

    @Override
    public GoalReadDto map(Goal object) {
        return GoalReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .color(object.getColor())
                .description(object.getDescription())
                .smiles(object.getSmiles())
                .dateTime(object.getDateTime())
                .build();
    }
}