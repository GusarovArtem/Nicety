package ua.nicety.service.event;

import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.Goal;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.EventReadDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GoalEventService implements EventService<Goal, EventReadDto> {
    @Override
    public Goal create(EventCreateEditDto eventCreateEditDto) {
        return null;
    }

    @Override
    public Optional<Goal> update(Long id, EventCreateEditDto eventCreateEditDto) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Optional<Goal> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<EventReadDto> findByScheduleId(String id) {
        return null;
    }

    @Override
    public Map<Day, List<EventReadDto>> findAllByName(String name, String scheduleId) {
        return null;
    }

    @Override
    public Map<Day, List<EventReadDto>> getMapEvents(String scheduleId) {
        return null;
    }
}
