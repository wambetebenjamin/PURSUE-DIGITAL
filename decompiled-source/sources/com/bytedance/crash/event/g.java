package com.bytedance.crash.event;

import java.util.Set;
/* compiled from: EventConfig.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: c  reason: collision with root package name */
    private static boolean f6719c;

    /* renamed from: a  reason: collision with root package name */
    private Set<String> f6720a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f6721b;

    public g(boolean z) {
        this.f6721b = z;
    }

    public g(Set<String> set) {
        this.f6720a = set;
    }

    public static void a(boolean z) {
        f6719c = z;
    }

    public boolean a(String str) {
        if (f6719c) {
            return false;
        }
        if (this.f6721b) {
            return true;
        }
        Set<String> set = this.f6720a;
        return set != null && set.contains(str);
    }

    public String toString() {
        return "EventConfig{sampledService=" + this.f6720a + ", isAllSample=" + this.f6721b + '}';
    }
}
