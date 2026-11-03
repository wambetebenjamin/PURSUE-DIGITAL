package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.rocketapi.account.pojo.AccountBindInfo;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/ChangeMobileResult;", "", "()V", "data", "Lcom/bytedance/ttgame/rocketapi/account/pojo/AccountBindInfo;", "getData", "()Lcom/bytedance/ttgame/rocketapi/account/pojo/AccountBindInfo;", "setData", "(Lcom/bytedance/ttgame/rocketapi/account/pojo/AccountBindInfo;)V", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "getGsdkError", "()Lcom/bytedance/ttgame/base/GSDKError;", "setGsdkError", "(Lcom/bytedance/ttgame/base/GSDKError;)V", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class cj {

    /* renamed from: a  reason: collision with root package name */
    private GSDKError f19477a;

    /* renamed from: b  reason: collision with root package name */
    private AccountBindInfo f19478b;

    public final GSDKError a() {
        return this.f19477a;
    }

    public final void a(GSDKError gSDKError) {
        this.f19477a = gSDKError;
    }

    public final void a(AccountBindInfo accountBindInfo) {
        this.f19478b = accountBindInfo;
    }

    public final AccountBindInfo b() {
        return this.f19478b;
    }
}
