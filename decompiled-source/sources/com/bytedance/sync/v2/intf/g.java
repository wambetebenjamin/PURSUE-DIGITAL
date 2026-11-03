package com.bytedance.sync.v2.intf;
/* compiled from: IMsgSender.java */
/* loaded from: classes3.dex */
public interface g extends com.ss.android.ug.bus.a {
    boolean isPendingPayloadToSend();

    void send(com.bytedance.sync.v2.model.a aVar);
}
