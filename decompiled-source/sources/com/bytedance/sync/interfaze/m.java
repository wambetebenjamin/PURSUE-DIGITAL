package com.bytedance.sync.interfaze;

import java.util.Collection;
/* compiled from: ISyncApi.java */
/* loaded from: classes3.dex */
public interface m {
    Collection<com.bytedance.sync.o> getRegisteredBusinesses();

    void onReceiveWsChannelEvent(com.bytedance.common.wschannel.model.i iVar);

    n registerBusiness(com.bytedance.sync.n nVar);

    void start(String str);

    void subscribeTopic(com.bytedance.sync.model.e eVar, a<Void> aVar);

    void unsubscribeTopic(com.bytedance.sync.model.e eVar, a<Void> aVar);
}
