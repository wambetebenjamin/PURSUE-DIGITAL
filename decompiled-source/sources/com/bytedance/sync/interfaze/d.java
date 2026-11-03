package com.bytedance.sync.interfaze;
/* compiled from: ICompensatorService.java */
/* loaded from: classes3.dex */
public interface d extends com.ss.android.ug.bus.a {
    public static final String TAG = "Compensator: ";

    void onReceiveHeartBeat(com.bytedance.sync.protocal.g gVar);

    void startOnce();
}
