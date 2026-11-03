package com.ss.android.ugc.quota;

import java.util.Map;
/* compiled from: BDNetworkTagContextProviderAdapter.java */
/* loaded from: classes9.dex */
public class a implements c {
    @Override // com.ss.android.ugc.quota.c
    public Map<String, Object> extra() {
        return null;
    }

    @Override // com.ss.android.ugc.quota.c
    public int triggerType() {
        return 1;
    }

    @Override // com.ss.android.ugc.quota.c
    public boolean markAsNewUser() {
        return BDNetworkTagManager.getInstance().isFirstInstallLaunch();
    }

    @Override // com.ss.android.ugc.quota.c
    public int launchType() {
        int currentLaunchType = BDNetworkTagManager.getInstance().currentLaunchType();
        if (currentLaunchType == -999 || currentLaunchType == -1 || currentLaunchType == 0 || currentLaunchType == 1 || currentLaunchType == 2) {
            return currentLaunchType;
        }
        throw new IllegalStateException("BDNetworkTagManager#updateLaunchType should be called while launch type changed");
    }
}
