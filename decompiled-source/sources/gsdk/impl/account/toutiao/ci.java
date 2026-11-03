package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.rocketapi.account.pojo.AccountBindInfo;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/ChangeMobileResponse;", "Lcom/bytedance/ttgame/base/BaseResponse;", "()V", "data", "Lcom/bytedance/ttgame/rocketapi/account/pojo/AccountBindInfo;", "getData", "()Lcom/bytedance/ttgame/rocketapi/account/pojo/AccountBindInfo;", "setData", "(Lcom/bytedance/ttgame/rocketapi/account/pojo/AccountBindInfo;)V", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ci extends BaseResponse {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private AccountBindInfo f19476a;

    public final AccountBindInfo a() {
        return this.f19476a;
    }

    public final void a(AccountBindInfo accountBindInfo) {
        this.f19476a = accountBindInfo;
    }
}
