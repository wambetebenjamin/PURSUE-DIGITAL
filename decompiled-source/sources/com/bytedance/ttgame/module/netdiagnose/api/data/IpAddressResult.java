package com.bytedance.ttgame.module.netdiagnose.api.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: IpAddressResult.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0002J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R$\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\u0004¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/netdiagnose/api/data/IpAddressResult;", "", "ipv4Address", "", "(Ljava/lang/String;)V", "getIpv4Address", "()Ljava/lang/String;", "setIpv4Address", "value", "ipv6Address", "getIpv6Address", "setIpv6Address", "component1", "copy", "equals", "", "other", "hashCode", "", "processIpv6Address", "toString", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class IpAddressResult {
    private String ipv4Address;
    private String ipv6Address;

    public IpAddressResult() {
        this(null, 1, null);
    }

    public static /* synthetic */ IpAddressResult copy$default(IpAddressResult ipAddressResult, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ipAddressResult.ipv4Address;
        }
        return ipAddressResult.copy(str);
    }

    public final String component1() {
        return this.ipv4Address;
    }

    public final IpAddressResult copy(String ipv4Address) {
        Intrinsics.checkNotNullParameter(ipv4Address, "ipv4Address");
        return new IpAddressResult(ipv4Address);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IpAddressResult) && Intrinsics.areEqual(this.ipv4Address, ((IpAddressResult) obj).ipv4Address);
    }

    public int hashCode() {
        return this.ipv4Address.hashCode();
    }

    public String toString() {
        return "IpAddressResult(ipv4Address=" + this.ipv4Address + ')';
    }

    public IpAddressResult(String ipv4Address) {
        Intrinsics.checkNotNullParameter(ipv4Address, "ipv4Address");
        this.ipv4Address = ipv4Address;
        this.ipv6Address = "";
    }

    public /* synthetic */ IpAddressResult(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str);
    }

    public final String getIpv4Address() {
        return this.ipv4Address;
    }

    public final void setIpv4Address(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ipv4Address = str;
    }

    public final String getIpv6Address() {
        return this.ipv6Address;
    }

    public final void setIpv6Address(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.ipv6Address = processIpv6Address(value);
    }

    private final String processIpv6Address(String str) {
        String str2 = str;
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "%", false, 2, (Object) null)) {
            String substring = str.substring(0, StringsKt.lastIndexOf$default((CharSequence) str2, "%", 0, false, 6, (Object) null));
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return str;
    }
}
