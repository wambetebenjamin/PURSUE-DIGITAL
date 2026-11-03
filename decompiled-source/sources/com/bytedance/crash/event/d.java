package com.bytedance.crash.event;

import java.util.Set;
import org.json.JSONObject;
/* compiled from: EnsureConfig.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: e  reason: collision with root package name */
    private static boolean f6691e;

    /* renamed from: a  reason: collision with root package name */
    private Set<String> f6692a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f6693b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f6694c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f6695d;

    public d(Set<String> set, JSONObject jSONObject, boolean z) {
        this.f6692a = set;
        this.f6693b = z;
        if (jSONObject != null) {
            this.f6694c = jSONObject;
        } else {
            this.f6694c = new JSONObject();
        }
    }

    public d(boolean z) {
        this.f6695d = z;
    }

    public boolean a(String str) {
        JSONObject jSONObject;
        if (this.f6695d || (jSONObject = this.f6694c) == null) {
            return true;
        }
        return Math.random() <= jSONObject.optDouble(str, 1.0d);
    }

    public static void a(boolean z) {
        f6691e = z;
    }

    public boolean a() {
        return this.f6695d || this.f6693b;
    }

    public boolean a(String str, String str2) {
        if (!f6691e && b(str)) {
            return a(str2);
        }
        return false;
    }

    public boolean b(String str) {
        if (f6691e) {
            return false;
        }
        if (this.f6695d) {
            return true;
        }
        if ("core_exception_monitor".equals(str)) {
            return this.f6693b;
        }
        Set<String> set = this.f6692a;
        return set != null && set.contains(str);
    }

    public String toString() {
        return "EnsureConfig{sampledLogType=" + this.f6692a + ", isCoreExceptionEnable=" + this.f6693b + ", exceptionMessage=" + this.f6694c + ", isAllEnable=" + this.f6695d + '}';
    }
}
