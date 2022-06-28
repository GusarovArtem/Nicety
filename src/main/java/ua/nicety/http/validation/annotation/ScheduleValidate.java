package ua.nicety.http.validation.annotation;

import ua.nicety.http.validation.ScheduleConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ScheduleConstraintValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduleValidate {
    String message() default "Schedule doesn't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
