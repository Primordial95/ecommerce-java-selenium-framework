package com.automationexercise.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
    private DateUtils(){
        //helper class
    }

    public static String parsedDate(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate = LocalDate.parse(date, dateTimeFormatter);
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        return parsedDate.format(dateTimeFormatter);
    }
}
