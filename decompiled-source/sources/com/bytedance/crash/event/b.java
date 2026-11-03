package com.bytedance.crash.event;

import java.util.Set;
/* compiled from: BizExceptionConfig.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private boolean f6671a;

    /* renamed from: b  reason: collision with root package name */
    private Set<String> f6672b;

    public b(boolean z, Set<String> set) {
        this.f6671a = z;
        this.f6672b = set;
    }

    public boolean a() {
        return this.f6671a;
    }

    public boolean a(String str) {
        Set<String> set = this.f6672b;
        if (set == null) {
            return false;
        }
        return set.contains(str);
    }

    public String b() {
        Set<String> set = this.f6672b;
        return set == null ? "null" : set.toString();
    }

    public String toString() {
        return "BizExceptionConfig{isEnableUpload=" + this.f6671a + ", sampledLogTypes=" + this.f6672b + '}';
    }
}
