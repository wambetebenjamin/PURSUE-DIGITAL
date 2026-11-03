package com.appsflyer.internal;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFe1gSDK {
    final String AFKeystoreWrapper;
    final String values;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AFe1gSDK) {
            AFe1gSDK aFe1gSDK = (AFe1gSDK) obj;
            return Intrinsics.areEqual(this.values, aFe1gSDK.values) && Intrinsics.areEqual(this.AFKeystoreWrapper, aFe1gSDK.AFKeystoreWrapper);
        }
        return false;
    }

    public final int hashCode() {
        return (this.values.hashCode() * 31) + this.AFKeystoreWrapper.hashCode();
    }

    public final String toString() {
        return new StringBuilder("HostConfig(prefix=").append(this.values).append(", host=").append(this.AFKeystoreWrapper).append(')').toString();
    }

    public AFe1gSDK(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        this.values = str;
        this.AFKeystoreWrapper = str2;
    }
}
