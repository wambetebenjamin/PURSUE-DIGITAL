package com.bytedance.ttnet.debug;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.internal.ServerProtocol;
/* compiled from: DebugSetting.java */
/* loaded from: classes8.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static String f15260a = "ttnet_debug_setting";

    /* renamed from: b  reason: collision with root package name */
    private static String f15261b = "log_switcher";

    /* renamed from: c  reason: collision with root package name */
    private static String f15262c = "x86_support";

    private static String a(Context context, String str) {
        if (context != null) {
            return context.getSharedPreferences(f15260a, 0).getString(str, null);
        }
        return null;
    }

    private static void a(Context context, String str, String str2) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(f15260a, 0).edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    public static void a(Context context, boolean z) {
        a(context, f15261b, String.valueOf(z));
    }

    public static boolean a(Context context) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(a(context, f15261b));
    }

    public static void b(Context context, boolean z) {
        a(context, f15262c, String.valueOf(z));
    }

    public static boolean b(Context context) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(a(context, f15262c));
    }
}
