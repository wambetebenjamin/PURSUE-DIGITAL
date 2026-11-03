package com.bytedance.pipo.game.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Products.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/bytedance/pipo/game/api/Products;", "", "payError", "Lcom/bytedance/pipo/game/api/PayError;", "rocketGoods", "Lcom/bytedance/pipo/game/api/Data;", "(Lcom/bytedance/pipo/game/api/PayError;Lcom/bytedance/pipo/game/api/Data;)V", "isSuccess", "", "()Z", "getPayError", "()Lcom/bytedance/pipo/game/api/PayError;", "setPayError", "(Lcom/bytedance/pipo/game/api/PayError;)V", "getRocketGoods", "()Lcom/bytedance/pipo/game/api/Data;", "setRocketGoods", "(Lcom/bytedance/pipo/game/api/Data;)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class r {
    @SerializedName("gsdkError")

    /* renamed from: a  reason: collision with root package name */
    private j f10157a;
    @SerializedName("data")

    /* renamed from: b  reason: collision with root package name */
    private Data f10158b;

    public static /* synthetic */ r a(r rVar, j jVar, Data data, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            jVar = rVar.f10157a;
        }
        if ((i2 & 2) != 0) {
            data = rVar.f10158b;
        }
        return rVar.a(jVar, data);
    }

    public final r a(j payError, Data data) {
        Intrinsics.checkNotNullParameter(payError, "payError");
        return new r(payError, data);
    }

    public final j d() {
        return this.f10157a;
    }

    public final Data e() {
        return this.f10158b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof r) {
            r rVar = (r) obj;
            return Intrinsics.areEqual(this.f10157a, rVar.f10157a) && Intrinsics.areEqual(this.f10158b, rVar.f10158b);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.f10157a.hashCode() * 31;
        Data data = this.f10158b;
        return hashCode + (data == null ? 0 : data.hashCode());
    }

    public r(j payError, Data data) {
        Intrinsics.checkNotNullParameter(payError, "payError");
        this.f10157a = payError;
        this.f10158b = data;
    }

    public final j a() {
        return this.f10157a;
    }

    public final void a(j jVar) {
        Intrinsics.checkNotNullParameter(jVar, "<set-?>");
        this.f10157a = jVar;
    }

    public final void a(Data data) {
        this.f10158b = data;
    }

    public final Data b() {
        return this.f10158b;
    }

    public final boolean c() {
        return this.f10157a.b() == 0;
    }

    public String toString() {
        return "RocketGoods(rocketGoods=" + this.f10158b + ", error:" + this.f10157a + ')';
    }
}
