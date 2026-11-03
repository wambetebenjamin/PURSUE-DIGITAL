package com.bytedance.sync.v2.intf;
/* compiled from: ICompensatorV2.java */
/* loaded from: classes3.dex */
public interface a extends com.ss.android.ug.bus.a {
    public static final String TAG = "[Compensator] ";

    void onReceiveSyncMsg();

    void reset();

    void resetPollingInterval(com.bytedance.sync.v2.protocal.e eVar);

    void startOnce();
}
