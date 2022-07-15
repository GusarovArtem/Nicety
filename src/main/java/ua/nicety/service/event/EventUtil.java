package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.event.BaseEvent;

@Component
@RequiredArgsConstructor
public class EventUtil {

    private final CommonEventService commonEventService;
    private final MeetingService meetingService;
    private final GoalService goalService;

    public EventService<? extends BaseEvent, ?> getEventService(String typeEvent){
        return switch (typeEvent) {
            case "common" ->  commonEventService;
            case "meeting" -> meetingService;
            case "goal" ->    goalService;
            default -> null;
        };
    }
}
