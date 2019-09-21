package com.time.plan.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class GardeniaDateUtil {


    public static String dateWeek(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();

        String displayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.CHINESE);
        return displayName;
    }

    public static String dateWeek(String dateTimeStr, String pattern) {
        return dateWeek(LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern, Locale.CHINESE)));
    }


    public static Calendar calendarAddDay(LocalDateTime localDateTime, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),0,0,0);
        calendar.add(Calendar.DAY_OF_MONTH, day);//星期一
        return calendar;
    }

//    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        DayOfWeek dayOfWeek = now.getDayOfWeek();
//        int value = dayOfWeek.getValue();
//
//
//        System.out.println(value);
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(0, 0, 0, 0, 0, 0);
//        calendar.set(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
//
//
//        calendar.add(Calendar.DAY_OF_MONTH, -value + 1);//星期一
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        System.out.println(year + " " + month + " " + day);
//        LocalDateTime fisrtDayOfWeek = LocalDateTime.of(year, month, day, 0, 0, 0);
//        String first = fisrtDayOfWeek.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CHINESE);
//
//
//        calendar.add(Calendar.DAY_OF_MONTH, 6);// 周末
//        year = calendar.get(Calendar.YEAR);
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//        System.out.println(year + " " + month + " " + day);
//        LocalDateTime sevenDayOfWeek = LocalDateTime.of(year, month, day, 23, 59, 59);
//
//
//        String seven = sevenDayOfWeek.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CHINESE);
//        System.out.println(seven);
//
//    }

}
