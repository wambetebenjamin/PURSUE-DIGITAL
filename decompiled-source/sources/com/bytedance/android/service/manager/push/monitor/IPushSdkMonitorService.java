package com.bytedance.android.service.manager.push.monitor;

import android.content.Context;
import com.bytedance.push.monitor.i;
/* loaded from: classes.dex */
public interface IPushSdkMonitorService extends i {
    public static final int ALLIANCE_USE_NEW_LOCAL_PUSH_API = 4096;
    public static final int ALLIANCE_USE_NEW_RED_BADGE_API = 256;
    public static final int BADGE_OPERATE_TYPE_ADD_NUMBER = 3;
    public static final int BADGE_OPERATE_TYPE_GET_NUMBER = 2;
    public static final int BADGE_OPERATE_TYPE_REDUCE_NUMBER = 4;
    public static final int BADGE_OPERATE_TYPE_SET_NUMBER = 1;
    public static final int ONLINE_USE_NEW_LOCAL_PUSH_API = 16;
    public static final int ONLINE_USE_NEW_RED_BADGE_API = 1;
    public static final int SCENE_ALLIANCE = 2;
    public static final int SCENE_ONLINE = 1;

    boolean curIsDau();

    boolean curIsTransmit();

    void initOnApplication(Context context);

    void onBadgeOperateFailed(int i2, Integer num, String str);

    void onBadgeOperateSuccess(int i2, Integer num);

    void onContentRequest(String str, int i2, int i3);

    void onContentShow(String str, String str2, int i2);

    void onKeepAliveFrom(String str, String str2, int i2);

    void onLaunchEvent(boolean z, boolean z2, long j2, long j3, int i2, int i3, String str);

    void onMessageArrive(long j2);

    void onMessageShow(long j2, long j3);

    void onTransmitStrategyRequest();
}
