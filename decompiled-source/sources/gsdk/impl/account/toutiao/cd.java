package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.BaseResponse;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\b\u0010\u0010\u001a\u00020\u0011H\u0016R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountCodeResponse;", "Lcom/bytedance/ttgame/base/BaseResponse;", "data", "Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountCodeData;", "(Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountCodeData;)V", "getData", "()Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountCodeData;", "setData", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class cd extends BaseResponse {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private cc f19465a;

    public static /* synthetic */ cd a(cd cdVar, cc ccVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            ccVar = cdVar.f19465a;
        }
        return cdVar.b(ccVar);
    }

    public final cc b() {
        return this.f19465a;
    }

    public final cd b(cc ccVar) {
        return new cd(ccVar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof cd) && Intrinsics.areEqual(this.f19465a, ((cd) obj).f19465a);
    }

    public int hashCode() {
        cc ccVar = this.f19465a;
        if (ccVar == null) {
            return 0;
        }
        return ccVar.hashCode();
    }

    public cd(cc ccVar) {
        this.f19465a = ccVar;
    }

    public final cc a() {
        return this.f19465a;
    }

    public final void a(cc ccVar) {
        this.f19465a = ccVar;
    }

    public String toString() {
        return "AccountCodeResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.f19465a + ')';
    }
}
