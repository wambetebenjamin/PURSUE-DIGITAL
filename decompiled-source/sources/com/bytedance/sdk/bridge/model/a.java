package com.bytedance.sdk.bridge.model;

import androidx.lifecycle.Lifecycle;
import com.bytedance.sdk.bridge.g;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BridgeInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\f\"\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/bytedance/sdk/bridge/model/BridgeInfo;", "", "subscriber", "birdgeMethodinfo", "Lcom/bytedance/sdk/bridge/BridgeMethodInfo;", "isActive", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "(Ljava/lang/Object;Lcom/bytedance/sdk/bridge/BridgeMethodInfo;ZLandroid/arch/lifecycle/Lifecycle;)V", "getBirdgeMethodinfo", "()Lcom/bytedance/sdk/bridge/BridgeMethodInfo;", "()Z", "setActive", "(Z)V", "getLifecycle", "()Landroid/arch/lifecycle/Lifecycle;", "getSubscriber", "()Ljava/lang/Object;", "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final Object f13248a;

    /* renamed from: b  reason: collision with root package name */
    private final g f13249b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f13250c;

    /* renamed from: d  reason: collision with root package name */
    private final Lifecycle f13251d;

    public a(Object subscriber, g birdgeMethodinfo, boolean z, Lifecycle lifecycle) {
        Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
        Intrinsics.checkParameterIsNotNull(birdgeMethodinfo, "birdgeMethodinfo");
        this.f13248a = subscriber;
        this.f13249b = birdgeMethodinfo;
        this.f13250c = z;
        this.f13251d = lifecycle;
    }

    public /* synthetic */ a(Object obj, g gVar, boolean z, Lifecycle lifecycle, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, gVar, (i2 & 4) != 0 ? true : z, (i2 & 8) != 0 ? null : lifecycle);
    }

    public final g getBirdgeMethodinfo() {
        return this.f13249b;
    }

    public final Lifecycle getLifecycle() {
        return this.f13251d;
    }

    public final Object getSubscriber() {
        return this.f13248a;
    }

    public final boolean isActive() {
        return this.f13250c;
    }

    public final void setActive(boolean z) {
        this.f13250c = z;
    }
}
