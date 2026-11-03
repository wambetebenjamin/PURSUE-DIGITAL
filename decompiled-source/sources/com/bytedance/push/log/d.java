package com.bytedance.push.log;

import android.util.Log;
import com.bytedance.push.interfaze.n;
/* compiled from: DefaultLogger.java */
/* loaded from: classes2.dex */
public class d implements a {

    /* renamed from: a  reason: collision with root package name */
    private final String f11430a = n.TAG;

    /* renamed from: b  reason: collision with root package name */
    private boolean f11431b = false;

    public void a(boolean z) {
        this.f11431b = z;
    }

    @Override // com.bytedance.push.log.a
    public void d(String str) {
        Log.d(n.TAG, str);
    }

    @Override // com.bytedance.push.log.a
    public void d(String str, String str2) {
        Log.d(n.TAG, str + " >>> " + str2);
    }

    @Override // com.bytedance.push.log.a
    public void e(String str) {
        Log.e(n.TAG, str);
    }

    @Override // com.bytedance.push.log.a
    public void e(String str, String str2) {
        Log.e(n.TAG, str2);
    }

    @Override // com.bytedance.push.log.a
    public void a(String str, String str2, Throwable th) {
        Log.d(n.TAG, str + " >>> " + str2);
    }

    @Override // com.bytedance.push.log.a
    public boolean debug() {
        return this.f11431b;
    }

    @Override // com.bytedance.push.log.a
    public void i(String str) {
        Log.d(n.TAG, str);
    }

    @Override // com.bytedance.push.log.a
    public void i(String str, String str2) {
        Log.i(n.TAG, str + " >>> " + str2);
    }

    @Override // com.bytedance.push.log.a
    public void i(String str, String str2, Throwable th) {
        Log.i(n.TAG, str + " >>> " + str2, th);
    }

    @Override // com.bytedance.push.log.a
    public void v(String str, String str2) {
        Log.v(n.TAG, str + " >>> " + str2);
    }

    @Override // com.bytedance.push.log.a
    public void w(String str, String str2) {
        Log.w(n.TAG, str + " >>> " + str2);
    }
}
