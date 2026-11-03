package com.ss.android.newmedia.redbadge;

import android.content.Context;
import com.bytedance.android.service.manager.PushServiceManager;
import com.bytedance.android.service.manager.redbadge.IRedBadgeService;
/* loaded from: classes9.dex */
public class RedBadgerManager implements IRedBadgeService {
    private static volatile RedBadgerManager sInstance;

    private RedBadgerManager() {
    }

    public static synchronized RedBadgerManager inst() {
        RedBadgerManager redBadgerManager;
        synchronized (RedBadgerManager.class) {
            if (sInstance == null) {
                synchronized (RedBadgerManager.class) {
                    if (sInstance == null) {
                        sInstance = new RedBadgerManager();
                    }
                }
            }
            redBadgerManager = sInstance;
        }
        return redBadgerManager;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean applyCount(Context context, int i2) {
        return PushServiceManager.get().getIRedBadgeExternalService().applyCount(context, i2);
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean removeCount(Context context) {
        return PushServiceManager.get().getIRedBadgeExternalService().removeCount(context);
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public int getCurRedBadgeNumber(Context context) {
        return PushServiceManager.get().getIRedBadgeExternalService().getCurRedBadgeNumber(context);
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean isSupportGetCurRedBadgeNumber() {
        return PushServiceManager.get().getIRedBadgeExternalService().isSupportGetCurRedBadgeNumber();
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean addRedBadgeNumber(Context context, int i2) {
        return PushServiceManager.get().getIRedBadgeExternalService().addRedBadgeNumber(context, i2);
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean isSupportAddRedBadgeNumber(int i2) {
        return PushServiceManager.get().getIRedBadgeExternalService().isSupportAddRedBadgeNumber(i2);
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean reduceRedBadgeNumber(Context context, int i2) {
        return PushServiceManager.get().getIRedBadgeExternalService().reduceRedBadgeNumber(context, i2);
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean isSupportReduceRedBadgeNumber(int i2) {
        return PushServiceManager.get().getIRedBadgeExternalService().isSupportReduceRedBadgeNumber(i2);
    }
}
