package com.oasis.android;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes9.dex */
public class FileUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a() {
        return ActivityManager.getActivity().getExternalFilesDir(null).getParent() + "/files/cache/";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(String str) {
        return a(str, false);
    }

    private static boolean a(String str, boolean z) {
        File[] listFiles;
        try {
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    if (!(file2.isFile() ? file2.delete() : a(file2.getAbsolutePath(), true))) {
                        return false;
                    }
                }
                if (z) {
                    if (!file.delete()) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isFileExist(String str) {
        return new File(str).exists();
    }

    public static String readToString(InputStream inputStream) {
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            return new String(bArr);
        } catch (IOException unused) {
            return null;
        }
    }
}
