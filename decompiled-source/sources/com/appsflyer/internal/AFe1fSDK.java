package com.appsflyer.internal;

import android.net.TrafficStats;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class AFe1fSDK<Result> implements Comparable<AFe1fSDK<?>>, Callable<AFe1cSDK> {
    private static final AtomicInteger AFLogger = new AtomicInteger();
    public final Set<AFe1bSDK> AFInAppEventParameterName;
    public final AFe1bSDK AFInAppEventType;
    public AFe1cSDK AFKeystoreWrapper;

    /* renamed from: d  reason: collision with root package name */
    private Throwable f63d;

    /* renamed from: e  reason: collision with root package name */
    private long f64e;
    private final int registerClient;
    private final String unregisterClient;
    private boolean v;
    public final Set<AFe1bSDK> valueOf;
    public volatile int values;

    protected abstract AFe1cSDK AFInAppEventType() throws Exception;

    protected void AFInAppEventType(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract long AFKeystoreWrapper();

    protected void valueOf() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean values();

    public AFe1fSDK(AFe1bSDK aFe1bSDK, AFe1bSDK[] aFe1bSDKArr, String str) {
        HashSet hashSet = new HashSet();
        this.valueOf = hashSet;
        this.AFInAppEventParameterName = new HashSet();
        int incrementAndGet = AFLogger.incrementAndGet();
        this.registerClient = incrementAndGet;
        this.v = false;
        this.values = 0;
        this.AFInAppEventType = aFe1bSDK;
        Collections.addAll(hashSet, aFe1bSDKArr);
        if (str != null) {
            this.unregisterClient = str;
        } else {
            this.unregisterClient = String.valueOf(incrementAndGet);
        }
    }

    public void AFInAppEventParameterName() {
        this.v = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean AFLogger() {
        return this.v;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: e */
    public final AFe1cSDK call() throws Exception {
        TrafficStats.setThreadStatsTag("AppsFlyer".hashCode());
        this.AFKeystoreWrapper = null;
        this.f63d = null;
        long currentTimeMillis = System.currentTimeMillis();
        this.values++;
        try {
            AFe1cSDK AFInAppEventType = AFInAppEventType();
            this.AFKeystoreWrapper = AFInAppEventType;
            return AFInAppEventType;
        } finally {
        }
    }

    public final Throwable d() {
        return this.f63d;
    }

    @Override // java.lang.Comparable
    /* renamed from: AFInAppEventParameterName */
    public final int compareTo(AFe1fSDK<?> aFe1fSDK) {
        int i2 = this.AFInAppEventType.afErrorLog - aFe1fSDK.AFInAppEventType.afErrorLog;
        if (i2 == 0) {
            if (this.unregisterClient.equals(aFe1fSDK.unregisterClient)) {
                return 0;
            }
            return this.registerClient - aFe1fSDK.registerClient;
        }
        return i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AFe1fSDK aFe1fSDK = (AFe1fSDK) obj;
        if (this.AFInAppEventType != aFe1fSDK.AFInAppEventType) {
            return false;
        }
        return this.unregisterClient.equals(aFe1fSDK.unregisterClient);
    }

    public final int hashCode() {
        return (this.AFInAppEventType.hashCode() * 31) + this.unregisterClient.hashCode();
    }

    public String toString() {
        String obj = new StringBuilder().append(this.AFInAppEventType).append("-").append(this.unregisterClient).toString();
        return !String.valueOf(this.registerClient).equals(this.unregisterClient) ? new StringBuilder().append(obj).append("-").append(this.registerClient).toString() : obj;
    }
}
