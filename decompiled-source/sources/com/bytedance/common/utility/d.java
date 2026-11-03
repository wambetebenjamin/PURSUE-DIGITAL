package com.bytedance.common.utility;
/* compiled from: CommonHttpException.java */
/* loaded from: classes2.dex */
public class d extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private int f5247a;

    public d(int i2, String str) {
        super(str);
        this.f5247a = i2;
    }

    public int getResponseCode() {
        return this.f5247a;
    }
}
