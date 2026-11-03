package com.bytedance.watson.assist.file;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
/* compiled from: ProcPidStatFile.java */
/* loaded from: classes8.dex */
public class c extends a {

    /* renamed from: b  reason: collision with root package name */
    protected static final int f16254b = 0;

    /* renamed from: c  reason: collision with root package name */
    protected static final int f16255c = 1;

    /* renamed from: d  reason: collision with root package name */
    protected static final int f16256d = 13;

    /* renamed from: e  reason: collision with root package name */
    protected static final int f16257e = 14;

    /* renamed from: f  reason: collision with root package name */
    protected static final int f16258f = 15;

    /* renamed from: g  reason: collision with root package name */
    protected static final int f16259g = 16;

    /* renamed from: h  reason: collision with root package name */
    private static final String f16260h = "proc/self/stat";

    public c(String str) {
        super(str);
    }

    public c() {
        this(f16260h);
    }

    @Override // com.bytedance.watson.assist.file.a
    protected b a(File file) {
        return a(file, null);
    }

    protected b a(File file, com.bytedance.watson.assist.core.cpu.c cVar) {
        BufferedReader openFile = com.bytedance.watson.assist.utils.c.openFile(file);
        try {
            if (openFile == null) {
                return null;
            }
            try {
                String readLine = openFile.readLine();
                if (readLine != null && !readLine.isEmpty()) {
                    String[] split = readLine.split("\\s+");
                    if (split.length > 16) {
                        if (cVar == null) {
                            cVar = new com.bytedance.watson.assist.core.cpu.c();
                        }
                        cVar.setPid(Integer.parseInt(split[0]));
                        cVar.setProcessName(split[1].substring(1, split[1].length() - 1));
                        cVar.setCpuTime((Integer.parseInt(split[13]) + Integer.parseInt(split[14]) + Integer.parseInt(split[15]) + Integer.parseInt(split[16])) * 10);
                        com.bytedance.watson.assist.utils.c.closeFile(openFile);
                        return cVar;
                    }
                    com.bytedance.watson.assist.utils.c.closeFile(openFile);
                    return cVar;
                }
                com.bytedance.watson.assist.utils.c.closeFile(openFile);
                return cVar;
            } catch (Exception e2) {
                com.bytedance.watson.assist.utils.b.e(Log.getStackTraceString(e2));
                com.bytedance.watson.assist.utils.c.closeFile(openFile);
                return cVar;
            }
        } catch (Throwable unused) {
            com.bytedance.watson.assist.utils.c.closeFile(openFile);
            return cVar;
        }
    }
}
