package com.bytedance.router.dynamic;

import android.text.TextUtils;
/* compiled from: ServerParam.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private int f12184a;

    /* renamed from: b  reason: collision with root package name */
    private String f12185b;

    /* renamed from: c  reason: collision with root package name */
    private String f12186c;

    public b(int i2, String str, String str2) {
        this.f12185b = "";
        this.f12186c = "";
        this.f12184a = i2;
        this.f12185b = str;
        this.f12186c = str2;
        if (i2 <= 0) {
            com.bytedance.router.util.b.e("aid must > 0, it can request server!!!");
        }
        if (TextUtils.isEmpty(this.f12185b)) {
            com.bytedance.router.util.b.e("deviceId must be nut null, it can request server!!!");
        }
        if (this.f12186c == null) {
            this.f12186c = "";
        }
    }

    public int getAid() {
        return this.f12184a;
    }

    public String getDeviceId() {
        return this.f12185b;
    }

    public String getChannel() {
        return this.f12186c;
    }

    public boolean isAvailable() {
        if (this.f12184a <= 0) {
            com.bytedance.router.util.b.e("aid <= 0, ServerParam is unavailable!!!");
            return false;
        } else if (TextUtils.isEmpty(this.f12185b)) {
            com.bytedance.router.util.b.e("device id is empty, ServerParam is unavailable!!!");
            return false;
        } else {
            return true;
        }
    }
}
