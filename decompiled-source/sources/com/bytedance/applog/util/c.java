package com.bytedance.applog.util;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
/* compiled from: DateUtils.java */
/* loaded from: classes.dex */
public class c {
    public static SimpleDateFormat a() {
        return a("yyyy-MM-dd");
    }

    public static SimpleDateFormat b() {
        return a("yyyy-MM-dd HH:mm:ss");
    }

    public static SimpleDateFormat a(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Z"));
        return simpleDateFormat;
    }
}
