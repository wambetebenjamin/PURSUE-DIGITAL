package com.bytedance.common.jato.dex;

import android.os.Build;
import android.os.Process;
import android.util.Log;
/* compiled from: OdexLoadUtils.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4658a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4659b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4660c = 2;

    /* renamed from: d  reason: collision with root package name */
    private String f4661d;

    /* renamed from: e  reason: collision with root package name */
    private long f4662e;

    /* renamed from: f  reason: collision with root package name */
    private int f4663f = 0;

    /* renamed from: g  reason: collision with root package name */
    private boolean f4664g;

    public b(String str, long j2, boolean z) {
        this.f4662e = 1500L;
        this.f4664g = true;
        this.f4661d = str;
        this.f4664g = z;
        if (z) {
            this.f4662e = j2;
        } else {
            this.f4662e = j2 / 2;
        }
    }

    public int a(long j2) {
        if (Build.VERSION.SDK_INT >= 24 && b(j2)) {
            long currentTimeMillis = System.currentTimeMillis();
            boolean a2 = a("/proc/" + Process.myPid() + "/maps");
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (a2) {
                this.f4663f = 1;
            } else {
                this.f4663f = 2;
            }
            Log.e("OdexLoadUtils", "load odex:" + a2 + ", find use time:" + currentTimeMillis2);
        } else {
            this.f4663f = 0;
        }
        return this.f4663f;
    }

    public int a() {
        return this.f4663f;
    }

    private boolean b(long j2) {
        return j2 - Process.getStartUptimeMillis() >= this.f4662e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006a A[Catch: IOException -> 0x0044, TRY_ENTER, TryCatch #3 {IOException -> 0x0044, blocks: (B:18:0x003a, B:40:0x006a, B:42:0x006f, B:44:0x0074), top: B:58:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006f A[Catch: IOException -> 0x0044, TryCatch #3 {IOException -> 0x0044, blocks: (B:18:0x003a, B:40:0x006a, B:42:0x006f, B:44:0x0074), top: B:58:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0074 A[Catch: IOException -> 0x0044, TRY_LEAVE, TryCatch #3 {IOException -> 0x0044, blocks: (B:18:0x003a, B:40:0x006a, B:42:0x006f, B:44:0x0074), top: B:58:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0083 A[Catch: IOException -> 0x007f, TryCatch #7 {IOException -> 0x007f, blocks: (B:48:0x007b, B:52:0x0083, B:54:0x0088), top: B:62:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0088 A[Catch: IOException -> 0x007f, TRY_LEAVE, TryCatch #7 {IOException -> 0x007f, blocks: (B:48:0x007b, B:52:0x0083, B:54:0x0088), top: B:62:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = r6.f4661d
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 == 0) goto La
            return r1
        La:
            r0 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L60
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L60
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            r7.<init>(r2)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
        L1a:
            java.lang.String r0 = r3.readLine()     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L78
            if (r0 == 0) goto L3a
            java.lang.String r4 = "/base.odex"
            boolean r4 = r0.contains(r4)     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L78
            if (r4 == 0) goto L1a
            java.lang.String r4 = r6.f4661d     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L78
            boolean r4 = r0.contains(r4)     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L78
            if (r4 == 0) goto L1a
            java.lang.String r4 = "files"
            boolean r0 = r0.contains(r4)     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L78
            if (r0 != 0) goto L1a
            r0 = 1
            r1 = r0
        L3a:
            r3.close()     // Catch: java.io.IOException -> L44
            r7.close()     // Catch: java.io.IOException -> L44
            r2.close()     // Catch: java.io.IOException -> L44
            goto L77
        L44:
            r7 = move-exception
            r7.printStackTrace()
            goto L77
        L49:
            r0 = move-exception
            goto L65
        L4b:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L79
        L4f:
            r3 = move-exception
            r5 = r3
            r3 = r0
            r0 = r5
            goto L65
        L54:
            r7 = move-exception
            r3 = r0
            goto L5d
        L57:
            r7 = move-exception
            r3 = r0
            goto L63
        L5a:
            r7 = move-exception
            r2 = r0
            r3 = r2
        L5d:
            r0 = r7
            r7 = r3
            goto L79
        L60:
            r7 = move-exception
            r2 = r0
            r3 = r2
        L63:
            r0 = r7
            r7 = r3
        L65:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L78
            if (r3 == 0) goto L6d
            r3.close()     // Catch: java.io.IOException -> L44
        L6d:
            if (r7 == 0) goto L72
            r7.close()     // Catch: java.io.IOException -> L44
        L72:
            if (r2 == 0) goto L77
            r2.close()     // Catch: java.io.IOException -> L44
        L77:
            return r1
        L78:
            r0 = move-exception
        L79:
            if (r3 == 0) goto L81
            r3.close()     // Catch: java.io.IOException -> L7f
            goto L81
        L7f:
            r7 = move-exception
            goto L8c
        L81:
            if (r7 == 0) goto L86
            r7.close()     // Catch: java.io.IOException -> L7f
        L86:
            if (r2 == 0) goto L8f
            r2.close()     // Catch: java.io.IOException -> L7f
            goto L8f
        L8c:
            r7.printStackTrace()
        L8f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.jato.dex.b.a(java.lang.String):boolean");
    }
}
