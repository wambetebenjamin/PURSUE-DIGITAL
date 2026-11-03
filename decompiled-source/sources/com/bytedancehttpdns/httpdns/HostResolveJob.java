package com.bytedancehttpdns.httpdns;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class HostResolveJob implements Serializable, Comparable<HostResolveJob> {
    private long mCreatedTime = HttpDns.getService().getCurrentTimeStamps();
    private String mHost;
    public IHttpDnsCallback mHttpDnsCallback;
    private boolean mIsLocalDnsExpired;

    public HostResolveJob(String str, IHttpDnsCallback iHttpDnsCallback, boolean z) {
        this.mHost = str;
        this.mHttpDnsCallback = iHttpDnsCallback;
        this.mIsLocalDnsExpired = z;
    }

    @Override // java.lang.Comparable
    public int compareTo(HostResolveJob hostResolveJob) {
        long j2 = hostResolveJob.mCreatedTime;
        long j3 = this.mCreatedTime;
        if (j2 == j3) {
            return 0;
        }
        return j2 < j3 ? -1 : 1;
    }

    public String getHost() {
        return this.mHost;
    }

    public boolean isLocalDnsExpired() {
        return this.mIsLocalDnsExpired;
    }
}
