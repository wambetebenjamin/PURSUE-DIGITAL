package com.appsflyer.internal;

import com.appsflyer.AFLogger;
/* loaded from: classes.dex */
public abstract class AFi1ySDK extends AFi1nSDK {
    private AFd1rSDK valueOf;

    public AFi1ySDK(String str, String str2, AFd1rSDK aFd1rSDK, Runnable runnable) {
        super(str, str2, runnable);
        this.valueOf = aFd1rSDK;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean AFInAppEventParameterName() {
        if (this.valueOf.AFInAppEventParameterName.valueOf("appsFlyerCount", 0) > 0) {
            AFLogger.afRDLog("Install referrer will not load, the counter > 1, ");
            return false;
        }
        return true;
    }
}
