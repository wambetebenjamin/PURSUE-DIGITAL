package com.bytedance.sync.protocal;

import com.bytedance.sync.user.a;
import java.util.List;
/* compiled from: IMsgBuilder.java */
/* loaded from: classes3.dex */
public interface l extends com.ss.android.ug.bus.a {
    boolean isPendingPayloadToSend();

    void send(f fVar);

    void sendCalibrationEvent(long j2, g gVar, String str);

    void sendLifeCycleEvent(a.C0209a c0209a, List<com.bytedance.sync.model.c> list, a aVar);

    void sendPayload(long j2, List<com.bytedance.sync.persistence.upload.c> list, boolean z);

    void sendPoll(List<com.bytedance.sync.persistence.intermediate.a> list);

    void sendSyncLogAck(com.bytedance.sync.persistence.intermediate.a aVar);
}
