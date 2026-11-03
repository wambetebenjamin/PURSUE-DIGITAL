package com.bytedance.news.common.settings.api.cache;

import android.content.Context;
import android.content.SharedPreferences;
/* compiled from: CtxInfoManager.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9746a = "key_ctx_info";

    /* renamed from: b  reason: collision with root package name */
    private static volatile a f9747b;

    /* renamed from: c  reason: collision with root package name */
    private SharedPreferences f9748c;

    public static a getInstance(Context context) {
        if (f9747b == null) {
            synchronized (a.class) {
                if (f9747b == null) {
                    f9747b = new a(context);
                }
            }
        }
        return f9747b;
    }

    private a(Context context) {
        this.f9748c = context.getSharedPreferences("__ctx_info.sp", 0);
    }

    public synchronized void updateCtxInfo(String str) {
        this.f9748c.edit().putString(f9746a, str).apply();
    }

    public String getCtxInfo() {
        return this.f9748c.getString(f9746a, "");
    }
}
