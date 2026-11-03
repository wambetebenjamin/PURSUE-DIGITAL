package com.bytedance.apm.structure;
/* compiled from: LimitStack.java */
/* loaded from: classes.dex */
public class c<T> extends d<T> {

    /* renamed from: a  reason: collision with root package name */
    int f1451a;

    public c(int i2) {
        this.f1451a = 0;
        this.f1451a = i2;
    }

    @Override // com.bytedance.apm.structure.d, com.bytedance.apm.structure.a
    public void push(T t) {
        if (this.f1453b >= this.f1451a) {
            pop();
        }
        super.push(t);
    }
}
