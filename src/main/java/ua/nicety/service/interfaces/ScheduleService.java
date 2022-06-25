package ua.nicety.service.interfaces;

import ua.nicety.http.dto.ScheduleCreateEditDto;

public interface ScheduleService {
    
    void create(ScheduleCreateEditDto scheduleCreateEditDto);
    boolean update(Long id, ScheduleCreateEditDto scheduleCreateEditDto);
    boolean delete(Long id);
}
