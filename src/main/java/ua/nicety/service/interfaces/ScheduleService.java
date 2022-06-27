package ua.nicety.service.interfaces;

import ua.nicety.http.dto.ScheduleCreateEditDto;

public interface ScheduleService {
    
    void create(ScheduleCreateEditDto scheduleCreateEditDto);
    boolean update(String id, ScheduleCreateEditDto scheduleCreateEditDto);
    boolean delete(String id);
}
