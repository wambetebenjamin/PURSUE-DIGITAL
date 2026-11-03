package gsdk.impl.account.toutiao;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010$\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0011JV\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020\u00072\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\r\"\u0004\b\u0016\u0010\u000fR\"\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\r\"\u0004\b\u001a\u0010\u000fR\"\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006-"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountPasswordData;", "", "accountCode", "", "email", "mobile", "password", "", "changeAccountCodeTime", "", "lastChangeAccountCodeTime", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/Long;)V", "getAccountCode", "()Ljava/lang/String;", "setAccountCode", "(Ljava/lang/String;)V", "getChangeAccountCodeTime", "()Ljava/lang/Long;", "setChangeAccountCodeTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getEmail", "setEmail", "getLastChangeAccountCodeTime", "setLastChangeAccountCodeTime", "getMobile", "setMobile", "getPassword", "()Ljava/lang/Boolean;", "setPassword", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/Long;)Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountPasswordData;", "equals", "other", "hashCode", "", "toString", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class cf {
    @SerializedName("account_code")

    /* renamed from: a  reason: collision with root package name */
    private String f19466a;
    @SerializedName("email")

    /* renamed from: b  reason: collision with root package name */
    private String f19467b;
    @SerializedName("mobile")

    /* renamed from: c  reason: collision with root package name */
    private String f19468c;
    @SerializedName("has_password")

    /* renamed from: d  reason: collision with root package name */
    private Boolean f19469d;
    @SerializedName("change_account_code_time")

    /* renamed from: e  reason: collision with root package name */
    private Long f19470e;
    @SerializedName("last_change_account_code_time")

    /* renamed from: f  reason: collision with root package name */
    private Long f19471f;

    public static /* synthetic */ cf a(cf cfVar, String str, String str2, String str3, Boolean bool, Long l, Long l2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = cfVar.f19466a;
        }
        if ((i2 & 2) != 0) {
            str2 = cfVar.f19467b;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            str3 = cfVar.f19468c;
        }
        String str5 = str3;
        if ((i2 & 8) != 0) {
            bool = cfVar.f19469d;
        }
        Boolean bool2 = bool;
        if ((i2 & 16) != 0) {
            l = cfVar.f19470e;
        }
        Long l3 = l;
        if ((i2 & 32) != 0) {
            l2 = cfVar.f19471f;
        }
        return cfVar.a(str, str4, str5, bool2, l3, l2);
    }

    public final cf a(String str, String str2, String str3, Boolean bool, Long l, Long l2) {
        return new cf(str, str2, str3, bool, l, l2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof cf) {
            cf cfVar = (cf) obj;
            return Intrinsics.areEqual(this.f19466a, cfVar.f19466a) && Intrinsics.areEqual(this.f19467b, cfVar.f19467b) && Intrinsics.areEqual(this.f19468c, cfVar.f19468c) && Intrinsics.areEqual(this.f19469d, cfVar.f19469d) && Intrinsics.areEqual(this.f19470e, cfVar.f19470e) && Intrinsics.areEqual(this.f19471f, cfVar.f19471f);
        }
        return false;
    }

    public final String g() {
        return this.f19466a;
    }

    public final String h() {
        return this.f19467b;
    }

    public int hashCode() {
        String str = this.f19466a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f19467b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.f19468c;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.f19469d;
        int hashCode4 = (hashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        Long l = this.f19470e;
        int hashCode5 = (hashCode4 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.f19471f;
        return hashCode5 + (l2 != null ? l2.hashCode() : 0);
    }

    public final String i() {
        return this.f19468c;
    }

    public final Boolean j() {
        return this.f19469d;
    }

    public final Long k() {
        return this.f19470e;
    }

    public final Long l() {
        return this.f19471f;
    }

    public String toString() {
        return "AccountPasswordData(accountCode=" + this.f19466a + ", email=" + this.f19467b + ", mobile=" + this.f19468c + ", password=" + this.f19469d + ", changeAccountCodeTime=" + this.f19470e + ", lastChangeAccountCodeTime=" + this.f19471f + ')';
    }

    public cf(String str, String str2, String str3, Boolean bool, Long l, Long l2) {
        this.f19466a = str;
        this.f19467b = str2;
        this.f19468c = str3;
        this.f19469d = bool;
        this.f19470e = l;
        this.f19471f = l2;
    }

    public final String a() {
        return this.f19466a;
    }

    public final void a(String str) {
        this.f19466a = str;
    }

    public final String b() {
        return this.f19467b;
    }

    public final void b(String str) {
        this.f19467b = str;
    }

    public final String c() {
        return this.f19468c;
    }

    public final void c(String str) {
        this.f19468c = str;
    }

    public final void a(Boolean bool) {
        this.f19469d = bool;
    }

    public final Boolean d() {
        return this.f19469d;
    }

    public final void a(Long l) {
        this.f19470e = l;
    }

    public final Long e() {
        return this.f19470e;
    }

    public final void b(Long l) {
        this.f19471f = l;
    }

    public final Long f() {
        return this.f19471f;
    }
}
