package ua.nicety.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Event;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.mapper.EventCreateEditMapper;
import ua.nicety.service.interfaces.EventService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventCreateEditMapper eventCreateEditMapper;

    public Event create(EventCreateEditDto eventDto) {
        return Optional.of(eventDto)
                .map(eventCreateEditMapper::map)
                .map(eventRepository::save).orElse(null);
    }

    @Transactional
    public Optional<Event> update(Long id, EventCreateEditDto eventDto) {
        return eventRepository.findById(id)
                .map(entity -> eventCreateEditMapper.map(eventDto, entity))
                .map(eventRepository::saveAndFlush);
    }

    @Transactional
    public boolean delete(Long id) {
        return eventRepository.findById(id)
                .map(entity -> {
                    eventRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }
}
