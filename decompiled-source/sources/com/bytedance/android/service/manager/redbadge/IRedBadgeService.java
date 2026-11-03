package com.bytedance.android.service.manager.redbadge;

import android.content.Context;
/* loaded from: classes.dex */
public interface IRedBadgeService {
    boolean addRedBadgeNumber(Context context, int i2);

    boolean applyCount(Context context, int i2);

    int getCurRedBadgeNumber(Context context);

    boolean isSupportAddRedBadgeNumber(int i2);

    boolean isSupportGetCurRedBadgeNumber();

    boolean isSupportReduceRedBadgeNumber(int i2);

    boolean reduceRedBadgeNumber(Context context, int i2);

    boolean removeCount(Context context);
}
