package com.bytedance.frameworks.core.encrypt;

import com.bytedance.frameworks.encryptor.EncryptorUtil;
/* compiled from: TTEncryptUtils.java */
/* loaded from: classes2.dex */
public class c {
    public static volatile boolean sUseNewEncrypt = true;

    public static byte[] encrypt(byte[] bArr, int i2) {
        return EncryptorUtil.encrypt(bArr, i2);
    }
}
