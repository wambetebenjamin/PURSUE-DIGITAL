package com.bytedance.common.utility.date;

import android.content.Context;
import android.text.format.DateUtils;
import com.bytedance.common.utility.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/* compiled from: DateUtils.java */
/* loaded from: classes2.dex */
public final class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private static final SimpleDateFormat f5248a = new SimpleDateFormat("yyyy-MM-dd");

    /* renamed from: b  reason: collision with root package name */
    private static final SimpleDateFormat f5249b = new SimpleDateFormat("MM-dd HH:mm");

    private b() {
    }

    public static String getDateString(Context context, long j2) {
        if (!isCurrentYear(j2)) {
            return f5248a.format(Long.valueOf(j2));
        }
        if (!DateUtils.isToday(j2)) {
            return f5249b.format(Long.valueOf(j2));
        }
        long currentTimeMillis = System.currentTimeMillis() - j2;
        return currentTimeMillis >= 3600000 ? context.getString(R.string.hours_ago, Long.valueOf(currentTimeMillis / 3600000)) : currentTimeMillis >= 60000 ? context.getString(R.string.minutes_ago, Long.valueOf(currentTimeMillis / 60000)) : context.getString(R.string.just_now);
    }

    public static boolean isCurrentYear(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        int i2 = calendar.get(1);
        calendar.setTimeInMillis(System.currentTimeMillis());
        return i2 == calendar.get(1);
    }
}
