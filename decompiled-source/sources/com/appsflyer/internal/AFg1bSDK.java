package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.deeplink.DeepLinkResult;
import com.bytedance.apm.perf.traffic.a;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class AFg1bSDK {
    public final Map<String, Object> AFInAppEventParameterName;
    public final Map<String, Object> AFInAppEventType;
    public final AFd1xSDK AFKeystoreWrapper;
    public final long[] AFLogger;

    /* renamed from: d  reason: collision with root package name */
    public long f86d;

    /* renamed from: e  reason: collision with root package name */
    public final long[] f87e;

    /* renamed from: i  reason: collision with root package name */
    public long f88i;
    public final long[] registerClient;
    public long unregisterClient;
    public long v;
    public long valueOf;
    public final Map<String, Object> values;

    public AFg1bSDK(AFd1xSDK aFd1xSDK) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.AFInAppEventParameterName = concurrentHashMap;
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        this.values = concurrentHashMap2;
        ConcurrentHashMap concurrentHashMap3 = new ConcurrentHashMap();
        this.AFInAppEventType = concurrentHashMap3;
        this.valueOf = 0L;
        this.unregisterClient = 0L;
        this.AFLogger = new long[2];
        this.registerClient = new long[2];
        this.f87e = new long[2];
        this.f86d = 0L;
        this.v = 0L;
        this.AFKeystoreWrapper = aFd1xSDK;
        concurrentHashMap.putAll(AFInAppEventParameterName("first_launch"));
        concurrentHashMap2.putAll(AFInAppEventParameterName("ddl"));
        concurrentHashMap3.putAll(AFInAppEventParameterName("gcd"));
        this.f88i = aFd1xSDK.AFInAppEventType("prev_session_dur", 0L);
    }

    public final void AFInAppEventParameterName() {
        this.unregisterClient = System.currentTimeMillis();
        if (values()) {
            long j2 = this.valueOf;
            if (j2 != 0) {
                this.AFInAppEventParameterName.put("init_to_fg", Long.valueOf(this.unregisterClient - j2));
                this.AFKeystoreWrapper.values("first_launch", new JSONObject(this.AFInAppEventParameterName).toString());
                return;
            }
            AFLogger.afInfoLog("Metrics: init ts is missing");
        }
    }

    public final void AFInAppEventParameterName(AFg1fSDK aFg1fSDK) {
        if (values()) {
            this.AFInAppEventParameterName.put("start_with", aFg1fSDK.toString());
            this.AFKeystoreWrapper.values("first_launch", new JSONObject(this.AFInAppEventParameterName).toString());
        }
    }

    public final void AFInAppEventParameterName(int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        this.f86d = currentTimeMillis;
        if (i2 == 1) {
            long j2 = this.unregisterClient;
            if (j2 != 0) {
                this.AFInAppEventParameterName.put("from_fg", Long.valueOf(currentTimeMillis - j2));
                this.AFKeystoreWrapper.values("first_launch", new JSONObject(this.AFInAppEventParameterName).toString());
                return;
            }
            AFLogger.afInfoLog("Metrics: fg ts is missing");
        }
    }

    public final void valueOf(DeepLinkResult deepLinkResult, long j2) {
        this.values.put("status", deepLinkResult.getStatus().toString());
        this.values.put("timeout_value", Long.valueOf(j2));
        this.AFKeystoreWrapper.values("ddl", new JSONObject(this.values).toString());
    }

    public final void values(int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.v;
        if (j2 != 0) {
            this.AFInAppEventType.put(a.KEY_TRAFFIC_STATUS_NET, Long.valueOf(currentTimeMillis - j2));
        } else {
            AFLogger.afInfoLog("Metrics: gcdStart ts is missing");
        }
        this.AFInAppEventType.put("retries", Integer.valueOf(i2));
        this.AFKeystoreWrapper.values("gcd", new JSONObject(this.AFInAppEventType).toString());
    }

    private Map<String, Object> AFInAppEventParameterName(String str) {
        Map<String, Object> emptyMap = Collections.emptyMap();
        String valueOf = this.AFKeystoreWrapper.valueOf(str, (String) null);
        if (valueOf != null) {
            try {
                return AFa1oSDK.AFInAppEventType(new JSONObject(valueOf));
            } catch (Exception e2) {
                AFLogger.afErrorLog("Error while parsing cached json data", e2, true);
                return emptyMap;
            }
        }
        return emptyMap;
    }

    public final boolean values() {
        return this.AFKeystoreWrapper.valueOf("appsFlyerCount", 0) == 0;
    }
}
