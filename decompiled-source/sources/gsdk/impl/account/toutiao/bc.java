package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginInfo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/login/seamless/pojo/SeamlessLoginAccountsResponse;", "Lcom/bytedance/ttgame/base/BaseResponse;", "()V", "data", "Lcom/bytedance/ttgame/sdk/module/account/login/seamless/pojo/SeamlessLoginAccountsResponse$LinkInfos;", "getData", "()Lcom/bytedance/ttgame/sdk/module/account/login/seamless/pojo/SeamlessLoginAccountsResponse$LinkInfos;", "setData", "(Lcom/bytedance/ttgame/sdk/module/account/login/seamless/pojo/SeamlessLoginAccountsResponse$LinkInfos;)V", "logId", "", "getLogId", "()Ljava/lang/String;", "setLogId", "(Ljava/lang/String;)V", "LinkInfos", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class bc extends BaseResponse {
    @SerializedName(com.bytedance.apm.constant.l.KEY_LOG_ID)

    /* renamed from: a  reason: collision with root package name */
    private String f19379a;
    @SerializedName("data")

    /* renamed from: b  reason: collision with root package name */
    private a f19380b;

    public final String a() {
        return this.f19379a;
    }

    public final void a(String str) {
        this.f19379a = str;
    }

    public final void a(a aVar) {
        this.f19380b = aVar;
    }

    public final a b() {
        return this.f19380b;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/login/seamless/pojo/SeamlessLoginAccountsResponse$LinkInfos;", "", "(Lcom/bytedance/ttgame/sdk/module/account/login/seamless/pojo/SeamlessLoginAccountsResponse;)V", "linkinfos", "", "Lcom/bytedance/ttgame/rocketapi/seamless/pojo/SeamlessLoginInfo;", "getLinkinfos", "()Ljava/util/List;", "setLinkinfos", "(Ljava/util/List;)V", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public final class a {
        @SerializedName("linkinfos")

        /* renamed from: b  reason: collision with root package name */
        private List<? extends SeamlessLoginInfo> f19382b;

        public a() {
        }

        public final List<SeamlessLoginInfo> a() {
            return this.f19382b;
        }

        public final void a(List<? extends SeamlessLoginInfo> list) {
            this.f19382b = list;
        }
    }
}
