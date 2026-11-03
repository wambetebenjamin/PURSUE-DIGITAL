package com.appsflyer.internal;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFg1vSDK {
    final int AFInAppEventParameterName;
    final int AFInAppEventType;
    final int AFKeystoreWrapper;
    final String valueOf;
    final int values;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AFg1vSDK) {
            AFg1vSDK aFg1vSDK = (AFg1vSDK) obj;
            return this.AFKeystoreWrapper == aFg1vSDK.AFKeystoreWrapper && this.AFInAppEventParameterName == aFg1vSDK.AFInAppEventParameterName && this.AFInAppEventType == aFg1vSDK.AFInAppEventType && this.values == aFg1vSDK.values && Intrinsics.areEqual(this.valueOf, aFg1vSDK.valueOf);
        }
        return false;
    }

    public final int hashCode() {
        return (((((((Integer.hashCode(this.AFKeystoreWrapper) * 31) + Integer.hashCode(this.AFInAppEventParameterName)) * 31) + Integer.hashCode(this.AFInAppEventType)) * 31) + Integer.hashCode(this.values)) * 31) + this.valueOf.hashCode();
    }

    public final String toString() {
        return new StringBuilder("CmpTcfData(policyVersion=").append(this.AFKeystoreWrapper).append(", gdprApplies=").append(this.AFInAppEventParameterName).append(", cmpSdkId=").append(this.AFInAppEventType).append(", cmpSdkVersion=").append(this.values).append(", tcString=").append(this.valueOf).append(')').toString();
    }

    public AFg1vSDK(int i2, int i3, int i4, int i5, String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.AFKeystoreWrapper = i2;
        this.AFInAppEventParameterName = i3;
        this.AFInAppEventType = i4;
        this.values = i5;
        this.valueOf = str;
    }
}
