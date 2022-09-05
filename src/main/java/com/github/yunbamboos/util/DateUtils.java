package com.github.yunbamboos.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * */
public class DateUtils {

    private DateUtils(){}

    /**格式化日期*/
    public static String parseDateToStr(LocalDateTime date, String pattern) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return dateFormat.format(date);
    }

    /**格式化日期*/
    public static String parseDateToStr(LocalDate date, String pattern) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return dateFormat.format(date);
    }

    /**格式化日期*/
    public static String parseDateToStr(LocalTime date, String pattern) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return dateFormat.format(date);
    }

    public static LocalDateTime stringToLocalDateTime(String dateString, String pattern) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate stringToLocalDate(String dateString, String pattern) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime stringToLocalTime(String dateString, String pattern) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        return LocalTime.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime ofEpochMilli(long epochMilli){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneOffset.of("+8"));
    }

    public static Integer getDifMonth(String startDate, String endDate, String pattern){
        DateTimeFormatter sdf =  DateTimeFormatter.ofPattern(pattern);
        YearMonth before = YearMonth.parse(startDate,sdf);
        YearMonth after = YearMonth.parse(endDate,sdf);
        int result = after.getMonthValue() - before.getMonthValue();
        int month = (after.getYear() -  before.getYear())*12;
        return month+result;
    }
}
