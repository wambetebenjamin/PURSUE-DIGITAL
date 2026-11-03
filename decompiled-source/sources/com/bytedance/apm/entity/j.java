package com.bytedance.apm.entity;

import java.util.ArrayList;
import java.util.List;
/* compiled from: TrafficEntity.java */
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private long f1036a;

    /* renamed from: b  reason: collision with root package name */
    private long f1037b;
    public long backTotalBytes;

    /* renamed from: c  reason: collision with root package name */
    private long f1038c;

    /* renamed from: d  reason: collision with root package name */
    private long f1039d;

    /* renamed from: e  reason: collision with root package name */
    private long f1040e;

    /* renamed from: f  reason: collision with root package name */
    private long f1041f;
    public long frontTotalBytes;

    /* renamed from: g  reason: collision with root package name */
    private long f1042g;

    /* renamed from: h  reason: collision with root package name */
    private long f1043h;
    public long sid;

    public List<k> convertToTrafficLogList() {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f1039d > 0) {
            arrayList.add(new k(this.f1039d, 1, 0, 0, currentTimeMillis));
        }
        if (this.f1038c > 0) {
            arrayList.add(new k(this.f1038c, 1, 0, 1, currentTimeMillis));
        }
        if (this.f1037b > 0) {
            arrayList.add(new k(this.f1037b, 1, 1, 0, currentTimeMillis));
        }
        if (this.f1036a > 0) {
            arrayList.add(new k(this.f1036a, 1, 1, 1, currentTimeMillis));
        }
        if (this.f1043h > 0) {
            arrayList.add(new k(this.f1043h, 0, 0, 0, currentTimeMillis));
        }
        if (this.f1042g > 0) {
            arrayList.add(new k(this.f1042g, 0, 0, 1, currentTimeMillis));
        }
        if (this.f1041f > 0) {
            arrayList.add(new k(this.f1041f, 0, 1, 0, currentTimeMillis));
        }
        if (this.f1040e > 0) {
            arrayList.add(new k(this.f1040e, 0, 1, 1, currentTimeMillis));
        }
        return arrayList;
    }

    public void setSid(long j2) {
        this.sid = j2;
    }

    public long getFrontWifiSendBytes() {
        return this.f1036a;
    }

    public void setFrontWifiSendBytes(long j2) {
        this.f1036a = j2;
    }

    public long getFrontWifiRecBytes() {
        return this.f1037b;
    }

    public void setFrontWifiRecBytes(long j2) {
        this.f1037b = j2;
    }

    public long getFrontMobileSendBytes() {
        return this.f1038c;
    }

    public void setFrontMobileSendBytes(long j2) {
        this.f1038c = j2;
    }

    public long getFrontMobileRecBytes() {
        return this.f1039d;
    }

    public void setFrontMobileRecBytes(long j2) {
        this.f1039d = j2;
    }

    public long getBackWifiSendBytes() {
        return this.f1040e;
    }

    public void setBackWifiSendBytes(long j2) {
        this.f1040e = j2;
    }

    public long getBackWifiRecBytes() {
        return this.f1041f;
    }

    public void setBackWifiRecBytes(long j2) {
        this.f1041f = j2;
    }

    public long getBackMobileSendBytes() {
        return this.f1042g;
    }

    public void setBackMobileSendBytes(long j2) {
        this.f1042g = j2;
    }

    public long getBackMobileRecBytes() {
        return this.f1043h;
    }

    public void setBackMobileRecBytes(long j2) {
        this.f1043h = j2;
    }

    public long getFrontTotalBytes() {
        return this.frontTotalBytes;
    }

    public void setFrontTotalBytes(long j2) {
        this.frontTotalBytes = j2;
    }

    public long getBackTotalBytes() {
        return this.backTotalBytes;
    }

    public void setBackTotalBytes(long j2) {
        this.backTotalBytes = j2;
    }

    public String toString() {
        return "TrafficEntity{frontWifiSendBytes=" + this.f1036a + ", frontWifiRecBytes=" + this.f1037b + ", frontMobileSendBytes=" + this.f1038c + ", frontMobileRecBytes=" + this.f1039d + ", backWifiSendBytes=" + this.f1040e + ", backWifiRecBytes=" + this.f1041f + ", backMobileSendBytes=" + this.f1042g + ", backMobileRecBytes=" + this.f1043h + ", frontTotalBytes=" + this.frontTotalBytes + ", backTotalBytes=" + this.backTotalBytes + "=" + this.sid + '}';
    }
}
