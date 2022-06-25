package ua.nicety.http.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.nicety.database.entity.Schedule;
import ua.nicety.http.dto.ScheduleCreateEditDto;

@Component
@RequiredArgsConstructor
public class ScheduleCreateEditMapper implements Mapper<ScheduleCreateEditDto, Schedule> {

    @Override
    public Schedule map(ScheduleCreateEditDto object) {
        Schedule schedule = new Schedule();
        copy(object, schedule);

        return schedule;
    }

    public Schedule map(ScheduleCreateEditDto fromObject, Schedule toObject) {
        copy(fromObject, toObject);
        return toObject;
    }


    private void copy(ScheduleCreateEditDto object, Schedule schedule) {
        schedule.setName(object.getName());
        schedule.setAuthor(object.getAuthor());
    }
}
