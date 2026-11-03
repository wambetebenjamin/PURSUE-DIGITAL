package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.BaseResponse;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\b\u0010\u0010\u001a\u00020\u0011H\u0016R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountPasswordResponse;", "Lcom/bytedance/ttgame/base/BaseResponse;", "data", "Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountPasswordData;", "(Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountPasswordData;)V", "getData", "()Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountPasswordData;", "setData", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class cg extends BaseResponse {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private cf f19472a;

    public static /* synthetic */ cg a(cg cgVar, cf cfVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cfVar = cgVar.f19472a;
        }
        return cgVar.b(cfVar);
    }

    public final cf b() {
        return this.f19472a;
    }

    public final cg b(cf cfVar) {
        return new cg(cfVar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof cg) && Intrinsics.areEqual(this.f19472a, ((cg) obj).f19472a);
    }

    public int hashCode() {
        cf cfVar = this.f19472a;
        if (cfVar == null) {
            return 0;
        }
        return cfVar.hashCode();
    }

    public cg(cf cfVar) {
        this.f19472a = cfVar;
    }

    public final cf a() {
        return this.f19472a;
    }

    public final void a(cf cfVar) {
        this.f19472a = cfVar;
    }

    public String toString() {
        return "AccountPasswordResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.f19472a + ')';
    }
}
