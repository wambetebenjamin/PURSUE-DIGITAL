package com.bytedance.crash.upload;
/* compiled from: UploadLimitConfig.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private int f7214a;

    /* renamed from: b  reason: collision with root package name */
    private int f7215b;

    /* renamed from: c  reason: collision with root package name */
    private int f7216c;

    /* renamed from: d  reason: collision with root package name */
    private int f7217d;

    /* renamed from: e  reason: collision with root package name */
    private int f7218e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f7219f;

    public c(int i2, int i3, int i4, int i5, int i6) {
        this.f7214a = 50;
        this.f7215b = 100;
        this.f7216c = 100;
        this.f7217d = 5;
        this.f7218e = 2;
        if (i2 > 0) {
            this.f7214a = i2;
        }
        if (i3 > 0) {
            this.f7215b = i3;
        }
        if (i4 > 0) {
            this.f7216c = i4;
        }
        if (i5 > 0) {
            this.f7217d = i5;
        }
        if (i6 > 0) {
            this.f7218e = i6;
        }
    }

    public c(boolean z) {
        this.f7214a = 50;
        this.f7215b = 100;
        this.f7216c = 100;
        this.f7217d = 5;
        this.f7218e = 2;
        this.f7219f = z;
    }

    public boolean a() {
        return this.f7219f;
    }

    public int b() {
        return this.f7214a;
    }

    public int c() {
        return this.f7215b;
    }

    public int d() {
        return this.f7216c;
    }

    public int e() {
        return this.f7217d;
    }

    public int f() {
        return this.f7218e;
    }

    public String toString() {
        return "UploadLimitConfig{crashLimitIssue=" + this.f7214a + ", crashLimitAll=" + this.f7215b + ", exceptionAllLimit=" + this.f7216c + ", exceptionMsgLimit=" + this.f7217d + ", exceptionStackLimit=" + this.f7218e + ", isNoLimit=" + this.f7219f + '}';
    }
}
