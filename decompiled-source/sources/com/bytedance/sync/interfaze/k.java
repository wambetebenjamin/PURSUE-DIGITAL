package com.bytedance.sync.interfaze;
/* compiled from: IMsgSender.java */
/* loaded from: classes3.dex */
public interface k extends com.ss.android.ug.bus.a {
    boolean isPendingPayloadToSend();

    void send(com.bytedance.sync.model.b bVar);

    void send(com.bytedance.sync.protocal.f fVar, boolean z);
}
