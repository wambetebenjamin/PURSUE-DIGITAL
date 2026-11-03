package com.bytedance.ttgame.library.module_manager.internal;

import com.bytedance.ttgame.library.module_manager.GSDKRegion;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GSDKSuiteMetaInfo.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JM\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010¨\u0006#"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/GSDKSuiteMetaInfo;", "", "region", "Lcom/bytedance/ttgame/library/module_manager/GSDKRegion;", "suiteFamily", "", "suiteRevision", "suitePublicVersion", "gsdkAppId", "components", "", "Lcom/bytedance/ttgame/library/module_manager/internal/ComponentSpec;", "(Lcom/bytedance/ttgame/library/module_manager/GSDKRegion;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "getGsdkAppId", "()Ljava/lang/String;", "getRegion", "()Lcom/bytedance/ttgame/library/module_manager/GSDKRegion;", "getSuiteFamily", "getSuitePublicVersion", "getSuiteRevision", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GSDKSuiteMetaInfo {
    @SerializedName("components")
    private final List<ComponentSpec> components;
    @SerializedName("gsdk_app_id")
    private final String gsdkAppId;
    @SerializedName("region")
    private final GSDKRegion region;
    @SerializedName("suite_family")
    private final String suiteFamily;
    @SerializedName("suite_public_version")
    private final String suitePublicVersion;
    @SerializedName("suite_revision")
    private final String suiteRevision;

    public static /* synthetic */ GSDKSuiteMetaInfo copy$default(GSDKSuiteMetaInfo gSDKSuiteMetaInfo, GSDKRegion gSDKRegion, String str, String str2, String str3, String str4, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            gSDKRegion = gSDKSuiteMetaInfo.region;
        }
        if ((i2 & 2) != 0) {
            str = gSDKSuiteMetaInfo.suiteFamily;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = gSDKSuiteMetaInfo.suiteRevision;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = gSDKSuiteMetaInfo.suitePublicVersion;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            str4 = gSDKSuiteMetaInfo.gsdkAppId;
        }
        String str8 = str4;
        List<ComponentSpec> list2 = list;
        if ((i2 & 32) != 0) {
            list2 = gSDKSuiteMetaInfo.components;
        }
        return gSDKSuiteMetaInfo.copy(gSDKRegion, str5, str6, str7, str8, list2);
    }

    public final GSDKRegion component1() {
        return this.region;
    }

    public final String component2() {
        return this.suiteFamily;
    }

    public final String component3() {
        return this.suiteRevision;
    }

    public final String component4() {
        return this.suitePublicVersion;
    }

    public final String component5() {
        return this.gsdkAppId;
    }

    public final List<ComponentSpec> component6() {
        return this.components;
    }

    public final GSDKSuiteMetaInfo copy(GSDKRegion region, String suiteFamily, String suiteRevision, String str, String gsdkAppId, List<ComponentSpec> components) {
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(suiteFamily, "suiteFamily");
        Intrinsics.checkNotNullParameter(suiteRevision, "suiteRevision");
        Intrinsics.checkNotNullParameter(gsdkAppId, "gsdkAppId");
        Intrinsics.checkNotNullParameter(components, "components");
        return new GSDKSuiteMetaInfo(region, suiteFamily, suiteRevision, str, gsdkAppId, components);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GSDKSuiteMetaInfo) {
            GSDKSuiteMetaInfo gSDKSuiteMetaInfo = (GSDKSuiteMetaInfo) obj;
            return this.region == gSDKSuiteMetaInfo.region && Intrinsics.areEqual(this.suiteFamily, gSDKSuiteMetaInfo.suiteFamily) && Intrinsics.areEqual(this.suiteRevision, gSDKSuiteMetaInfo.suiteRevision) && Intrinsics.areEqual(this.suitePublicVersion, gSDKSuiteMetaInfo.suitePublicVersion) && Intrinsics.areEqual(this.gsdkAppId, gSDKSuiteMetaInfo.gsdkAppId) && Intrinsics.areEqual(this.components, gSDKSuiteMetaInfo.components);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((this.region.hashCode() * 31) + this.suiteFamily.hashCode()) * 31) + this.suiteRevision.hashCode()) * 31;
        String str = this.suitePublicVersion;
        return ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.gsdkAppId.hashCode()) * 31) + this.components.hashCode();
    }

    public String toString() {
        return "GSDKSuiteMetaInfo(region=" + this.region + ", suiteFamily=" + this.suiteFamily + ", suiteRevision=" + this.suiteRevision + ", suitePublicVersion=" + this.suitePublicVersion + ", gsdkAppId=" + this.gsdkAppId + ", components=" + this.components + ')';
    }

    public GSDKSuiteMetaInfo(GSDKRegion region, String suiteFamily, String suiteRevision, String str, String gsdkAppId, List<ComponentSpec> components) {
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(suiteFamily, "suiteFamily");
        Intrinsics.checkNotNullParameter(suiteRevision, "suiteRevision");
        Intrinsics.checkNotNullParameter(gsdkAppId, "gsdkAppId");
        Intrinsics.checkNotNullParameter(components, "components");
        this.region = region;
        this.suiteFamily = suiteFamily;
        this.suiteRevision = suiteRevision;
        this.suitePublicVersion = str;
        this.gsdkAppId = gsdkAppId;
        this.components = components;
    }

    public final GSDKRegion getRegion() {
        return this.region;
    }

    public final String getSuiteFamily() {
        return this.suiteFamily;
    }

    public final String getSuiteRevision() {
        return this.suiteRevision;
    }

    public final String getSuitePublicVersion() {
        return this.suitePublicVersion;
    }

    public final String getGsdkAppId() {
        return this.gsdkAppId;
    }

    public final List<ComponentSpec> getComponents() {
        return this.components;
    }
}
