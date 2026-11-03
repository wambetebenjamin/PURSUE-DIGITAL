package com.bytedance.sdk.account.bpea;

import android.content.Context;
import android.text.TextUtils;
/* compiled from: BpeaManager.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12309a = "CookieManager";

    /* renamed from: b  reason: collision with root package name */
    public static final String f12310b = "getNetworkType";

    /* renamed from: c  reason: collision with root package name */
    public static final String f12311c = "getCookie";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12312d = "bpea-account_request_api";

    /* renamed from: e  reason: collision with root package name */
    private static boolean f12313e = true;

    /* renamed from: f  reason: collision with root package name */
    private static b f12314f;

    private static b b() {
        if (f12314f == null) {
            synchronized (a.class) {
                if (f12314f == null) {
                    try {
                        f12314f = (b) Class.forName("com.bytedance.sdk.account.adapter.bpea.AccountSdkBpeaAdapter").newInstance();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return f12314f;
    }

    public static boolean a() {
        return b() != null;
    }

    public static void a(boolean z) {
        f12313e = z;
    }

    public static Object a(Context context, String str, String str2, Object... objArr) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (a() && f12313e) {
            return b().a(context, str, str2, f12312d, objArr);
        }
        return b(context, str, str2, objArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:?, code lost:
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object b(android.content.Context r5, java.lang.String r6, java.lang.String r7, java.lang.Object... r8) {
        /*
            int r0 = r6.hashCode()     // Catch: java.lang.Throwable -> L7d
            r1 = -854125431(0xffffffffcd171489, float:-1.5841909E8)
            r2 = 1
            r3 = -1
            r4 = 0
            if (r0 == r1) goto L1c
            r1 = 106642798(0x65b3d6e, float:4.1234453E-35)
            if (r0 == r1) goto L12
            goto L26
        L12:
            java.lang.String r0 = "phone"
            boolean r0 = r6.equals(r0)     // Catch: java.lang.Throwable -> L7d
            if (r0 == 0) goto L26
            r0 = r4
            goto L27
        L1c:
            java.lang.String r0 = "CookieManager"
            boolean r0 = r6.equals(r0)     // Catch: java.lang.Throwable -> L7d
            if (r0 == 0) goto L26
            r0 = r2
            goto L27
        L26:
            r0 = r3
        L27:
            if (r0 == 0) goto L52
            if (r0 == r2) goto L2c
            goto L81
        L2c:
            android.webkit.CookieManager r5 = android.webkit.CookieManager.getInstance()     // Catch: java.lang.Throwable -> L7d
            if (r5 != 0) goto L33
            goto L81
        L33:
            int r6 = r7.hashCode()     // Catch: java.lang.Throwable -> L7d
            r0 = 341257562(0x14572d5a, float:1.086367E-26)
            if (r6 == r0) goto L3d
            goto L46
        L3d:
            java.lang.String r6 = "getCookie"
            boolean r6 = r7.equals(r6)     // Catch: java.lang.Throwable -> L7d
            if (r6 == 0) goto L46
            r3 = r4
        L46:
            if (r3 == 0) goto L49
            goto L81
        L49:
            r6 = r8[r4]     // Catch: java.lang.Throwable -> L7d
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Throwable -> L7d
            java.lang.String r5 = r5.getCookie(r6)     // Catch: java.lang.Throwable -> L7d
            return r5
        L52:
            if (r5 != 0) goto L55
            goto L81
        L55:
            java.lang.Object r5 = r5.getSystemService(r6)     // Catch: java.lang.Throwable -> L7d
            android.telephony.TelephonyManager r5 = (android.telephony.TelephonyManager) r5     // Catch: java.lang.Throwable -> L7d
            if (r5 != 0) goto L5e
            goto L81
        L5e:
            int r6 = r7.hashCode()     // Catch: java.lang.Throwable -> L7d
            r8 = 1714085202(0x662add52, float:2.0172124E23)
            if (r6 == r8) goto L68
            goto L71
        L68:
            java.lang.String r6 = "getNetworkType"
            boolean r6 = r7.equals(r6)     // Catch: java.lang.Throwable -> L7d
            if (r6 == 0) goto L71
            r3 = r4
        L71:
            if (r3 == 0) goto L74
            goto L81
        L74:
            int r5 = r5.getNetworkType()     // Catch: java.lang.Throwable -> L7d
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L7d
            return r5
        L7d:
            r5 = move-exception
            r5.printStackTrace()
        L81:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.account.bpea.a.b(android.content.Context, java.lang.String, java.lang.String, java.lang.Object[]):java.lang.Object");
    }
}
