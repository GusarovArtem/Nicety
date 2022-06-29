package ua.nicety.service.interfaces;

import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.http.dto.ScheduleCreateEditDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Schedule getById(String id);

    Optional<Schedule> findById(String id);
    List<Schedule> findAllByAuthor(User User);

    Optional<Schedule> create(ScheduleCreateEditDto scheduleCreateEditDto);
    Optional<Schedule> update(String id, ScheduleCreateEditDto scheduleCreateEditDto);
    boolean delete(String id);
}
