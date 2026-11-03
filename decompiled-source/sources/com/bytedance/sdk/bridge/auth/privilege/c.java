package com.bytedance.sdk.bridge.auth.privilege;

import com.bytedance.sdk.bridge.auth.privilege.b;
import java.util.LinkedHashMap;
import java.util.Map;
/* compiled from: BridgeConfigTask.java */
/* loaded from: classes2.dex */
public abstract class c<T, B extends com.bytedance.sdk.bridge.auth.privilege.b> {

    /* renamed from: c  reason: collision with root package name */
    private static final long f13113c = 600000;

    /* renamed from: a  reason: collision with root package name */
    protected final long f13114a;

    /* renamed from: b  reason: collision with root package name */
    protected final Map<String, B> f13115b;

    protected abstract void a(T t, B b2);

    protected abstract void a(Map<String, String> map, a<T, B> aVar);

    public c() {
        this.f13115b = new b(16, 16);
        this.f13114a = 600000L;
    }

    public c(long j2) {
        this.f13115b = new b(16, 16);
        if (j2 >= 0) {
            this.f13114a = j2;
        } else {
            this.f13114a = 600000L;
        }
    }

    protected String a(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (String str : map.values()) {
            sb.append(str).append("_");
        }
        if (map.size() > 0) {
            return sb.toString().substring(0, sb.length() - 1);
        }
        return null;
    }

    public B getConfig(T t, Map<String, String> map, a<T, B> aVar) {
        String a2 = a(map);
        B b2 = this.f13115b.get(a2);
        if (b2 != null && System.currentTimeMillis() - b2.f13112a < this.f13114a) {
            a((c<T, B>) t, (T) b2);
            return b2;
        }
        ((a) aVar).f13118c = a2;
        ((a) aVar).f13117b = t;
        ((a) aVar).f13116a = this;
        a(map, aVar);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, T t, B b2) {
        this.f13115b.put(str, b2);
        a((c<T, B>) t, (T) b2);
    }

    /* compiled from: BridgeConfigTask.java */
    /* loaded from: classes2.dex */
    private static class b<K, V> extends LinkedHashMap<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final int f13119a;

        public b(int i2, int i3) {
            this(i2, i3, false);
        }

        public b(int i2, int i3, boolean z) {
            super(i3, 0.75f, true);
            this.f13119a = i2;
            if (i2 <= 0) {
                throw new IllegalArgumentException("maxSize <= 0");
            }
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
            return size() > this.f13119a;
        }
    }

    /* compiled from: BridgeConfigTask.java */
    /* loaded from: classes2.dex */
    public static class a<T, B extends com.bytedance.sdk.bridge.auth.privilege.b> {

        /* renamed from: a  reason: collision with root package name */
        private c<T, B> f13116a;

        /* renamed from: b  reason: collision with root package name */
        private T f13117b;

        /* renamed from: c  reason: collision with root package name */
        private String f13118c;

        public void doResult(B b2) {
            if (b2 != null) {
                b2.f13112a = System.currentTimeMillis();
                this.f13116a.a(this.f13118c, this.f13117b, b2);
            }
        }
    }
}
