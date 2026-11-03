package com.bytedance.push.interfaze;
/* compiled from: OnSwitcherSyncListener.java */
/* loaded from: classes2.dex */
public interface z {
    public static final int ERROR_CODE_SERVER = 1001;
    public static final int ERROR_NETWORK_NOT_AVAILABLE = 1002;
    public static final int ERROR_UNKNOWN = 1003;

    void onFailed(int i2, String str);

    void onSuccess();
}
