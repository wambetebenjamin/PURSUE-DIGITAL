package com.bytedance.sync.interfaze;
/* compiled from: IWsService.java */
/* loaded from: classes3.dex */
public interface p {
    boolean isConnect();

    void registerOnWsStatusChangedListener(q qVar);

    void send(com.bytedance.common.wschannel.model.i iVar);
}
