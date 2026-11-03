package com.bytedance.crash.runtime;

import android.text.TextUtils;
import com.bytedance.crash.monitor.h;
/* compiled from: SettingManager.java */
/* loaded from: classes2.dex */
public final class r {
    public static final int AID_DEFAULT = 4444;

    /* renamed from: a  reason: collision with root package name */
    private String f7069a;

    /* renamed from: b  reason: collision with root package name */
    private final int f7070b = -1;

    public void setDeviceId(String str) {
        this.f7069a = str;
    }

    public int getAid() {
        try {
            return Integer.parseInt(h.c());
        } catch (Throwable unused) {
            return AID_DEFAULT;
        }
    }

    public String getDeviceId() {
        if (TextUtils.isEmpty(this.f7069a)) {
            this.f7069a = "0";
        }
        return this.f7069a;
    }

    public boolean isDidSet() {
        return (TextUtils.isEmpty(this.f7069a) || "0".equals(this.f7069a)) ? false : true;
    }
}
