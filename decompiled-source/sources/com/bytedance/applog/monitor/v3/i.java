package com.bytedance.applog.monitor.v3;

import android.os.SystemClock;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
/* compiled from: MonitorV3Utils.java */
/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicLong f2975a;

    /* renamed from: b  reason: collision with root package name */
    private static final AtomicLong f2976b;

    /* renamed from: c  reason: collision with root package name */
    private static final SimpleDateFormat f2977c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    static {
        long elapsedRealtime = (((SystemClock.elapsedRealtime() / 1000) * 100) + new SecureRandom().nextInt(99)) * 100000;
        f2975a = new AtomicLong(elapsedRealtime);
        f2976b = new AtomicLong(elapsedRealtime);
    }

    public static long a() {
        return f2975a.incrementAndGet();
    }

    public static long b() {
        return f2976b.incrementAndGet();
    }

    public static String c() {
        return String.valueOf(a());
    }

    public static String d() {
        return f2977c.format(new Date());
    }

    public static List<String> a(String str, char c2) {
        if (str == null || str.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.charAt(i3) == c2) {
                arrayList.add(str.substring(i2, i3));
                i2 = i3 + 1;
            }
        }
        if (i2 <= length) {
            arrayList.add(str.substring(i2));
        }
        return arrayList;
    }
}
