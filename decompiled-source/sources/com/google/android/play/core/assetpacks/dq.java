package com.google.android.play.core.assetpacks;

import android.util.Base64;
import com.oasis.android.AppTool;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class dq {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(List list) throws NoSuchAlgorithmException, IOException {
        int read;
        MessageDigest messageDigest = MessageDigest.getInstance(AppTool.SHA256);
        byte[] bArr = new byte[8192];
        Iterator it = list.iterator();
        while (it.hasNext()) {
            FileInputStream fileInputStream = new FileInputStream((File) it.next());
            do {
                try {
                    read = fileInputStream.read(bArr);
                    if (read > 0) {
                        messageDigest.update(bArr, 0, read);
                    }
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        try {
                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                        } catch (Exception unused) {
                        }
                    }
                    throw th;
                }
            } while (read != -1);
            fileInputStream.close();
        }
        return Base64.encodeToString(messageDigest.digest(), 11);
    }
}
