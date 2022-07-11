package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Goal;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.GoalReadDto;
import ua.nicety.http.mapper.EventCreateEditMapper;
import ua.nicety.http.mapper.read.GoalReadMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service("goal")
@RequiredArgsConstructor
public class GoalEventService implements EventService<Goal, GoalReadDto> {
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
    public List<GoalReadDto> findByScheduleId(String id) {
        return null;
    }

    @Override
    public Map<?, List<GoalReadDto>> findAllByName(String name, String scheduleId) {
        return null;
    }

    @Override
    public Map<?, List<GoalReadDto>> getMapEvents(String scheduleId) {
        return null;
    }

//    private final EventRepository<Goal> repository;
//    private final EventCreateEditMapper createEditMapper;
//
//    private final GoalReadMapper readMapper;
//
//
//    public Goal create(EventCreateEditDto eventDto) {
//        return Optional.of(eventDto)
//                .map(createEditMapper::map)
//                .map(repository::save).orElse(null);
//    }
//
//    @Transactional
//    public Optional<Goal> update(Long id, EventCreateEditDto eventDto) {
//        return repository.findById(id)
//                .map(entity -> createEditMapper.map(eventDto, entity))
//                .map(repository::saveAndFlush);
//    }
//
//    @Transactional
//    public boolean delete(Long id) {
//        return repository.findById(id)
//                .map(entity -> {
//                    repository.deleteById(id);
//                    return true;
//                })
//                .orElse(false);
//    }
//
//    @Override
//    public Optional<Goal> findById(Long id) {
//        return repository.findById(id);
//    }
//
//
//    @Override
//    public List<GoalReadDto> findByScheduleId(String id) {
//        return repository.findByScheduleId(id)
//                .stream().map(readMapper::map)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Map<LocalDateTime, List<GoalReadDto>> findAllByName(String name, String scheduleId) {
//        List<GoalReadDto> events = findByScheduleId(scheduleId);
//        return events.stream()
//                .filter(readDto -> readDto.getName().equals(name))
//                .sorted()
//                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
//    }
//
//    @Override
//    public Map<LocalDateTime, List<GoalReadDto>> getMapEvents(String scheduleId) {
//        List<GoalReadDto> events = findByScheduleId(scheduleId);
//
//        return events.stream()
//                .sorted()
//                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
//    }
}
