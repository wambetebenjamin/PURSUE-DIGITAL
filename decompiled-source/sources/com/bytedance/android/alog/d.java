package com.bytedance.android.alog;

import android.content.Context;
import android.text.TextUtils;
/* compiled from: Util.java */
/* loaded from: classes.dex */
class d {
    d() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            str = null;
        }
        return TextUtils.isEmpty(str) ? "unknown" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0036, code lost:
        if (r0 == null) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(android.content.Context r4) {
        /*
            r4 = 0
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L35
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L35
            java.lang.String r2 = "/proc/self/cmdline"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L35
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L35
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L36
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L36
            if (r2 != 0) goto L25
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L36
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L36
            if (r2 != 0) goto L25
            r0.close()     // Catch: java.lang.Exception -> L24
        L24:
            return r1
        L25:
            r0.close()     // Catch: java.lang.Exception -> L39
            goto L39
        L29:
            r4 = move-exception
            goto L2f
        L2b:
            r0 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
        L2f:
            if (r0 == 0) goto L34
            r0.close()     // Catch: java.lang.Exception -> L34
        L34:
            throw r4
        L35:
            r0 = r4
        L36:
            if (r0 == 0) goto L39
            goto L25
        L39:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.android.alog.d.b(android.content.Context):java.lang.String");
    }
}
