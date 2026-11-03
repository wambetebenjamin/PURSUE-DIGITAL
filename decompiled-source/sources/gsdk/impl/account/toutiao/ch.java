package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.rocketapi.account.pojo.AccountBindInfo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/BindInfoResponse;", "Lcom/bytedance/ttgame/base/BaseResponse;", "()V", "data", "Lcom/bytedance/ttgame/sdk/module/account/pojo/BindInfoResponse$Data;", "getData", "()Lcom/bytedance/ttgame/sdk/module/account/pojo/BindInfoResponse$Data;", "Data", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ch extends BaseResponse {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private final a f19473a;

    public final a a() {
        return this.f19473a;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/BindInfoResponse$Data;", "", "(Lcom/bytedance/ttgame/sdk/module/account/pojo/BindInfoResponse;)V", "bind_infos", "", "Lcom/bytedance/ttgame/rocketapi/account/pojo/AccountBindInfo;", "getBind_infos", "()Ljava/util/List;", "setBind_infos", "(Ljava/util/List;)V", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public final class a {
        @SerializedName("bind_infos")

        /* renamed from: b  reason: collision with root package name */
        private List<? extends AccountBindInfo> f19475b;

        public a() {
        }

        public final List<AccountBindInfo> a() {
            return this.f19475b;
        }

        public final void a(List<? extends AccountBindInfo> list) {
            this.f19475b = list;
        }
    }
}
