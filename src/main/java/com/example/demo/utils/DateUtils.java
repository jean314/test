package com.example.demo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date[] getStartAndDateDatesFromNumberOfDays (Integer days){
        LocalDate endDate = LocalDate.now();
        //resto el número de días según lo que venga en el endpoint
        LocalDateTime startDate = endDate.minusDays(days-1).atTime(0,1,0);
        Date start = Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant());
        Date end = new Date();

        if(days==1){
            //Si solo es 1, solo consulto del día actual. Desde las 00:01:00 hasta la hora actual
            LocalDateTime localDateTime = LocalDateTime.now();
            LocalDateTime customLocalDateTime = localDateTime.withHour(0).withMinute(1).withSecond(0).withNano(0);
            start = Date.from(customLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }




        Date[] dateRange = {start, end};
        return dateRange;
    }

}
