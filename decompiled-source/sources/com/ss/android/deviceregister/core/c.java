package com.ss.android.deviceregister.core;

import android.content.Context;
import android.content.SharedPreferences;
import com.ss.android.deviceregister.j;
import com.ss.android.deviceregister.utils.f;
/* compiled from: PrivateAgreement.java */
/* loaded from: classes9.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18170a = "_install_started_v2";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18171b = "ug_install_settings_pref";

    /* renamed from: c  reason: collision with root package name */
    private static boolean f18172c;

    /* renamed from: d  reason: collision with root package name */
    private static f<SharedPreferences> f18173d = new f<SharedPreferences>() { // from class: com.ss.android.deviceregister.core.c.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ss.android.deviceregister.utils.f
        /* renamed from: a */
        public SharedPreferences b(Object... objArr) {
            return ((Context) objArr[0]).getSharedPreferences("ug_install_settings_pref", 0);
        }
    };

    public static boolean hasAccept(Context context) {
        if (j.isI18n()) {
            return true;
        }
        if (context == null) {
            return false;
        }
        if (f18172c) {
            return true;
        }
        return a(context).getBoolean("_install_started_v2", false);
    }

    private static SharedPreferences a(Context context) {
        return f18173d.get(context);
    }

    public static void setAccepted(final Context context) {
        f18172c = true;
        com.bytedance.common.utility.concurrent.e.submitRunnable(new Runnable() { // from class: com.ss.android.deviceregister.core.c.2
            @Override // java.lang.Runnable
            public void run() {
                ((SharedPreferences) c.f18173d.get(context)).edit().putBoolean("_install_started_v2", true).apply();
            }
        });
    }
}
