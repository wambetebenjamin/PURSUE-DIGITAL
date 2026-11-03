package com.bytedance.ttgame.module.share.request;

import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/bytedance/ttgame/module/share/request/IAweShareIdRequestBody;", "", RemoteConfigConstants.RequestFieldKey.APP_ID, "", "clientKey", "", "defaultHashTag", "(ILjava/lang/String;Ljava/lang/String;)V", "getAppId", "()I", "getClientKey", "()Ljava/lang/String;", "getDefaultHashTag", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "share_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class IAweShareIdRequestBody {
    @SerializedName("app_id")
    private final int appId;
    @SerializedName(ParamKeyConstants.WebViewConstants.QUERY_CLIENT_KEY)
    private final String clientKey;
    @SerializedName("default_hashtag")
    private final String defaultHashTag;

    public static /* synthetic */ IAweShareIdRequestBody copy$default(IAweShareIdRequestBody iAweShareIdRequestBody, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = iAweShareIdRequestBody.appId;
        }
        if ((i3 & 2) != 0) {
            str = iAweShareIdRequestBody.clientKey;
        }
        if ((i3 & 4) != 0) {
            str2 = iAweShareIdRequestBody.defaultHashTag;
        }
        return iAweShareIdRequestBody.copy(i2, str, str2);
    }

    public final int component1() {
        return this.appId;
    }

    public final String component2() {
        return this.clientKey;
    }

    public final String component3() {
        return this.defaultHashTag;
    }

    public final IAweShareIdRequestBody copy(int i2, String clientKey, String defaultHashTag) {
        Intrinsics.checkNotNullParameter(clientKey, "clientKey");
        Intrinsics.checkNotNullParameter(defaultHashTag, "defaultHashTag");
        return new IAweShareIdRequestBody(i2, clientKey, defaultHashTag);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IAweShareIdRequestBody) {
            IAweShareIdRequestBody iAweShareIdRequestBody = (IAweShareIdRequestBody) obj;
            return this.appId == iAweShareIdRequestBody.appId && Intrinsics.areEqual(this.clientKey, iAweShareIdRequestBody.clientKey) && Intrinsics.areEqual(this.defaultHashTag, iAweShareIdRequestBody.defaultHashTag);
        }
        return false;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.appId) * 31) + this.clientKey.hashCode()) * 31) + this.defaultHashTag.hashCode();
    }

    public String toString() {
        return "IAweShareIdRequestBody(appId=" + this.appId + ", clientKey=" + this.clientKey + ", defaultHashTag=" + this.defaultHashTag + ')';
    }

    public IAweShareIdRequestBody(int i2, String clientKey, String defaultHashTag) {
        Intrinsics.checkNotNullParameter(clientKey, "clientKey");
        Intrinsics.checkNotNullParameter(defaultHashTag, "defaultHashTag");
        this.appId = i2;
        this.clientKey = clientKey;
        this.defaultHashTag = defaultHashTag;
    }

    public final int getAppId() {
        return this.appId;
    }

    public final String getClientKey() {
        return this.clientKey;
    }

    public final String getDefaultHashTag() {
        return this.defaultHashTag;
    }
}
