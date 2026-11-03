package gsdk.impl.geas.DEFAULT;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: StrategyResponse.kt */
/* loaded from: classes10.dex */
public final class h {
    @SerializedName("speed_svr_list")

    /* renamed from: a  reason: collision with root package name */
    private List<f> f20368a;
    @SerializedName("strategy")

    /* renamed from: b  reason: collision with root package name */
    private g f20369b;

    public h() {
        this(null, null, 3, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            return Intrinsics.areEqual(this.f20368a, hVar.f20368a) && Intrinsics.areEqual(this.f20369b, hVar.f20369b);
        }
        return false;
    }

    public int hashCode() {
        return (this.f20368a.hashCode() * 31) + this.f20369b.hashCode();
    }

    public String toString() {
        return "StrategyData(speedSvrList=" + this.f20368a + ", config=" + this.f20369b + ')';
    }

    public h(List<f> speedSvrList, g config) {
        Intrinsics.checkNotNullParameter(speedSvrList, "speedSvrList");
        Intrinsics.checkNotNullParameter(config, "config");
        this.f20368a = speedSvrList;
        this.f20369b = config;
    }

    public /* synthetic */ h(List list, g gVar, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        h hVar;
        g gVar2;
        List emptyList = (i2 & 1) != 0 ? CollectionsKt.emptyList() : list;
        if ((i2 & 2) != 0) {
            gVar2 = new g(0, null, null, null, null, 0, 0, 0L, 0L, 0, 0, 0, 0, 8191, null);
            hVar = this;
        } else {
            hVar = this;
            gVar2 = gVar;
        }
        new h(emptyList, gVar2);
    }

    public final List<f> a() {
        return this.f20368a;
    }

    public final g b() {
        return this.f20369b;
    }
}
