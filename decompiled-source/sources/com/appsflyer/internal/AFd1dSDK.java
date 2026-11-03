package com.appsflyer.internal;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFd1dSDK implements AFd1jSDK {
    private final AFd1gSDK AFInAppEventType;

    public AFd1dSDK(AFd1gSDK aFd1gSDK) {
        Intrinsics.checkNotNullParameter(aFd1gSDK, "");
        this.AFInAppEventType = aFd1gSDK;
    }

    @Override // com.appsflyer.internal.AFd1jSDK
    public final void AFKeystoreWrapper(byte[] bArr, Map<String, String> map, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "");
        Intrinsics.checkNotNullParameter(bArr, "");
        if (new AFd1bSDK(bArr, map, 2000).AFInAppEventParameterName()) {
            this.AFInAppEventType.AFInAppEventType();
        }
    }
}
