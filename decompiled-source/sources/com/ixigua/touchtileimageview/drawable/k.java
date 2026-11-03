package com.ixigua.touchtileimageview.drawable;

import androidx.core.util.Pools;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Tile.java */
/* loaded from: classes9.dex */
public class k {

    /* renamed from: g  reason: collision with root package name */
    private static final int f17122g = 100;

    /* renamed from: h  reason: collision with root package name */
    private static final Pools.SynchronizedPool<k> f17123h = new Pools.SynchronizedPool<>(100);

    /* renamed from: a  reason: collision with root package name */
    int f17124a;

    /* renamed from: b  reason: collision with root package name */
    int f17125b;

    /* renamed from: c  reason: collision with root package name */
    int f17126c;

    /* renamed from: d  reason: collision with root package name */
    int f17127d;

    /* renamed from: e  reason: collision with root package name */
    int f17128e;

    /* renamed from: f  reason: collision with root package name */
    boolean f17129f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k a(int i2, int i3, int i4, int i5, int i6, boolean z) {
        k acquire = f17123h.acquire();
        if (acquire == null) {
            return new k(i2, i3, i4, i5, i6, z);
        }
        acquire.f17124a = i2;
        acquire.f17125b = i3;
        acquire.f17126c = i4;
        acquire.f17127d = i5;
        acquire.f17128e = i6;
        acquire.f17129f = z;
        return acquire;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k a(k kVar) {
        return a(kVar.f17124a, kVar.f17125b, kVar.f17126c, kVar.f17127d, kVar.f17128e, kVar.f17129f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(k kVar) {
        f17123h.release(kVar);
    }

    private k(int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.f17129f = false;
        this.f17124a = i2;
        this.f17125b = i3;
        this.f17126c = i4;
        this.f17127d = i5;
        this.f17128e = i6;
        this.f17129f = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return this.f17128e == kVar.f17128e && this.f17124a == kVar.f17124a && this.f17125b == kVar.f17125b && this.f17126c == kVar.f17126c && this.f17127d == kVar.f17127d;
    }

    public int hashCode() {
        return (((((((this.f17124a * 31) + this.f17125b) * 31) + this.f17126c) * 31) + this.f17127d) * 31) + this.f17128e;
    }

    public String toString() {
        return "Tile{left=" + this.f17124a + ", top=" + this.f17125b + ", right=" + this.f17126c + ", bottom=" + this.f17127d + ", scale=" + this.f17128e + '}';
    }
}
