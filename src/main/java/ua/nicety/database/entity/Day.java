package ua.nicety.database.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Day {
    MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"), FRIDAY("Friday"), SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String value;

    Day(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Day getByValue(@JsonProperty("day") String value){
        for (Day day:values()) {
            if(day.value.equals(value))
                return day;
        }

        return null;
    }
}