package gsdk.impl.share.DEFAULT;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b)\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\bHÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\fHÆ\u0003J\t\u0010/\u001a\u00020\fHÆ\u0003Jc\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001J\u0013\u00101\u001a\u00020\b2\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\fHÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001e\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001e\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 ¨\u00065"}, d2 = {"Lcom/bytedance/ttgame/module/share/request/AweShareVideoInfoResponseData;", "", "videoId", "", "shareId", "fromUserId", "itemId", "hasDefaultHashtag", "", "iframeCode", "videoTitle", "videoWidth", "", "videoHeight", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;II)V", "getFromUserId", "()Ljava/lang/String;", "setFromUserId", "(Ljava/lang/String;)V", "getHasDefaultHashtag", "()Z", "setHasDefaultHashtag", "(Z)V", "getIframeCode", "setIframeCode", "getItemId", "setItemId", "getShareId", "setShareId", "getVideoHeight", "()I", "setVideoHeight", "(I)V", "getVideoId", "setVideoId", "getVideoTitle", "setVideoTitle", "getVideoWidth", "setVideoWidth", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "share_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ag {
    @SerializedName("video_id")

    /* renamed from: a  reason: collision with root package name */
    private String f20727a;
    @SerializedName("share_id")

    /* renamed from: b  reason: collision with root package name */
    private String f20728b;
    @SerializedName("from_user_id")

    /* renamed from: c  reason: collision with root package name */
    private String f20729c;
    @SerializedName(FirebaseAnalytics.Param.ITEM_ID)

    /* renamed from: d  reason: collision with root package name */
    private String f20730d;
    @SerializedName("has_default_hashtag")

    /* renamed from: e  reason: collision with root package name */
    private boolean f20731e;
    @SerializedName("iframe_code")

    /* renamed from: f  reason: collision with root package name */
    private String f20732f;
    @SerializedName("video_title")

    /* renamed from: g  reason: collision with root package name */
    private String f20733g;
    @SerializedName("video_width")

    /* renamed from: h  reason: collision with root package name */
    private int f20734h;
    @SerializedName("video_height")

    /* renamed from: i  reason: collision with root package name */
    private int f20735i;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ag) {
            ag agVar = (ag) obj;
            return Intrinsics.areEqual(this.f20727a, agVar.f20727a) && Intrinsics.areEqual(this.f20728b, agVar.f20728b) && Intrinsics.areEqual(this.f20729c, agVar.f20729c) && Intrinsics.areEqual(this.f20730d, agVar.f20730d) && this.f20731e == agVar.f20731e && Intrinsics.areEqual(this.f20732f, agVar.f20732f) && Intrinsics.areEqual(this.f20733g, agVar.f20733g) && this.f20734h == agVar.f20734h && this.f20735i == agVar.f20735i;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((this.f20727a.hashCode() * 31) + this.f20728b.hashCode()) * 31) + this.f20729c.hashCode()) * 31) + this.f20730d.hashCode()) * 31;
        boolean z = this.f20731e;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return ((((((((hashCode + i2) * 31) + this.f20732f.hashCode()) * 31) + this.f20733g.hashCode()) * 31) + Integer.hashCode(this.f20734h)) * 31) + Integer.hashCode(this.f20735i);
    }

    public String toString() {
        return "AweShareVideoInfoResponseData(videoId=" + this.f20727a + ", shareId=" + this.f20728b + ", fromUserId=" + this.f20729c + ", itemId=" + this.f20730d + ", hasDefaultHashtag=" + this.f20731e + ", iframeCode=" + this.f20732f + ", videoTitle=" + this.f20733g + ", videoWidth=" + this.f20734h + ", videoHeight=" + this.f20735i + ')';
    }

    public final String a() {
        return this.f20727a;
    }

    public final String b() {
        return this.f20729c;
    }

    public final String c() {
        return this.f20730d;
    }

    public final boolean d() {
        return this.f20731e;
    }

    public final String e() {
        return this.f20732f;
    }
}
