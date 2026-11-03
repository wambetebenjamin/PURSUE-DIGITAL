package com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns;

import android.os.Bundle;
import android.os.Message;
import com.bytedance.common.utility.collection.WeakHandler;
import java.util.List;
/* compiled from: DnsRecord.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7992a = 10;

    /* renamed from: b  reason: collision with root package name */
    public static final int f7993b = 11;

    /* renamed from: c  reason: collision with root package name */
    public static final int f7994c = 12;

    /* renamed from: d  reason: collision with root package name */
    public static final int f7995d = 13;

    /* renamed from: e  reason: collision with root package name */
    public static final String f7996e = "dnsrecord_host";

    /* renamed from: f  reason: collision with root package name */
    private static final String f7997f = "a";

    /* renamed from: g  reason: collision with root package name */
    private String f7998g;

    /* renamed from: h  reason: collision with root package name */
    private List<String> f7999h;

    /* renamed from: i  reason: collision with root package name */
    private List<String> f8000i;

    /* renamed from: j  reason: collision with root package name */
    private long f8001j;
    private long k;
    private final WeakHandler l = new WeakHandler(e.a().l().getLooper(), e.a());

    /* compiled from: DnsRecord.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public enum EnumC0114a {
        CACHE_UNSET,
        CACHE_VALID,
        CACHE_STALE_BOTH,
        CACHE_STALE_NETCHANGED,
        CACHE_STALE_EXPIRED,
        PRELOAD_BATCH,
        REFRESH_BATCH
    }

    public a(String str, long j2, List<String> list, List<String> list2, int i2) {
        this.f7998g = str;
        this.k = j2;
        this.f7999h = list;
        this.f8000i = list2;
        this.f8001j = i2;
    }

    public void a() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 12;
        a(obtain);
        this.l.sendMessageDelayed(obtain, this.f8001j * 1000);
        Message obtain2 = Message.obtain();
        obtain2.obj = this;
        obtain2.what = 10;
        a(obtain2);
        this.l.sendMessageDelayed(obtain2, (this.f8001j * 1000) + (e.a().d().get() * 1000));
    }

    public void b() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 13;
        a(obtain);
        this.l.sendMessageDelayed(obtain, this.f8001j * 1000);
    }

    public void c() {
        this.l.removeMessages(10);
        this.l.removeMessages(12);
    }

    public void d() {
        this.l.removeMessages(13);
    }

    public void e() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 11;
        a(obtain);
        this.l.sendMessageDelayed(obtain, e.a().c().get() * 1000);
    }

    public void f() {
        this.l.removeMessages(11);
    }

    public long g() {
        return this.k;
    }

    public long h() {
        return this.f8001j;
    }

    public List<String> i() {
        return this.f7999h;
    }

    public List<String> j() {
        return this.f8000i;
    }

    public String k() {
        return this.f7998g;
    }

    private void a(Message message) {
        Bundle bundle = new Bundle();
        bundle.putString("dnsrecord_host", this.f7998g);
        message.setData(bundle);
    }
}
