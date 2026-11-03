package gsdk.impl.share.DEFAULT;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015¨\u0006\""}, d2 = {"Lcom/bytedance/ttgame/module/share/request/AweShareIdResponse;", "", "code", "", "message", "", "logId", "data", "Lcom/bytedance/ttgame/module/share/request/AweShareIdResponseData;", "(ILjava/lang/String;Ljava/lang/String;Lcom/bytedance/ttgame/module/share/request/AweShareIdResponseData;)V", "getCode", "()I", "setCode", "(I)V", "getData", "()Lcom/bytedance/ttgame/module/share/request/AweShareIdResponseData;", "setData", "(Lcom/bytedance/ttgame/module/share/request/AweShareIdResponseData;)V", "getLogId", "()Ljava/lang/String;", "setLogId", "(Ljava/lang/String;)V", "getMessage", "setMessage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "share_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ad {
    @SerializedName("code")

    /* renamed from: a  reason: collision with root package name */
    private int f20717a;
    @SerializedName(alternate = {"message"}, value = "msg")

    /* renamed from: b  reason: collision with root package name */
    private String f20718b;
    @SerializedName(com.bytedance.apm.constant.l.KEY_LOG_ID)

    /* renamed from: c  reason: collision with root package name */
    private String f20719c;
    @SerializedName("data")

    /* renamed from: d  reason: collision with root package name */
    private ae f20720d;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ad) {
            ad adVar = (ad) obj;
            return this.f20717a == adVar.f20717a && Intrinsics.areEqual(this.f20718b, adVar.f20718b) && Intrinsics.areEqual(this.f20719c, adVar.f20719c) && Intrinsics.areEqual(this.f20720d, adVar.f20720d);
        }
        return false;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.f20717a) * 31) + this.f20718b.hashCode()) * 31) + this.f20719c.hashCode()) * 31) + this.f20720d.hashCode();
    }

    public String toString() {
        return "AweShareIdResponse(code=" + this.f20717a + ", message=" + this.f20718b + ", logId=" + this.f20719c + ", data=" + this.f20720d + ')';
    }

    public final int a() {
        return this.f20717a;
    }

    public final String b() {
        return this.f20718b;
    }

    public final ae c() {
        return this.f20720d;
    }
}
