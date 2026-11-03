package gsdk.impl.share.DEFAULT;

import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/share/request/AweShareIdResponseData;", "", "shareId", "", "clientKey", "(Ljava/lang/String;Ljava/lang/String;)V", "getClientKey", "()Ljava/lang/String;", "setClientKey", "(Ljava/lang/String;)V", "getShareId", "setShareId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "share_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ae {
    @SerializedName("share_id")

    /* renamed from: a  reason: collision with root package name */
    private String f20721a;
    @SerializedName(ParamKeyConstants.WebViewConstants.QUERY_CLIENT_KEY)

    /* renamed from: b  reason: collision with root package name */
    private String f20722b;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ae) {
            ae aeVar = (ae) obj;
            return Intrinsics.areEqual(this.f20721a, aeVar.f20721a) && Intrinsics.areEqual(this.f20722b, aeVar.f20722b);
        }
        return false;
    }

    public int hashCode() {
        return (this.f20721a.hashCode() * 31) + this.f20722b.hashCode();
    }

    public String toString() {
        return "AweShareIdResponseData(shareId=" + this.f20721a + ", clientKey=" + this.f20722b + ')';
    }

    public final String a() {
        return this.f20721a;
    }
}
