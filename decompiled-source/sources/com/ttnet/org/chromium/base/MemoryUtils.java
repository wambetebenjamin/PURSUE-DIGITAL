package com.ttnet.org.chromium.base;
/* loaded from: classes9.dex */
public class MemoryUtils {
    private static String TAG = "MemoryUtils";
    private static boolean sInitialized = false;
    private static String sMemoryInfoPath = "/proc/meminfo";
    private static final Object sMemoryLock = new Object();
    private static String sTotalMemoryGB = "unknown";

    public static boolean initialize() {
        if (sInitialized) {
            return true;
        }
        fetchTotalMemory();
        sInitialized = true;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
        r10 = java.lang.Long.parseLong(r3.group(1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
        if (r10 > 1024) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
        android.util.Log.w(com.ttnet.org.chromium.base.MemoryUtils.TAG, "Invalid " + com.ttnet.org.chromium.base.MemoryUtils.sMemoryInfoPath + " total size in kB: " + r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x005e, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005f, code lost:
        r3 = r9;
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0063, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0064, code lost:
        r3 = r9;
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0067, code lost:
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0068, code lost:
        r9.close();
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0091, code lost:
        android.util.Log.w(com.ttnet.org.chromium.base.MemoryUtils.TAG, "Cannot get total physical size from " + com.ttnet.org.chromium.base.MemoryUtils.sMemoryInfoPath, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ab, code lost:
        if (r3 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ad, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b0, code lost:
        if (r8 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b2, code lost:
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b7, code lost:
        monitor-enter(com.ttnet.org.chromium.base.MemoryUtils.sMemoryLock);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bb, code lost:
        com.ttnet.org.chromium.base.MemoryUtils.sTotalMemoryGB = java.lang.String.valueOf((int) java.lang.Math.ceil((r1 * 1.0d) / 1048576.0d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c7, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00cc, code lost:
        if (r3 != null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ce, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d1, code lost:
        if (r8 != null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d3, code lost:
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d8, code lost:
        monitor-enter(com.ttnet.org.chromium.base.MemoryUtils.sMemoryLock);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00dc, code lost:
        com.ttnet.org.chromium.base.MemoryUtils.sTotalMemoryGB = java.lang.String.valueOf((int) java.lang.Math.ceil((r1 * 1.0d) / 1048576.0d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00e8, code lost:
        throw r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void fetchTotalMemory() {
        /*
            Method dump skipped, instructions count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ttnet.org.chromium.base.MemoryUtils.fetchTotalMemory():void");
    }

    public static String getTotalMemoryGB() {
        String str;
        synchronized (sMemoryLock) {
            str = sTotalMemoryGB;
        }
        return str;
    }
}
