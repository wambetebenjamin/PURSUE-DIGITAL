package com.appsflyer.internal;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFd1cSDK {
    public static boolean AFInAppEventType(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        int AFInAppEventParameterName = AFc1uSDK.AFInAppEventParameterName(str);
        int AFInAppEventParameterName2 = AFc1uSDK.AFInAppEventParameterName(str2);
        Pair<Integer, Integer> valueOf = AFe1wSDK.valueOf(str2);
        Pair<Integer, Integer> AFKeystoreWrapper = AFe1wSDK.AFKeystoreWrapper(str2);
        return (AFInAppEventParameterName2 == -1 || valueOf != null) ? AFKeystoreWrapper != null ? AFKeystoreWrapper.getFirst().intValue() <= AFInAppEventParameterName && AFInAppEventParameterName <= AFKeystoreWrapper.getSecond().intValue() : valueOf != null && valueOf.getFirst().intValue() <= AFInAppEventParameterName && AFInAppEventParameterName <= valueOf.getSecond().intValue() : AFInAppEventParameterName2 == AFInAppEventParameterName;
    }
}
