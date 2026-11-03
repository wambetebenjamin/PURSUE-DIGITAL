package com.bytedance.push.notification;

import android.os.SystemClock;
import android.text.TextUtils;
/* compiled from: DebouncingClickHelper.java */
/* loaded from: classes2.dex */
class c {

    /* renamed from: a  reason: collision with root package name */
    private long f11561a;

    /* renamed from: b  reason: collision with root package name */
    private String f11562b;

    /* renamed from: c  reason: collision with root package name */
    private int f11563c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str, int i2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f11563c == i2 && elapsedRealtime - this.f11561a <= 1000 && TextUtils.equals(this.f11562b, str)) {
            return true;
        }
        this.f11562b = str;
        this.f11563c = i2;
        this.f11561a = elapsedRealtime;
        return false;
    }
}
