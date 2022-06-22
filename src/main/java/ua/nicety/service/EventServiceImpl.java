package ua.nicety.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.repository.EventRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.mapper.EventCreateEditMapper;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventCreateEditMapper eventCreateEditMapper;

    public void create(EventCreateEditDto eventDto) {
        Optional.of(eventDto)
                .map(eventCreateEditMapper::map)
                .map(event -> {
                    eventRepository.save(event);
                    return event;
                });
    }

    @Transactional
    public boolean update(Long id, EventCreateEditDto eventDto) {
        return eventRepository.findById(id)
                .map(entity -> eventCreateEditMapper.map(eventDto, entity))
                .map(event -> {
                    eventRepository.saveAndFlush(event);
                    return true;
                }).orElse(false);
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
}
