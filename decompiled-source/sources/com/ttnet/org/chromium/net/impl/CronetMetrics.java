package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.RequestFinishedInfo;
import java.util.Date;
/* loaded from: classes9.dex */
public final class CronetMetrics extends RequestFinishedInfo.Metrics {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Long mAddCookieCostUs;
    private long mBizReadResponseBodyInterval;
    private boolean mBypassApiReport;
    private final long mConnectEndMs;
    private final long mConnectStartMs;
    private final long mDnsEndMs;
    private final long mDnsStartMs;
    private long mFirstReadResponseBodyStart;
    private long mLastReadResponseBodyEnd;
    private final String mPeerAddr;
    private final Long mPeerPort;
    private boolean mPreSamplingReportable;
    private final long mPushEndMs;
    private final long mPushStartMs;
    private long mReadResponseBodyDuration;
    private final Long mReceivedByteCount;
    private Integer mRequestCookieSource;
    private final long mRequestEndMs;
    private final String mRequestHeaders;
    private String mRequestLog;
    private final long mRequestStartMs;
    private long mResponseBodyReadTimes;
    private final String mResponseHeaders;
    private final long mResponseStartMs;
    private final Long mRetryAttempts;
    private Long mSaveCookieCostUs;
    private final long mSendingEndMs;
    private final long mSendingStartMs;
    private final Long mSentByteCount;
    private final boolean mSocketReused;
    private final long mSslEndMs;
    private final long mSslStartMs;
    private final Long mTotalTimeMs;
    private final Long mTtfbMs;
    private int mURLDispatchDropReason;

    private static boolean checkOrder(long j2, long j3) {
        return (j3 >= j2 && j2 != -1) || j3 == -1;
    }

    private static Date toDate(long j2) {
        if (j2 != -1) {
            return new Date(j2);
        }
        return null;
    }

    public CronetMetrics(Long l, Long l2, Long l3, Long l4) {
        this.mResponseBodyReadTimes = 0L;
        this.mFirstReadResponseBodyStart = -1L;
        this.mLastReadResponseBodyEnd = -1L;
        this.mReadResponseBodyDuration = -1L;
        this.mBizReadResponseBodyInterval = -1L;
        this.mTtfbMs = l;
        this.mTotalTimeMs = l2;
        this.mSentByteCount = l3;
        this.mReceivedByteCount = l4;
        this.mRequestStartMs = -1L;
        this.mDnsStartMs = -1L;
        this.mDnsEndMs = -1L;
        this.mConnectStartMs = -1L;
        this.mConnectEndMs = -1L;
        this.mSslStartMs = -1L;
        this.mSslEndMs = -1L;
        this.mSendingStartMs = -1L;
        this.mSendingEndMs = -1L;
        this.mPushStartMs = -1L;
        this.mPushEndMs = -1L;
        this.mResponseStartMs = -1L;
        this.mRequestEndMs = -1L;
        this.mSocketReused = false;
        this.mPeerAddr = "";
        this.mPeerPort = 0L;
        this.mRetryAttempts = 0L;
        this.mRequestHeaders = "";
        this.mResponseHeaders = "";
        this.mAddCookieCostUs = -1L;
        this.mSaveCookieCostUs = -1L;
        this.mRequestCookieSource = -1;
        this.mPreSamplingReportable = false;
        this.mBypassApiReport = false;
    }

