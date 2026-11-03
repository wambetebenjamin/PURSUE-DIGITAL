package com.bytedance.crash.util;

import java.util.concurrent.Callable;
import org.json.JSONArray;
/* compiled from: ActivityUtils.java */
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static Callable<JSONArray> f7274a;

    public static JSONArray getActivityTrack() {
        Callable<JSONArray> callable = f7274a;
        if (callable != null) {
            try {
                return callable.call();
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static void setActivityImpl(Callable<JSONArray> callable) {
        f7274a = callable;
    }
}
