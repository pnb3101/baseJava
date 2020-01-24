package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.Organization;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.postgresql.jdbc.EscapedFunctions.NOW;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/yyyy");

    public static YearMonth parse(String date) {
        if (date == null || "Сейчас".equalsIgnoreCase(date)) return YearMonth.now();
        return YearMonth.parse(date, DATE_FORMAT);
    }

    public static String formatDates(Organization.Position position) {
        return DateUtil.format(position.getDateOfStart()) + " - " + DateUtil.format(position.getDateOfFinish());
    }

    public static String format(YearMonth date) {
        if (date == null) return "";
        return date.equals(NOW) ? "Сейчас" : date.format(DATE_FORMAT);
    }
}
