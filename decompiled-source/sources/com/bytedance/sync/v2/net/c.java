package com.bytedance.sync.v2.net;
/* compiled from: FakeMsgSender.java */
/* loaded from: classes3.dex */
public class c implements com.bytedance.sync.v2.intf.g {
    @Override // com.bytedance.sync.v2.intf.g
    public boolean isPendingPayloadToSend() {
        return false;
    }

    @Override // com.bytedance.sync.v2.intf.g
    public void send(com.bytedance.sync.v2.model.a aVar) {
        com.bytedance.sync.logger.c.d("【mock】send success -> " + aVar);
    }
}
