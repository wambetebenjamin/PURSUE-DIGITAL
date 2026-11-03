package com.bytedance.ttnet.retrofit;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
/* compiled from: RequestDelayHelper.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15449a = "RequestDelayHelper";

    /* renamed from: b  reason: collision with root package name */
    private static final String f15450b = "00:00:00";

    public static int a(int i2) {
        return new Random(UUID.randomUUID().hashCode()).nextInt(i2);
    }

    public static boolean a(String str, Set<String> set) {
        if (!o.isEmpty(str) && set != null) {
            for (String str2 : set) {
                if (!TextUtils.isEmpty(str2) && str.startsWith(str2)) {
                    if (Logger.debug()) {
                        Logger.d(f15449a, "app delay, matched, rule is " + str2 + " request path is " + str);
                        return true;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(String str, String str2) {
        if (!o.isEmpty(str) && !o.isEmpty(str2)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                Date parse = simpleDateFormat.parse(str);
                Date parse2 = simpleDateFormat.parse(str2);
                long currentTimeMillis = System.currentTimeMillis();
                Date date = new Date(currentTimeMillis);
                parse2.setDate(date.getDate());
                parse2.setMonth(date.getMonth());
                parse2.setYear(date.getYear());
                parse.setDate(date.getDate());
                parse.setMonth(date.getMonth());
                parse.setYear(date.getYear());
                if (parse.before(parse2)) {
                    return currentTimeMillis >= parse.getTime() && currentTimeMillis <= parse2.getTime();
                }
                Date parse3 = simpleDateFormat.parse(f15450b);
                Date parse4 = simpleDateFormat.parse(f15450b);
                parse4.setDate(date.getDate());
                parse4.setMonth(date.getMonth());
                parse4.setYear(date.getYear());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(5, 1);
                Date time = calendar.getTime();
                parse3.setDate(time.getDate());
                parse3.setMonth(time.getMonth());
                parse3.setYear(time.getYear());
                return (currentTimeMillis >= parse4.getTime() && currentTimeMillis <= parse2.getTime()) || (currentTimeMillis >= parse.getTime() && currentTimeMillis < parse3.getTime());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }
}
