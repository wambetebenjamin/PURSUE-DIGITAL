package com.bytedance.monitor.collector;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONArray;
/* compiled from: FileUtils.java */
/* loaded from: classes2.dex */
public class e {
    private e() {
    }

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
                com.bytedance.monitor.util.c.safeClose(fileOutputStream2);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
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
        BufferedReader bufferedReader = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        if (str2 != null) {
                            sb.append(str2);
                        }
                    } else {
                        com.bytedance.monitor.util.c.safeClose(bufferedReader2);
                        return sb.toString();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    com.bytedance.monitor.util.c.safeClose(bufferedReader);
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static JSONArray readFileArray(String str) {
        BufferedReader bufferedReader = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        JSONArray jSONArray = new JSONArray();
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        jSONArray.put(readLine);
                    } else {
                        com.bytedance.monitor.util.c.safeClose(bufferedReader2);
                        return jSONArray;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    com.bytedance.monitor.util.c.safeClose(bufferedReader);
                    throw th;
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
        return readFile(str, null);
    }

    public static JSONArray toJsonArray(String str, String str2) {
        JSONArray jSONArray = new JSONArray();
        if (str != null && str2 != null) {
            for (String str3 : str.split(str2)) {
                jSONArray.put(str3);
            }
        }
        return jSONArray;
    }

    public static Map<String, String> readPropertiesFile(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
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
                com.bytedance.monitor.util.c.safeClose(fileInputStream);
                return hashMap;
            } catch (IOException unused) {
                com.bytedance.monitor.util.c.safeClose(fileInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                com.bytedance.monitor.util.c.safeClose(fileInputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String[] readProcFile(String str) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)), 1000);
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            int indexOf = readLine.indexOf(")");
            if (indexOf > 0) {
                readLine = readLine.substring(indexOf + 2);
            }
            return readLine.split(" ");
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                return null;
            } finally {
                com.bytedance.monitor.util.c.safeClose(bufferedReader);
            }
        }
    }

    public static boolean isEmpty(File[] fileArr) {
        return fileArr == null || fileArr.length == 0;
    }

    public static void writeListMap(File file, ArrayList<HashMap<String, String>> arrayList, String str, String str2) {
        FileOutputStream fileOutputStream;
        if (file == null || arrayList == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                HashMap<String, String> hashMap = arrayList.get(i2);
                if (hashMap != null && hashMap.size() != 0) {
                    for (String str3 : hashMap.keySet()) {
                        sb.append(str3).append(str2).append(hashMap.get(str3)).append(str);
                    }
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
            }
            fileOutputStream.write(sb.toString().getBytes());
            com.bytedance.monitor.util.c.safeClose(fileOutputStream);
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            com.bytedance.monitor.util.c.safeClose(fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            com.bytedance.monitor.util.c.safeClose(fileOutputStream2);
            throw th;
        }
    }

    public static ArrayList<HashMap<String, String>> readListMap(File file, String str, String str2) {
        ArrayList<HashMap<String, String>> arrayList;
        String[] split;
        BufferedReader bufferedReader = null;
        ArrayList<HashMap<String, String>> arrayList2 = null;
        BufferedReader bufferedReader2 = null;
        if (file == null || !file.exists() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            BufferedReader bufferedReader3 = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader3.readLine();
                    if (readLine != null) {
                        if (!ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE.equals(readLine) && (split = readLine.split(str)) != null) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            for (String str3 : split) {
                                String[] split2 = str3.split(str2);
                                if (split2 != null && split2.length == 2) {
                                    hashMap.put(split2[0], split2[1]);
                                }
                            }
                            if (hashMap.size() > 0) {
                                if (arrayList2 == null) {
                                    arrayList2 = new ArrayList<>();
                                }
                                arrayList2.add(hashMap);
                            }
                        }
                    } else {
                        com.bytedance.monitor.util.c.safeClose(bufferedReader3);
                        return arrayList2;
                    }
                } catch (FileNotFoundException | IOException unused) {
                    arrayList = arrayList2;
                    bufferedReader2 = bufferedReader3;
                    com.bytedance.monitor.util.c.safeClose(bufferedReader2);
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader3;
                    com.bytedance.monitor.util.c.safeClose(bufferedReader);
                    throw th;
                }
            }
        } catch (FileNotFoundException | IOException unused2) {
            arrayList = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void copy(File file, File file2) {
        FileOutputStream fileOutputStream;
        if (file == null || file2 == null) {
            return;
        }
        FileInputStream fileInputStream = null;
        try {
            file2.getParentFile().mkdirs();
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    com.bytedance.monitor.util.c.safeClose(fileInputStream2);
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                    try {
                        e.printStackTrace();
                        com.bytedance.monitor.util.c.safeClose(fileInputStream);
                        com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        com.bytedance.monitor.util.c.safeClose(fileInputStream);
                        com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    com.bytedance.monitor.util.c.safeClose(fileInputStream);
                    com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
        com.bytedance.monitor.util.c.safeClose(fileOutputStream);
    }

    public static void zip(String str, String str2) {
        a(str2, new File(str));
    }

    public static void zip(List<String> list, String str) {
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(str));
            try {
                for (String str2 : list) {
                    File file = new File(str2);
                    a(zipOutputStream2, file, file.getName());
                }
                com.bytedance.monitor.util.c.safeClose(zipOutputStream2);
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                com.bytedance.monitor.util.c.safeClose(zipOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void a(String str, File file) {
        ZipOutputStream zipOutputStream = null;
        try {
            new File(str).getParentFile().mkdirs();
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(str));
            try {
                a(zipOutputStream2, file, "");
                com.bytedance.monitor.util.c.safeClose(zipOutputStream2);
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                com.bytedance.monitor.util.c.safeClose(zipOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void zip(OutputStream outputStream, File... fileArr) {
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(outputStream);
            try {
                zipOutputStream2.putNextEntry(new ZipEntry(c.a.SEPARATOR));
                for (File file : fileArr) {
                    a(zipOutputStream2, file);
                }
                com.bytedance.monitor.util.c.safeClose(zipOutputStream2);
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                com.bytedance.monitor.util.c.safeClose(zipOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void a(ZipOutputStream zipOutputStream, File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                a(zipOutputStream, listFiles[i2], listFiles[i2].getName());
            }
            return;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (-1 != read) {
                        zipOutputStream.write(bArr, 0, read);
                    } else {
                        com.bytedance.monitor.util.c.safeClose(fileInputStream2);
                        return;
                    }
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                com.bytedance.monitor.util.c.safeClose(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void a(ZipOutputStream zipOutputStream, File file, String str) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            zipOutputStream.putNextEntry(new ZipEntry(str + c.a.SEPARATOR));
            String str2 = str.length() == 0 ? "" : str + c.a.SEPARATOR;
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                a(zipOutputStream, listFiles[i2], str2 + listFiles[i2].getName());
            }
            return;
        }
        zipOutputStream.putNextEntry(new ZipEntry(str));
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (-1 != read) {
                        zipOutputStream.write(bArr, 0, read);
                    } else {
                        com.bytedance.monitor.util.c.safeClose(fileInputStream2);
                        return;
                    }
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                com.bytedance.monitor.util.c.safeClose(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
