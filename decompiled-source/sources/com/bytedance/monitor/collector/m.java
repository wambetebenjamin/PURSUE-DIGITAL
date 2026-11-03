package com.bytedance.monitor.collector;
/* compiled from: MonitorConfig.java */
/* loaded from: classes2.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private boolean f9603a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f9604b;

    /* renamed from: d  reason: collision with root package name */
    private boolean f9605d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f9606e;

    /* renamed from: f  reason: collision with root package name */
    private long f9607f;

    /* renamed from: g  reason: collision with root package name */
    private int f9608g;

    /* renamed from: h  reason: collision with root package name */
    private long f9609h = 0;

    public m(a aVar) {
        this.f9603a = aVar.f9610a;
        this.f9604b = aVar.f9611b;
        this.f9605d = aVar.f9613d;
        this.f9607f = aVar.f9615f;
        this.f9608g = aVar.f9616g;
        this.f9606e = aVar.f9614e;
    }

    public boolean isEnableAtrace() {
        return this.f9603a;
    }

    public boolean isEnableBinder() {
        return this.f9604b;
    }

    public boolean isEnableLooperMonitor() {
        return this.f9605d;
    }

    public boolean isEnableStackSampling() {
        return this.f9606e;
    }

    public long getAtraceTag() {
        return this.f9607f;
    }

    public int getRunMode() {
        return this.f9608g;
    }

    public long getAlogRef() {
        return this.f9609h;
    }

    public String toString() {
        return "MonitorConfig{enableAtrace=" + this.f9603a + ", enableBinder=" + this.f9604b + ", enableLooperMonitor=" + this.f9605d + ", enableStackSampling=" + this.f9606e + ", atraceTag=" + this.f9607f + ", runMode=" + this.f9608g + ", alogRef=" + this.f9609h + '}';
    }

    /* compiled from: MonitorConfig.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private boolean f9610a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f9611b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f9612c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f9613d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f9614e;

        /* renamed from: f  reason: collision with root package name */
        private long f9615f;

        /* renamed from: g  reason: collision with root package name */
        private int f9616g;

        /* renamed from: h  reason: collision with root package name */
        private long f9617h;

        @Deprecated
        public a enableLock(boolean z) {
            return this;
        }

        public a enableAtrace(boolean z) {
            this.f9610a = z;
            return this;
        }

        public a enableBinder(boolean z) {
            this.f9611b = z;
            return this;
        }

        public a enableLooperMonitor(boolean z) {
            this.f9613d = z;
            return this;
        }

        public a enableStackSampling(boolean z) {
            this.f9614e = z;
            return this;
        }

        public a atraceTag(long j2) {
            this.f9615f = j2;
            return this;
        }

        public a runMode(int i2) {
            this.f9616g = i2;
            return this;
        }

        public a alogRef(long j2) {
            this.f9617h = j2;
            return this;
        }

        public m build() {
            return new m(this);
        }
    }
}
