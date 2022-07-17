package ua.nicety.http.validation;

import lombok.RequiredArgsConstructor;
import ua.nicety.http.validation.annotation.ScheduleExistValidate;
import ua.nicety.service.schedule.AllSchedulesService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ScheduleExistValidator implements ConstraintValidator<ScheduleExistValidate, String> {

    private final AllSchedulesService scheduleService;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {

        return scheduleService.getById(id).isPresent();
    }
}
