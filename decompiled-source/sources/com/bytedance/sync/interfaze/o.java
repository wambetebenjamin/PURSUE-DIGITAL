package com.bytedance.sync.interfaze;
/* compiled from: ISyncNetService.java */
/* loaded from: classes3.dex */
public interface o extends com.ss.android.ug.bus.a {
    com.bytedance.sync.v2.protocal.e sendPipeline(com.bytedance.sync.v2.protocal.e eVar);

    com.bytedance.sync.model.d subscribeTopic(com.bytedance.sync.model.e eVar);

    com.bytedance.sync.model.d unsubscribeTopic(com.bytedance.sync.model.e eVar);
}
