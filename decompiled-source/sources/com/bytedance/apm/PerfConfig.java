package com.bytedance.apm;

import android.util.Log;
/* loaded from: classes.dex */
public class PerfConfig {

    /* renamed from: a  reason: collision with root package name */
    static boolean f220a = false;

    public static void setReportMessage() {
        Log.e("PerfConfig", "setReportMessage set true");
        f220a = true;
    }

    public static boolean isReportMessage() {
        return f220a;
    }
}
