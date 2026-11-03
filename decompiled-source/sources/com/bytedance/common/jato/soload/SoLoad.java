package com.bytedance.common.jato.soload;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.bytedance.common.jato.c;
import com.bytedance.common.jato.d;
import com.bytedance.librarian.c;
import java.io.File;
/* loaded from: classes2.dex */
public class SoLoad {

    /* renamed from: a  reason: collision with root package name */
    private static String f5013a = "SoLoad";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f5014b;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f5015c;

    /* renamed from: d  reason: collision with root package name */
    private static c f5016d;

    private static native long dlopenInternal(String str, boolean z);

    private static native int initSoLoadInternal(int i2);

    private static native void soLoadOptStartInternal();

    private static native void soLoadOptStopInternal();

    private static boolean d() {
        if (!f5014b || f5015c) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 26) {
            Log.d(f5013a, "features.So load support greater than android o(26)");
            return false;
        } else if (Build.VERSION.SDK_INT > 30) {
            Log.d(f5013a, "features.So load support greater than android R(30)");
            return false;
        } else {
            return true;
        }
    }

    public static synchronized void a() {
        synchronized (SoLoad.class) {
            if (!f5014b && !f5015c) {
                if (d.loadLibrary()) {
                    if (Build.VERSION.SDK_INT < 26) {
                        Log.d(f5013a, "features.So load support greater than android o(26)");
                    } else if (Build.VERSION.SDK_INT > 30) {
                        Log.d(f5013a, "features.So load support greater than android R(30)");
                    } else {
                        int initSoLoadInternal = initSoLoadInternal(Build.VERSION.SDK_INT);
                        if (initSoLoadInternal != 0) {
                            c cVar = f5016d;
                            if (cVar != null) {
                                cVar.onDebugInfo("init soload failed:" + initSoLoadInternal);
                            }
                            f5015c = true;
                        }
                        f5014b = true;
                    }
                }
            }
        }
    }

    public static void b() {
        if (d()) {
            soLoadOptStartInternal();
        }
    }

    public static void c() {
        if (d()) {
            soLoadOptStopInternal();
        }
    }

    public static long a(Context context, String str) {
        if (d()) {
            String packageCodePath = context.getPackageCodePath();
            if (packageCodePath.indexOf("base.apk") > 0) {
                boolean z = false;
                String substring = packageCodePath.substring(0, packageCodePath.length() - 8);
                String str2 = substring + "lib/arm64/lib" + str + c.a.SO_SUFFIX;
                Log.d("SoLoad", "sopath:" + str2);
                Log.d("SoLoad", "CPU ABI:" + Build.CPU_ABI);
                if (!new File(str2).exists()) {
                    if (new File(substring + "lib/arm").exists()) {
                        String str3 = substring + "lib/arm/lib" + str + c.a.SO_SUFFIX;
                        Log.d("SoLoad", "32 bits not support");
                        return 0L;
                    } else if ("arm64-v8a".equals(Build.CPU_ABI)) {
                        z = true;
                        str2 = packageCodePath + "!/lib/arm64-v8a/lib" + str + c.a.SO_SUFFIX;
                        Log.d("SoLoad", "sopath:" + str2);
                    } else {
                        Log.d("SoLoad", "32 bits not support");
                        return 0L;
                    }
                }
                return dlopenInternal(str2, z);
            }
        }
        return 0L;
    }

    public static void a(com.bytedance.common.jato.c cVar) {
        f5016d = cVar;
    }
}
