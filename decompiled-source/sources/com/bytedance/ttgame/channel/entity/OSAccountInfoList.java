package com.bytedance.ttgame.channel.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AccountWithSamePuidInfoResponse.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/bytedance/ttgame/channel/entity/OSAccountInfoList;", "", "osAccountInfos", "", "Lcom/bytedance/ttgame/channel/entity/OSAccountItem;", "(Ljava/util/List;)V", "getOsAccountInfos", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OSAccountInfoList {
    @SerializedName("list")
    private final List<OSAccountItem> osAccountInfos;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ OSAccountInfoList copy$default(OSAccountInfoList oSAccountInfoList, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = oSAccountInfoList.osAccountInfos;
        }
        return oSAccountInfoList.copy(list);
    }

    public final List<OSAccountItem> component1() {
        return this.osAccountInfos;
    }

    public final OSAccountInfoList copy(List<OSAccountItem> osAccountInfos) {
        Intrinsics.checkNotNullParameter(osAccountInfos, "osAccountInfos");
        return new OSAccountInfoList(osAccountInfos);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof OSAccountInfoList) && Intrinsics.areEqual(this.osAccountInfos, ((OSAccountInfoList) obj).osAccountInfos);
    }

    public int hashCode() {
        return this.osAccountInfos.hashCode();
    }

    public String toString() {
        return "OSAccountInfoList(osAccountInfos=" + this.osAccountInfos + ')';
    }

    public OSAccountInfoList(List<OSAccountItem> osAccountInfos) {
        Intrinsics.checkNotNullParameter(osAccountInfos, "osAccountInfos");
        this.osAccountInfos = osAccountInfos;
    }

    public final List<OSAccountItem> getOsAccountInfos() {
        return this.osAccountInfos;
    }
}
