package com.bytedance.ttgame.module.share.api.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AweShareVideoInfo.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003JO\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/bytedance/ttgame/module/share/api/entity/AweShareVideoInfo;", "", "videoId", "", "iFrameCode", "fromUserId", "shareId", "itemId", "hasDefaultHashTag", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getFromUserId", "()Ljava/lang/String;", "getHasDefaultHashTag", "()Z", "getIFrameCode", "getItemId", "getShareId", "getVideoId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "share_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AweShareVideoInfo {
    private final String fromUserId;
    private final boolean hasDefaultHashTag;
    private final String iFrameCode;
    private final String itemId;
    private final String shareId;
    private final String videoId;

    public static /* synthetic */ AweShareVideoInfo copy$default(AweShareVideoInfo aweShareVideoInfo, String str, String str2, String str3, String str4, String str5, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = aweShareVideoInfo.videoId;
        }
        if ((i2 & 2) != 0) {
            str2 = aweShareVideoInfo.iFrameCode;
        }
        String str6 = str2;
        if ((i2 & 4) != 0) {
            str3 = aweShareVideoInfo.fromUserId;
        }
        String str7 = str3;
        if ((i2 & 8) != 0) {
            str4 = aweShareVideoInfo.shareId;
        }
        String str8 = str4;
        if ((i2 & 16) != 0) {
            str5 = aweShareVideoInfo.itemId;
        }
        String str9 = str5;
        if ((i2 & 32) != 0) {
            z = aweShareVideoInfo.hasDefaultHashTag;
        }
        return aweShareVideoInfo.copy(str, str6, str7, str8, str9, z);
    }

    public final String component1() {
        return this.videoId;
    }

    public final String component2() {
        return this.iFrameCode;
    }

    public final String component3() {
        return this.fromUserId;
    }

    public final String component4() {
        return this.shareId;
    }

    public final String component5() {
        return this.itemId;
    }

    public final boolean component6() {
        return this.hasDefaultHashTag;
    }

    public final AweShareVideoInfo copy(String str, String str2, String str3, String str4, String str5, boolean z) {
        return new AweShareVideoInfo(str, str2, str3, str4, str5, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AweShareVideoInfo) {
            AweShareVideoInfo aweShareVideoInfo = (AweShareVideoInfo) obj;
            return Intrinsics.areEqual(this.videoId, aweShareVideoInfo.videoId) && Intrinsics.areEqual(this.iFrameCode, aweShareVideoInfo.iFrameCode) && Intrinsics.areEqual(this.fromUserId, aweShareVideoInfo.fromUserId) && Intrinsics.areEqual(this.shareId, aweShareVideoInfo.shareId) && Intrinsics.areEqual(this.itemId, aweShareVideoInfo.itemId) && this.hasDefaultHashTag == aweShareVideoInfo.hasDefaultHashTag;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.videoId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.iFrameCode;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fromUserId;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.shareId;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.itemId;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        boolean z = this.hasDefaultHashTag;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode5 + i2;
    }

    public String toString() {
        return "AweShareVideoInfo(videoId=" + this.videoId + ", iFrameCode=" + this.iFrameCode + ", fromUserId=" + this.fromUserId + ", shareId=" + this.shareId + ", itemId=" + this.itemId + ", hasDefaultHashTag=" + this.hasDefaultHashTag + ')';
    }

    public AweShareVideoInfo(String str, String str2, String str3, String str4, String str5, boolean z) {
        this.videoId = str;
        this.iFrameCode = str2;
        this.fromUserId = str3;
        this.shareId = str4;
        this.itemId = str5;
        this.hasDefaultHashTag = z;
    }

    public final String getVideoId() {
        return this.videoId;
    }

    public final String getIFrameCode() {
        return this.iFrameCode;
    }

    public final String getFromUserId() {
        return this.fromUserId;
    }

    public final String getShareId() {
        return this.shareId;
    }

    public final String getItemId() {
        return this.itemId;
    }

    public final boolean getHasDefaultHashTag() {
        return this.hasDefaultHashTag;
    }
}
