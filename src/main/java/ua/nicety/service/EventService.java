package ua.nicety.service;

import ua.nicety.http.dto.EventCreateEditDto;

public interface EventService {

    void create(EventCreateEditDto eventCreateEditDto);
    boolean update(Long id, EventCreateEditDto eventCreateEditDto);
    boolean delete(Long id);
}
