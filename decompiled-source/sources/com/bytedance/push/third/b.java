package com.bytedance.push.third;

import android.content.Context;
/* compiled from: IPushAdapter.java */
/* loaded from: classes2.dex */
public interface b extends com.bytedance.push.e {
    boolean checkThirdPushConfig(String str, Context context);

    boolean isPushAvailable(Context context, int i2);

    void registerPush(Context context, int i2);

    boolean requestNotificationPermission(int i2, com.bytedance.common.push.interfaze.f fVar);

    boolean requestRemoveVoipNotification(Context context, int i2);

    void setAlias(Context context, String str, int i2);

    void trackPush(Context context, int i2, Object obj);

    void unregisterPush(Context context, int i2);
}
