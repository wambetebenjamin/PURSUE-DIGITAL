package com.google.firebase.remoteconfig;
/* loaded from: classes9.dex */
public class FirebaseRemoteConfigFetchThrottledException extends FirebaseRemoteConfigException {
    private final long throttleEndTimeMillis;

    public FirebaseRemoteConfigFetchThrottledException(long j2) {
        this("Fetch was throttled.", j2);
    }

    public FirebaseRemoteConfigFetchThrottledException(String str, long j2) {
        super(str);
        this.throttleEndTimeMillis = j2;
    }

    public long getThrottleEndTimeMillis() {
        return this.throttleEndTimeMillis;
    }
}
