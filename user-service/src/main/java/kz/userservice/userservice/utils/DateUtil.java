package kz.userservice.userservice.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

    public static LocalDate parseToLocalDateTime(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return LocalDate.parse(value, formatter);
    }
}
