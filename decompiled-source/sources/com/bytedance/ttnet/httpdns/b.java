package com.bytedance.ttnet.httpdns;

import com.bytedance.frameworks.baselib.network.http.cronet.impl.g;
import com.bytedance.ttnet.TTNetInit;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
/* compiled from: TTDnsQuery.java */
/* loaded from: classes8.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private String f15317a;

    /* renamed from: b  reason: collision with root package name */
    private int f15318b;

    /* renamed from: c  reason: collision with root package name */
    private String f15319c = UUID.randomUUID().toString();

    /* renamed from: d  reason: collision with root package name */
    private CountDownLatch f15320d = new CountDownLatch(1);

    /* renamed from: e  reason: collision with root package name */
    private c f15321e;

    /* renamed from: f  reason: collision with root package name */
    private Map<String, String> f15322f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str, int i2, Map<String, String> map) {
        this.f15317a = str;
        this.f15318b = i2;
        this.f15322f = map;
    }

    public String a() {
        return this.f15317a;
    }

    public int b() {
        return this.f15318b;
    }

    public String c() {
        return this.f15319c;
    }

    public void d() {
        this.f15320d.await();
    }

    public void e() {
        this.f15320d.countDown();
    }

    public c f() {
        return this.f15321e;
    }

    public void a(c cVar) {
        this.f15321e = cVar;
    }

    public void g() {
        g.a(TTNetInit.getTTNetDepend().getContext()).a(this.f15317a, this.f15318b, this.f15319c, this.f15322f);
    }
}
