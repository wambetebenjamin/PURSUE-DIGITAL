package com.bytedance.watson.assist.file;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
/* compiled from: ProcTidStatFile.java */
/* loaded from: classes8.dex */
public class d extends c {
    public d(long j2) {
        super("proc/self/task/" + j2 + "/stat");
    }

    @Override // com.bytedance.watson.assist.file.c, com.bytedance.watson.assist.file.a
    protected b a(File file) {
        return a(file, new com.bytedance.watson.assist.core.cpu.e());
    }

    protected b a(File file, com.bytedance.watson.assist.core.cpu.e eVar) {
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
                        if (eVar == null) {
                            eVar = new com.bytedance.watson.assist.core.cpu.e();
                        }
                        eVar.setPid(Integer.parseInt(split[0]));
                        eVar.setProcessName(split[1].substring(1, split[1].length() - 1));
                        eVar.setCpuTime((Integer.parseInt(split[13]) + Integer.parseInt(split[14])) * 10);
                        com.bytedance.watson.assist.utils.c.closeFile(openFile);
                        return eVar;
                    }
                    com.bytedance.watson.assist.utils.c.closeFile(openFile);
                    return eVar;
                }
                com.bytedance.watson.assist.utils.c.closeFile(openFile);
                return eVar;
            } catch (Exception e2) {
                com.bytedance.watson.assist.utils.b.e(Log.getStackTraceString(e2));
                com.bytedance.watson.assist.utils.c.closeFile(openFile);
                return eVar;
            }
        } catch (Throwable unused) {
            com.bytedance.watson.assist.utils.c.closeFile(openFile);
            return eVar;
        }
    }
}
