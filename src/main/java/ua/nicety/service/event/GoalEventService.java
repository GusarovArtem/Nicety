package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Goal;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.EventReadDto;
import ua.nicety.http.mapper.EventCreateEditMapper;
import ua.nicety.http.mapper.read.EventReadMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service("goal")
@RequiredArgsConstructor
public class GoalEventService implements EventService<Goal, EventReadDto> {

    private final EventRepository<Goal> repository;
    private final EventCreateEditMapper<Goal> createEditMapper;

    private final EventReadMapper<Goal> readMapper;


    public Goal create(EventCreateEditDto eventDto) {
        return Optional.of(eventDto)
                .map(createEditMapper::map)
                .map(repository::save).orElse(null);
    }

    @Transactional
    public Optional<Goal> update(Long id, EventCreateEditDto eventDto) {
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
    public Optional<Goal> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public List<EventReadDto> findByScheduleId(String id) {
        return repository.findByScheduleId(id)
                .stream().map(readMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Map<LocalDateTime, List<EventReadDto>> findAllByName(String name, String scheduleId) {
        List<EventReadDto> events = findByScheduleId(scheduleId);
        return events.stream()
                .filter(eventReadDto -> eventReadDto.getName().equals(name))
                .sorted()
                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
    }

    @Override
    public Map<LocalDateTime, List<EventReadDto>> getMapEvents(String scheduleId) {
        List<EventReadDto> events = findByScheduleId(scheduleId);

        return events.stream()
                .sorted()
                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
    }
}
