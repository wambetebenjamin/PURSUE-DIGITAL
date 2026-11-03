package com.bytedance.crash.dumper;

import com.bytedance.crash.CrashInfoCallback;
import com.bytedance.crash.CrashType;
import java.io.File;
import java.io.FilenameFilter;
import org.json.JSONObject;
/* compiled from: EvilDumpFile.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static CrashInfoCallback f6526a;

    public static void a(CrashInfoCallback crashInfoCallback) {
        f6526a = crashInfoCallback;
    }

    public static void a(File file, final JSONObject jSONObject) {
        try {
            file.listFiles(new FilenameFilter() { // from class: com.bytedance.crash.dumper.i.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    try {
                        if (str.contains("evil_trace")) {
                            jSONObject.put("has_evil_trace_file", String.valueOf(true));
                            return false;
                        }
                        return false;
                    } catch (Throwable unused) {
                        return false;
                    }
                }
            });
        } catch (Throwable th) {
            com.bytedance.crash.diagnose.b.c("pushEvilFile", th);
        }
    }

    public static void a(File file) {
        File[] crashFileList;
        CrashInfoCallback crashInfoCallback = f6526a;
        if (crashInfoCallback == null || (crashFileList = crashInfoCallback.crashFileList(CrashType.ANR)) == null) {
            return;
        }
        for (File file2 : crashFileList) {
            File file3 = new File(file, file2.getName());
            if (!file3.exists()) {
                com.bytedance.crash.util.m.copy(file2, file3);
            }
        }
    }
}
