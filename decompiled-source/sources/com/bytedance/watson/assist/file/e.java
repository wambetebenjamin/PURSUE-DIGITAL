package com.bytedance.watson.assist.file;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.util.LinkedHashMap;
/* compiled from: ProcTimeInStateFile.java */
/* loaded from: classes8.dex */
public class e extends a {

    /* renamed from: b  reason: collision with root package name */
    private static final String f16261b = "/proc/self/time_in_state";

    /* renamed from: c  reason: collision with root package name */
    private static final int f16262c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static final int f16263d = 1;

    public e() {
        this(f16261b);
    }

    public e(int i2) {
        super("/proc/self/task/" + i2 + "/time_in_state");
    }

    public e(String str) {
        super(str);
    }

    @Override // com.bytedance.watson.assist.file.a
    protected b a(File file) {
        return b(file);
    }

    private b b(File file) {
        BufferedReader openFile = com.bytedance.watson.assist.utils.c.openFile(file);
        if (openFile == null) {
            return null;
        }
        com.bytedance.watson.assist.core.cpu.d dVar = new com.bytedance.watson.assist.core.cpu.d();
        LinkedHashMap<Long, Long> linkedHashMap = null;
        long j2 = 0;
        while (true) {
            try {
                try {
                    String readLine = openFile.readLine();
                    if (readLine == null) {
                        break;
                    } else if (!readLine.isEmpty() && !readLine.contains("cpu")) {
                        String[] split = readLine.split("\\s+");
                        if (split.length <= 1) {
                            break;
                        }
                        long parseLong = Long.parseLong(split[0]);
                        long parseLong2 = Long.parseLong(split[1]) * 10;
                        if (j2 != 0 && parseLong < j2) {
                            dVar.addFreqTimeItem(linkedHashMap);
                            linkedHashMap = null;
                        }
                        if (linkedHashMap == null) {
                            linkedHashMap = new LinkedHashMap<>();
                        }
                        linkedHashMap.put(Long.valueOf(parseLong), Long.valueOf(parseLong2));
                        j2 = parseLong;
                    }
                } catch (Exception e2) {
                    com.bytedance.watson.assist.utils.b.e(Log.getStackTraceString(e2));
                    com.bytedance.watson.assist.utils.c.closeFile(openFile);
                    return dVar;
                }
            } catch (Throwable unused) {
                com.bytedance.watson.assist.utils.c.closeFile(openFile);
                return dVar;
            }
        }
        dVar.addFreqTimeItem(linkedHashMap);
        com.bytedance.watson.assist.utils.c.closeFile(openFile);
        return dVar;
    }
}
