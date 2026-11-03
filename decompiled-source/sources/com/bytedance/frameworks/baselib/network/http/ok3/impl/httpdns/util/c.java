package com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util;

import java.util.regex.Pattern;
/* compiled from: IPAddressUtils.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static Pattern f8065a = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

    /* renamed from: b  reason: collision with root package name */
    private static final Pattern f8066b = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

    /* renamed from: c  reason: collision with root package name */
    private static final Pattern f8067c = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    public static boolean a(String str) {
        try {
            return f8066b.matcher(str).matches();
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean b(String str) {
        try {
            return f8067c.matcher(str).matches();
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean c(String str) {
        return a(str) || b(str);
    }

    public static boolean d(String str) {
        int i2;
        int indexOf;
        if (str != null) {
            try {
                if (str.length() >= 7 && str.length() <= 15 && !str.equals("")) {
                    if (f8065a.matcher(str).matches()) {
                        return true;
                    }
                }
            } catch (Throwable unused) {
                try {
                    int indexOf2 = str.indexOf(46);
                    if (indexOf2 != -1 && str.charAt(0) != '-' && Integer.parseInt(str.substring(0, indexOf2)) <= 255 && (indexOf = str.indexOf(46, (i2 = indexOf2 + 1))) != -1 && str.charAt(i2) != '-' && Integer.parseInt(str.substring(i2, indexOf)) <= 255) {
                        int i3 = indexOf + 1;
                        int indexOf3 = str.indexOf(46, i3);
                        if (indexOf3 != -1 && str.charAt(i3) != '-' && Integer.parseInt(str.substring(i3, indexOf3)) <= 255) {
                            int i4 = indexOf3 + 1;
                            if (str.charAt(i4) != '-' && Integer.parseInt(str.substring(i4, str.length())) <= 255) {
                                if (str.charAt(str.length() - 1) != '.') {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                } catch (Exception unused2) {
                }
                return false;
            }
        }
        return false;
    }
}
