package com.appsflyer.internal;
/* loaded from: classes.dex */
public final class AFh1iSDK {
    public final long AFInAppEventParameterName;
    public final long AFInAppEventType;
    public final String AFKeystoreWrapper;

    /* renamed from: e  reason: collision with root package name */
    public final AFh1fSDK f98e;
    public final Throwable registerClient;
    public final String unregisterClient;
    public final int valueOf;
    public final String values;

    public AFh1iSDK(String str, String str2, long j2, long j3, int i2, AFh1fSDK aFh1fSDK, String str3, Throwable th) {
        this.AFKeystoreWrapper = str;
        this.values = str2;
        this.AFInAppEventParameterName = j2;
        this.AFInAppEventType = j3;
        this.valueOf = i2;
        this.f98e = aFh1fSDK;
        this.unregisterClient = str3;
        this.registerClient = th;
    }
}
