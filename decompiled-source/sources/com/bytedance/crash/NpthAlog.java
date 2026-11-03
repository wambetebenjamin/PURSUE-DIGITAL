package com.bytedance.crash;

import com.bytedance.crash.NpthAlogApi;
import com.ss.android.agilelogger.a;
import com.ttnet.org.chromium.base.TimeUtils;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class NpthAlog {
    NpthAlog() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        NpthAlogApi.a(new Runnable() { // from class: com.bytedance.crash.NpthAlog.1
            @Override // java.lang.Runnable
            public void run() {
                a.syncFlush();
            }
        });
        NpthAlogApi.a(new NpthAlogApi.AlogGetFilesInterface() { // from class: com.bytedance.crash.NpthAlog.2
            @Override // com.bytedance.crash.NpthAlogApi.AlogGetFilesInterface
            public List<String> getFiles(long j2, String str) {
                long j3 = j2 / 1000;
                return a.getALogFiles(str, null, j3 - TimeUtils.SECONDS_PER_HOUR, j3);
            }
        });
        NpthAlogApi.a(new NpthAlogApi.AlogInitInterface() { // from class: com.bytedance.crash.NpthAlog.3
            @Override // com.bytedance.crash.NpthAlogApi.AlogInitInterface
            public boolean isInited() {
                return a.isInitSuccess();
            }
        });
    }
}
