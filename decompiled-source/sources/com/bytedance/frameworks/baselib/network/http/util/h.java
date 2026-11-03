package com.bytedance.frameworks.baselib.network.http.util;

import java.util.regex.Pattern;
/* compiled from: InetAddressUtils.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static final Pattern f8337a = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    /* renamed from: b  reason: collision with root package name */
    private static final Pattern f8338b = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

    /* renamed from: c  reason: collision with root package name */
    private static final Pattern f8339c = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    private h() {
    }

    public static boolean a(String str) {
        try {
            return f8337a.matcher(str).matches();
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean b(String str) {
        try {
            return f8338b.matcher(str).matches();
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean c(String str) {
        try {
            return f8339c.matcher(str).matches();
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean d(String str) {
        return b(str) || c(str);
    }
}
