package gsdk.impl.compliance.cookie;

import com.bytedance.apm.constant.l;
import com.bytedance.ttgame.base.BaseResponse;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CookieInfo.kt */
/* loaded from: classes10.dex */
public final class d extends BaseResponse {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private c f19924a;
    @SerializedName(l.KEY_LOG_ID)

    /* renamed from: b  reason: collision with root package name */
    private String f19925b;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            return Intrinsics.areEqual(this.f19924a, dVar.f19924a) && Intrinsics.areEqual(this.f19925b, dVar.f19925b);
        }
        return false;
    }

    public int hashCode() {
        c cVar = this.f19924a;
        return ((cVar == null ? 0 : cVar.hashCode()) * 31) + this.f19925b.hashCode();
    }

    public String toString() {
        return "CookieInfo(data=" + this.f19924a + ", logId=" + this.f19925b + ')';
    }

    public final c a() {
        return this.f19924a;
    }

    public final void a(c cVar) {
        this.f19924a = cVar;
    }

    public final String b() {
        return this.f19925b;
    }
}
