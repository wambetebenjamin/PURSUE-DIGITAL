package com.bytedance.sdk.account.save;
/* compiled from: IBDSaveAPI.java */
/* loaded from: classes2.dex */
public interface b {
    void onTerminate();

    void queryByUid(long j2, com.bytedance.sdk.account.save.callback.c cVar);

    void queryLatest(com.bytedance.sdk.account.save.callback.b bVar);

    void queryLatestAccounts(com.bytedance.sdk.account.save.callback.c cVar);

    void saveLoginInfo(com.bytedance.sdk.account.save.entity.d dVar, com.bytedance.sdk.account.save.callback.d dVar2);
}
