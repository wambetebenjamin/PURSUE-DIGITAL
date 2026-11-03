package com.bytedance.apm.config;
/* compiled from: ApmReportConfig.java */
/* loaded from: classes.dex */
public class d {
    public static final int REPORT_SMALL_BATCH_ONCE_SIZE_BYTES = 512000;

    /* renamed from: a  reason: collision with root package name */
    private int f880a;

    /* renamed from: b  reason: collision with root package name */
    private long f881b;

    public static a newBuilder() {
        return new a();
    }

    private d(a aVar) {
        this.f880a = aVar.f882a;
        this.f881b = aVar.f883b;
    }

    public int getReportMode() {
        return this.f880a;
    }

    public long getOnceReportMaxSizeBytes() {
        return this.f881b;
    }

    /* compiled from: ApmReportConfig.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private int f882a = 0;

        /* renamed from: b  reason: collision with root package name */
        private long f883b = 2147483647L;

        a() {
        }

        public a setReportMode(int i2) {
            this.f882a = i2;
            if (i2 == 1) {
                this.f883b = 512000L;
            }
            return this;
        }

        public a setOnceReportMaxSizeBytes(int i2) {
            this.f883b = i2;
            return this;
        }

        public d build() {
            return new d(this);
        }
    }
}
