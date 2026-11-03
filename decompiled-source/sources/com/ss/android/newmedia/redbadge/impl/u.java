package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* compiled from: OPPOHomeBaderV2.java */
/* loaded from: classes9.dex */
public class u extends com.ss.android.newmedia.redbadge.h {

    /* renamed from: b  reason: collision with root package name */
    private static final String f18445b = "content://com.android.badge/badge";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18446c = "com.oppo.unsettledevent";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18447d = "pakeageName";

    /* renamed from: e  reason: collision with root package name */
    private static final String f18448e = "number";

    /* renamed from: f  reason: collision with root package name */
    private static final String f18449f = "upgradeNumber";

    /* renamed from: g  reason: collision with root package name */
    private static final String f18450g = "app_badge_count";

    /* renamed from: h  reason: collision with root package name */
    private static int f18451h = -1;

    /* renamed from: a  reason: collision with root package name */
    private final String f18452a = "OPPOHomeBader";

    /* renamed from: i  reason: collision with root package name */
    private int f18453i = Integer.MIN_VALUE;

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (this.f18453i <= 99 || i2 <= 99) {
            this.f18453i = i2;
            Intent intent = new Intent(f18446c);
            intent.putExtra(f18447d, componentName.getPackageName());
            intent.putExtra("number", i2);
            intent.putExtra(f18449f, i2);
            if (com.ss.android.newmedia.redbadge.util.a.canResolveBroadcast(context, intent)) {
                try {
                    context.sendBroadcast(intent);
                    if (i2 > 0 || Build.VERSION.SDK_INT >= 23) {
                        return;
                    }
                    Intent intent2 = (Intent) intent.clone();
                    intent2.putExtra("number", -1);
                    intent2.putExtra(f18449f, -1);
                    context.sendBroadcast(intent2);
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
                }
            }
            Bundle bundle = new Bundle();
            bundle.putInt(f18450g, i2);
            context.getContentResolver().call(Uri.parse(f18445b), "setAppBadgeCount", (String) null, bundle);
            if (i2 > 0 || Build.VERSION.SDK_INT >= 23) {
                return;
            }
            Bundle bundle2 = (Bundle) bundle.clone();
            bundle2.putInt(f18450g, -1);
            context.getContentResolver().call(Uri.parse(f18445b), "setAppBadgeCount", (String) null, bundle2);
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        String str = Build.MANUFACTURER;
        if (com.bytedance.push.utils.n.isColorOS() || (!TextUtils.isEmpty(str) && str.contains("realme"))) {
            return Arrays.asList("com.oppo.launcher", "com.android.launcher");
        }
        return Collections.singletonList("com.oppo.launcher");
    }

    private int b() {
        int i2;
        int i3 = f18451h;
        if (i3 >= 0) {
            return i3;
        }
        try {
            i2 = ((Integer) a(a("com.color.os.ColorBuild"), "getColorOSVERSION", null, null)).intValue();
        } catch (Exception unused) {
            i2 = 0;
        }
        if (i2 == 0) {
            try {
                String b2 = b("ro.build.version.opporom");
                if (b2.startsWith("V1.4")) {
                    return 3;
                }
                if (b2.startsWith("V2.0")) {
                    return 4;
                }
                if (b2.startsWith("V2.1")) {
                    return 5;
                }
            } catch (Exception unused2) {
            }
        }
        f18451h = i2;
        return i2;
    }

    private Object a(Class cls, String str, Class[] clsArr, Object[] objArr) {
        Method a2;
        if (cls == null || a((Object) str) || (a2 = a(cls, str, clsArr)) == null) {
            return null;
        }
        a2.setAccessible(true);
        try {
            return a2.invoke(null, objArr);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private Method a(Class cls, String str, Class[] clsArr) {
        if (cls == null || a((Object) str)) {
            return null;
        }
        try {
            try {
                cls.getMethods();
                cls.getDeclaredMethods();
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Throwable unused) {
                return cls.getMethod(str, clsArr);
            }
        } catch (Throwable unused2) {
            if (cls.getSuperclass() != null) {
                return a(cls.getSuperclass(), str, clsArr);
            }
            return null;
        }
    }

    private Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private boolean a(Object obj) {
        return obj == null || obj.toString().equals("") || obj.toString().trim().equals("null");
    }

    private String b(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                com.ss.android.newmedia.redbadge.util.b.closeQuietly(bufferedReader);
                return readLine;
            } catch (IOException unused) {
                com.ss.android.newmedia.redbadge.util.b.closeQuietly(bufferedReader);
                return null;
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                com.ss.android.newmedia.redbadge.util.b.closeQuietly(bufferedReader2);
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.ss.android.newmedia.redbadge.h
    public int a(Context context, ComponentName componentName) {
        if (context == null || componentName == null) {
            com.bytedance.push.utils.h.d("OPPOHomeBader", "args is null");
            return -1;
        }
        Bundle bundle = new Bundle();
        bundle.putString("app_badge_packageName", context.getPackageName());
        Bundle call = Build.VERSION.SDK_INT >= 11 ? context.getContentResolver().call(Uri.parse(f18445b), "getAppBadgeCount", (String) null, bundle) : null;
        if (call != null) {
            return call.getInt(f18450g);
        }
        return -1;
    }
}
