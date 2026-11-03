package com.bytedance.apm6.consumer.slardar;

import android.app.Application;
import java.util.Properties;
/* compiled from: SlardarProperties.java */
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1787a = "slardar.properties";

    /* renamed from: b  reason: collision with root package name */
    private static final String f1788b = "release_build";

    /* renamed from: c  reason: collision with root package name */
    private static Properties f1789c;

    private static void a() {
        Application context = com.bytedance.apm6.foundation.context.a.getContext();
        if (f1789c == null) {
            Properties properties = new Properties();
            f1789c = properties;
            try {
                properties.load(context.getApplicationContext().getAssets().open(f1787a));
            } catch (Throwable unused) {
            }
        }
    }

    private static Object a(String str) {
        a();
        try {
            if (f1789c.containsKey(str)) {
                return f1789c.get(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getReleaseBuild() {
        return String.valueOf(a("release_build"));
    }
}
