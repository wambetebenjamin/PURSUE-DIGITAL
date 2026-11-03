package com.bytedance.common.jato.jit;

import android.os.Build;
import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class JitCodeCacheMProtectOpt {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4766a = Build.VERSION.SDK_INT;

    private static native void nStartPharse1(long j2, long j3);

    private static native void nStartPharse2();

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0049, code lost:
        r2 = r10.split("\\s+")[0].split("-");
        r3 = r2[0];
        r2 = r2[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005b, code lost:
        if (r3.startsWith("0x") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005d, code lost:
        r3 = r3.substring(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0065, code lost:
        if (r2.startsWith("0x") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0067, code lost:
        r2 = r2.substring(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0108, code lost:
        r2 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a() {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.jato.jit.JitCodeCacheMProtectOpt.a():void");
    }

    public static void b() {
        int i2 = f4766a;
        if (i2 < 24 || i2 > 30 || !d.loadLibrary()) {
            return;
        }
        nStartPharse2();
    }
}
