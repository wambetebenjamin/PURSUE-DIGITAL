package com.bytedance.push.interfaze;

import android.content.Context;
/* compiled from: ISenderService.java */
/* loaded from: classes2.dex */
public interface r extends com.bytedance.push.e {
    void d(Context context);

    void e(Context context);

    boolean isAllowPushService(int i2);

    boolean isSenderChanged(Context context);

    boolean registerAllSender(Context context);

    void registerSenderInPushServiceProcess();

    void setAlias(Context context, String str, int i2);

    void tryUpdateSender(boolean z);

    void unRegisterAllThirdPush(Context context);
}
