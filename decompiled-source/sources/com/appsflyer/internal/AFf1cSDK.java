package com.appsflyer.internal;

import android.os.Process;
import android.text.TextUtils;
import android.view.ViewConfiguration;
import com.bytedance.geckox.b;
import kotlin.text.Typography;
/* loaded from: classes.dex */
public final class AFf1cSDK {
    private static int $10 = 0;
    private static int $11 = 1;
    private static int AFInAppEventParameterName = 0;
    private static int AFInAppEventType = 1;
    private static long AFKeystoreWrapper;
    private static char[] valueOf;

    static {
        AFKeystoreWrapper();
        TextUtils.indexOf((CharSequence) "", '0', 0, 0);
        TextUtils.lastIndexOf("", '0', 0, 0);
        ViewConfiguration.getPressedStateDuration();
        int i2 = AFInAppEventParameterName + 1;
        AFInAppEventType = i2 % 128;
        int i3 = i2 % 2;
    }

    static void AFKeystoreWrapper() {
        valueOf = new char[]{6229, 30102, 50135, 20764, 44889, 15519, 35523, 6149, 30285, 50052, 20943, 44862, 15728, 35509, 6392, 30265, 50276, 20899, 45028, 15658, 35688, 6304, 30352, 50395, 21018, 41053, 15772, 35763, 6404, 30530, 50313, 21194, 41008, 15987, 35765, 6651, 30524, 50440, 21153, 41188, 15912, 35864, 6573, 30694, 50645, 21264, 41311, 16031, 36033, 6657, 26691, 50568, 21453, 41230, 16246, 36018, 6905, 26700, 50810, 21410, 41446, 16162, 36206, 6874};
        AFKeystoreWrapper = 2927107144130647763L;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.appsflyer.internal.AFh1hSDK valueOf(com.appsflyer.internal.AFh1nSDK r6, java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r5 = this;
            int r0 = com.appsflyer.internal.AFf1cSDK.AFInAppEventType
            int r1 = r0 + 119
            int r2 = r1 % 128
            com.appsflyer.internal.AFf1cSDK.AFInAppEventParameterName = r2
            int r1 = r1 % 2
            r1 = 43
            if (r6 == 0) goto L10
            r2 = r1
            goto L12
        L10:
            r2 = 46
        L12:
            r3 = 1
            r4 = 0
            if (r2 == r1) goto L17
            goto L31
        L17:
            if (r8 == 0) goto L1b
            r1 = r4
            goto L1c
        L1b:
            r1 = r3
        L1c:
            if (r1 == r3) goto L31
            r1 = 51
            if (r9 == 0) goto L25
            r2 = 19
            goto L26
        L25:
            r2 = r1
        L26:
            if (r2 == r1) goto L31
            int r0 = r0 + 63
            int r1 = r0 % 128
            com.appsflyer.internal.AFf1cSDK.AFInAppEventParameterName = r1
            int r0 = r0 % 2
            goto L32
        L31:
            r3 = r4
        L32:
            if (r3 != 0) goto L3c
            com.appsflyer.internal.AFh1hSDK r6 = new com.appsflyer.internal.AFh1hSDK
            com.appsflyer.internal.AFh1fSDK r7 = com.appsflyer.internal.AFh1fSDK.INTERNAL_ERROR
            r6.<init>(r4, r7)
            return r6
        L3c:
            com.appsflyer.internal.AFh1hSDK r6 = values(r6, r7, r8, r9)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1cSDK.valueOf(com.appsflyer.internal.AFh1nSDK, java.lang.String, java.lang.String, java.lang.String):com.appsflyer.internal.AFh1hSDK");
    }

    private static AFh1hSDK values(AFh1nSDK aFh1nSDK, String str, String str2, String str3) {
        if (str == null) {
            return new AFh1hSDK(aFh1nSDK.valueOf == AFh1pSDK.DEFAULT, AFh1fSDK.NA);
        }
        String str4 = "";
        Object[] objArr = new Object[1];
        a(TextUtils.indexOf((CharSequence) "", '0', 0) + 65, (char) ((ViewConfiguration.getGlobalActionKeyTimeout() > 0L ? 1 : (ViewConfiguration.getGlobalActionKeyTimeout() == 0L ? 0 : -1)) + 9077), (Process.getThreadPriority(0) + 20) >> 6, objArr);
        String intern = ((String) objArr[0]).intern();
        if (aFh1nSDK.valueOf == AFh1pSDK.CUSTOM) {
            str4 = new StringBuilder(str2).reverse().toString();
        } else {
            str3 = intern;
        }
        boolean equals = AFInAppEventParameterName(new StringBuilder(str3).reverse().toString(), aFh1nSDK.AFInAppEventParameterName, "android", b.a.f8886a, str4).equals(str);
        return new AFh1hSDK(equals, equals ? AFh1fSDK.SUCCESS : AFh1fSDK.FAILURE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0055, code lost:
        if ((r8.length() < 12) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0057, code lost:
        r9 = com.appsflyer.internal.AFf1cSDK.AFInAppEventType + 123;
        com.appsflyer.internal.AFf1cSDK.AFInAppEventParameterName = r9 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0060, code lost:
        if ((r9 % 2) == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0062, code lost:
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0063, code lost:
        r9.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0066, code lost:
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0069, code lost:
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006e, code lost:
        return r8.substring(0, 12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0034, code lost:
        if (r8.length() < 79) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String AFInAppEventParameterName(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            int r0 = com.appsflyer.internal.AFf1cSDK.AFInAppEventParameterName
            int r0 = r0 + 27
            int r1 = r0 % 128
            com.appsflyer.internal.AFf1cSDK.AFInAppEventType = r1
            r1 = 2
            int r0 = r0 % r1
            r2 = 1
            r3 = 0
            if (r0 != 0) goto L10
            r0 = r2
            goto L11
        L10:
            r0 = r3
        L11:
            r4 = 12
            java.lang.String r5 = "\u2063"
            java.lang.String r6 = ""
            r7 = 5
            if (r0 == 0) goto L37
            java.lang.String[] r0 = new java.lang.String[r7]
            r0[r2] = r9
            r0[r3] = r10
            r0[r7] = r11
            r0[r1] = r12
            r0[r7] = r6
            java.lang.String r9 = android.text.TextUtils.join(r5, r0)
            java.lang.String r8 = com.appsflyer.internal.AFb1mSDK.AFInAppEventParameterName(r9, r8)
            int r9 = r8.length()
            r10 = 79
            if (r9 >= r10) goto L6a
            goto L57
        L37:
            java.lang.String[] r0 = new java.lang.String[r7]
            r0[r3] = r9
            r0[r2] = r10
            r0[r1] = r11
            r9 = 3
            r0[r9] = r12
            r9 = 4
            r0[r9] = r6
            java.lang.String r9 = android.text.TextUtils.join(r5, r0)
            java.lang.String r8 = com.appsflyer.internal.AFb1mSDK.AFInAppEventParameterName(r9, r8)
            int r9 = r8.length()
            if (r9 >= r4) goto L54
            goto L55
        L54:
            r2 = r3
        L55:
            if (r2 == 0) goto L6a
        L57:
            int r9 = com.appsflyer.internal.AFf1cSDK.AFInAppEventType
            int r9 = r9 + 123
            int r10 = r9 % 128
            com.appsflyer.internal.AFf1cSDK.AFInAppEventParameterName = r10
            int r9 = r9 % r1
            if (r9 == 0) goto L69
            r9 = 0
            r9.hashCode()     // Catch: java.lang.Throwable -> L67
            return r8
        L67:
            r8 = move-exception
            throw r8
        L69:
            return r8
        L6a:
            java.lang.String r8 = r8.substring(r3, r4)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1cSDK.AFInAppEventParameterName(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    private static void a(int i2, char c2, int i3, Object[] objArr) {
        int i4;
        AFj1qSDK aFj1qSDK = new AFj1qSDK();
        long[] jArr = new long[i2];
        aFj1qSDK.AFInAppEventParameterName = 0;
        while (true) {
            if (aFj1qSDK.AFInAppEventParameterName >= i2) {
                break;
            }
            jArr[aFj1qSDK.AFInAppEventParameterName] = (((char) (valueOf[i3 + aFj1qSDK.AFInAppEventParameterName] ^ (-2584935672999232752L))) ^ (aFj1qSDK.AFInAppEventParameterName * ((-2584935672999232752L) ^ AFKeystoreWrapper))) ^ c2;
            aFj1qSDK.AFInAppEventParameterName++;
            int i5 = $11 + 103;
            $10 = i5 % 128;
            int i6 = i5 % 2;
        }
        char[] cArr = new char[i2];
        aFj1qSDK.AFInAppEventParameterName = 0;
        while (aFj1qSDK.AFInAppEventParameterName < i2) {
            int i7 = $11 + 69;
            $10 = i7 % 128;
            if ((i7 % 2 != 0 ? Typography.quote : '3') != '3') {
                cArr[aFj1qSDK.AFInAppEventParameterName] = (char) jArr[aFj1qSDK.AFInAppEventParameterName];
                i4 = aFj1qSDK.AFInAppEventParameterName % 1;
            } else {
                cArr[aFj1qSDK.AFInAppEventParameterName] = (char) jArr[aFj1qSDK.AFInAppEventParameterName];
                i4 = aFj1qSDK.AFInAppEventParameterName + 1;
            }
            aFj1qSDK.AFInAppEventParameterName = i4;
        }
        objArr[0] = new String(cArr);
    }
}
