package com.bytedance.bdinstall.loader;

import org.json.JSONObject;
/* compiled from: BaseLoader.java */
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: e  reason: collision with root package name */
    private static final String f3875e = "UTF-8";

    /* renamed from: a  reason: collision with root package name */
    boolean f3876a;

    /* renamed from: b  reason: collision with root package name */
    boolean f3877b;

    /* renamed from: c  reason: collision with root package name */
    boolean f3878c;

    /* renamed from: d  reason: collision with root package name */
    boolean f3879d;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean a(JSONObject jSONObject);

    public void b(JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(boolean z, boolean z2) {
        this.f3877b = z;
        this.f3878c = z2;
        this.f3879d = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(boolean z, boolean z2, boolean z3) {
        this.f3877b = z;
        this.f3878c = z2;
        this.f3879d = z3;
    }
}
