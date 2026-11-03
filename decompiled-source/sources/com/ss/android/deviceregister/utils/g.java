package com.ss.android.deviceregister.utils;

import android.os.SystemProperties;
/* compiled from: SystemPropertiesProxy.java */
/* loaded from: classes9.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static volatile Object f18286a;

    private Object a() {
        if (f18286a == null) {
            synchronized (g.class) {
                if (f18286a == null) {
                    f18286a = Class.forName("android.os.SystemProperties").newInstance();
                }
            }
        }
        return f18286a;
    }

    public String get(String str) {
        try {
            return SystemProperties.get(str);
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                Object a2 = a();
                return (String) a2.getClass().getMethod("get", String.class).invoke(a2, str);
            } catch (IllegalArgumentException e2) {
                throw e2;
            } catch (Throwable unused) {
                return "";
            }
        }
    }

    public String get(String str, String str2) {
        try {
            return SystemProperties.get(str);
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                Object a2 = a();
                return (String) a2.getClass().getMethod("get", String.class, String.class).invoke(a2, str, str2);
            } catch (IllegalArgumentException e2) {
                throw e2;
            } catch (Throwable unused) {
                return str2;
            }
        }
    }

    public Integer getInt(String str, int i2) {
        try {
            return Integer.valueOf(SystemProperties.getInt(str, i2));
        } catch (Throwable th) {
            th.printStackTrace();
            Integer.valueOf(i2);
            try {
                Object a2 = a();
                return (Integer) a2.getClass().getMethod("getInt", String.class, Integer.TYPE).invoke(a2, str, Integer.valueOf(i2));
            } catch (IllegalArgumentException e2) {
                throw e2;
            } catch (Throwable unused) {
                return Integer.valueOf(i2);
            }
        }
    }

    public Long getLong(String str, long j2) {
        try {
            return Long.valueOf(SystemProperties.getLong(str, j2));
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                Object a2 = a();
                return (Long) a2.getClass().getMethod("getLong", String.class, Integer.TYPE).invoke(a2, str, Long.valueOf(j2));
            } catch (IllegalArgumentException e2) {
                throw e2;
            } catch (Throwable unused) {
                return Long.valueOf(j2);
            }
        }
    }

    public Boolean getBoolean(String str, boolean z) {
        try {
            return Boolean.valueOf(SystemProperties.getBoolean(str, z));
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                Object a2 = a();
                return (Boolean) a2.getClass().getMethod("getBoolean", String.class, Boolean.TYPE).invoke(a2, str, Boolean.valueOf(z));
            } catch (IllegalArgumentException e2) {
                throw e2;
            } catch (Throwable unused) {
                return Boolean.valueOf(z);
            }
        }
    }
}
