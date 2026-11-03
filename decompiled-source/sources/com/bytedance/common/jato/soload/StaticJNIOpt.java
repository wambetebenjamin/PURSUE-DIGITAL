package com.bytedance.common.jato.soload;

import android.content.Context;
import android.util.Log;
import com.bytedance.common.jato.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class StaticJNIOpt {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5017a = "jni_method_profile/";

    private static native void nativeInitStaticJNIOpt();

    private static native void nativeLoadMethodProfile(String str);

    static {
        d.loadLibrary();
    }

    public static void a(Context context, String str, String str2) {
        File file = new File(context.getFilesDir(), f5017a);
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
        File file2 = null;
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file3 : listFiles) {
                if (file3.getName().equals(str2)) {
                    file2 = file3;
                } else {
                    file3.delete();
                }
            }
        }
        if (file2 == null) {
            file2 = new File(file, str2);
        }
        if (!file2.exists()) {
            try {
                InputStream open = context.getAssets().open(str);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
                open.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        nativeLoadMethodProfile(file2.getAbsolutePath());
    }

    public static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            nativeLoadMethodProfile(file.getAbsolutePath());
        } else {
            Log.e("Jato", "cannot fount static jni profile");
        }
    }

    public static void a() {
        nativeInitStaticJNIOpt();
    }
}
