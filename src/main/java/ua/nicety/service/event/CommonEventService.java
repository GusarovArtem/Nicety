package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.event.Event;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.EventReadDto;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service("common")
@RequiredArgsConstructor
public class CommonEventService implements EventService<Event, EventReadDto> {

    private final EventRepository<Event> repository;
    private final ModelMapper modelMapper;

    public Event create(EventCreateEditDto eventDto) {
        return Optional.of(eventDto)
                .map(createDto -> modelMapper.map(createDto, Event.class))
                .map(repository::save).orElse(null);
    }

    @Transactional
    public Optional<Event> update(Long id, EventCreateEditDto eventDto) {
        return repository.findById(id)
                .map(entity -> {
                    Event mappedEntity = modelMapper.map(eventDto, Event.class);
                    mappedEntity.setId(entity.getId());
                    return mappedEntity;
                })
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
    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public List<EventReadDto> findByScheduleId(String id) {
        return repository.findByScheduleId(id)
                .stream().map(event -> modelMapper.map(event, EventReadDto.class))
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