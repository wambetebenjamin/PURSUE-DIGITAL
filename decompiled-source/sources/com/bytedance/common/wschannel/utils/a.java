package com.bytedance.common.wschannel.utils;

import android.content.Context;
import com.bytedance.common.utility.Logger;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
/* compiled from: DiskStorageUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5999a = "a";

    /* renamed from: b  reason: collision with root package name */
    private static File f6000b = null;

    /* renamed from: c  reason: collision with root package name */
    private static File f6001c = null;

    /* renamed from: d  reason: collision with root package name */
    private static final String f6002d = "wschannel";

    /* renamed from: e  reason: collision with root package name */
    private static final String f6003e = "_frontier.bin";

    private static File b(Context context) {
        File file = f6001c;
        if (file != null) {
            return file;
        }
        File file2 = new File(context.getFilesDir(), f6002d);
        f6001c = file2;
        if (!file2.exists()) {
            f6001c.mkdirs();
        }
        return f6001c;
    }

    public static synchronized File a(Context context) {
        synchronized (a.class) {
            File file = f6000b;
            if (file != null) {
                return file;
            }
            try {
                File file2 = new File(b(context), e.b(context).replace(c.a.DOT, "_").replace(CertificateUtil.DELIMITER, "-") + f6003e);
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                f6000b = file2;
                if (Logger.debug()) {
                    Logger.d(f5999a, "prepare PersistentFile success. fileName=" + f6000b);
                }
            } catch (Exception e2) {
                Logger.e(f5999a, "prepare PersistentFile fail.", e2);
            }
            return f6000b;
        }
    }
}
