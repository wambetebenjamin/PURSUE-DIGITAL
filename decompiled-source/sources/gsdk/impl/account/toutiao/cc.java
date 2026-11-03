package gsdk.impl.account.toutiao;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountCodeData;", "", "accountCode", "", "(Ljava/lang/String;)V", "getAccountCode", "()Ljava/lang/String;", "setAccountCode", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class cc {
    @SerializedName("account_code")

    /* renamed from: a  reason: collision with root package name */
    private String f19464a;

    public static /* synthetic */ cc a(cc ccVar, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ccVar.f19464a;
        }
        return ccVar.b(str);
    }

    public final cc b(String accountCode) {
        Intrinsics.checkNotNullParameter(accountCode, "accountCode");
        return new cc(accountCode);
    }

    public final String b() {
        return this.f19464a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof cc) && Intrinsics.areEqual(this.f19464a, ((cc) obj).f19464a);
    }

    public int hashCode() {
        return this.f19464a.hashCode();
    }

    public String toString() {
        return "AccountCodeData(accountCode=" + this.f19464a + ')';
    }

    public cc(String accountCode) {
        Intrinsics.checkNotNullParameter(accountCode, "accountCode");
        this.f19464a = accountCode;
    }

    public final String a() {
        return this.f19464a;
    }

    public final void a(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f19464a = str;
    }
}
