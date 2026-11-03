package com.ss.android.deviceregister;

import android.content.Context;
import android.util.Pair;
/* compiled from: GaidGetter.java */
/* loaded from: classes9.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static volatile Pair<String, Boolean> f18219a = null;

    /* renamed from: b  reason: collision with root package name */
    private static final String f18220b = "gaid_limited";

    public static Pair<String, Boolean> getGaid(Context context) {
        if (f18219a == null) {
            synchronized (i.class) {
                if (f18219a == null) {
                    f18219a = com.ss.android.deviceregister.utils.d.getGaid(context);
                }
            }
        }
        return f18219a;
    }

    public static boolean isInitGaid() {
        return f18219a != null;
    }

    public static void clearSp(Context context) {
        com.ss.android.deviceregister.base.a.getApplogStatsSp(context).edit().remove("google_aid").remove("gaid_limited").apply();
    }
}
