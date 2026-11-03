package com.bytedance.ttgame.rocketapi.account.internal;

import com.bytedance.ttgame.base.GSDKError;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FriendInfo.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/account/internal/FriendsInfoResult;", "", "friendInfo", "", "Lcom/bytedance/ttgame/rocketapi/account/internal/FriendInfo;", "hasMore", "", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "(Ljava/util/List;ZLcom/bytedance/ttgame/base/GSDKError;)V", "getFriendInfo", "()Ljava/util/List;", "getGsdkError", "()Lcom/bytedance/ttgame/base/GSDKError;", "getHasMore", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class FriendsInfoResult {
    private final List<FriendInfo> friendInfo;
    private final GSDKError gsdkError;
    private final boolean hasMore;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FriendsInfoResult copy$default(FriendsInfoResult friendsInfoResult, List list, boolean z, GSDKError gSDKError, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = friendsInfoResult.friendInfo;
        }
        if ((i2 & 2) != 0) {
            z = friendsInfoResult.hasMore;
        }
        if ((i2 & 4) != 0) {
            gSDKError = friendsInfoResult.gsdkError;
        }
        return friendsInfoResult.copy(list, z, gSDKError);
    }

    public final List<FriendInfo> component1() {
        return this.friendInfo;
    }

    public final boolean component2() {
        return this.hasMore;
    }

    public final GSDKError component3() {
        return this.gsdkError;
    }

    public final FriendsInfoResult copy(List<FriendInfo> list, boolean z, GSDKError gSDKError) {
        return new FriendsInfoResult(list, z, gSDKError);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FriendsInfoResult) {
            FriendsInfoResult friendsInfoResult = (FriendsInfoResult) obj;
            return Intrinsics.areEqual(this.friendInfo, friendsInfoResult.friendInfo) && this.hasMore == friendsInfoResult.hasMore && Intrinsics.areEqual(this.gsdkError, friendsInfoResult.gsdkError);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        List<FriendInfo> list = this.friendInfo;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        boolean z = this.hasMore;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        GSDKError gSDKError = this.gsdkError;
        return i3 + (gSDKError != null ? gSDKError.hashCode() : 0);
    }

    public String toString() {
        return "FriendsInfoResult(friendInfo=" + this.friendInfo + ", hasMore=" + this.hasMore + ", gsdkError=" + this.gsdkError + ')';
    }

    public FriendsInfoResult(List<FriendInfo> list, boolean z, GSDKError gSDKError) {
        this.friendInfo = list;
        this.hasMore = z;
        this.gsdkError = gSDKError;
    }

    public /* synthetic */ FriendsInfoResult(List list, boolean z, GSDKError gSDKError, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i2 & 2) != 0 ? false : z, gSDKError);
    }

    public final List<FriendInfo> getFriendInfo() {
        return this.friendInfo;
    }

    public final GSDKError getGsdkError() {
        return this.gsdkError;
    }

    public final boolean getHasMore() {
        return this.hasMore;
    }
}
