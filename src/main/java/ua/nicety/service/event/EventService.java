package ua.nicety.service.event;

import ua.nicety.database.entity.BaseEvent;
import ua.nicety.http.dto.EventCreateEditDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface EventService<E extends BaseEvent, R> {

    E create(EventCreateEditDto eventCreateEditDto);

    Optional<E> update(Long id, EventCreateEditDto eventCreateEditDto);

    boolean delete(Long id);

    Optional<E> findById(Long id);

    List<R> findByScheduleId(String id);

    Map<?, List<R>> findAllByName(String name, String scheduleId);

    Map<?, List<R>> getMapEvents(String scheduleId);
}
