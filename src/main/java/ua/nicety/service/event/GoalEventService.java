package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.Event;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.EventReadDto;
import ua.nicety.http.mapper.EventCreateEditMapper;
import ua.nicety.http.mapper.read.EventReadMapper;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service("goal")
@RequiredArgsConstructor
public class GoalEventService implements EventService {


    public Event create(EventCreateEditDto eventDto) {
        return null;
    }

    @Transactional
    public Optional<Event> update(Long id, EventCreateEditDto eventDto) {
        return null;
    }

    @Transactional
    public boolean delete(Long id) {
        return true;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return null;
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
        List<EventReadDto> events = findByScheduleId(scheduleId);
        return null;
    }

}
