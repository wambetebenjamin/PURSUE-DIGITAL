package com.bytedance.crash.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipFile;
import org.json.JSONObject;
/* compiled from: FileSystemUtils.java */
/* loaded from: classes2.dex */
public class k {
    public static File a(File file, String str) {
        File file2 = new File(file, str);
        b(file2);
        return file2;
    }

    public static void a(File file) {
        if (file == null || file.exists()) {
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
    }

    public static File b(File file) {
        try {
            if (!file.exists()) {
                file.mkdirs();
            } else if (!file.isDirectory()) {
                file.delete();
                file.mkdirs();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return file;
    }

    public static File b(File file, String str) {
        File file2 = new File(file, str);
        if (file2.isDirectory()) {
            c(file2);
        }
        try {
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return file2;
    }

    public static void c(File file, String str) {
        try {
            m.writeFile(file, str, false);
        } catch (Exception unused) {
        }
    }

    public static void a(File file, String str, String str2) {
        try {
            m.writeFile(b(file, str), str2, false);
        } catch (Exception unused) {
        }
    }

    public static void a(File file, String str, JSONObject jSONObject) {
        try {
            m.writeFile(b(file, str), jSONObject, false);
        } catch (Exception unused) {
        }
    }

    public static void c(File file) {
        File[] listFiles;
        if (file.exists()) {
            if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    c(file2);
                }
            }
            try {
                m.deleteFile(file);
            } catch (Throwable unused) {
            }
        }
    }

    public static String d(File file) {
        FileInputStream fileInputStream;
        if (file.exists() && !file.isDirectory()) {
            int length = (int) file.length();
            byte[] bArr = new byte[length];
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable unused) {
                fileInputStream = null;
            }
            try {
                String str = new String(bArr, 0, fileInputStream.read(bArr, 0, length));
                try {
                    fileInputStream.close();
                } catch (IOException unused2) {
                }
                return str;
            } catch (Throwable unused3) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                return null;
            }
        }
        return null;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(ZipFile zipFile) {
        if (zipFile == null) {
            try {
                zipFile.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(String str, int i2) {
        try {
            s.a("android.os.FileUtils", "setPermissions", (Class<?>[]) new Class[]{String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE}).invoke(null, str, Integer.valueOf(i2), -1, -1);
        } catch (Throwable unused) {
        }
    }
}
