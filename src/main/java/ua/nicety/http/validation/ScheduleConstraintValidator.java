package ua.nicety.http.validation;

import lombok.RequiredArgsConstructor;
import ua.nicety.http.validation.annotation.ScheduleValidate;
import ua.nicety.service.ScheduleService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ScheduleConstraintValidator implements ConstraintValidator<ScheduleValidate, String> {

    private final ScheduleService scheduleService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return scheduleService.findById(s).isPresent();
    }
}
