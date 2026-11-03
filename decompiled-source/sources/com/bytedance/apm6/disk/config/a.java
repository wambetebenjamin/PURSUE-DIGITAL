package com.bytedance.apm6.disk.config;

import java.util.List;
/* compiled from: DiskConfig.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2133a;

    /* renamed from: b  reason: collision with root package name */
    private long f2134b;

    /* renamed from: c  reason: collision with root package name */
    private long f2135c;

    /* renamed from: d  reason: collision with root package name */
    private long f2136d;

    /* renamed from: e  reason: collision with root package name */
    private int f2137e;

    /* renamed from: f  reason: collision with root package name */
    private int f2138f;

    /* renamed from: g  reason: collision with root package name */
    private int f2139g;

    /* renamed from: h  reason: collision with root package name */
    private int f2140h;

    /* renamed from: i  reason: collision with root package name */
    private long f2141i;

    /* renamed from: j  reason: collision with root package name */
    private List<String> f2142j;
    private List<String> k;
    private boolean l;
    private List<String> m;

    public boolean isEnable() {
        return this.f2133a;
    }

    public void setEnable(boolean z) {
        this.f2133a = z;
    }

    public void setSample(boolean z) {
        this.l = z;
    }

    public boolean isSample() {
        return this.l;
    }

    public long getDiskThresholdBytes() {
        return this.f2134b;
    }

    public void setDiskThresholdBytes(long j2) {
        this.f2134b = j2;
    }

    public long getFolderThresholdBytes() {
        return this.f2135c;
    }

    public void setFolderThresholdBytes(long j2) {
        this.f2135c = j2;
    }

    public long getFileThresholdBytes() {
        return this.f2136d;
    }

    public void setFileThresholdBytes(long j2) {
        this.f2136d = j2;
    }

    public int getReportMaxCount() {
        return this.f2137e;
    }

    public void setReportMaxCount(int i2) {
        this.f2137e = i2;
    }

    public int getReportMaxOutdatedCount() {
        return this.f2138f;
    }

    public void setReportMaxOutdatedCount(int i2) {
        this.f2138f = i2;
    }

    public int getReportMaxTopCount() {
        return this.f2139g;
    }

    public void setReportMaxTopCount(int i2) {
        this.f2139g = i2;
    }

    public int getReportExceptionDirMaxCount() {
        return this.f2140h;
    }

    public void setReportExceptionDirMaxCount(int i2) {
        this.f2140h = i2;
    }

    public void setOutdatedIntervalMs(long j2) {
        this.f2141i = j2;
    }

    public long getOutdatedIntervalMs() {
        return this.f2141i;
    }

    public List<String> getCustomRelativePaths() {
        return this.f2142j;
    }

    public void setCustomRelativePaths(List<String> list) {
        this.f2142j = list;
    }

    public List<String> getIgnoredRelativePaths() {
        return this.k;
    }

    public List<String> a() {
        return this.m;
    }

    public void setIgnoredRelativePaths(List<String> list) {
        this.k = list;
    }

    public void a(List<String> list) {
        this.m = list;
    }

    public String toString() {
        return "DiskConfig{isEnable=" + this.f2133a + ", diskThresholdBytes=" + this.f2134b + ", folderThresholdBytes=" + this.f2135c + ", fileThresholdBytes=" + this.f2136d + ", reportMaxCount=" + this.f2137e + ", reportMaxOutdatedCount=" + this.f2138f + ", reportMaxTopCount=" + this.f2139g + ", reportExceptionDirMaxCount=" + this.f2140h + ", outdatedIntervalMs=" + this.f2141i + ", customRelativePaths=" + this.f2142j + ", ignoredRelativePaths=" + this.k + ", complianceRelativePaths=" + this.m + ", isSample=" + this.l + '}';
    }
}
