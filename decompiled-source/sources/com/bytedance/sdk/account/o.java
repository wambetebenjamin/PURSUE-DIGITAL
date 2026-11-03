package com.bytedance.sdk.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Pair;
/* compiled from: CsrfTokenManager.java */
/* loaded from: classes2.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12678a = "CsrfTokenManager_sp";

    /* renamed from: b  reason: collision with root package name */
    public static final String f12679b = "csrf_token";

    /* renamed from: c  reason: collision with root package name */
    public static final String f12680c = "passport_csrf_token";

    /* renamed from: d  reason: collision with root package name */
    public static final String f12681d = "passport_csrf_token_default";

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f12682e;

    /* renamed from: f  reason: collision with root package name */
    private static SharedPreferences f12683f;

    /* renamed from: g  reason: collision with root package name */
    private static String f12684g;

    public static void a() {
        if (f12682e) {
            return;
        }
        synchronized (o.class) {
            if (!f12682e) {
                Context b2 = com.ss.android.token.d.b();
                if (b2 == null) {
                    return;
                }
                SharedPreferences sharedPreferences = b2.getSharedPreferences(f12678a, 0);
                f12683f = sharedPreferences;
                f12684g = sharedPreferences.getString(f12679b, null);
                f12682e = true;
            }
        }
    }

    public static void a(String str) {
        a();
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, f12684g)) {
            return;
        }
        f12684g = str;
        f12683f.edit().putString(f12679b, str).apply();
    }

    public static Pair<Integer, String> b(String str) {
        int i2;
        String c2 = c(str);
        if (TextUtils.isEmpty(c2)) {
            c2 = b();
            i2 = !TextUtils.isEmpty(c2) ? -2 : 0;
        } else {
            i2 = 1;
        }
        return new Pair<>(Integer.valueOf(i2), c2);
    }

    public static String c(String str) {
        String specialCookieInfo = com.bytedance.sdk.account.utils.l.getSpecialCookieInfo(str, "passport_csrf_token");
        return TextUtils.isEmpty(specialCookieInfo) ? com.bytedance.sdk.account.utils.l.getSpecialCookieInfo(str, "passport_csrf_token_default") : specialCookieInfo;
    }

    public static String b() {
        a();
        return f12684g;
    }

    public static boolean c() {
        a();
        return !TextUtils.isEmpty(f12684g);
    }

    public static String d() {
        boolean z = !TextUtils.isEmpty(b());
        boolean z2 = !TextUtils.isEmpty(c(com.ss.android.token.d.d()));
        return (z && z2) ? "both" : z ? "sp" : z2 ? com.bytedance.frameworks.baselib.network.http.a.f7602g : "none";
    }
}
