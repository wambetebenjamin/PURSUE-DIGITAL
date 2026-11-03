package com.appsflyer.internal;
/* loaded from: classes.dex */
public final class AFh1sSDK extends AFa1pSDK {
    @Override // com.appsflyer.internal.AFa1pSDK
    public final boolean registerClient() {
        return true;
    }

    @Override // com.appsflyer.internal.AFa1pSDK
    public final AFe1bSDK AFInAppEventParameterName() {
        if (this.registerClient == 1) {
            return AFe1bSDK.CONVERSION;
        }
        return AFe1bSDK.LAUNCH;
    }
}
