package com.bytedance.ttgame.library.luffy.adapter;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RequestData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u0017\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007HÆ\u0003J5\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/adapter/RequestData;", "", "success", "", "message", "", "configs", "", "(ZLjava/lang/String;Ljava/util/Map;)V", "getConfigs", "()Ljava/util/Map;", "getMessage", "()Ljava/lang/String;", "getSuccess", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f14158a;

    /* renamed from: b  reason: collision with root package name */
    private final String f14159b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, Object> f14160c;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ e copy$default(e eVar, boolean z, String str, Map map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = eVar.f14158a;
        }
        if ((i2 & 2) != 0) {
            str = eVar.f14159b;
        }
        if ((i2 & 4) != 0) {
            map = eVar.f14160c;
        }
        return eVar.copy(z, str, map);
    }

    public final boolean component1() {
        return this.f14158a;
    }

    public final String component2() {
        return this.f14159b;
    }

    public final Map<String, Object> component3() {
        return this.f14160c;
    }

    public final e copy(boolean z, String message, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new e(z, message, map);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof e) {
                e eVar = (e) obj;
                return this.f14158a == eVar.f14158a && Intrinsics.areEqual(this.f14159b, eVar.f14159b) && Intrinsics.areEqual(this.f14160c, eVar.f14160c);
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.f14158a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i2 = r0 * 31;
        String str = this.f14159b;
        int hashCode = (i2 + (str != null ? str.hashCode() : 0)) * 31;
        Map<String, Object> map = this.f14160c;
        return hashCode + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "RequestData(success=" + this.f14158a + ", message=" + this.f14159b + ", configs=" + this.f14160c + ")";
    }

    public e(boolean z, String message, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.f14158a = z;
        this.f14159b = message;
        this.f14160c = map;
    }

    public final boolean getSuccess() {
        return this.f14158a;
    }

    public /* synthetic */ e(boolean z, String str, Map map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? "" : str, map);
    }

    public final String getMessage() {
        return this.f14159b;
    }

    public final Map<String, Object> getConfigs() {
        return this.f14160c;
    }
}
