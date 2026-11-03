package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.BaseResponse;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\b\u0018\u00010\nR\u00020\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/PassRegisterResponse;", "Lcom/bytedance/ttgame/base/BaseResponse;", "()V", "logId", "", "getLogId", "()Ljava/lang/String;", "setLogId", "(Ljava/lang/String;)V", "passRegister", "Lcom/bytedance/ttgame/sdk/module/account/pojo/PassRegisterResponse$PassRegister;", "getPassRegister", "()Lcom/bytedance/ttgame/sdk/module/account/pojo/PassRegisterResponse$PassRegister;", "setPassRegister", "(Lcom/bytedance/ttgame/sdk/module/account/pojo/PassRegisterResponse$PassRegister;)V", "PassRegister", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class cn extends BaseResponse {
    @SerializedName(com.bytedance.apm.constant.l.KEY_LOG_ID)

    /* renamed from: a  reason: collision with root package name */
    private String f19482a;
    @SerializedName("data")

    /* renamed from: b  reason: collision with root package name */
    private a f19483b;

    public final String a() {
        return this.f19482a;
    }

    public final void a(String str) {
        this.f19482a = str;
    }

    public final void a(a aVar) {
        this.f19483b = aVar;
    }

    public final a b() {
        return this.f19483b;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/PassRegisterResponse$PassRegister;", "", "(Lcom/bytedance/ttgame/sdk/module/account/pojo/PassRegisterResponse;)V", "isRegistered", "", "()Z", "setRegistered", "(Z)V", "subStatus", "", "getSubStatus", "()I", "setSubStatus", "(I)V", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public final class a {
        @SerializedName("is_registered")

        /* renamed from: b  reason: collision with root package name */
        private boolean f19485b;
        @SerializedName("sub_status")

        /* renamed from: c  reason: collision with root package name */
        private int f19486c;

        public a() {
        }

        public final void a(boolean z) {
            this.f19485b = z;
        }

        public final boolean a() {
            return this.f19485b;
        }

        public final void a(int i2) {
            this.f19486c = i2;
        }

        public final int b() {
            return this.f19486c;
        }
    }
}
