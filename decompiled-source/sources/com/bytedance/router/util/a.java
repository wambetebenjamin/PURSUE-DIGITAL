package com.bytedance.router.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import com.facebook.cache.disk.DefaultDiskStorage;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
/* compiled from: ClassUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12229a = ".classes";

    /* renamed from: b  reason: collision with root package name */
    private static final String f12230b = ".zip";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12231c = "code_cache" + File.separator + "secondary-dexes";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12232d = "multidex.version";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12233e = "dex.number";

    /* renamed from: f  reason: collision with root package name */
    private static final int f12234f = 2;

    /* renamed from: g  reason: collision with root package name */
    private static final int f12235g = 1;

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences(f12232d, Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    public static List<String> getFileNameByPackageName(Context context, String str) {
        DexFile dexFile;
        ArrayList arrayList = new ArrayList();
        for (String str2 : getSourcePaths(context)) {
            if (str2.endsWith(f12230b)) {
                dexFile = DexFile.loadDex(str2, str2 + DefaultDiskStorage.FileType.TEMP, 0);
            } else {
                dexFile = new DexFile(str2);
            }
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()) {
                String nextElement = entries.nextElement();
                if (nextElement.contains(str)) {
                    arrayList.add(nextElement);
                }
            }
        }
        Log.d("galaxy", "Filter " + arrayList.size() + " classes by packageName <" + str + ">");
        return arrayList;
    }

    public static List<String> getSourcePaths(Context context) {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        ArrayList arrayList = new ArrayList();
        arrayList.add(applicationInfo.sourceDir);
        String str = file.getName() + f12229a;
        if (!a()) {
            int i2 = a(context).getInt(f12233e, 1);
            File file2 = new File(applicationInfo.dataDir, f12231c);
            for (int i3 = 2; i3 <= i2; i3++) {
                File file3 = new File(file2, str + i3 + f12230b);
                if (file3.isFile()) {
                    arrayList.add(file3.getAbsolutePath());
                } else {
                    throw new IOException("Missing extracted secondary dex file '" + file3.getPath() + "'");
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004e, code lost:
        if (r2 < 1) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001b, code lost:
        if (java.lang.Integer.valueOf(java.lang.System.getProperty("ro.build.version.sdk")).intValue() >= 21) goto L7;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a() {
        /*
            r0 = 0
            r1 = 0
            boolean r2 = b()     // Catch: java.lang.Throwable -> L51
            r3 = 1
            if (r2 == 0) goto L1f
            java.lang.String r1 = "'YunOS'"
            java.lang.String r2 = "ro.build.version.sdk"
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch: java.lang.Throwable -> L51
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L51
            int r2 = r2.intValue()     // Catch: java.lang.Throwable -> L51
            r4 = 21
            if (r2 < r4) goto L51
        L1d:
            r0 = r3
            goto L51
        L1f:
            java.lang.String r1 = "'Android'"
            java.lang.String r2 = "java.vm.version"
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch: java.lang.Throwable -> L51
            if (r2 == 0) goto L51
            java.lang.String r4 = "(\\d+)\\.(\\d+)(\\.\\d+)?"
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch: java.lang.Throwable -> L51
            java.util.regex.Matcher r2 = r4.matcher(r2)     // Catch: java.lang.Throwable -> L51
            boolean r4 = r2.matches()     // Catch: java.lang.Throwable -> L51
            if (r4 == 0) goto L51
            java.lang.String r4 = r2.group(r3)     // Catch: java.lang.Throwable -> L51
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.Throwable -> L51
            r5 = 2
            java.lang.String r2 = r2.group(r5)     // Catch: java.lang.Throwable -> L51
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.Throwable -> L51
            if (r4 > r5) goto L1d
            if (r4 != r5) goto L51
            if (r2 < r3) goto L51
            goto L1d
        L51:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "VM with name "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            if (r0 == 0) goto L65
            java.lang.String r2 = " has multidex support"
            goto L67
        L65:
            java.lang.String r2 = " does not have multidex support"
        L67:
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "galaxy"
            android.util.Log.i(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.router.util.a.a():boolean");
    }

    private static boolean b() {
        try {
            String property = System.getProperty("ro.yunos.version");
            String property2 = System.getProperty("java.vm.name");
            if (property2 == null || !property2.toLowerCase().contains("lemur")) {
                if (property == null) {
                    return false;
                }
                if (property.trim().length() <= 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