    public CronetMetrics(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, boolean z, long j15, long j16, String str, long j17, long j18, String str2, String str3, long j19, long j20, int i2, boolean z2, boolean z3, int i3) {
        this.mResponseBodyReadTimes = 0L;
        this.mFirstReadResponseBodyStart = -1L;
        this.mLastReadResponseBodyEnd = -1L;
        this.mReadResponseBodyDuration = -1L;
        this.mBizReadResponseBodyInterval = -1L;
        this.mRequestStartMs = j2;
        this.mDnsStartMs = j3;
        this.mDnsEndMs = j4;
        this.mConnectStartMs = j5;
        this.mConnectEndMs = j6;
        this.mSslStartMs = j7;
        this.mSslEndMs = j8;
        this.mSendingStartMs = j9;
        this.mSendingEndMs = j10;
        this.mPushStartMs = j11;
        this.mPushEndMs = j12;
        this.mResponseStartMs = j13;
        this.mRequestEndMs = j14;
        this.mSocketReused = z;
        this.mSentByteCount = Long.valueOf(j15);
        this.mReceivedByteCount = Long.valueOf(j16);
        this.mAddCookieCostUs = Long.valueOf(j19);
        this.mSaveCookieCostUs = Long.valueOf(j20);
        this.mRequestCookieSource = Integer.valueOf(i2);
        this.mPreSamplingReportable = z2;
        this.mBypassApiReport = z3;
        this.mURLDispatchDropReason = i3;
        this.mPeerAddr = str;
        this.mPeerPort = Long.valueOf(j17);
        this.mRetryAttempts = Long.valueOf(j18);
        if (j13 != -1 && j10 != -1) {
            this.mTtfbMs = Long.valueOf(j13 - j10);
        } else {
            this.mTtfbMs = null;
        }
        if (j2 != -1 && j14 != -1) {
            this.mTotalTimeMs = Long.valueOf(j14 - j2);
        } else {
            this.mTotalTimeMs = null;
        }
        this.mRequestHeaders = str2;
        this.mResponseHeaders = str3;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getRequestStart() {
        return toDate(this.mRequestStartMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getDnsStart() {
        return toDate(this.mDnsStartMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getDnsEnd() {
        return toDate(this.mDnsEndMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getConnectStart() {
        return toDate(this.mConnectStartMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getConnectEnd() {
        return toDate(this.mConnectEndMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getSslStart() {
        return toDate(this.mSslStartMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getSslEnd() {
        return toDate(this.mSslEndMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getSendingStart() {
        return toDate(this.mSendingStartMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getSendingEnd() {
        return toDate(this.mSendingEndMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getPushStart() {
        return toDate(this.mPushStartMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getPushEnd() {
        return toDate(this.mPushEndMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getResponseStart() {
        return toDate(this.mResponseStartMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Date getRequestEnd() {
        return toDate(this.mRequestEndMs);
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public boolean getSocketReused() {
        return this.mSocketReused;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Long getTtfbMs() {
        return this.mTtfbMs;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Long getTotalTimeMs() {
        return this.mTotalTimeMs;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Long getSentByteCount() {
        return this.mSentByteCount;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public String getPeerAddr() {
        return this.mPeerAddr;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Long getPeerPort() {
        return this.mPeerPort;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Long getRetryAttempts() {
        return this.mRetryAttempts;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public String getRequestHeaders() {
        return this.mRequestHeaders;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public String getResponseHeaders() {
        return this.mResponseHeaders;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Long getAddCookieCostUs() {
        return this.mAddCookieCostUs;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Long getSaveCookieCostUs() {
        return this.mSaveCookieCostUs;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Integer getRequestCookieSource() {
        return this.mRequestCookieSource;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public boolean getPreSamplingReportable() {
        return this.mPreSamplingReportable;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public boolean getBypassApiReport() {
        return this.mBypassApiReport;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public int getURLDispatchDropReason() {
        return this.mURLDispatchDropReason;
    }

    public void setRequestLog(String str) {
        this.mRequestLog = str;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public String getRequestLog() {
        return this.mRequestLog;
    }

    public void setReadResponseBodyMetrics(long j2, long j3, long j4, long j5, long j6) {
        this.mResponseBodyReadTimes = j2;
        this.mFirstReadResponseBodyStart = j3;
        this.mLastReadResponseBodyEnd = j4;
        this.mReadResponseBodyDuration = j5;
        this.mBizReadResponseBodyInterval = j6;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public long getResponseBodyReadTimes() {
        return this.mResponseBodyReadTimes;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public long getReadResponseBodyDuration() {
        return this.mReadResponseBodyDuration;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public long getBizReadResponseBodyInterval() {
        return this.mBizReadResponseBodyInterval;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public long getFirstReadResponseBodyStart() {
        return this.mFirstReadResponseBodyStart;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public long getLastReadResponseBodyEnd() {
        return this.mLastReadResponseBodyEnd;
    }

    @Override // com.ttnet.org.chromium.net.RequestFinishedInfo.Metrics
    public Long getReceivedByteCount() {
        return this.mReceivedByteCount;
    }
}
