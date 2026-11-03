package com.bytedance.sdk.bridge;
/* compiled from: BridgeParamInfo.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private int f13161a;

    /* renamed from: b  reason: collision with root package name */
    private Class<?> f13162b;

    /* renamed from: c  reason: collision with root package name */
    private String f13163c;

    /* renamed from: d  reason: collision with root package name */
    private Object f13164d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f13165e;

    public h(int i2) {
        this.f13161a = i2;
    }

    public h(int i2, Class<?> cls, String str, Object obj, boolean z) {
        this.f13161a = i2;
        this.f13162b = cls;
        this.f13163c = str;
        this.f13164d = obj;
        this.f13165e = z;
    }

    public int getParamType() {
        return this.f13161a;
    }

    public Class<?> getParamClass() {
        return this.f13162b;
    }

    public String getParamName() {
        return this.f13163c;
    }

    public Object getDefaultValue() {
        return this.f13164d;
    }

    public boolean isRequired() {
        return this.f13165e;
    }
}
