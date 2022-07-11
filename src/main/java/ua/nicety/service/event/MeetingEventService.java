package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Meeting;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.MeetingReadDto;
import ua.nicety.http.mapper.EventCreateEditMapper;
import ua.nicety.http.mapper.read.MeetingReadMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service("goal")
@RequiredArgsConstructor
public class MeetingEventService implements EventService<Meeting, MeetingReadDto> {

    private final EventRepository<Meeting> repository;
    private final EventCreateEditMapper<Meeting> createEditMapper;

    private final MeetingReadMapper readMapper;


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
    public List<MeetingReadDto> findByScheduleId(String id) {
        return repository.findByScheduleId(id)
                .stream().map(readMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Map<LocalDateTime, List<MeetingReadDto>> findAllByName(String name, String scheduleId) {
        List<MeetingReadDto> events = findByScheduleId(scheduleId);
        return events.stream()
                .filter(eventReadDto -> eventReadDto.getName().equals(name))
                .sorted()
                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
    }

    @Override
    public Map<LocalDateTime, List<MeetingReadDto>> getMapEvents(String scheduleId) {
        List<MeetingReadDto> events = findByScheduleId(scheduleId);

        return events.stream()
                .sorted()
                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
    }
}