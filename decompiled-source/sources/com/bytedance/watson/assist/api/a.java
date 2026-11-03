package com.bytedance.watson.assist.api;
/* compiled from: AssistConfig.java */
/* loaded from: classes8.dex */
public class a {
    public static a DEFAULT = new a();

    /* renamed from: a  reason: collision with root package name */
    private boolean f16192a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f16193b;

    /* renamed from: c  reason: collision with root package name */
    private int f16194c;

    /* renamed from: d  reason: collision with root package name */
    private int f16195d;

    /* renamed from: e  reason: collision with root package name */
    private C0263a f16196e;

    public a() {
        this.f16192a = true;
        this.f16193b = false;
        this.f16194c = 37;
        this.f16195d = 30;
        this.f16196e = new C0263a();
    }

    public a(C0263a c0263a) {
        this.f16192a = true;
        this.f16193b = false;
        this.f16194c = 37;
        this.f16195d = 30;
        this.f16196e = new C0263a();
        this.f16196e = c0263a;
    }

    public C0263a getCpuAbnormalConfig() {
        return this.f16196e;
    }

    public void setCpuAbnormalConfig(C0263a c0263a) {
        this.f16196e = c0263a;
    }

    public int getCpuSampleBatteryTemp() {
        return this.f16194c;
    }

    public void setCpuSampleBatteryTemp(int i2) {
        this.f16194c = i2;
    }

    public int getCpuSampleBatteryLevel() {
        return this.f16195d;
    }

    public void setCpuSampleBatteryLevel(int i2) {
        this.f16195d = i2;
    }

    public void setEnableCpuUsageStat(boolean z) {
        this.f16193b = z;
    }

    public void setEnableProcessTimeFreqPercent(boolean z) {
        this.f16192a = z;
    }

    public boolean isEnableCpuUsageStat() {
        return this.f16193b;
    }

    public boolean isEnableProcessTimeFreqPercent() {
        return this.f16192a;
    }

    /* compiled from: AssistConfig.java */
    /* renamed from: com.bytedance.watson.assist.api.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public static class C0263a {

        /* renamed from: a  reason: collision with root package name */
        private String f16197a = "unknown";

        /* renamed from: b  reason: collision with root package name */
        private String f16198b = "default";

        /* renamed from: c  reason: collision with root package name */
        private float f16199c = 0.0f;

        /* renamed from: d  reason: collision with root package name */
        private float f16200d = 0.0f;

        /* renamed from: e  reason: collision with root package name */
        private float f16201e = 0.0f;

        /* renamed from: f  reason: collision with root package name */
        private float f16202f = 0.0f;

        public float getCpuSpeed() {
            return this.f16199c;
        }

        public void setCpuSpeed(float f2) {
            this.f16199c = f2;
        }

        public float getSmallCpuCoreTimePercent() {
            return this.f16200d;
        }

        public void setSmallCpuCoreTimePercent(float f2) {
            this.f16200d = f2;
        }

        public float getMiddleCpuCoreTimePercent() {
            return this.f16201e;
        }

        public void setMiddleCpuCoreTimePercent(float f2) {
            this.f16201e = f2;
        }

        public float getBigCpuCoreTimePercent() {
            return this.f16202f;
        }

        public void setBigCpuCoreTimePercent(float f2) {
            this.f16202f = f2;
        }
    }
}
