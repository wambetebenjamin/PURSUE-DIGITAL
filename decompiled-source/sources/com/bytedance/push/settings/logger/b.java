package com.bytedance.push.settings.logger;

import android.util.Log;
/* compiled from: SettingsLoggerHelper.java */
/* loaded from: classes2.dex */
public class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private static final b f11793a = new b();

    /* renamed from: b  reason: collision with root package name */
    private a f11794b;

    /* renamed from: c  reason: collision with root package name */
    private final String f11795c = "SettingsManager";

    public static b a() {
        return f11793a;
    }

    public void a(a aVar) {
        this.f11794b = aVar;
    }

    @Override // com.bytedance.push.settings.logger.a
    public void a(String str) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.a(str);
        } else {
            Log.d("SettingsManager", str);
        }
    }

    @Override // com.bytedance.push.settings.logger.a
    public void a(String str, String str2) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.a("SettingsManager-->" + str, str2);
        } else {
            Log.d("SettingsManager-->" + str, str2);
        }
    }

    @Override // com.bytedance.push.settings.logger.a
    public void b(String str) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.b("SettingsManager-->" + str);
        } else {
            Log.e("SettingsManager", str);
        }
    }

    @Override // com.bytedance.push.settings.logger.a
    public void b(String str, String str2) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.b("SettingsManager-->" + str, str2);
        } else {
            Log.e("SettingsManager-->" + str, str2);
        }
    }

    @Override // com.bytedance.push.settings.logger.a
    public void c(String str) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.c("SettingsManager-->" + str);
        } else {
            Log.i("SettingsManager", str);
        }
    }

    @Override // com.bytedance.push.settings.logger.a
    public void c(String str, String str2) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.c("SettingsManager-->" + str, str2);
        } else {
            Log.i("SettingsManager-->" + str, str2);
        }
    }

    @Override // com.bytedance.push.settings.logger.a
    public void a(String str, String str2, Throwable th) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.a("SettingsManager-->" + str, str2, th);
        } else {
            Log.i("SettingsManager-->" + str, str2, th);
        }
    }

    @Override // com.bytedance.push.settings.logger.a
    public void d(String str, String str2) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.d("SettingsManager-->" + str, str2);
        } else {
            Log.v("SettingsManager-->" + str, str2);
        }
    }

    @Override // com.bytedance.push.settings.logger.a
    public void e(String str, String str2) {
        a aVar = this.f11794b;
        if (aVar != null) {
            aVar.e("SettingsManager-->" + str, str2);
        } else {
            Log.w("SettingsManager-->" + str, str2);
        }
    }
}
