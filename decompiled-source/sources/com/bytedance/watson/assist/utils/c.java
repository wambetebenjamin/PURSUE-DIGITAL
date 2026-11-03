package com.bytedance.watson.assist.utils;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/* compiled from: FileUtils.java */
/* loaded from: classes8.dex */
public class c {

    /* compiled from: FileUtils.java */
    /* loaded from: classes8.dex */
    public interface a {
        boolean onReadLine(String str);
    }

    public static BufferedReader openFile(File file) {
        if (file != null && file.exists() && file.canRead()) {
            try {
                return new BufferedReader(new FileReader(file), 10000);
            } catch (Exception e2) {
                b.e(Log.getStackTraceString(e2));
                return null;
            }
        }
        return null;
    }

    public static void closeFile(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (Exception e2) {
                b.e(Log.getStackTraceString(e2));
            }
        }
    }

    public static void readFile(File file, a aVar) {
        BufferedReader openFile;
        if (file == null || aVar == null || (openFile = openFile(file)) == null) {
            return;
        }
        boolean z = true;
        while (z) {
            try {
                String readLine = openFile.readLine();
                if (readLine == null) {
                    break;
                }
                z = aVar.onReadLine(readLine);
            } catch (IOException e2) {
                b.e(Log.getStackTraceString(e2));
            }
        }
        closeFile(openFile);
    }

    public static void readFile(String str, a aVar) {
        if (str == null || aVar == null) {
            return;
        }
        readFile(new File(str), aVar);
    }

    public static void createFile(String str, boolean z) {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            b.i(str + " already exist");
        } else if (z) {
            file.mkdir();
            b.i(str + " created");
        } else {
            File file2 = new File(file.getParent());
            if (!file2.exists()) {
                file2.mkdir();
            }
            try {
                file.createNewFile();
                b.i(str + " created");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void deleteDir(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                deleteDir(file2);
            }
        }
        file.delete();
    }
}
