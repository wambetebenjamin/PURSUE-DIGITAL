package com.bytedance.bdinstall;
/* compiled from: IEncryptor.java */
/* loaded from: classes.dex */
public interface ad {
    byte[] a(byte[] bArr, int i2);

    /* compiled from: IEncryptor.java */
    /* loaded from: classes.dex */
    public static class a implements ad {
        @Override // com.bytedance.bdinstall.ad
        public byte[] a(byte[] bArr, int i2) {
            try {
                return com.bytedance.frameworks.core.encrypt.c.encrypt(bArr, i2);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }
}
