package com.bytedance.bdinstall;
/* compiled from: RangersHttpException.java */
/* loaded from: classes.dex */
public class au extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private int f3734a;

    public au(int i2, String str) {
        super(str);
        this.f3734a = i2;
    }

    public au(int i2, Throwable th) {
        super(th);
        this.f3734a = i2;
    }

    public int a() {
        return this.f3734a;
    }
}
