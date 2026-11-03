package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import com.bytedance.retrofit2.client.Header;
import java.util.List;
/* compiled from: URLRequest.java */
/* loaded from: classes2.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private String f8221a;

    /* renamed from: b  reason: collision with root package name */
    private String f8222b;

    /* renamed from: c  reason: collision with root package name */
    private int f8223c;

    /* renamed from: d  reason: collision with root package name */
    private final List<Header> f8224d;

    public l(String str, String str2) {
        this.f8221a = str;
        this.f8222b = str2;
        this.f8223c = 0;
        this.f8224d = null;
    }

    public l(String str, String str2, List<Header> list) {
        this.f8221a = str;
        this.f8222b = str2;
        this.f8223c = 0;
        this.f8224d = list;
    }

    public void a(String str) {
        this.f8222b = str;
    }

    public String a() {
        return this.f8222b;
    }

    public void b(String str) {
        this.f8221a = str;
    }

    public String b() {
        return this.f8221a;
    }

    public void a(int i2) {
        this.f8223c = i2;
    }

    public int c() {
        return this.f8223c;
    }

    public List<Header> d() {
        return this.f8224d;
    }
}
