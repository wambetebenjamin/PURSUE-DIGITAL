package com.bytedance.geckox.utils;

import android.os.Build;
import android.os.Process;
import com.bytedance.librarian.c;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
/* compiled from: FileUtils.java */
/* loaded from: classes2.dex */
public class f {
    public static boolean delete(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "delete file，pid:", Integer.valueOf(Process.myPid()), ", thread:", Thread.currentThread().toString(), ", file:" + file.getAbsolutePath());
        return a(file);
    }

    private static boolean a(File file) {
        boolean z;
        File[] listFiles;
        if (file == null || !file.exists()) {
            return true;
        }
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            z = true;
        } else {
            z = true;
            for (File file2 : listFiles) {
                z = z && a(file2);
            }
        }
        return z && file.delete();
    }

    public static void a(File file, File file2) {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        file2.mkdir();
        if (file.renameTo(new File(file2, file.getName()))) {
            return;
        }
        try {
            b(file, file2);
        } catch (Exception e2) {
            throw new RuntimeException("move file failed：" + e2.getMessage(), e2);
        }
    }

    public static void b(File file, File file2) {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        if (file.getParent().equals(file2.getAbsolutePath())) {
            return;
        }
        if (file2.getAbsolutePath().startsWith(file.getAbsolutePath())) {
            throw new RuntimeException("copy failed，src:" + file.getAbsolutePath() + " dest:" + file2.getAbsolutePath());
        }
        file2.mkdir();
        if (file.isFile()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file2, file.getName()));
            try {
                a(fileInputStream, fileOutputStream);
            } finally {
                d.close(fileInputStream);
                d.close(fileOutputStream);
            }
        } else if (file.isDirectory()) {
            File file3 = new File(file2, file.getName());
            file3.mkdirs();
            for (File file4 : file.listFiles()) {
                b(file4, file3);
            }
        }
    }

    public static long a(InputStream inputStream, OutputStream outputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] bArr = new byte[1024];
        int i2 = 0;
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                i2 += read;
                bufferedOutputStream.write(bArr, 0, read);
            } else {
                bufferedOutputStream.flush();
                return i2;
            }
        }
    }

    public static List<File> b(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return null;
        }
        List<File> asList = Arrays.asList(listFiles);
        final HashMap hashMap = new HashMap();
        for (File file2 : asList) {
            hashMap.put(file2, Long.valueOf(file2.lastModified()));
        }
        Collections.sort(asList, new Comparator<File>() { // from class: com.bytedance.geckox.utils.f.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(File file3, File file4) {
                if (file3 == null && file4 == null) {
                    return 0;
                }
                if (file3 == null) {
                    return 1;
                }
                if (file4 == null) {
                    return -1;
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    return Long.compare(((Long) hashMap.get(file3)).longValue(), ((Long) hashMap.get(file4)).longValue());
                }
                int i2 = (((Long) hashMap.get(file3)).longValue() > ((Long) hashMap.get(file4)).longValue() ? 1 : (((Long) hashMap.get(file3)).longValue() == ((Long) hashMap.get(file4)).longValue() ? 0 : -1));
                if (i2 < 0) {
                    return -1;
                }
                return i2 == 0 ? 0 : 1;
            }
        });
        return asList;
    }

    public static boolean c(File file) {
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!c(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static void a(String str, String str2) {
        File file;
        try {
            new File(str2).mkdirs();
            String[] list = new File(str).list();
            for (int i2 = 0; i2 < list.length; i2++) {
                if (str.endsWith(File.separator)) {
                    file = new File(str + list[i2]);
                } else {
                    file = new File(str + File.separator + list[i2]);
                }
                if (file.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2 + c.a.SEPARATOR + file.getName());
                    byte[] bArr = new byte[65536];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                }
                if (file.isDirectory()) {
                    a(str + c.a.SEPARATOR + list[i2], str2 + c.a.SEPARATOR + list[i2]);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            com.bytedance.geckox.logger.b.e(com.bytedance.geckox.d.f8984a, "copy folder error:", e2);
        }
    }

    public static void b(String str, String str2) {
        a(str, str2);
        c(new File(str));
    }

    public static String d(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    return d(listFiles[0]);
                }
                return null;
            } else if (file.length() == 0) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }
}
