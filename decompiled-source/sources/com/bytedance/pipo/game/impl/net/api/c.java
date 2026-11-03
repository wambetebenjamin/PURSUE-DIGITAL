package com.bytedance.pipo.game.impl.net.api;

import com.bytedance.pipo.game.api.Data;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ProductsResponse.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u0005H\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/bytedance/pipo/game/impl/net/api/PipoGoodsResponse;", "Lcom/bytedance/pipo/game/impl/net/api/BaseResponse;", "code", "", "msg", "", "rocketGoods", "Lcom/bytedance/pipo/game/api/Data;", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/bytedance/pipo/game/api/Data;)V", "getRocketGoods", "()Lcom/bytedance/pipo/game/api/Data;", "setRocketGoods", "(Lcom/bytedance/pipo/game/api/Data;)V", "toString", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class c extends a {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private Data f10456a;

    public /* synthetic */ c(Integer num, String str, Data data, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, (i2 & 4) != 0 ? null : data);
    }

    public c(Integer num, String str, Data data) {
        super(num, str);
        this.f10456a = data;
    }

    public final void a(Data data) {
        this.f10456a = data;
    }

    public final Data d() {
        return this.f10456a;
    }

    public String toString() {
        return "RocketGoods(code=" + a() + ", msg=" + b() + ", rocketGoods=" + this.f10456a + ')';
    }
}
