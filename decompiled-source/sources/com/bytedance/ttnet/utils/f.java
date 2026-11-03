package com.bytedance.ttnet.utils;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.i;
import com.bytedance.common.utility.o;
import com.bytedance.librarian.c;
import com.bytedance.retrofit2.Retrofit;
import com.bytedance.retrofit2.intercept.Interceptor;
import java.util.Collection;
import java.util.List;
import java.util.Map;
/* compiled from: TtnetUtil.java */
/* loaded from: classes8.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15541a = "TtnetUtil";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f15542b = false;

    /* renamed from: c  reason: collision with root package name */
    private static final int f15543c = 4;

    private f() {
    }

    public static void a(Context context, Class<? extends BroadcastReceiver> cls, boolean z) {
        int i2 = z ? 1 : 2;
        try {
            if (Logger.debug()) {
                Logger.d(f15541a, (z ? "enabling" : "disabling") + " connectivity receiver");
            }
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls), i2, 1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
        if (r2 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.Throwable r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L4
            return r0
        L4:
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch: java.lang.Throwable -> L2d
            r1.<init>()     // Catch: java.lang.Throwable -> L2d
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch: java.lang.Throwable -> L2d
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L2d
            r3.printStackTrace(r2)     // Catch: java.lang.Throwable -> L2b
            java.lang.Throwable r3 = r3.getCause()     // Catch: java.lang.Throwable -> L2b
            if (r3 == 0) goto L23
            r3.printStackTrace(r2)     // Catch: java.lang.Throwable -> L2b
            java.lang.Throwable r3 = r3.getCause()     // Catch: java.lang.Throwable -> L2b
            if (r3 == 0) goto L23
            r3.printStackTrace(r2)     // Catch: java.lang.Throwable -> L2b
        L23:
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L2b
        L27:
            r2.close()
            goto L35
        L2b:
            r3 = move-exception
            goto L2f
        L2d:
            r3 = move-exception
            r2 = r0
        L2f:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L36
            if (r2 == 0) goto L35
            goto L27
        L35:
            return r0
        L36:
            r3 = move-exception
            if (r2 == 0) goto L3c
            r2.close()
        L3c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttnet.utils.f.a(java.lang.Throwable):java.lang.String");
    }

    public static boolean a(String str, String str2) {
        if (o.isEmpty(str) || o.isEmpty(str2)) {
            return false;
        }
        if (str.equalsIgnoreCase(str2)) {
            return true;
        }
        String lowerCase = str.toLowerCase();
        if (str2.startsWith(c.a.DOT)) {
            return lowerCase.endsWith(str2.toLowerCase());
        }
        return lowerCase.endsWith(c.a.DOT + str2.toLowerCase());
    }

    public static boolean a(String str, List<String> list) {
        if (!o.isEmpty(str) && !i.isEmpty(list)) {
            for (String str2 : list) {
                if (!o.isEmpty(str2)) {
                    if (com.bytedance.frameworks.baselib.network.config.b.e()) {
                        if (str.endsWith(str2)) {
                            return true;
                        }
                    } else if (a(str, str2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void b(String str, List<String> list) {
        String[] split;
        if (o.isEmpty(str) || list == null) {
            return;
        }
        for (String str2 : str.split(",")) {
            if (!o.isEmpty(str2) && !a(str2, list)) {
                list.add(str2.trim());
            }
        }
    }

    public static void a(com.bytedance.frameworks.baselib.network.http.util.i<String, Retrofit> iVar, Interceptor interceptor) {
        if (iVar == null || iVar.b() <= 0) {
            return;
        }
        Map<String, Retrofit> j2 = iVar.j();
        Collection<Retrofit> values = j2.values();
        synchronized (j2) {
            for (Retrofit retrofit : values) {
                List<Interceptor> interceptors = retrofit.interceptors();
                if (interceptors != null && !interceptors.contains(interceptor)) {
                    interceptors.add(interceptor);
                }
            }
        }
    }

    public static void b(com.bytedance.frameworks.baselib.network.http.util.i<String, Retrofit> iVar, Interceptor interceptor) {
        if (iVar == null || iVar.b() <= 0) {
            return;
        }
        Map<String, Retrofit> j2 = iVar.j();
        Collection<Retrofit> values = j2.values();
        synchronized (j2) {
            for (Retrofit retrofit : values) {
                List<Interceptor> interceptors = retrofit.interceptors();
                if (interceptors != null) {
                    interceptors.remove(interceptor);
                }
            }
        }
    }

    public static void a(boolean z) {
        f15542b = z;
    }

    public static boolean a() {
        return f15542b;
    }

    public static void b(boolean z) {
        com.bytedance.frameworks.baselib.network.http.util.a.a(z);
    }

    public static void a(String str) {
        com.bytedance.frameworks.baselib.network.http.util.a.c(str);
    }

    public static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        return a(context, broadcastReceiver, intentFilter, null, null, 4);
    }

    public static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Handler handler) {
        return a(context, broadcastReceiver, intentFilter, null, handler, 4);
    }

    private static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i2);
        }
        return context.registerReceiver(broadcastReceiver, intentFilter, str, handler);
    }
}
