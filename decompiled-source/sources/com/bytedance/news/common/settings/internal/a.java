package com.bytedance.news.common.settings.internal;

import android.app.Application;
import android.content.Context;
/* compiled from: GlobalConfig.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile Context f9799a;

    private a() {
    }

    public static synchronized void init(Context context) {
        synchronized (a.class) {
            if (f9799a == null) {
                if (context instanceof Application) {
                    f9799a = context;
                } else {
                    f9799a = context.getApplicationContext();
                }
            }
        }
    }

    public static void checkConfig() {
        if (f9799a == null) {
            synchronized (a.class) {
                if (f9799a == null) {
                    throw new IllegalStateException("SettingsManager尚未被配置");
                }
            }
        }
    }

    public static Context getContext() {
        checkConfig();
        return f9799a;
    }
}
