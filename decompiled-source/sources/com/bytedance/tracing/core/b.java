package com.bytedance.tracing.core;
/* compiled from: SpanContext.java */
@Deprecated
/* loaded from: classes3.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final String f14034a;

    /* renamed from: b  reason: collision with root package name */
    private final long f14035b;

    /* renamed from: c  reason: collision with root package name */
    private long f14036c;

    /* renamed from: d  reason: collision with root package name */
    private final String f14037d;

    /* renamed from: e  reason: collision with root package name */
    private final long f14038e;

    /* renamed from: f  reason: collision with root package name */
    private long f14039f;

    /* renamed from: g  reason: collision with root package name */
    private d f14040g;

    /* compiled from: SpanContext.java */
    /* loaded from: classes3.dex */
    public static final class a {
    }

    b(String str, long j2, long j3, String str2, long j4, long j5) {
        this.f14034a = str;
        this.f14035b = j2;
        this.f14036c = j3;
        this.f14037d = str2;
        this.f14038e = j4;
        this.f14039f = j5;
    }

    public b(String str, String str2) {
        this.f14034a = str;
        this.f14037d = str2;
        this.f14035b = com.bytedance.tracing.internal.utils.a.uniqueId();
        this.f14038e = com.bytedance.tracing.internal.utils.a.uniqueId();
    }

    public synchronized d getTracer() {
        if (this.f14040g == null) {
            this.f14040g = new d(this);
        }
        return this.f14040g;
    }

    public b makeChild(String str) {
        return new b(this.f14034a, this.f14035b, this.f14038e, str, com.bytedance.tracing.internal.utils.a.uniqueId(), 0L);
    }

    public b makeChildAndRef(String str, long j2) {
        return new b(this.f14034a, this.f14035b, this.f14038e, str, com.bytedance.tracing.internal.utils.a.uniqueId(), j2);
    }

    public String toString() {
        return "SpanContext{service='" + this.f14034a + "', traceId='" + this.f14035b + "', parentId='" + this.f14036c + "', operationName='" + this.f14037d + "', spanId='" + this.f14038e + "'}";
    }

    public String getService() {
        return this.f14034a;
    }

    public long getTraceId() {
        return this.f14035b;
    }

    public long getParentId() {
        return this.f14036c;
    }

    public String getOperationName() {
        return this.f14037d;
    }

    public long getSpanId() {
        return this.f14038e;
    }

    public long getRefId() {
        return this.f14039f;
    }
}
