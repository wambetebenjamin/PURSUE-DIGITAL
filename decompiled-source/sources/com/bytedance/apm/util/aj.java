package com.bytedance.apm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/* compiled from: TimeUtils.java */
/* loaded from: classes.dex */
public class aj {
    public static long getYesterdayStart() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTimeInMillis();
    }

    public static long getYesterdayEnd() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -1);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTimeInMillis();
    }

    public static long getNDayAgoStart(int i2) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -i2);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTimeInMillis();
    }

    public static String getCurrentFormatTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }
}
