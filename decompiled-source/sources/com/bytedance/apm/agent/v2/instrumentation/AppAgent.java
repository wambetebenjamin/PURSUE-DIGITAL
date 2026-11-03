package com.bytedance.apm.agent.v2.instrumentation;

import android.text.TextUtils;
import com.bytedance.apm.agent.tracing.a;
/* loaded from: classes.dex */
public class AppAgent {

    /* renamed from: a  reason: collision with root package name */
    private static final String f346a = "<init>";

    /* renamed from: b  reason: collision with root package name */
    private static final String f347b = "attachBaseContext";

    /* renamed from: c  reason: collision with root package name */
    private static final String f348c = "onCreate";

    /* renamed from: d  reason: collision with root package name */
    private static long f349d;

    /* renamed from: e  reason: collision with root package name */
    private static long f350e;

    /* renamed from: f  reason: collision with root package name */
    private static long f351f;

    /* renamed from: g  reason: collision with root package name */
    private static long f352g;

    /* renamed from: h  reason: collision with root package name */
    private static long f353h;

    /* renamed from: i  reason: collision with root package name */
    private static long f354i;

    public static void onTrace(String str, boolean z) {
        if (TextUtils.equals(str, f346a)) {
            if (z) {
                f349d = System.currentTimeMillis();
            } else {
                f350e = System.currentTimeMillis();
            }
        } else if (TextUtils.equals(str, f347b)) {
            if (z) {
                f351f = System.currentTimeMillis();
            } else {
                f352g = System.currentTimeMillis();
            }
        } else if (TextUtils.equals(str, "onCreate")) {
            if (z) {
                f353h = System.currentTimeMillis();
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            f354i = currentTimeMillis;
            a.assignAppTime(f349d, f350e, f351f, f352g, f353h, currentTimeMillis);
        }
    }
}
