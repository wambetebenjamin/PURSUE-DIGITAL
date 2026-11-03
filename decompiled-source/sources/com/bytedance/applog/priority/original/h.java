package com.bytedance.applog.priority.original;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Common.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/bytedance/applog/priority/original/LocalLazy;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/Lazy;", "initializer", "Lcom/bytedance/applog/priority/original/ValueLoader;", "(Lcom/bytedance/applog/priority/original/ValueLoader;)V", "cached", "Ljava/lang/Object;", "getInitializer", "()Lcom/bytedance/applog/priority/original/ValueLoader;", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", "", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class h<T> implements Lazy<T> {

    /* renamed from: a  reason: collision with root package name */
    private final o<T> f3173a;

    /* renamed from: b  reason: collision with root package name */
    private T f3174b;

    public h(o<T> initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        this.f3173a = initializer;
    }

    public final o<T> a() {
        return this.f3173a;
    }

    @Override // kotlin.Lazy
    public T getValue() {
        if (this.f3174b == null) {
            this.f3174b = this.f3173a.load();
        }
        T t = this.f3174b;
        Intrinsics.checkNotNull(t);
        return t;
    }

    @Override // kotlin.Lazy
    public boolean isInitialized() {
        return this.f3174b != null;
    }
}
