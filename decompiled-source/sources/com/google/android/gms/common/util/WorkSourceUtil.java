package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public class WorkSourceUtil {
    private static final int zza = Process.myUid();
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;
    private static final Method zze;
    private static final Method zzf;
    private static final Method zzg;
    private static final Method zzh;
    private static final Method zzi;
    private static Boolean zzj;

    /* JADX WARN: Removed duplicated region for block: B:52:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            java.lang.String r0 = "add"
            int r1 = android.os.Process.myUid()
            com.google.android.gms.common.util.WorkSourceUtil.zza = r1
            r1 = 1
            r2 = 0
            r3 = 0
            java.lang.Class<android.os.WorkSource> r4 = android.os.WorkSource.class
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L18
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L18
            r5[r2] = r6     // Catch: java.lang.Exception -> L18
            java.lang.reflect.Method r4 = r4.getMethod(r0, r5)     // Catch: java.lang.Exception -> L18
            goto L19
        L18:
            r4 = r3
        L19:
            com.google.android.gms.common.util.WorkSourceUtil.zzb = r4
            r4 = 2
            java.lang.Class<android.os.WorkSource> r5 = android.os.WorkSource.class
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L2d
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L2d
            r6[r2] = r7     // Catch: java.lang.Exception -> L2d
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r1] = r7     // Catch: java.lang.Exception -> L2d
            java.lang.reflect.Method r0 = r5.getMethod(r0, r6)     // Catch: java.lang.Exception -> L2d
            goto L2e
        L2d:
            r0 = r3
        L2e:
            com.google.android.gms.common.util.WorkSourceUtil.zzc = r0
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r5 = "size"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> L3b
            java.lang.reflect.Method r0 = r0.getMethod(r5, r6)     // Catch: java.lang.Exception -> L3b
            goto L3c
        L3b:
            r0 = r3
        L3c:
            com.google.android.gms.common.util.WorkSourceUtil.zzd = r0
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r5 = "get"
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L4d
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L4d
            r6[r2] = r7     // Catch: java.lang.Exception -> L4d
            java.lang.reflect.Method r0 = r0.getMethod(r5, r6)     // Catch: java.lang.Exception -> L4d
            goto L4e
        L4d:
            r0 = r3
        L4e:
            com.google.android.gms.common.util.WorkSourceUtil.zze = r0
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r5 = "getName"
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L5f
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L5f
            r6[r2] = r7     // Catch: java.lang.Exception -> L5f
            java.lang.reflect.Method r0 = r0.getMethod(r5, r6)     // Catch: java.lang.Exception -> L5f
            goto L60
        L5f:
            r0 = r3
        L60:
            com.google.android.gms.common.util.WorkSourceUtil.zzf = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            java.lang.String r5 = "WorkSourceUtil"
            if (r0 == 0) goto L7b
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r6 = "createWorkChain"
            java.lang.Class[] r7 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> L75
            java.lang.reflect.Method r0 = r0.getMethod(r6, r7)     // Catch: java.lang.Exception -> L75
            goto L7c
        L75:
            r0 = move-exception
            java.lang.String r6 = "Missing WorkChain API createWorkChain"
            android.util.Log.w(r5, r6, r0)
        L7b:
            r0 = r3
        L7c:
            com.google.android.gms.common.util.WorkSourceUtil.zzg = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            if (r0 == 0) goto La1
            java.lang.String r0 = "android.os.WorkSource$WorkChain"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Exception -> L9b
            java.lang.String r6 = "addNode"
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L9b
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L9b
            r4[r2] = r7     // Catch: java.lang.Exception -> L9b
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r4[r1] = r7     // Catch: java.lang.Exception -> L9b
            java.lang.reflect.Method r0 = r0.getMethod(r6, r4)     // Catch: java.lang.Exception -> L9b
            goto La2
        L9b:
            r0 = move-exception
            java.lang.String r4 = "Missing WorkChain class"
            android.util.Log.w(r5, r4, r0)
        La1:
            r0 = r3
        La2:
            com.google.android.gms.common.util.WorkSourceUtil.zzh = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            if (r0 == 0) goto Lb8
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r4 = "isEmpty"
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> Lb8
            java.lang.reflect.Method r0 = r0.getMethod(r4, r2)     // Catch: java.lang.Exception -> Lb8
            r0.setAccessible(r1)     // Catch: java.lang.Exception -> Lb9
            goto Lb9
        Lb8:
            r0 = r3
        Lb9:
            com.google.android.gms.common.util.WorkSourceUtil.zzi = r0
            com.google.android.gms.common.util.WorkSourceUtil.zzj = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.WorkSourceUtil.<clinit>():void");
    }

    private WorkSourceUtil() {
    }

    public static void add(WorkSource workSource, int i2, String str) {
        Method method = zzc;
        if (method == null) {
            Method method2 = zzb;
            if (method2 != null) {
                try {
                    method2.invoke(workSource, Integer.valueOf(i2));
                    return;
                } catch (Exception e2) {
                    Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
                    return;
                }
            }
            return;
        }
        if (str == null) {
            str = "";
        }
        try {
            method.invoke(workSource, Integer.valueOf(i2), str);
        } catch (Exception e3) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e3);
        }
    }

    public static WorkSource fromPackage(Context context, String str) {
        if (context != null && context.getPackageManager() != null && str != null) {
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
                    return null;
                }
                int i2 = applicationInfo.uid;
                WorkSource workSource = new WorkSource();
                add(workSource, i2, str);
                return workSource;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
            }
        }
        return null;
    }

    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) {
        Method method;
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int i2 = -1;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
            } else {
                i2 = applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
        }
        if (i2 < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        Method method2 = zzg;
        if (method2 == null || (method = zzh) == null) {
            add(workSource, i2, str);
        } else {
            try {
                Object invoke = method2.invoke(workSource, new Object[0]);
                int i3 = zza;
                if (i2 != i3) {
                    method.invoke(invoke, Integer.valueOf(i2), str);
                }
                method.invoke(invoke, Integer.valueOf(i3), str2);
            } catch (Exception e2) {
                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e2);
            }
        }
        return workSource;
    }

    public static int get(WorkSource workSource, int i2) {
        Method method = zze;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, Integer.valueOf(i2));
                Preconditions.checkNotNull(invoke);
                return ((Integer) invoke).intValue();
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        }
        return 0;
    }

    public static String getName(WorkSource workSource, int i2) {
        Method method = zzf;
        if (method != null) {
            try {
                return (String) method.invoke(workSource, Integer.valueOf(i2));
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
                return null;
            }
        }
        return null;
    }

    public static List<String> getNames(WorkSource workSource) {
        ArrayList arrayList = new ArrayList();
        int size = workSource == null ? 0 : size(workSource);
        if (size != 0) {
            for (int i2 = 0; i2 < size; i2++) {
                String name = getName(workSource, i2);
                if (!Strings.isEmptyOrWhitespace(name)) {
                    Preconditions.checkNotNull(name);
                    arrayList.add(name);
                }
            }
        }
        return arrayList;
    }

    public static synchronized boolean hasWorkSourcePermission(Context context) {
        synchronized (WorkSourceUtil.class) {
            Boolean bool = zzj;
            if (bool != null) {
                return bool.booleanValue();
            }
            if (context == null) {
                return false;
            }
            Boolean valueOf = Boolean.valueOf(ContextCompat.checkSelfPermission(context, "android.permission.UPDATE_DEVICE_STATS") == 0);
            zzj = valueOf;
            return valueOf.booleanValue();
        }
    }

    public static boolean isEmpty(WorkSource workSource) {
        Method method = zzi;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, new Object[0]);
                Preconditions.checkNotNull(invoke);
                return ((Boolean) invoke).booleanValue();
            } catch (Exception e2) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e2);
            }
        }
        return size(workSource) == 0;
    }

    public static int size(WorkSource workSource) {
        Method method = zzd;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, new Object[0]);
                Preconditions.checkNotNull(invoke);
                return ((Integer) invoke).intValue();
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        }
        return 0;
    }
}
