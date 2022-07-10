package ua.nicety.service.event;

import ua.nicety.database.entity.Day;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface EventService<E, ReadDto, CreateDto> {

    E create(CreateDto eventCreateEditDto);

    Optional<E> update(Long id, CreateDto eventCreateEditDto);

    boolean delete(Long id);

    Optional<E> findById(Long id);

    List<ReadDto> findByScheduleId(String id);

    Map<Day, List<ReadDto>> findAllByName(String name, String scheduleId);

    Map<Day, List<ReadDto>> getMapEvents(String scheduleId);
}
