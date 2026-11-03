package com.bytedance.sync.processor;
/* compiled from: IDispatcher.java */
/* loaded from: classes3.dex */
public interface c extends d {
    void notifyOnce(com.bytedance.sync.persistence.intermediate.b bVar);

    void notifyPatch(long j2);

    void saveAndSendMsg(com.bytedance.sync.persistence.upload.c cVar);

    void tryNotifyListeners();

    void tryNotifyOneByOne(long j2);

    void trySendUploadMsg();
}
