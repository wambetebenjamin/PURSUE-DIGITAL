package com.bytedance.crash.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* compiled from: DateUtils.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static DateFormat f7290a = null;

    /* renamed from: b  reason: collision with root package name */
    private static DateFormat f7291b = null;

    /* renamed from: c  reason: collision with root package name */
    private static DateFormat f7292c = null;

    /* renamed from: d  reason: collision with root package name */
    private static DateFormat f7293d = null;

    /* renamed from: e  reason: collision with root package name */
    public static final int f7294e = 86400000;

    public static DateFormat getDateInstance() {
        if (f7290a == null) {
            f7290a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
        return f7290a;
    }

    public static DateFormat getDateMsInstance() {
        if (f7291b == null) {
            f7291b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        }
        return f7291b;
    }

    public static DateFormat getFileDateInstance() {
        if (f7292c == null) {
            f7292c = new SimpleDateFormat("yyyy-MM-dd@HH-mm-ss", Locale.getDefault());
        }
        return f7292c;
    }

    public static DateFormat a() {
        if (f7293d == null) {
            f7293d = new SimpleDateFormat("yyyy-MM-dd@HH-mm-ss.SSS", Locale.getDefault());
        }
        return f7293d;
    }

    public static long a(String str) {
        Date parse = getDateMsInstance().parse(str);
        if (parse != null) {
            return parse.getTime();
        }
        return 0L;
    }

    public static String a(long j2) {
        return getDateMsInstance().format(new Date(j2));
    }

    public static String b(long j2) {
        return getDateInstance().format(new Date(j2));
    }
}
