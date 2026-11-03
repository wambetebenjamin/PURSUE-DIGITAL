package com.bytedance.apm6.commonevent.config;

import java.util.Set;
/* compiled from: CommonEventConfig.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private Set<String> f1732a;

    /* renamed from: b  reason: collision with root package name */
    private Set<String> f1733b;

    public a(Set<String> set, Set<String> set2) {
        this.f1732a = set;
        this.f1733b = set2;
    }

    public boolean isSampledService(String str) {
        Set<String> set = this.f1732a;
        return set != null && set.contains(str);
    }

    public boolean isSampledLogType(String str) {
        Set<String> set = this.f1733b;
        return set != null && set.contains(str);
    }

    public String toString() {
        return "CommonEventConfig{sampledService=" + this.f1732a + ", sampledLogType=" + this.f1733b + '}';
    }
}
