package com.bytedance.sdk.bridge.model;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BridgeTmpInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\b\"\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/bytedance/sdk/bridge/model/BridgeTmpInfo;", "", "subscriber", "isActive", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "(Ljava/lang/Object;ZLandroid/arch/lifecycle/Lifecycle;)V", "()Z", "setActive", "(Z)V", "getLifecycle", "()Landroid/arch/lifecycle/Lifecycle;", "getSubscriber", "()Ljava/lang/Object;", "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final Object f13258a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f13259b;

    /* renamed from: c  reason: collision with root package name */
    private final Lifecycle f13260c;

    public c(Object subscriber, boolean z, Lifecycle lifecycle) {
        Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
        this.f13258a = subscriber;
        this.f13259b = z;
        this.f13260c = lifecycle;
    }

    public /* synthetic */ c(Object obj, boolean z, Lifecycle lifecycle, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i2 & 2) != 0 ? true : z, (i2 & 4) != 0 ? null : lifecycle);
    }

    public final Lifecycle getLifecycle() {
        return this.f13260c;
    }

    public final Object getSubscriber() {
        return this.f13258a;
    }

    public final boolean isActive() {
        return this.f13259b;
    }

    public final void setActive(boolean z) {
        this.f13259b = z;
    }
}
