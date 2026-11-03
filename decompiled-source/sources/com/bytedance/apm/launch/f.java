package com.bytedance.apm.launch;
/* compiled from: LaunchInitConfig.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1176a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f1177b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1178c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1179d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1180e;

    /* renamed from: f  reason: collision with root package name */
    private long f1181f;

    /* renamed from: g  reason: collision with root package name */
    private long f1182g;

    public f(boolean z, boolean z2, boolean z3, long j2, boolean z4, long j3, boolean z5) {
        this.f1176a = z;
        this.f1177b = z2;
        this.f1178c = z3;
        this.f1181f = j2;
        this.f1179d = z4;
        this.f1182g = j3;
        this.f1180e = z5;
    }

    public boolean isNeedCollectPerfData() {
        return this.f1176a;
    }

    public boolean isNeedCollectNetData() {
        return this.f1177b;
    }

    public boolean isNeedCollectTimingTrace() {
        return this.f1178c;
    }

    public boolean isNeedCollectDeviceInfo() {
        return this.f1179d;
    }

    public long getMaxCollectTimeMs() {
        return this.f1181f;
    }

    public long getSlowLaunchThreshold() {
        return this.f1182g;
    }

    public boolean isNeedCollectSlowLaunchTrace() {
        return this.f1180e;
    }

    /* compiled from: LaunchInitConfig.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final long f1183a = 1000;

        /* renamed from: b  reason: collision with root package name */
        private boolean f1184b = false;

        /* renamed from: c  reason: collision with root package name */
        private boolean f1185c = false;

        /* renamed from: d  reason: collision with root package name */
        private boolean f1186d = false;

        /* renamed from: e  reason: collision with root package name */
        private boolean f1187e = false;

        /* renamed from: f  reason: collision with root package name */
        private boolean f1188f = false;

        /* renamed from: g  reason: collision with root package name */
        private long f1189g = -1;

        /* renamed from: h  reason: collision with root package name */
        private long f1190h = 1000;

        public a collectPerfData() {
            this.f1184b = true;
            return this;
        }

        public a collectNetData() {
            this.f1185c = true;
            return this;
        }

        public a collectTimingTrace() {
            this.f1186d = true;
            return this;
        }

        public a collectDeviceInfo() {
            this.f1187e = true;
            return this;
        }

        public a collectSlowLaunchTrace() {
            this.f1188f = true;
            return this;
        }

        public a setSlowLaunchThreshold(long j2) {
            this.f1190h = j2;
            return this;
        }

        public a setMaxCollectTimeMs(long j2) {
            this.f1189g = j2;
            return this;
        }

        public f build() {
            return new f(this.f1184b, this.f1185c, this.f1186d, this.f1189g, this.f1187e, this.f1190h, this.f1188f);
        }
    }
}
