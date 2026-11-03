package com.appsflyer.internal;
/* loaded from: classes.dex */
public abstract class AFh1vSDK extends AFa1pSDK {
    private final boolean force;

    /* renamed from: i  reason: collision with root package name */
    private boolean f99i;
    private final boolean w;

    AFh1vSDK() {
        this(null, null, null, null, null);
    }

    public AFh1vSDK(String str, String str2, Boolean bool, Boolean bool2, Boolean bool3) {
        super(str, str2, Boolean.valueOf(bool3 != null ? bool3.booleanValue() : false));
        this.w = bool != null ? bool.booleanValue() : true;
        this.force = bool2 != null ? bool2.booleanValue() : true;
    }

    public final boolean w() {
        return this.f99i;
    }

    public final boolean i() {
        return this.w;
    }

    public final boolean v() {
        return this.force;
    }
}
