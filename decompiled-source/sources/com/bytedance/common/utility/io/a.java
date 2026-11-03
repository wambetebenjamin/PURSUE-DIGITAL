package com.bytedance.common.utility.io;

import android.os.Environment;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import com.bytedance.librarian.c;
import com.google.common.base.Ascii;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
/* compiled from: FileUtils.java */
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5271a = "FileUtils";

    /* renamed from: b  reason: collision with root package name */
    private static final byte[] f5272b = {71, 73, 70, 56, 55, 97};

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f5273c = {71, 73, 70, 56, 57, 97};

    /* renamed from: d  reason: collision with root package name */
    private static final byte[] f5274d = {-1, -40, -1};

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f5275e = {-119, 80, 78, 71, Ascii.CR, 10, Ascii.SUB, 10};

    /* compiled from: FileUtils.java */
    /* renamed from: com.bytedance.common.utility.io.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public enum EnumC0075a {
        UNKNOWN,
        JPG,
        PNG,
        GIF
    }

    private a() {
    }

    public static InputStream getInputStream(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                return new FileInputStream(file);
            } catch (Exception e2) {
                try {
                    e2.printStackTrace();
                    return null;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    public static byte[] getByteArray(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int length = (int) file.length();
                byte[] bArr = new byte[length];
                for (int i2 = 0; i2 < length; i2++) {
                    bArr[i2] = (byte) fileInputStream.read();
                }
                fileInputStream.close();
                return bArr;
            } catch (Exception e2) {
                try {
                    e2.printStackTrace();
                    return null;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    public static boolean copyFile(String str, String str2, String str3) {
        FileInputStream fileInputStream;
        if (o.isEmpty(str) || o.isEmpty(str2) || o.isEmpty(str3)) {
            return false;
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean saveInputStream = saveInputStream(fileInputStream, str2, str3);
            try {
                fileInputStream.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            return saveInputStream;
        } catch (Exception e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            e.printStackTrace();
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean saveInputStream(InputStream inputStream, String str, String str2) {
        if (inputStream == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(str);
                if (!file.exists() && !file.mkdirs()) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    return false;
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, str2));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    }
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                    inputStream.close();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            return true;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return true;
                        }
                    }
                    return true;
                } catch (Exception e4) {
                    e = e4;
                    fileOutputStream = fileOutputStream2;
                    Logger.d(f5271a, "save inputstream error: " + e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused2) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e7) {
            e = e7;
        }
    }

    public static EnumC0075a getImageType(String str) {
        if (!o.isEmpty(str)) {
            return getImageType(new File(str));
        }
        return EnumC0075a.UNKNOWN;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static EnumC0075a getImageType(File file) {
        FileInputStream fileInputStream;
        byte[] bArr;
        if (file == null || !file.exists()) {
            return EnumC0075a.UNKNOWN;
        }
        EnumC0075a enumC0075a = EnumC0075a.UNKNOWN;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            bArr = new byte[8];
            fileInputStream.read(bArr);
        } catch (Exception e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            e.printStackTrace();
            if (fileInputStream2 != null) {
                fileInputStream2.close();
                fileInputStream2 = fileInputStream2;
            }
            return enumC0075a;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        if (!a(bArr, f5273c) && !a(bArr, f5272b)) {
            if (a(bArr, f5274d)) {
                EnumC0075a enumC0075a2 = EnumC0075a.JPG;
                try {
                    fileInputStream.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                return enumC0075a2;
            }
            byte[] bArr2 = f5275e;
            if (!a(bArr, bArr2)) {
                fileInputStream.close();
                fileInputStream2 = bArr2;
                return enumC0075a;
            }
            EnumC0075a enumC0075a3 = EnumC0075a.PNG;
            try {
                fileInputStream.close();
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            return enumC0075a3;
        }
        EnumC0075a enumC0075a4 = EnumC0075a.GIF;
        try {
            fileInputStream.close();
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        return enumC0075a4;
    }

    public static boolean isGif(File file) {
        return getImageType(file) == EnumC0075a.GIF;
    }

    public static boolean exists(String str) {
        if (o.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    private static boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static void chmod(String str, int i2) {
        Method method;
        int intValue;
        try {
            Class<?> cls = Class.forName("android.os.FileUtils");
            if (cls == null || (method = cls.getMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE)) == null || (intValue = ((Integer) method.invoke(null, str, Integer.valueOf(i2), -1, -1)).intValue()) == 0) {
                return;
            }
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() returned " + intValue + " for '" + str + "', probably didn't work.");
        } catch (ClassNotFoundException unused) {
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() failed - ClassNotFoundException.");
        } catch (IllegalAccessException unused2) {
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() failed - IllegalAccessException.");
        } catch (NoSuchMethodException unused3) {
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() failed - NoSuchMethodException.");
        } catch (InvocationTargetException unused4) {
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() failed - InvocationTargetException.");
        } catch (Throwable unused5) {
        }
    }

    public static String getFileNameWithoutExtension(String str) {
        int lastIndexOf;
        String name = new File(str).getName();
        return (o.isEmpty(name) || -1 == (lastIndexOf = name.lastIndexOf(c.a.DOT))) ? "" : name.substring(0, lastIndexOf);
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(c.a.DOT);
        return lastIndexOf == -1 ? "" : str.substring(lastIndexOf + 1);
    }

    public static void clearDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath());
                } else if (listFiles[i2].isFile()) {
                    listFiles[i2].delete();
                }
            }
        }
    }

    public static void removeDir(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath());
                } else {
                    listFiles[i2].delete();
                }
            }
            file.delete();
        }
    }

    public static void clearDir(String str, Set<String> set) {
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath(), set);
                } else if (listFiles[i2].isFile()) {
                    String name = listFiles[i2].getName();
                    if (set == null || !set.contains(name)) {
                        listFiles[i2].delete();
                    }
                }
            }
        }
    }

    public static void removeDir(String str, Set<String> set) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath(), set);
                } else {
                    String name = listFiles[i2].getName();
                    if (set == null || !set.contains(name)) {
                        listFiles[i2].delete();
                    }
                }
            }
        }
    }

    public static long getDirectorySize(File file, boolean z) {
        File[] listFiles;
        long length;
        long j2 = 0;
        if (file.exists() || !file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    length = getDirectorySize(file2, z);
                } else if (file2.isFile()) {
                    length = file2.length();
                }
                j2 += length;
            }
            return j2;
        }
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCacheDirPath(android.content.Context r3) {
        /*
            java.lang.String r0 = "Context is NUll"
            java.util.Objects.requireNonNull(r3, r0)
            r0 = 0
            java.io.File r1 = r3.getCacheDir()     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L16
            java.io.File r3 = r3.getCacheDir()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L3f
        L14:
            r0 = r3
            goto L3f
        L16:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r1.<init>()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "/data/data/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = r3.getPackageName()     // Catch: java.lang.Throwable -> L3f
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "/cache/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L3f
            r2 = 0
            java.io.File r3 = r3.getDir(r1, r2)     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L3f
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L3f
            goto L14
        L3f:
            boolean r3 = com.bytedance.common.utility.o.isEmpty(r0)
            if (r3 != 0) goto L46
            return r0
        L46:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Cannot Create Cache Dir"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.io.a.getCacheDirPath(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFilesDirPath(android.content.Context r3) {
        /*
            java.lang.String r0 = "Context is NUll"
            java.util.Objects.requireNonNull(r3, r0)
            r0 = 0
            java.io.File r1 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L16
            java.io.File r3 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L3f
        L14:
            r0 = r3
            goto L3f
        L16:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r1.<init>()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "/data/data/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = r3.getPackageName()     // Catch: java.lang.Throwable -> L3f
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "/files/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L3f
            r2 = 0
            java.io.File r3 = r3.getDir(r1, r2)     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L3f
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L3f
            goto L14
        L3f:
            boolean r3 = com.bytedance.common.utility.o.isEmpty(r0)
            if (r3 != 0) goto L46
            return r0
        L46:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Cannot Create Files Dir"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.io.a.getFilesDirPath(android.content.Context):java.lang.String");
    }

    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isExternalStorageReadable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }
}
