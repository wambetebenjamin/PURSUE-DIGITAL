package com.bytedance.pipo.iap.common.ability.model.api.entity;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: DynamicErrorsData.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR$\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/DynamicErrorsData;", "", "errorsMap", "", "", "Lcom/bytedance/pipo/iap/common/ability/model/api/entity/ErrorGuideForUser;", "checksum", "(Ljava/util/Map;Ljava/lang/String;)V", "getChecksum", "()Ljava/lang/String;", "getErrorsMap", "()Ljava/util/Map;", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DynamicErrorsData {
    @SerializedName("checksum")
    private final String checksum;
    @SerializedName("errors_map")
    private final Map<String, ErrorGuideForUser> errorsMap;

    public DynamicErrorsData() {
        this(null, null, 3, null);
    }

    public DynamicErrorsData(Map<String, ErrorGuideForUser> map, String str) {
        this.errorsMap = map;
        this.checksum = str;
    }

    public /* synthetic */ DynamicErrorsData(Map map, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : map, (i2 & 2) != 0 ? null : str);
    }

    public final Map<String, ErrorGuideForUser> getErrorsMap() {
        return this.errorsMap;
    }

    public final String getChecksum() {
        return this.checksum;
    }
}
