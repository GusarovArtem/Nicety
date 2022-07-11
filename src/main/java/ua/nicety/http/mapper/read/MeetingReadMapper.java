package ua.nicety.http.mapper.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.Meeting;
import ua.nicety.http.dto.read.MeetingReadDto;
import ua.nicety.http.mapper.Mapper;

@Component
@RequiredArgsConstructor
public class MeetingReadMapper implements Mapper<Meeting, MeetingReadDto> {

    @Override
    public MeetingReadDto map(Meeting object) {
        return MeetingReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .color(object.getColor())
                .description(object.getDescription())
                .smiles(object.getSmiles())
                .dateTime(object.getDateTime())
                .notify(object.isNotify())
                .build();
    }
}