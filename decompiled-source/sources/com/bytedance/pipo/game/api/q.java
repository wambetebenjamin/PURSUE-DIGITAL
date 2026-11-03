package com.bytedance.pipo.game.api;

import java.util.List;
/* compiled from: ProductList.java */
/* loaded from: classes2.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    private int f10153a;

    /* renamed from: b  reason: collision with root package name */
    private String f10154b;

    /* renamed from: c  reason: collision with root package name */
    private List<o> f10155c;

    /* renamed from: d  reason: collision with root package name */
    private List<t> f10156d;

    public int a() {
        return this.f10153a;
    }

    public void a(int i2) {
        this.f10153a = i2;
    }

    public String b() {
        return this.f10154b;
    }

    public void a(String str) {
        this.f10154b = str;
    }

    public List<o> c() {
        return this.f10155c;
    }

    public void a(List<o> list) {
        this.f10155c = list;
    }

    public List<t> d() {
        return this.f10156d;
    }

    public void b(List<t> list) {
        this.f10156d = list;
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("ProductList{code=").append(this.f10153a).append(", message='").append(this.f10154b).append('\'').append(", productInfoList=");
        List<o> list = this.f10155c;
        StringBuilder append2 = append.append(list == null ? "" : list.toString()).append(", subscriptionInfoList=");
        List<t> list2 = this.f10156d;
        return append2.append(list2 != null ? list2.toString() : "").append('}').toString();
    }
}
