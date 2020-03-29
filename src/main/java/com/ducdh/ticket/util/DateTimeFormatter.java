package com.ducdh.ticket.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {

    public static String parse(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return "ERROR";
        }
    }
}
