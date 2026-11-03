package com.bytedance.apm.internal;

import android.content.SharedPreferences;
import com.bytedance.apm.core.e;
/* compiled from: SpManager.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f1138a;

    private c() {
        this.f1138a = e.getSharedPreferences(com.bytedance.apm.c.getContext(), "monitor_config");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SpManager.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final c f1139a = new c();

        private a() {
        }
    }

    public static c getInstance() {
        return a.f1139a;
    }

    public String getString(String str) {
        return this.f1138a.getString(str, null);
    }

    public int getInt(String str) {
        return this.f1138a.getInt(str, -1);
    }

    public long getLong(String str) {
        return this.f1138a.getLong(str, 0L);
    }

    public void saveAsync(String str, String str2) {
        this.f1138a.edit().putString(str, str2).apply();
    }

    public void saveAsync(String str, long j2) {
        this.f1138a.edit().putLong(str, j2).apply();
    }

    public void saveAsync(String str, int i2) {
        this.f1138a.edit().putInt(str, i2).apply();
    }
}
