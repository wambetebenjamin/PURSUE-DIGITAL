package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public class ImportantFileWriterAndroid {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        boolean writeFileAtomically(String str, byte[] bArr);
    }

    public static boolean writeFileAtomically(String str, byte[] bArr) {
        return ImportantFileWriterAndroidJni.get().writeFileAtomically(str, bArr);
    }
}
