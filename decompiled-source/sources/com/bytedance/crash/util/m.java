package com.bytedance.crash.util;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: FileUtils.java */
/* loaded from: classes2.dex */
public class m {
    public static void writeFile(File file, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
            try {
                fileOutputStream2.write(str.getBytes());
                fileOutputStream2.flush();
                k.a(fileOutputStream2);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                k.a(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void b(File file) {
        FileOutputStream fileOutputStream;
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file, false);
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write("".getBytes());
            fileOutputStream.flush();
            k.a(fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            k.a(fileOutputStream2);
            throw th;
        }
    }

    public static void writeFile(File file, JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return;
        }
        file.getParentFile().mkdirs();
        BufferedWriter bufferedWriter = null;
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, z));
            try {
                r.writeTo(jSONObject, bufferedWriter2);
                k.a(bufferedWriter2);
            } catch (Throwable unused) {
                bufferedWriter = bufferedWriter2;
                k.a(bufferedWriter);
            }
        } catch (Throwable unused2) {
        }
    }

    public static void writeFile(File file, JSONArray jSONArray, boolean z) {
        if (jSONArray == null) {
            return;
        }
        file.getParentFile().mkdirs();
        BufferedWriter bufferedWriter = null;
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, z));
            try {
                r.writeTo(jSONArray, bufferedWriter2);
                k.a(bufferedWriter2);
            } catch (Throwable unused) {
                bufferedWriter = bufferedWriter2;
                k.a(bufferedWriter);
            }
        } catch (Throwable unused2) {
        }
    }

    public static void a(File file, JSONArray jSONArray, boolean z) {
        FileOutputStream fileOutputStream;
        if (file == null) {
            return;
        }
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream2 = null;
        try {
            if (jSONArray == null) {
                FileOutputStream fileOutputStream3 = new FileOutputStream(file, false);
                try {
                    FileOutputStream fileOutputStream4 = fileOutputStream3;
                    fileOutputStream3.write("".getBytes());
                    FileOutputStream fileOutputStream5 = fileOutputStream3;
                    fileOutputStream3.flush();
                    fileOutputStream = fileOutputStream3;
                } catch (Throwable unused) {
                    fileOutputStream2 = fileOutputStream3;
                    k.a(fileOutputStream2);
                    return;
                }
            } else {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, z));
                try {
                    BufferedWriter bufferedWriter2 = bufferedWriter;
                    r.writeTo(jSONArray, bufferedWriter);
                    fileOutputStream = bufferedWriter;
                } catch (Throwable unused2) {
                    fileOutputStream2 = bufferedWriter;
                    k.a(fileOutputStream2);
                    return;
                }
            }
            k.a(fileOutputStream);
        } catch (Throwable unused3) {
        }
    }

    public static boolean deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return deleteFile(new File(str));
    }

    public static boolean deleteFile(File file) {
        boolean deleteFile;
        boolean z = true;
        if (file.exists()) {
            if (file.canWrite()) {
                if (file.isFile()) {
                    return file.delete();
                }
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    for (int i2 = 0; listFiles != null && i2 < listFiles.length; i2++) {
                        if (listFiles[i2].isFile()) {
                            if (listFiles[i2].canWrite()) {
                                deleteFile = listFiles[i2].delete();
                            } else {
                                z = false;
                            }
                        } else {
                            deleteFile = deleteFile(listFiles[i2]);
                        }
                        z &= deleteFile;
                    }
                    return z & file.delete();
                }
                return true;
            }
            return false;
        }
        return true;
    }

    public static String readFile(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return readFile(new File(str), str2);
    }

    public static String readFile(File file, String str) {
        return readFile(file, str, -1L);
    }

    public static String readFile(File file, String str, long j2) {
        if (file == null || !file.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            if (j2 > 0) {
                try {
                    bufferedReader2.skip(j2);
                    bufferedReader2.readLine();
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    k.a(bufferedReader);
                    throw th;
                }
            }
            while (true) {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    if (sb.length() != 0 && str != null) {
                        sb.append(str);
                    }
                    sb.append(readLine);
                } else {
                    k.a(bufferedReader2);
                    return sb.toString();
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static JSONArray readFileArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return readFileArray(new File(str), -1L);
    }

    public static JSONArray readFileArray(File file) {
        return readFileArray(file, -1L);
    }

    public static JSONArray readFileArray(File file, long j2) {
        JSONArray jSONArray = new JSONArray();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            if (j2 > 0) {
                try {
                    bufferedReader2.skip(j2);
                    bufferedReader2.readLine();
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    k.a(bufferedReader);
                    throw th;
                }
            }
            while (true) {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    jSONArray.put(readLine);
                } else {
                    k.a(bufferedReader2);
                    return jSONArray;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isEmpty(File file) {
        String[] list = file.list();
        return list == null || list.length == 0;
    }

    public static boolean isEmpty(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    public static String readFile(String str) {
        return readFile(str, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
    }

    public static String tryReadFile(File file) {
        try {
            return readFile(file);
        } catch (IOException unused) {
            return null;
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return readFile(new File(str), ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String readFile(File file) {
        return readFile(file, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
    }

    public static String readFile(File file, long j2) {
        return readFile(file, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, j2);
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0041: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:21:0x0041 */
    public static Map<String, String> readPropertiesFile(File file) {
        FileInputStream fileInputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            try {
                Properties properties = new Properties();
                fileInputStream = new FileInputStream(file);
                try {
                    properties.load(fileInputStream);
                    Set<String> stringPropertyNames = properties.stringPropertyNames();
                    HashMap hashMap = new HashMap();
                    for (String str : stringPropertyNames) {
                        hashMap.put(str, properties.getProperty(str));
                    }
                    k.a(fileInputStream);
                    return hashMap;
                } catch (IOException e2) {
                    e = e2;
                    y.w(e);
                    k.a(fileInputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                closeable2 = closeable;
                k.a(closeable2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            k.a(closeable2);
            throw th;
        }
    }

    public static void writePropertiesFile(File file, Map<String, String> map) {
        Properties properties;
        FileOutputStream fileOutputStream;
        if (map == null || map.isEmpty()) {
            return;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                properties = new Properties();
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue());
            }
            properties.store(fileOutputStream, "no");
            k.a(fileOutputStream);
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            y.w(e);
            k.a(fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            k.a(fileOutputStream2);
            throw th;
        }
    }

    public static boolean isEmpty(File[] fileArr) {
        return fileArr == null || fileArr.length == 0;
    }

    public static void copy(File file, File file2) {
        copy(file, file2, Integer.MAX_VALUE);
    }

    public static void copyDir(File file, File file2) {
        String[] list;
        if (file.exists()) {
            if (file.isFile()) {
                if (file2.exists()) {
                    if (file2.isDirectory()) {
                        return;
                    }
                    file2.delete();
                }
                copy(file, file2, Integer.MAX_VALUE);
                return;
            }
            if (file2.exists()) {
                if (file2.isFile()) {
                    file2.delete();
                } else if (!file2.getName().equals(file.getName())) {
                    return;
                }
            } else {
                file2.mkdirs();
            }
            for (String str : file.list()) {
                copyDir(new File(file, str), new File(file2, str));
            }
        }
    }

    public static void copy(File file, File file2, int i2) {
        copy(file, file2, i2, false);
    }

    public static void copyAppend(File file, File file2) {
        copy(file, file2, Integer.MAX_VALUE, true);
    }

    public static void copy(File file, File file2, int i2, boolean z) {
        FileOutputStream fileOutputStream;
        if (file == null || file2 == null || !file.exists()) {
            return;
        }
        if (!file2.exists() || z) {
            FileInputStream fileInputStream = null;
            try {
                file2.getParentFile().mkdirs();
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2, z);
                    try {
                        byte[] bArr = new byte[8192];
                        int i3 = 0;
                        do {
                            int read = fileInputStream2.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                            i3 += read;
                        } while (i3 < i2);
                        k.a(fileInputStream2);
                    } catch (Exception unused) {
                        fileInputStream = fileInputStream2;
                        k.a(fileInputStream);
                        k.a(fileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        k.a(fileInputStream);
                        k.a(fileOutputStream);
                        throw th;
                    }
                } catch (Exception unused2) {
                    fileOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
            } catch (Exception unused3) {
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
            k.a(fileOutputStream);
        }
    }

    public static void a(OutputStream outputStream, List<File> list) {
        ZipOutputStream zipOutputStream;
        if (list.size() != 0) {
            ZipOutputStream zipOutputStream2 = null;
            try {
                zipOutputStream = new ZipOutputStream(outputStream);
            } catch (Throwable th) {
                th = th;
            }
            try {
                zipOutputStream.putNextEntry(new ZipEntry(c.a.SEPARATOR));
                for (File file : list) {
                    a(zipOutputStream, file, true);
                }
                k.a(zipOutputStream);
            } catch (Throwable th2) {
                th = th2;
                zipOutputStream2 = zipOutputStream;
                k.a(zipOutputStream2);
                throw th;
            }
        }
    }

    public static void zip(String str, String str2) {
        a(str2, new File(str));
    }

    private static void a(String str, File file) {
        ZipOutputStream zipOutputStream = null;
        try {
            new File(str).getParentFile().mkdirs();
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(str));
            try {
                a(zipOutputStream2, file, "");
                k.a(zipOutputStream2);
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                k.a(zipOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void a(ZipOutputStream zipOutputStream, File file, boolean z) {
        if (file == null || !file.exists()) {
            return;
        }
        if (!z && file.isDirectory()) {
            a(zipOutputStream, file, file.getName());
            return;
        }
        File[] listFiles = file.isDirectory() ? file.listFiles() : new File[]{file};
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            a(zipOutputStream, file2, file2.getName());
        }
    }

    private static void a(ZipOutputStream zipOutputStream, File file, String str) {
        FileInputStream fileInputStream;
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return;
            }
            zipOutputStream.putNextEntry(new ZipEntry(str + c.a.SEPARATOR));
            String str2 = str.length() == 0 ? "" : str + c.a.SEPARATOR;
            for (File file2 : listFiles) {
                a(zipOutputStream, file2, str2 + file2.getName());
            }
            return;
        }
        zipOutputStream.putNextEntry(new ZipEntry(str));
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (-1 != read) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    k.a(fileInputStream);
                    return;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            k.a(fileInputStream2);
            throw th;
        }
    }

    public static File a(File file) {
        File[] listFiles;
        if (file == null) {
            return null;
        }
        try {
            listFiles = file.listFiles();
        } catch (Throwable th) {
            y.e("NPTH", " path:" + file + " exception:" + th);
        }
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (!file2.isFile()) {
                    return file2;
                }
            }
            return null;
        }
        return null;
    }
}
