package com.bytedance.push.utils;

import android.util.Log;
import com.bytedance.security.Sword.Sword;
/* compiled from: EncryptUtils.java */
/* loaded from: classes2.dex */
public class d {
    public static byte[] decrypt(byte[] bArr) {
        return decrypt(bArr, 0);
    }

    public static byte[] decrypt(byte[] bArr, int i2) {
        if (bArr != null && bArr.length != 0) {
            try {
                return Sword.clientUnpackedBase64(new String(bArr, i2, bArr.length - i2));
            } catch (Throwable th) {
                h.e("decrypt error:" + Log.getStackTraceString(th));
            }
        }
        return null;
    }
}
