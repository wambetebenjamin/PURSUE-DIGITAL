package com.bytedance.push.msg.callback;

import android.os.SystemClock;
import android.text.TextUtils;
/* compiled from: ClickHelper.java */
/* loaded from: classes2.dex */
class a {

    /* renamed from: a  reason: collision with root package name */
    private long f11522a;

    /* renamed from: b  reason: collision with root package name */
    private String f11523b;

    /* renamed from: c  reason: collision with root package name */
    private String f11524c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str, String str2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (TextUtils.equals(this.f11524c, str2) && elapsedRealtime - this.f11522a <= 1000 && TextUtils.equals(this.f11523b, str)) {
            return true;
        }
        this.f11523b = str;
        this.f11524c = str2;
        this.f11522a = elapsedRealtime;
        return false;
    }
}
