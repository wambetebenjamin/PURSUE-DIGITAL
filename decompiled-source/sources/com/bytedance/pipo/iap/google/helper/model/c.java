package com.bytedance.pipo.iap.google.helper.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RestoreParams.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/bytedance/pipo/iap/google/helper/model/RestoreParams;", "", "forceQuery", "", "extraScene", "Lcom/bytedance/pipo/iap/enums/ExtraScene;", "(ZLcom/bytedance/pipo/iap/enums/ExtraScene;)V", "getExtraScene", "()Lcom/bytedance/pipo/iap/enums/ExtraScene;", "getForceQuery", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "iap_3rd_google_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f10771a;

    /* renamed from: b  reason: collision with root package name */
    private final com.bytedance.pipo.iap.enums.a f10772b;

    public static /* synthetic */ c a(c cVar, boolean z, com.bytedance.pipo.iap.enums.a aVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = cVar.f10771a;
        }
        if ((i2 & 2) != 0) {
            aVar = cVar.f10772b;
        }
        return cVar.a(z, aVar);
    }

    public final c a(boolean z, com.bytedance.pipo.iap.enums.a extraScene) {
        Intrinsics.checkNotNullParameter(extraScene, "extraScene");
        return new c(z, extraScene);
    }

    public final boolean c() {
        return this.f10771a;
    }

    public final com.bytedance.pipo.iap.enums.a d() {
        return this.f10772b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f10771a == cVar.f10771a && this.f10772b == cVar.f10772b;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.f10771a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (r0 * 31) + this.f10772b.hashCode();
    }

    public String toString() {
        return "RestoreParams(forceQuery=" + this.f10771a + ", extraScene=" + this.f10772b + ')';
    }

    public c(boolean z, com.bytedance.pipo.iap.enums.a extraScene) {
        Intrinsics.checkNotNullParameter(extraScene, "extraScene");
        this.f10771a = z;
        this.f10772b = extraScene;
    }

    public final boolean a() {
        return this.f10771a;
    }

    public final com.bytedance.pipo.iap.enums.a b() {
        return this.f10772b;
    }
}
