package com.bytedance.article.common.monitor.debug;

import java.util.List;
/* compiled from: DebugLogUploadManager.java */
@Deprecated
/* loaded from: classes.dex */
public class a {
    public static String formatLog(String str) {
        return null;
    }

    public static boolean registerModule(String str, b bVar) {
        return true;
    }

    public static boolean unRegisterUploadContentModule(String str) {
        return true;
    }

    public static b getFileUploadModuleForType(String str) {
        return new b() { // from class: com.bytedance.article.common.monitor.debug.a.1
            @Override // com.bytedance.article.common.monitor.debug.b
            public List<String> getUploadContentFileNameList() {
                return null;
            }

            @Override // com.bytedance.article.common.monitor.debug.b
            public void notifyBeginUpload(String str2) {
            }

            @Override // com.bytedance.article.common.monitor.debug.b
            public void notifyEndUpload(String str2, boolean z) {
            }
        };
    }
}
