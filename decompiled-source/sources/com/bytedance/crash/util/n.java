package com.bytedance.crash.util;

import com.bytedance.crash.CrashAgent;
import com.bytedance.crash.Global;
import java.io.File;
import org.json.JSONObject;
/* compiled from: NpthDiskMonitor.java */
/* loaded from: classes2.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    public static String f7304a = "npth_disk_event";

    /* renamed from: b  reason: collision with root package name */
    private static n f7305b = new n();

    private n() {
    }

    public static n a() {
        return f7305b;
    }

    public void a(com.bytedance.crash.event.g gVar) {
        if (gVar != null && gVar.a(f7304a)) {
            com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.util.n.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        File[] listFiles = Global.e().listFiles();
                        JSONObject jSONObject = new JSONObject();
                        if (listFiles != null && listFiles.length > 0) {
                            for (File file : listFiles) {
                                jSONObject.put(file.getName(), n.a(file));
                            }
                        }
                        CrashAgent.a(n.f7304a, jSONObject, null, null);
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    public void b() {
        com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.util.n.2
            @Override // java.lang.Runnable
            public void run() {
                n.this.c();
            }
        });
    }

    public void c() {
        try {
            File e2 = Global.e();
            if (new File(e2, "crash.deleted").exists()) {
                return;
            }
            m.deleteFile(new File(e2, "RuntimeContext"));
            m.deleteFile(new File(e2, "ProcessTrack"));
            m.deleteFile(new File(e2, "TrackInfo"));
            m.deleteFile(new File(e2, "CrashCommonLog"));
            m.deleteFile(new File(e2, "CrashLogJava"));
            m.deleteFile(new File(e2, "CrashLogNative"));
            m.deleteFile(new File(e2, "procanr"));
            m.deleteFile(new File(e2, "configCrash"));
            new File(e2, "crash.deleted").createNewFile();
        } catch (Throwable unused) {
        }
    }

    public static long a(File file) {
        long j2 = 0;
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    j2 += a(file2);
                }
                return j2;
            }
            return file.length() / 1024;
        }
        return 0L;
    }
}
