package com.bytedance.pipo.sec;
/* loaded from: classes2.dex */
public class CryptTool {
    static {
        try {
            System.loadLibrary("ppsec");
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static native byte[] decrypt(byte[] bArr);

    public static native byte[] encrypt(byte[] bArr);

    public static native String getKid();
}
