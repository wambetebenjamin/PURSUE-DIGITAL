package com.bytedance.crash.repair;

import org.json.JSONArray;
/* compiled from: RepairConfig.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7028a = 60000;

    /* renamed from: b  reason: collision with root package name */
    private int f7029b;

    /* renamed from: c  reason: collision with root package name */
    private final int f7030c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f7031d;

    /* renamed from: e  reason: collision with root package name */
    private final com.bytedance.crash.npth_repair.utils.c f7032e;

    public b(int i2, int i3, boolean z, JSONArray jSONArray) {
        this.f7029b = 60000;
        this.f7030c = i2;
        if (i3 > 0) {
            this.f7029b = i3;
        }
        this.f7031d = z;
        this.f7032e = com.bytedance.crash.npth_repair.utils.c.a(jSONArray);
    }

    public int a() {
        return this.f7029b;
    }

    public int b() {
        return this.f7030c;
    }

    public boolean c() {
        return this.f7031d;
    }

    public com.bytedance.crash.npth_repair.utils.c d() {
        return this.f7032e;
    }
}
