package com.bytedance.tracing.core;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* compiled from: Span.java */
@Deprecated
/* loaded from: classes3.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14023a = "Span";

    /* renamed from: b  reason: collision with root package name */
    private long f14024b;

    /* renamed from: c  reason: collision with root package name */
    private long f14025c;

    /* renamed from: d  reason: collision with root package name */
    private String f14026d;

    /* renamed from: e  reason: collision with root package name */
    private String f14027e;

    /* renamed from: f  reason: collision with root package name */
    private long f14028f;

    /* renamed from: g  reason: collision with root package name */
    private long f14029g;

    /* renamed from: h  reason: collision with root package name */
    private long f14030h;

    /* renamed from: i  reason: collision with root package name */
    private long f14031i;

    /* renamed from: j  reason: collision with root package name */
    private String f14032j;
    private Map<String, String> k;
    private List<com.bytedance.tracing.internal.b> l;
    private boolean m;
    private b n;

    public a(b bVar) {
        this.n = bVar;
        this.f14024b = bVar.getTraceId();
        this.f14025c = bVar.getParentId();
        this.f14026d = bVar.getService();
        this.f14027e = bVar.getOperationName();
        this.f14028f = bVar.getSpanId();
        this.f14029g = bVar.getRefId();
    }

    public a(String str, String str2) {
        this(str, str2, com.bytedance.tracing.internal.utils.a.uniqueId(), 0L, com.bytedance.tracing.internal.utils.a.uniqueId(), 0L);
    }

    public a(String str, String str2, long j2, long j3, long j4, long j5) {
        this.f14024b = j2;
        this.f14025c = j3;
        this.f14026d = str;
        this.f14027e = str2;
        this.f14028f = j4;
        this.f14029g = j5;
    }

    public a start() {
        this.f14030h = System.currentTimeMillis();
        return this;
    }

    public void finish() {
        this.f14031i = System.currentTimeMillis();
        this.f14032j = Thread.currentThread().getName();
        com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.tracing.core.a.1
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.apm.data.pipeline.a.getInstance().handle(new com.bytedance.tracing.internal.c(a.this));
            }
        });
    }

    public a makeChild(String str) {
        return new a(this.f14026d, str, this.f14024b, this.f14028f, com.bytedance.tracing.internal.utils.a.uniqueId(), 0L);
    }

    public a makeChildAndRef(String str, long j2) {
        return new a(this.f14026d, str, this.f14024b, this.f14028f, com.bytedance.tracing.internal.utils.a.uniqueId(), j2);
    }

    public a addReference(long j2) {
        this.f14029g = j2;
        return this;
    }

    public a addLog(String str) {
        if (str == null) {
            return this;
        }
        if (this.l == null) {
            this.l = new LinkedList();
        }
        this.l.add(new com.bytedance.tracing.internal.b(System.currentTimeMillis(), str, null));
        return this;
    }

    public a addLog(String str, Map<String, String> map) {
        if (str == null) {
            return this;
        }
        if (this.l == null) {
            this.l = new LinkedList();
        }
        this.l.add(new com.bytedance.tracing.internal.b(System.currentTimeMillis(), str, map));
        return this;
    }

    public a setErrorTag(String str) {
        return addTag("error", str);
    }

    public a addTag(String str, String str2) {
        if (this.k == null) {
            this.k = new HashMap();
        }
        if (TextUtils.equals(str, "error")) {
            this.m = true;
        }
        this.k.put(str, str2);
        return this;
    }

    public long getStartTs() {
        return this.f14030h;
    }

    public long getFinishTs() {
        return this.f14031i;
    }

    public String getThreadName() {
        return this.f14032j;
    }

    public Map<String, String> getTags() {
        return this.k;
    }

    public List<com.bytedance.tracing.internal.b> getLogs() {
        return this.l;
    }

    public boolean isErrorTag() {
        return this.m;
    }

    public long getRefId() {
        return this.f14029g;
    }

    public long getTraceId() {
        return this.f14024b;
    }

    public long getParentId() {
        return this.f14025c;
    }

    public String getOperationName() {
        return this.f14027e;
    }

    public String getServiceName() {
        return this.f14026d;
    }

    public long getSpanId() {
        return this.f14028f;
    }

    public b getContext() {
        return this.n;
    }

    public String toString() {
        return "Span{traceId='" + this.f14024b + "', parentId='" + this.f14025c + "', serviceName='" + this.f14026d + "', operationName='" + this.f14027e + "', spanId='" + this.f14028f + "', refId='" + this.f14029g + "', startTs=" + this.f14030h + ", finishTs=" + this.f14031i + ", threadName='" + this.f14032j + "', tags=" + this.k + ", logs=" + this.l + ", errorTag=" + this.m + '}';
    }
}
