package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Day;
import ua.nicety.database.entity.Event;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.EventReadDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventUtil {
    private final CommonEventService commonEventService;

    public EventService getEventService(String typeEvent){
        if(typeEvent.equals("common"))
            return commonEventService;

        return null;
    }
}
