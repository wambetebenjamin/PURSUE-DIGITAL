package com.google.android.play.core.assetpacks.internal;

import java.util.Objects;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class aq implements as {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f16816a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private volatile as f16817b;

    /* renamed from: c  reason: collision with root package name */
    private volatile Object f16818c = f16816a;

    private aq(as asVar) {
        this.f16817b = asVar;
    }

    public static as b(as asVar) {
        Objects.requireNonNull(asVar);
        return asVar instanceof aq ? asVar : new aq(asVar);
    }

    public static aq c(as asVar) {
        return asVar instanceof aq ? (aq) asVar : new aq(asVar);
    }

    @Override // com.google.android.play.core.assetpacks.internal.as
    public final Object a() {
        Object obj = this.f16818c;
        Object obj2 = f16816a;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.f16818c;
                if (obj == obj2) {
                    obj = this.f16817b.a();
                    Object obj3 = this.f16818c;
                    if (obj3 != obj2 && obj3 != obj) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                    }
                    this.f16818c = obj;
                    this.f16817b = null;
                }
            }
        }
        return obj;
    }
}
