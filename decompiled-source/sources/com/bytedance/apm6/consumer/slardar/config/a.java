package com.bytedance.apm6.consumer.slardar.config;

import android.text.TextUtils;
import com.bytedance.apm6.util.g;
import java.util.ArrayList;
import java.util.List;
/* compiled from: SlardarHandlerConfig.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: j  reason: collision with root package name */
    public static final int f1754j = 1;
    public static final int k = 2;

    /* renamed from: a  reason: collision with root package name */
    private long f1755a;

    /* renamed from: b  reason: collision with root package name */
    private List<String> f1756b;

    /* renamed from: c  reason: collision with root package name */
    private List<String> f1757c;

    /* renamed from: d  reason: collision with root package name */
    private List<String> f1758d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1759e = true;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1760f = true;

    /* renamed from: g  reason: collision with root package name */
    private long f1761g;

    /* renamed from: h  reason: collision with root package name */
    private int f1762h;

    /* renamed from: i  reason: collision with root package name */
    private int f1763i;
    private int l;
    private int m;

    public void a(int i2) {
        this.m = i2;
    }

    public int a() {
        return this.m;
    }

    public void setOnceReportMaxSizeBytes(long j2) {
        this.f1755a = j2;
    }

    public void setReportInterval(long j2) {
        this.f1761g = j2;
    }

    public void setReportUrlList(List<String> list) {
        if (g.isEmpty(list)) {
            return;
        }
        this.f1756b = list;
    }

    public List<String> getReportUrlList() {
        return this.f1756b;
    }

    public void setExceptionUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.f1757c = arrayList;
        arrayList.add(str);
    }

    public void setTraceReportUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.f1758d = arrayList;
        arrayList.add(str);
    }

    public List<String> getTraceReportUrl() {
        return this.f1758d;
    }

    public List<String> getExceptionUrl() {
        return this.f1757c;
    }

    public void setEncrypt(boolean z) {
        this.f1759e = z;
    }

    public boolean isEncrypt() {
        return this.f1759e;
    }

    public boolean isUploadInternalException() {
        return this.f1760f;
    }

    public void setUploadInternalException(boolean z) {
        this.f1760f = z;
    }

    public long getOnceReportMaxSizeBytes() {
        return this.f1755a;
    }

    public long getReportInterval() {
        return this.f1761g;
    }

    public int getMaxSizeMB() {
        return this.f1762h;
    }

    public void setMaxSizeMB(int i2) {
        this.f1762h = i2;
    }

    public int getKeepDays() {
        return this.f1763i;
    }

    public void setKeepDays(int i2) {
        this.f1763i = i2;
    }

    public int b() {
        return this.l;
    }

    public void b(int i2) {
        this.l = i2;
    }

    public String toString() {
        return "SlardarHandlerConfig{onceReportMaxSizeBytes=" + this.f1755a + ", reportUrlList=" + this.f1756b + ", exceptionUrl=" + this.f1757c + ", traceReportUrl=" + this.f1758d + ", isEncrypt=" + this.f1759e + ", isUploadInternalExcetpion=" + this.f1760f + ", reportInterval=" + this.f1761g + ", maxSizeMB=" + this.f1762h + ", keepDays=" + this.f1763i + ", maxSizeMBToday=" + this.l + '}';
    }
}
