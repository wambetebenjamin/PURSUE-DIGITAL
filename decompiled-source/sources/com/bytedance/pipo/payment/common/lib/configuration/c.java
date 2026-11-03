package com.bytedance.pipo.payment.common.lib.configuration;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HostConfiguration.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/bytedance/pipo/payment/common/lib/configuration/HostConfiguration;", "", "isBoe", "", "boeHost", "", "region", "Lcom/bytedance/pipo/payment/common/lib/enums/Region;", "customizedOnlineHost", "(ZLjava/lang/String;Lcom/bytedance/pipo/payment/common/lib/enums/Region;Ljava/lang/String;)V", "getBoeHost", "()Ljava/lang/String;", "getCustomizedOnlineHost", "()Z", "getRegion", "()Lcom/bytedance/pipo/payment/common/lib/enums/Region;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "payment_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f10890a;

    /* renamed from: b  reason: collision with root package name */
    private final String f10891b;

    /* renamed from: c  reason: collision with root package name */
    private final com.bytedance.pipo.payment.common.lib.enums.a f10892c;

    /* renamed from: d  reason: collision with root package name */
    private final String f10893d;

    public static /* synthetic */ c a(c cVar, boolean z, String str, com.bytedance.pipo.payment.common.lib.enums.a aVar, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = cVar.f10890a;
        }
        if ((i2 & 2) != 0) {
            str = cVar.f10891b;
        }
        if ((i2 & 4) != 0) {
            aVar = cVar.f10892c;
        }
        if ((i2 & 8) != 0) {
            str2 = cVar.f10893d;
        }
        return cVar.a(z, str, aVar, str2);
    }

    public final c a(boolean z, String boeHost, com.bytedance.pipo.payment.common.lib.enums.a region, String str) {
        Intrinsics.checkNotNullParameter(boeHost, "boeHost");
        Intrinsics.checkNotNullParameter(region, "region");
        return new c(z, boeHost, region, str);
    }

    public final boolean e() {
        return this.f10890a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f10890a == cVar.f10890a && Intrinsics.areEqual(this.f10891b, cVar.f10891b) && this.f10892c == cVar.f10892c && Intrinsics.areEqual(this.f10893d, cVar.f10893d);
        }
        return false;
    }

    public final String f() {
        return this.f10891b;
    }

    public final com.bytedance.pipo.payment.common.lib.enums.a g() {
        return this.f10892c;
    }

    public final String h() {
        return this.f10893d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public int hashCode() {
        boolean z = this.f10890a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int hashCode = ((((r0 * 31) + this.f10891b.hashCode()) * 31) + this.f10892c.hashCode()) * 31;
        String str = this.f10893d;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "HostConfiguration(isBoe=" + this.f10890a + ", boeHost=" + this.f10891b + ", region=" + this.f10892c + ", customizedOnlineHost=" + ((Object) this.f10893d) + ')';
    }

    public c(boolean z, String boeHost, com.bytedance.pipo.payment.common.lib.enums.a region, String str) {
        Intrinsics.checkNotNullParameter(boeHost, "boeHost");
        Intrinsics.checkNotNullParameter(region, "region");
        this.f10890a = z;
        this.f10891b = boeHost;
        this.f10892c = region;
        this.f10893d = str;
    }

    public /* synthetic */ c(boolean z, String str, com.bytedance.pipo.payment.common.lib.enums.a aVar, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, str, aVar, (i2 & 8) != 0 ? null : str2);
    }

    public final boolean a() {
        return this.f10890a;
    }

    public final String b() {
        return this.f10891b;
    }

    public final com.bytedance.pipo.payment.common.lib.enums.a c() {
        return this.f10892c;
    }

    public final String d() {
        return this.f10893d;
    }
}
