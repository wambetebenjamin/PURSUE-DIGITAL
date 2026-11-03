package com.bytedancehttpdns.httpdns;
/* loaded from: classes8.dex */
public enum DnsErrorCode {
    OK(0),
    UNKNOWN(-1),
    INVALID_FUTURE(-2),
    EMPTY_CACHE(-3),
    INVALID_HOST(-4),
    PREFER_TIMEOUT(-5),
    LOCALDNS_RESOLVE_FAIL(-6);
    
    public final int mValue;

    DnsErrorCode(int i2) {
        this.mValue = i2;
    }
}
