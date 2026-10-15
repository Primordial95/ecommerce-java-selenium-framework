package com.automationexercise.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
    private static DateTimeFormatter dateTimeFormatter;
    private DateUtils(){
        //helper class
    }

    public static String parsedDate(String date){
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate = LocalDate.parse(date, dateTimeFormatter);
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        return parsedDate.format(dateTimeFormatter);
    }

    public static String getDateForReport(){
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(dateTimeFormatter);
    }
}
