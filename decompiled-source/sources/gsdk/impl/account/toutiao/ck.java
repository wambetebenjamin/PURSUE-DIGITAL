package gsdk.impl.account.toutiao;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0005\"\u0004\b\u0006\u0010\u0004¨\u0006\u000f"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/pojo/Data;", "", "isRegistered", "", "(Z)V", "()Z", "setRegistered", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ck {
    @SerializedName("is_registered")

    /* renamed from: a  reason: collision with root package name */
    private boolean f19479a;

    public ck() {
        this(false, 1, null);
    }

    public static /* synthetic */ ck a(ck ckVar, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = ckVar.f19479a;
        }
        return ckVar.b(z);
    }

    public final ck b(boolean z) {
        return new ck(z);
    }

    public final boolean b() {
        return this.f19479a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ck) && this.f19479a == ((ck) obj).f19479a;
    }

    public int hashCode() {
        boolean z = this.f19479a;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "Data(isRegistered=" + this.f19479a + ')';
    }

    public ck(boolean z) {
        this.f19479a = z;
    }

    public /* synthetic */ ck(boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z);
    }

    public final void a(boolean z) {
        this.f19479a = z;
    }

    public final boolean a() {
        return this.f19479a;
    }
}
