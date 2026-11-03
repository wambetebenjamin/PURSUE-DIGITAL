package com.bytedance.apm.config;
/* compiled from: ActivityLeakDetectConfig.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private boolean f850a;

    /* renamed from: b  reason: collision with root package name */
    private long f851b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f852c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f853d;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.apm.perf.memory.b f854e;

    public boolean isGcDetect() {
        return this.f850a;
    }

    public long getWaitDetectActivityTimeMs() {
        return this.f851b;
    }

    public boolean isReportActivityLeakEvent() {
        return this.f852c;
    }

    public boolean isUnbindActivityLeak() {
        return this.f853d;
    }

    public com.bytedance.apm.perf.memory.b getActivityLeakListener() {
        return this.f854e;
    }

    public static a builder() {
        return new a();
    }

    public b(a aVar) {
        this.f850a = aVar.f855a;
        this.f851b = aVar.f856b;
        this.f852c = aVar.f857c;
        this.f853d = aVar.f858d;
        this.f854e = aVar.f859e;
    }

    /* compiled from: ActivityLeakDetectConfig.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private boolean f855a;

        /* renamed from: b  reason: collision with root package name */
        private long f856b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f857c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f858d;

        /* renamed from: e  reason: collision with root package name */
        private com.bytedance.apm.perf.memory.b f859e;

        private a() {
            this.f855a = false;
            this.f856b = 60000L;
            this.f857c = false;
            this.f858d = true;
        }

        public a gcDetectActivityLeak(boolean z) {
            this.f855a = z;
            return this;
        }

        public a waitDetectActivityTimeMs(long j2) {
            this.f856b = j2;
            return this;
        }

        public a reportActivityLeakEvent(boolean z) {
            this.f857c = z;
            return this;
        }

        public a unbindActivityLeakSwitch(boolean z) {
            this.f858d = z;
            return this;
        }

        public a activityLeakListener(com.bytedance.apm.perf.memory.b bVar) {
            this.f859e = bVar;
            return this;
        }

        public b build() {
            return new b(this);
        }
    }
}
