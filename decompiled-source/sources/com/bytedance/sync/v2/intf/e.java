package com.bytedance.sync.v2.intf;
/* compiled from: IMsgConverter.java */
/* loaded from: classes3.dex */
public interface e extends com.ss.android.ug.bus.a {
    byte[] convertToBytes(com.bytedance.sync.v2.protocal.e eVar);

    com.bytedance.sync.v2.protocal.e convertToProtocol(com.bytedance.common.wschannel.model.i iVar);

    com.bytedance.sync.v2.protocal.e convertToProtocol(byte[] bArr);

    com.bytedance.common.wschannel.model.i convertToWsMsg(com.bytedance.sync.v2.protocal.e eVar);
}
