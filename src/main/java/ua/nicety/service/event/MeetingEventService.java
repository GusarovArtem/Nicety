package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.Meeting;
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
public class MeetingEventService implements EventService<Meeting, EventReadDto> {

    private final EventRepository<Meeting> repository;
    private final EventCreateEditMapper<Meeting> createEditMapper;

    private final EventReadMapper<Meeting> readMapper;


    public Meeting create(EventCreateEditDto eventDto) {
        return Optional.of(eventDto)
                .map(createEditMapper::map)
                .map(repository::save).orElse(null);
    }

    @Transactional
    public Optional<Meeting> update(Long id, EventCreateEditDto eventDto) {
        return repository.findById(id)
                .map(entity -> createEditMapper.map(eventDto, entity))
                .map(repository::saveAndFlush);
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<Meeting> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public List<EventReadDto> findByScheduleId(String id) {
        return repository.findByScheduleId(id)
                .stream().map(readMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Day, List<EventReadDto>> findAllByName(String name, String scheduleId) {
        List<EventReadDto> events = findByScheduleId(scheduleId);
        return events.stream()
                .filter(eventReadDto -> eventReadDto.getName().equals(name))
                .sorted(Comparator.comparing((EventReadDto event) -> event.getDay().ordinal())
                        .thenComparing(EventReadDto::getTime))
                .collect(groupingBy(EventReadDto::getDay, LinkedHashMap::new, Collectors.toList()));
    }

    @Override
    public Map<Day, List<EventReadDto>> getMapEvents(String scheduleId) {
        List<EventReadDto> events = findByScheduleId(scheduleId);
        return events.stream()
                .sorted(Comparator.comparing((EventReadDto event) -> event.getDay().ordinal())
                        .thenComparing(EventReadDto::getTime))
                .collect(groupingBy(EventReadDto::getDay, LinkedHashMap::new, Collectors.toList()));
    }
}