package com.bytedance.watson.assist.file;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
/* compiled from: SystemTimeInStateFile.java */
/* loaded from: classes8.dex */
public class f extends a {

    /* renamed from: b  reason: collision with root package name */
    private static final String f16264b = "/sys/devices/system/cpu/cpu";

    /* renamed from: c  reason: collision with root package name */
    private static final String f16265c = "/cpufreq/stats/time_in_state";

    /* renamed from: d  reason: collision with root package name */
    private static final int f16266d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static final int f16267e = 1;

    /* renamed from: f  reason: collision with root package name */
    private int f16268f;

    /* renamed from: g  reason: collision with root package name */
    private List<File> f16269g;

    public f(String str) {
        super(str);
        this.f16268f = com.bytedance.watson.assist.utils.a.getCpuCoreNum();
        this.f16269g = new ArrayList();
    }

    public f() {
        super("");
        this.f16268f = com.bytedance.watson.assist.utils.a.getCpuCoreNum();
        this.f16269g = new ArrayList();
    }

    @Override // com.bytedance.watson.assist.file.a
    public b refresh() {
        if (this.f16269g.isEmpty()) {
            for (int i2 = 0; i2 != this.f16268f; i2++) {
                this.f16269g.add(new File(f16264b + i2 + f16265c));
            }
        }
        this.f16251a = a(this.f16269g);
        return this.f16251a;
    }

    private b a(List<File> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        com.bytedance.watson.assist.core.cpu.b bVar = new com.bytedance.watson.assist.core.cpu.b();
        for (File file : list) {
            LinkedHashMap<Long, Long> b2 = b(file);
            if (b2 != null) {
                bVar.putFreqTimeMap(b2);
            }
        }
        return bVar;
    }

    private LinkedHashMap<Long, Long> b(File file) {
        BufferedReader openFile = com.bytedance.watson.assist.utils.c.openFile(file);
        LinkedHashMap<Long, Long> linkedHashMap = null;
        if (openFile == null) {
            return null;
        }
        while (true) {
            try {
                try {
                    String readLine = openFile.readLine();
                    if (readLine == null || readLine.isEmpty()) {
                        break;
                    }
                    String[] split = readLine.split("\\s+");
                    if (split.length <= 1) {
                        break;
                    }
                    if (linkedHashMap == null) {
                        linkedHashMap = new LinkedHashMap<>();
                    }
                    linkedHashMap.put(Long.valueOf(Long.parseLong(split[0])), Long.valueOf(Long.parseLong(split[1]) * 10));
                } catch (Exception e2) {
                    com.bytedance.watson.assist.utils.b.e(Log.getStackTraceString(e2));
                    com.bytedance.watson.assist.utils.c.closeFile(openFile);
                    return linkedHashMap;
                }
            } catch (Throwable unused) {
                com.bytedance.watson.assist.utils.c.closeFile(openFile);
                return linkedHashMap;
            }
        }
        com.bytedance.watson.assist.utils.c.closeFile(openFile);
        return linkedHashMap;
    }
}
