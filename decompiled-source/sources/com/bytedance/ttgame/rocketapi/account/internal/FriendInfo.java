package com.bytedance.ttgame.rocketapi.account.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FriendInfo.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/account/internal/FriendInfo;", "", "openId", "", "nickName", "avatarUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAvatarUrl", "()Ljava/lang/String;", "getNickName", "getOpenId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class FriendInfo {
    private final String avatarUrl;
    private final String nickName;
    private final String openId;

    public static /* synthetic */ FriendInfo copy$default(FriendInfo friendInfo, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = friendInfo.openId;
        }
        if ((i2 & 2) != 0) {
            str2 = friendInfo.nickName;
        }
        if ((i2 & 4) != 0) {
            str3 = friendInfo.avatarUrl;
        }
        return friendInfo.copy(str, str2, str3);
    }

    public final String component1() {
        return this.openId;
    }

    public final String component2() {
        return this.nickName;
    }

    public final String component3() {
        return this.avatarUrl;
    }

    public final FriendInfo copy(String str, String str2, String str3) {
        return new FriendInfo(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FriendInfo) {
            FriendInfo friendInfo = (FriendInfo) obj;
            return Intrinsics.areEqual(this.openId, friendInfo.openId) && Intrinsics.areEqual(this.nickName, friendInfo.nickName) && Intrinsics.areEqual(this.avatarUrl, friendInfo.avatarUrl);
        }
        return false;
    }

    public int hashCode() {
        String str = this.openId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.nickName;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.avatarUrl;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "FriendInfo(openId=" + this.openId + ", nickName=" + this.nickName + ", avatarUrl=" + this.avatarUrl + ')';
    }

    public FriendInfo(String str, String str2, String str3) {
        this.openId = str;
        this.nickName = str2;
        this.avatarUrl = str3;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public final String getNickName() {
        return this.nickName;
    }

    public final String getOpenId() {
        return this.openId;
    }
}
