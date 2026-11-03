package com.bytedance.applog.monitor.v3;

import java.util.Map;
/* compiled from: SentryConfig.java */
/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private boolean f3010a;

    /* renamed from: b  reason: collision with root package name */
    private int f3011b;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, Integer> f3012c;

    private k(a aVar) {
        this.f3010a = false;
        this.f3011b = 0;
        a(aVar.f3013a);
        this.f3011b = aVar.f3014b;
        this.f3012c = aVar.f3015c;
    }

    public boolean a() {
        return this.f3010a;
    }

    public void a(boolean z) {
        this.f3010a = z;
    }

    /* compiled from: SentryConfig.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private boolean f3013a;

        /* renamed from: b  reason: collision with root package name */
        private int f3014b;

        /* renamed from: c  reason: collision with root package name */
        private Map<String, Integer> f3015c;

        public a a(boolean z) {
            this.f3013a = z;
            return this;
        }

        public a a(int i2) {
            this.f3014b = i2;
            return this;
        }

        public a a(Map<String, Integer> map) {
            this.f3015c = map;
            return this;
        }

        public k a() {
            return new k(this);
        }
    }
}
