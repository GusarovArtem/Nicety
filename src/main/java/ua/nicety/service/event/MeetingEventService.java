package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nicety.database.entity.Meeting;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.MeetingReadDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("meeting")
@RequiredArgsConstructor
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
    public Map<?, List<MeetingReadDto>> findAllByName(String name, String scheduleId) {
        return null;
    }

    @Override
    public Map<?, List<MeetingReadDto>> getMapEvents(String scheduleId) {
        return null;
    }

//    private final EventRepository<Meeting> repository;
//    private final EventCreateEditMapper<Meeting> createEditMapper;
//
//    private final MeetingReadMapper readMapper;
//
//
//    public Meeting create(EventCreateEditDto eventDto) {
//        return Optional.of(eventDto)
//                .map(createEditMapper::map)
//                .map(repository::save).orElse(null);
//    }
//
//    @Transactional
//    public Optional<Meeting> update(Long id, EventCreateEditDto eventDto) {
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
//    public Optional<Meeting> findById(Long id) {
//        return repository.findById(id);
//    }
//
//
//    @Override
//    public List<MeetingReadDto> findByScheduleId(String id) {
//        return repository.findByScheduleId(id)
//                .stream().map(readMapper::map)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Map<LocalDateTime, List<MeetingReadDto>> findAllByName(String name, String scheduleId) {
//        List<MeetingReadDto> events = findByScheduleId(scheduleId);
//        return events.stream()
//                .filter(eventReadDto -> eventReadDto.getName().equals(name))
//                .sorted()
//                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
//    }
//
//    @Override
//    public Map<LocalDateTime, List<MeetingReadDto>> getMapEvents(String scheduleId) {
//        List<MeetingReadDto> events = findByScheduleId(scheduleId);
//
//        return events.stream()
//                .sorted()
//                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
//    }
}