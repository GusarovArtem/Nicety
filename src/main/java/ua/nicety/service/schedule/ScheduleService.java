package ua.nicety.service.schedule;

import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.http.dto.read.ScheduleReadDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Schedule getById(String id);

    Optional<ScheduleReadDto> findById(String id);
    List<ScheduleReadDto> findAllByAuthor(User User);

    Optional<ScheduleReadDto> create(ScheduleCreateEditDto scheduleCreateEditDto);
    Optional<ScheduleReadDto> update(String id, ScheduleCreateEditDto scheduleCreateEditDto);
    boolean delete(String id);
}
