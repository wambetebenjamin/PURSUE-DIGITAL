package com.bytedance.apm.perf.methodtrace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
/* compiled from: ThreadTimeUtil.java */
/* loaded from: classes.dex */
public class c {
    public static final double START_RATE_THRESHOLD = 0.2d;
    public static final double THREAD_USAGE_THRESHOLD = 0.1d;

    public static LinkedList<b> getThreadInfo(int i2) {
        File[] listFiles = new File("/proc/" + i2 + "/task/").listFiles();
        LinkedList<b> linkedList = new LinkedList<>();
        int length = listFiles.length;
        BufferedReader bufferedReader = null;
        for (int i3 = 0; i3 < length; i3++) {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(listFiles[i3].getPath() + "/stat")), 1000);
                try {
                    String readLine = bufferedReader2.readLine();
                    int lastIndexOf = readLine.lastIndexOf(41);
                    String substring = readLine.substring(0, lastIndexOf);
                    String substring2 = readLine.substring(lastIndexOf + 4);
                    int indexOf = substring.indexOf(40);
                    int intValue = Integer.valueOf(substring.substring(0, indexOf - 1)).intValue();
                    String substring3 = substring.substring(indexOf + 1);
                    String[] split = substring2.split(" ");
                    linkedList.add(new b(intValue, substring3, Long.parseLong(split[10]) + Long.parseLong(split[11])));
                    com.bytedance.monitor.util.c.safeClose(bufferedReader2);
                    bufferedReader = bufferedReader2;
                } catch (Exception unused) {
                    bufferedReader = bufferedReader2;
                    com.bytedance.monitor.util.c.safeClose(bufferedReader);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    com.bytedance.monitor.util.c.safeClose(bufferedReader);
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
                com.bytedance.monitor.util.c.safeClose(bufferedReader);
            } catch (Exception unused3) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return linkedList;
    }

    public static void filterThreads(int i2, List<b> list, long j2) {
        BufferedReader bufferedReader;
        String str = "/proc/" + i2 + "/task/";
        ListIterator<b> listIterator = list.listIterator();
        BufferedReader bufferedReader2 = null;
        while (listIterator.hasNext()) {
            try {
                b next = listIterator.next();
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str + next.threadId + "/stat")), 1000);
                } catch (FileNotFoundException unused) {
                    listIterator.remove();
                    com.bytedance.common.utility.io.b.close(bufferedReader2);
                }
                try {
                    String readLine = bufferedReader.readLine();
                    String[] split = readLine.substring(readLine.lastIndexOf(41) + 4).split(" ");
                    long parseLong = Long.parseLong(split[10]) + Long.parseLong(split[11]);
                    float f2 = ((float) (parseLong - next.threadTime)) / ((float) j2);
                    if (f2 >= 0.1d) {
                        next.threadUsage = f2;
                        next.threadTime = parseLong;
                    } else {
                        listIterator.remove();
                    }
                    com.bytedance.common.utility.io.b.close(bufferedReader);
                    bufferedReader2 = bufferedReader;
                } catch (Exception unused2) {
                    bufferedReader2 = bufferedReader;
                    com.bytedance.common.utility.io.b.close(bufferedReader2);
                    return;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    com.bytedance.common.utility.io.b.close(bufferedReader2);
                    throw th;
                }
            } catch (Exception unused3) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
