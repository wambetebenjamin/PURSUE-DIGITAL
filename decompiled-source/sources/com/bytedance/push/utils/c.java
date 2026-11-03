package com.bytedance.push.utils;

import android.util.Log;
/* compiled from: DefaultALogImpl.java */
/* loaded from: classes2.dex */
public class c implements com.bytedance.push.interfaze.c {
    @Override // com.bytedance.push.interfaze.c
    public void d(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // com.bytedance.push.interfaze.c
    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.bytedance.push.interfaze.c
    public void i(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.bytedance.push.interfaze.c
    public void v(String str, String str2) {
        Log.v(str, str2);
    }

    @Override // com.bytedance.push.interfaze.c
    public void w(String str, String str2) {
        Log.w(str, str2);
    }
}
