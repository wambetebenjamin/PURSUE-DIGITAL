package com.bytedancehttpdns.httpdns;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.bytedancehttpdns.httpdns.util.WeakHandler;
import java.util.List;
/* loaded from: classes8.dex */
public class DnsRecord {
    public static final String MSG_DNSRECORD_HOST = "dnsrecord_host";
    public static final int MSG_HTTPDNS_CACHE_EXPIRED = 12;
    public static final int MSG_REFRESH_HTTPDNS_STALE_CACHE = 10;
    public static final int MSG_REMOVE_HTTPDNS_STALE_CACHE = 13;
    public static final int MSG_REMOVE_LOCALDNS_STALE_CACHE = 11;
    private static final String TAG = "veHttpDns";
    private final String mCip;
    private final long mFetchTime;
    private final Handler mHandler = new WeakHandler(HttpDns.getService().getHttpDnsHandler().getLooper(), HttpDns.getService());
    private final String mHost;
    private final List<String> mIpv4List;
    private final List<String> mIpv6List;
    private final long mRtt;
    private final long mTTL;

    /* loaded from: classes8.dex */
    public enum CacheStaleReason {
        CACHE_UNSET,
        CACHE_VALID,
        CACHE_STALE_BOTH,
        CACHE_STALE_NETCHANGED,
        CACHE_STALE_EXPIRED,
        PRELOAD_BATCH,
        REFRESH_BATCH
    }

    public DnsRecord(String str, long j2, List<String> list, List<String> list2, int i2, long j3, String str2) {
        this.mHost = str;
        this.mFetchTime = j2;
        this.mIpv4List = list;
        this.mIpv6List = list2;
        this.mTTL = i2;
        this.mRtt = j3;
        this.mCip = str2;
    }

    public void addHttpDnsRefreshTask() {
        if (HttpDns.getService().batchRefreshEnabled()) {
            Message obtain = Message.obtain();
            obtain.obj = this;
            obtain.what = 12;
            setHostToMessage(obtain);
            this.mHandler.sendMessageDelayed(obtain, this.mTTL * 1000);
        }
        Message obtain2 = Message.obtain();
        obtain2.obj = this;
        obtain2.what = 10;
        setHostToMessage(obtain2);
        this.mHandler.sendMessageDelayed(obtain2, (this.mTTL * 1000) + (HttpDns.getService().getHttpDnsRefreshStaleCacheInterval().get() * 1000));
    }

    public void addHttpDnsRemoveTask() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 13;
        setHostToMessage(obtain);
        this.mHandler.sendMessageDelayed(obtain, this.mTTL * 1000);
    }

    public void removeHttpDnsTask() {
        if (this.mHandler.hasMessages(10, this)) {
            this.mHandler.removeMessages(10, this);
        }
        if (this.mHandler.hasMessages(12, this)) {
            this.mHandler.removeMessages(12, this);
        }
        if (this.mHandler.hasMessages(13, this)) {
            this.mHandler.removeMessages(13, this);
        }
    }

    public void addLocalDnsRemoveTask() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 11;
        setHostToMessage(obtain);
        this.mHandler.sendMessageDelayed(obtain, HttpDns.getService().getLocalDnsCacheTTL().get() * 1000);
    }

    public void removeLocalDnsRemoveTask() {
        this.mHandler.removeMessages(11, this);
    }

    public long getFetchTime() {
        return this.mFetchTime;
    }

    public long getTTL() {
        return this.mTTL;
    }

    public List<String> getIpv4List() {
        return this.mIpv4List;
    }

    public List<String> getIpv6List() {
        return this.mIpv6List;
    }

    public String getHost() {
        return this.mHost;
    }

    public String getCip() {
        return this.mCip;
    }

    public long getRtt() {
        return this.mRtt;
    }

    private void setHostToMessage(Message message) {
        Bundle bundle = new Bundle();
        bundle.putString("dnsrecord_host", this.mHost);
        message.setData(bundle);
    }
}
