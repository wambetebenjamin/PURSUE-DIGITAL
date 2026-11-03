package com.bytedance.sync.interfaze;

import com.bytedance.sync.interfaze.n;
import java.util.List;
/* compiled from: IMonitor.java */
/* loaded from: classes3.dex */
public interface i {
    void ensureNotReachHere(Throwable th, String str);

    void monitorAccountChanged(boolean z);

    void monitorFallback(com.bytedance.sync.protocal.f fVar, boolean z);

    void monitorFallback(List<com.bytedance.sync.protocal.f> list, boolean z);

    void monitorPatchVeryLarge(long j2, List<com.bytedance.sync.persistence.intermediate.b> list);

    void monitorPayloadTooLarge(long j2, long j3, com.bytedance.sync.protocal.g gVar);

    void monitorServerAck(long j2, long j3, com.bytedance.sync.protocal.g gVar);

    void monitorServerHeartBeat(com.bytedance.sync.persistence.intermediate.a aVar, long j2);

    void monitorSettingError(String str, String str2);

    void monitorSinglePayloadTooLarge(long j2, long j3, com.bytedance.sync.protocal.g gVar);

    void patchError(com.bytedance.sync.persistence.intermediate.b bVar, long j2);

    void patchFinished(com.bytedance.sync.persistence.intermediate.b bVar, boolean z, long j2);

    void sendErrorAck(long j2, int i2, long j3, long j4, String str);

    void submitDataToBusiness(n.a aVar, long j2, com.bytedance.sync.protocal.g gVar, long j3, long j4);

    void tryMonitorReceiveData(com.bytedance.sync.protocal.f fVar, String str);
}
