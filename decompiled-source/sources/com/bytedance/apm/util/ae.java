package com.bytedance.apm.util;

import android.content.Context;
import java.io.InputStream;
import java.util.Properties;
/* compiled from: SlardarProperties.java */
/* loaded from: classes.dex */
public class ae {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1668a = "slardar.properties";

    /* renamed from: b  reason: collision with root package name */
    private static final String f1669b = "release_build";

    /* renamed from: c  reason: collision with root package name */
    private static Properties f1670c;

    private static void a() {
        Context context = com.bytedance.apm.c.getContext();
        if (f1670c == null) {
            f1670c = new Properties();
            InputStream inputStream = null;
            try {
                inputStream = context.getApplicationContext().getAssets().open(f1668a);
                f1670c.load(inputStream);
            } catch (Throwable unused) {
            }
            com.bytedance.apm6.util.e.closeQuietly(inputStream);
        }
    }

    private static Object a(String str) {
        a();
        try {
            if (f1670c.containsKey(str)) {
                return f1670c.get(str);
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
