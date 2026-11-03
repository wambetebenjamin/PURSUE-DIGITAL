package com.bytedance.bdinstall.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.bdinstall.am;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Constants.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4017a = "ug_install_settings_pref";

    /* renamed from: b  reason: collision with root package name */
    public static final long f4018b = 900000;

    /* renamed from: c  reason: collision with root package name */
    public static final String f4019c = "com.bytedance.bdinstall.nu.NUModeServiceImpl";

    /* renamed from: d  reason: collision with root package name */
    public static final String f4020d = "snssdk_openudid";

    /* renamed from: e  reason: collision with root package name */
    public static final int f4021e = 4;

    /* renamed from: f  reason: collision with root package name */
    private static final r<SharedPreferences> f4022f = new r<SharedPreferences>() { // from class: com.bytedance.bdinstall.util.a.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public SharedPreferences b(Object... objArr) {
            return ((Context) objArr[0]).getSharedPreferences(a.f4017a, 0);
        }
    };

    public static SharedPreferences a(Context context, am amVar) {
        if (amVar != null) {
            return amVar.p() ? a(context) : a(context, String.valueOf(amVar.a()));
        }
        return a(context);
    }

    public static SharedPreferences a(Context context) {
        return f4022f.c(context);
    }

    public static SharedPreferences a(Context context, String str) {
        return context.getSharedPreferences("ug_install_settings_pref_" + str, 0);
    }

    public static String a(String str, com.bytedance.bdinstall.c cVar) {
        if (cVar.b()) {
            str = str + "_i18n";
        }
        if (cVar.c()) {
            str = str + "_boe";
        }
        return cVar.d() ? str + "_cm" : str;
    }

    public static String a(com.bytedance.bdinstall.c cVar) {
        return a(f4017a, cVar);
    }

    public static String a(com.bytedance.bdinstall.c cVar, String str) {
        return a(f4017a, cVar) + "_" + str;
    }
}
