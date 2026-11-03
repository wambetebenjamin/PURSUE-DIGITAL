package com.bytedance.pipo.sec;
/* loaded from: classes2.dex */
public final class PipoProtect {
    private static UserConfig CONFIG = null;
    private static boolean GP_DISABLED = false;
    private static final String NOT_INITIALIZED_EXCEPTION = "PipoProtect Not Initialized!";
    private static String mHOST = "";
    private static boolean sIsUnInitialized = true;

    public static String getPayEnv() {
        if (sIsUnInitialized) {
            throw new RuntimeException(NOT_INITIALIZED_EXCEPTION);
        }
        return IlIlllIiI.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x017a A[Catch: IOException -> 0x01ab, all -> 0x01b9, TryCatch #6 {IOException -> 0x01ab, blocks: (B:44:0x0132, B:46:0x013c, B:47:0x0140, B:52:0x016f, B:54:0x017a, B:55:0x0192, B:48:0x0145, B:51:0x014c), top: B:77:0x0132, outer: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0192 A[Catch: IOException -> 0x01ab, all -> 0x01b9, TRY_LEAVE, TryCatch #6 {IOException -> 0x01ab, blocks: (B:44:0x0132, B:46:0x013c, B:47:0x0140, B:52:0x016f, B:54:0x017a, B:55:0x0192, B:48:0x0145, B:51:0x014c), top: B:77:0x0132, outer: #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void init(com.bytedance.pipo.sec.UserConfig r9) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pipo.sec.PipoProtect.init(com.bytedance.pipo.sec.UserConfig):void");
    }

    public static synchronized void reportRiskInfo(String str) {
        synchronized (PipoProtect.class) {
            if (GP_DISABLED) {
                return;
            }
            if (sIsUnInitialized) {
                throw new RuntimeException(NOT_INITIALIZED_EXCEPTION);
            }
            IlIlllIiI.a(str);
        }
    }

    public static synchronized void setDeviceInfo(String str, String str2) {
        synchronized (PipoProtect.class) {
            if (GP_DISABLED) {
                return;
            }
            if (!str.isEmpty()) {
                IlIlllIiI.a(str, str2);
            }
        }
    }

    public static synchronized void setEnabled(boolean z) {
        synchronized (PipoProtect.class) {
            GP_DISABLED = !z;
        }
    }

    public static synchronized void setHost(String str) {
        synchronized (PipoProtect.class) {
            if (str.isEmpty()) {
                return;
            }
            mHOST = str;
        }
    }
}
