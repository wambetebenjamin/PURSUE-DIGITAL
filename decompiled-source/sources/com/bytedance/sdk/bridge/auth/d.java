package com.bytedance.sdk.bridge.auth;

import com.bytedance.sdk.bridge.g;
import java.util.ArrayList;
import java.util.List;
/* compiled from: BridgeAuthFilterChain.java */
/* loaded from: classes2.dex */
public class d<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f13103a = false;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f13104b;

    /* renamed from: c  reason: collision with root package name */
    private List<c> f13105c;

    /* renamed from: d  reason: collision with root package name */
    private int f13106d;

    public void resetIndex() {
        this.f13106d = 0;
    }

    public d() {
        this.f13104b = false;
        this.f13105c = new ArrayList();
        this.f13106d = 0;
    }

    public d(boolean z) {
        this.f13104b = z;
        this.f13105c = new ArrayList();
        this.f13106d = 0;
    }

    public d addFilter(c cVar) {
        this.f13105c.add(cVar);
        return this;
    }

    public boolean doAuthFilter(T t, g gVar) {
        if (this.f13106d == this.f13105c.size()) {
            return this.f13104b;
        }
        List<c> list = this.f13105c;
        int i2 = this.f13106d;
        this.f13106d = i2 + 1;
        return list.get(i2).doAuthFilter(t, gVar, this);
    }
}
