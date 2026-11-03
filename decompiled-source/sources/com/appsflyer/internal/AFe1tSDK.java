package com.appsflyer.internal;
/* loaded from: classes.dex */
public class AFe1tSDK {
    public final long AFKeystoreWrapper;

    public AFe1tSDK(long j2) {
        this.AFKeystoreWrapper = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.AFKeystoreWrapper == ((AFe1tSDK) obj).AFKeystoreWrapper;
    }

    public int hashCode() {
        long j2 = this.AFKeystoreWrapper;
        return (int) (j2 ^ (j2 >>> 32));
    }
}
