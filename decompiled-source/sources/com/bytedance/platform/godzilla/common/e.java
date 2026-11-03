package com.bytedance.platform.godzilla.common;

import java.util.ArrayList;
import java.util.List;
/* compiled from: ConsumerResultFollower.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static e f11052a = new e();

    /* renamed from: b  reason: collision with root package name */
    private List<a> f11053b = new ArrayList();

    public void addAnalysisResult(Thread thread, Throwable th, m mVar, boolean z) {
        try {
            this.f11053b.add(new a(mVar.getClass().getSimpleName(), thread.getName(), z, th.getClass().getSimpleName()));
        } catch (Throwable unused) {
        }
    }

    public static e getInstance() {
        return f11052a;
    }

    public List<a> getFollowResult() {
        return this.f11053b;
    }

    /* compiled from: ConsumerResultFollower.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f11054a;

        /* renamed from: b  reason: collision with root package name */
        private String f11055b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f11056c;

        /* renamed from: d  reason: collision with root package name */
        private String f11057d;

        public a(String str, String str2, boolean z, String str3) {
            this.f11054a = str;
            this.f11055b = str2;
            this.f11056c = z;
            this.f11057d = str3;
        }

        public String toString() {
            return "Data{mPluginName='" + this.f11054a + "', mThreadName='" + this.f11055b + "', mResult=" + this.f11056c + ", mException='" + this.f11057d + "'}";
        }
    }
}
