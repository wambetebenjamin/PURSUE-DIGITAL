package com.ttnet.org.chromium.base;
/* loaded from: classes9.dex */
public class GpuUtils {
    private static String TAG = "GpuUtils";
    private static String sGlRenderer = "unknown";
    private static String sGlVendor = "unknown";
    private static final Object sGpuInfoLock = new Object();
    private static boolean sInitialized;

    /* JADX WARN: Removed duplicated region for block: B:137:0x0141 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0176 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x002e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean initialize() {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ttnet.org.chromium.base.GpuUtils.initialize():boolean");
    }

    public static String getGPUVendor() {
        String str;
        synchronized (sGpuInfoLock) {
            str = sGlVendor;
        }
        return str;
    }

    public static String getGPUModel() {
        String str;
        synchronized (sGpuInfoLock) {
            str = sGlRenderer;
        }
        return str;
    }
}
