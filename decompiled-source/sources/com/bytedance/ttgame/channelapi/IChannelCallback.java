package com.bytedance.ttgame.channelapi;
/* loaded from: classes3.dex */
public interface IChannelCallback<T> {
    public static final int COMMAN_CANCEL = 2;
    public static final int COMMON_FAIL = 1;
    public static final int SUCC = 0;

    void onResult(int i2, T t);
}
