package ua.nicety.service.interfaces;

import ua.nicety.database.entity.Event;
import ua.nicety.http.dto.EventCreateEditDto;

import java.util.Optional;

public interface EventService {

    Event create(EventCreateEditDto eventCreateEditDto);
    Optional<Event> update(Long id, EventCreateEditDto eventCreateEditDto);
    boolean delete(Long id);

    Optional<Event> findById(Long id);
}
