package com.vk.api.sdk.okhttp;

import com.bytedance.pipo.payment.common.lib.c;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RequestTag.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J>\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u001dJ\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/vk/api/sdk/okhttp/RequestTag;", "", "uid", "", "awaitNetwork", "", c.de, "", DBDefinition.RETRY_COUNT, "(Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;)V", "getAwaitNetwork", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getReason", "()Ljava/lang/String;", "getRetryCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUid", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;)Lcom/vk/api/sdk/okhttp/RequestTag;", "equals", "other", "hashCode", "toMap", "", "toString", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class RequestTag {
    private final Boolean awaitNetwork;
    private final String reason;
    private final Integer retryCount;
    private final Integer uid;

    public RequestTag() {
        this(null, null, null, null, 15, null);
    }

    public RequestTag(Integer num) {
        this(num, null, null, null, 14, null);
    }

    public RequestTag(Integer num, Boolean bool) {
        this(num, bool, null, null, 12, null);
    }

    public RequestTag(Integer num, Boolean bool, String str) {
        this(num, bool, str, null, 8, null);
    }

    public static /* synthetic */ RequestTag copy$default(RequestTag requestTag, Integer num, Boolean bool, String str, Integer num2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = requestTag.uid;
        }
        if ((i2 & 2) != 0) {
            bool = requestTag.awaitNetwork;
        }
        if ((i2 & 4) != 0) {
            str = requestTag.reason;
        }
        if ((i2 & 8) != 0) {
            num2 = requestTag.retryCount;
        }
        return requestTag.copy(num, bool, str, num2);
    }

    public final Integer component1() {
        return this.uid;
    }

    public final Boolean component2() {
        return this.awaitNetwork;
    }

    public final String component3() {
        return this.reason;
    }

    public final Integer component4() {
        return this.retryCount;
    }

    public final RequestTag copy(Integer num, Boolean bool, String str, Integer num2) {
        return new RequestTag(num, bool, str, num2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof RequestTag) {
                RequestTag requestTag = (RequestTag) obj;
                return Intrinsics.areEqual(this.uid, requestTag.uid) && Intrinsics.areEqual(this.awaitNetwork, requestTag.awaitNetwork) && Intrinsics.areEqual(this.reason, requestTag.reason) && Intrinsics.areEqual(this.retryCount, requestTag.retryCount);
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        Integer num = this.uid;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        Boolean bool = this.awaitNetwork;
        int hashCode2 = (hashCode + (bool != null ? bool.hashCode() : 0)) * 31;
        String str = this.reason;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        Integer num2 = this.retryCount;
        return hashCode3 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "RequestTag(uid=" + this.uid + ", awaitNetwork=" + this.awaitNetwork + ", reason=" + this.reason + ", retryCount=" + this.retryCount + ")";
    }

    public RequestTag(Integer num, Boolean bool, String str, Integer num2) {
        this.uid = num;
        this.awaitNetwork = bool;
        this.reason = str;
        this.retryCount = num2;
    }

    public /* synthetic */ RequestTag(Integer num, Boolean bool, String str, Integer num2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : num, (i2 & 2) != 0 ? null : bool, (i2 & 4) != 0 ? null : str, (i2 & 8) != 0 ? null : num2);
    }

    public final Integer getUid() {
        return this.uid;
    }

    public final Boolean getAwaitNetwork() {
        return this.awaitNetwork;
    }

    public final String getReason() {
        return this.reason;
    }

    public final Integer getRetryCount() {
        return this.retryCount;
    }

    public final Map<String, Object> toMap() {
        return MapsKt.mutableMapOf(TuplesKt.to("UID", this.uid), TuplesKt.to("AWAIT_NETWORK", this.awaitNetwork), TuplesKt.to("REASON", this.reason), TuplesKt.to("RETRY_COUNT", this.retryCount));
    }
}
