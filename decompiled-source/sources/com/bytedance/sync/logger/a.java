package com.bytedance.sync.logger;

import android.util.Log;
import com.bytedance.sync.interfaze.g;
/* compiled from: ALogger.java */
/* loaded from: classes3.dex */
final class a implements g {
    @Override // com.bytedance.sync.interfaze.g
    public void v(String str) {
        try {
            Log.v(c.TAG, str);
            com.ss.android.agilelogger.a.v(c.TAG, str);
        } catch (Throwable unused) {
        }
    }

    @Override // com.bytedance.sync.interfaze.g
    public void d(String str) {
        try {
            com.ss.android.agilelogger.a.i(c.TAG, str);
        } catch (Throwable unused) {
        }
    }

    @Override // com.bytedance.sync.interfaze.g
    public void i(String str) {
        try {
            com.ss.android.agilelogger.a.i(c.TAG, str);
        } catch (Throwable unused) {
        }
    }

    @Override // com.bytedance.sync.interfaze.g
    public void e(String str) {
        try {
            com.ss.android.agilelogger.a.e(c.TAG, str);
        } catch (Throwable unused) {
        }
    }
}
