package com.bytedance.article.common.monitor.debug;

import java.util.List;
/* compiled from: IDebugLogUploadCallBack.java */
@Deprecated
/* loaded from: classes.dex */
public interface b {
    List<String> getUploadContentFileNameList();

    void notifyBeginUpload(String str);

    void notifyEndUpload(String str, boolean z);
}
