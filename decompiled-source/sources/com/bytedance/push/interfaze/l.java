package com.bytedance.push.interfaze;

import android.content.Context;
/* compiled from: IPushMsgShowInterceptor.java */
/* loaded from: classes2.dex */
public interface l {
    boolean onReceivePassThoughMsg(Context context, int i2, com.bytedance.push.g gVar);

    boolean onReceiveRevokeMsg(Context context, int i2, com.bytedance.push.g gVar);
}
