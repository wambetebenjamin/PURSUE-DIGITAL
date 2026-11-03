package com.bytedance.article.common.monitor.file;

import java.util.List;
/* compiled from: IFileUploadCallBack.java */
@Deprecated
/* loaded from: classes.dex */
public interface c {
    List<String> getUploadFileList();

    void notifyUploadBegin(String str);

    void notifyUploadEnd(String str, boolean z);
}
