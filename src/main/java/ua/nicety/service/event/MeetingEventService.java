package ua.nicety.service.event;

import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.Meeting;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.MeetingReadDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MeetingEventService implements EventService<Meeting, MeetingReadDto> {


    @Override
    public Meeting create(EventCreateEditDto eventCreateEditDto) {
        return null;
    }

    @Override
    public Optional<Meeting> update(Long id, EventCreateEditDto eventCreateEditDto) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Optional<Meeting> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<MeetingReadDto> findByScheduleId(String id) {
        return null;
    }

    @Override
    public Map<Day, List<MeetingReadDto>> findAllByName(String name, String scheduleId) {
        return null;
    }

    @Override
    public Map<Day, List<MeetingReadDto>> getMapEvents(String scheduleId) {
        return null;
    }
}
