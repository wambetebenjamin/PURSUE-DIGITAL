package com.bytedance.ttgame.module.cdkey.api;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/CDKeyInfo;", "", "cdkey", "", "role_id", "server_id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCdkey", "()Ljava/lang/String;", "getRole_id", "getServer_id", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CDKeyInfo {
    private final String cdkey;
    private final String role_id;
    private final String server_id;

    public static /* synthetic */ CDKeyInfo copy$default(CDKeyInfo cDKeyInfo, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = cDKeyInfo.cdkey;
        }
        if ((i2 & 2) != 0) {
            str2 = cDKeyInfo.role_id;
        }
        if ((i2 & 4) != 0) {
            str3 = cDKeyInfo.server_id;
        }
        return cDKeyInfo.copy(str, str2, str3);
    }

    public final String component1() {
        return this.cdkey;
    }

    public final String component2() {
        return this.role_id;
    }

    public final String component3() {
        return this.server_id;
    }

    public final CDKeyInfo copy(String str, String str2, String str3) {
        return new CDKeyInfo(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CDKeyInfo) {
            CDKeyInfo cDKeyInfo = (CDKeyInfo) obj;
            return Intrinsics.areEqual(this.cdkey, cDKeyInfo.cdkey) && Intrinsics.areEqual(this.role_id, cDKeyInfo.role_id) && Intrinsics.areEqual(this.server_id, cDKeyInfo.server_id);
        }
        return false;
    }

    public int hashCode() {
        String str = this.cdkey;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.role_id;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.server_id;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "CDKeyInfo(cdkey=" + this.cdkey + ", role_id=" + this.role_id + ", server_id=" + this.server_id + ')';
    }

    public CDKeyInfo(String str, String str2, String str3) {
        this.cdkey = str;
        this.role_id = str2;
        this.server_id = str3;
    }

    public /* synthetic */ CDKeyInfo(String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? "" : str3);
    }

    public final String getCdkey() {
        return this.cdkey;
    }

    public final String getRole_id() {
        return this.role_id;
    }

    public final String getServer_id() {
        return this.server_id;
    }
}
