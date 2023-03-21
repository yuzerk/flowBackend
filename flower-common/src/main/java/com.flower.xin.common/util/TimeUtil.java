package com.flower.xin.common.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TimeUtil {

    /**
     * 获得minute分钟后的时间
     * @param date
     * @param minute
     * @return
     */
    public static Date getDateAddMinutes(Date date, int minute) {
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .plusMinutes(minute);
        return Date.from(zonedDateTime.toInstant());
    }
}
