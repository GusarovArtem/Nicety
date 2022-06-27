package ua.nicety.service.interfaces;

import ua.nicety.database.entity.Schedule;
import ua.nicety.http.dto.ScheduleCreateEditDto;

import java.util.Optional;

public interface ScheduleService {

    Schedule getById(String id);
    
    Optional<Schedule> create(ScheduleCreateEditDto scheduleCreateEditDto);
    boolean update(String id, ScheduleCreateEditDto scheduleCreateEditDto);
    boolean delete(String id);
}
