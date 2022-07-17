package ua.nicety.service.schedule;

import ua.nicety.database.entity.User;
import ua.nicety.http.dto.createEdit.ScheduleCreateEditDto;
import ua.nicety.http.dto.read.ScheduleReadDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Optional<ScheduleReadDto> getById(String id);
    List<ScheduleReadDto> getAllByAuthor(User User);

    Optional<ScheduleReadDto> create(ScheduleCreateEditDto scheduleCreateEditDto);
    Optional<ScheduleReadDto> update(String id, ScheduleCreateEditDto scheduleCreateEditDto);
    boolean delete(String id);
}
