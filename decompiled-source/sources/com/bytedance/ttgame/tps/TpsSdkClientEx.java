package com.bytedance.ttgame.tps;
/* loaded from: classes8.dex */
public final class TpsSdkClientEx {
    public static final int TPSSDK_CRYPTO_AES_CBC_128 = 2;
    public static final int TPSSDK_CRYPTO_AES_CBC_192 = 3;
    public static final int TPSSDK_CRYPTO_AES_CBC_256 = 4;
    public static final int TPSSDK_CRYPTO_RC4 = 1;

    private static final native void TpsSdkClose();

    private static final native byte[] TpsSdkDecrypt(byte[] bArr);

    private static final native byte[] TpsSdkEncrypt(byte[] bArr);

    private static final native int TpsSdkGetLastError();

    private static final native int TpsSdkInit(byte[] bArr, int i2);

    private static final native String TpsSdkVersion();

    public static synchronized int Init(byte[] bArr, int i2) {
        int TpsSdkInit;
        synchronized (TpsSdkClientEx.class) {
            TpsSdkInit = TpsSdkInit(bArr, i2);
        }
        return TpsSdkInit;
    }

    public static synchronized int GetLastError() {
        int TpsSdkGetLastError;
        synchronized (TpsSdkClientEx.class) {
            TpsSdkGetLastError = TpsSdkGetLastError();
        }
        return TpsSdkGetLastError;
    }

    public static synchronized void Close() {
        synchronized (TpsSdkClientEx.class) {
            TpsSdkClose();
        }
    }

    public static byte[] Encrypt(byte[] bArr) {
        return TpsSdkEncrypt(bArr);
    }

    public static byte[] Decrypt(byte[] bArr) {
        return TpsSdkDecrypt(bArr);
    }

    public static String Version() {
        return TpsSdkVersion();
    }

    static {
        System.loadLibrary("ttgame_tps_client");
    }
}
