package com.bytedance.sync.protocal;

import java.util.List;
/* compiled from: IMsgConverter.java */
/* loaded from: classes3.dex */
public interface m extends com.ss.android.ug.bus.a {
    byte[] convertToBytes(List<f> list);

    e convertToProtocolArray(byte[] bArr);

    f convertToProtocolArray(com.bytedance.common.wschannel.model.i iVar);

    com.bytedance.common.wschannel.model.i convertToWsMsg(f fVar);
}
