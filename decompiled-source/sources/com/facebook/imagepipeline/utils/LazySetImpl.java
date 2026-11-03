package com.facebook.imagepipeline.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LazySet.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/facebook/imagepipeline/utils/LazySetImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/facebook/imagepipeline/utils/LazySet;", "initializer", "Lkotlin/Function0;", "lock", "", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "_value", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "isInitialized", "", "toString", "", "imagepipeline_noasanRelease"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class LazySetImpl<T> implements LazySet<T> {
    private volatile Object _value;
    private Function0<? extends T> initializer;
    private final Object lock;

    public LazySetImpl(Function0<? extends T> initializer, Object obj) {
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        this.initializer = initializer;
        this._value = UNINITIALIZED_VALUE.INSTANCE;
        this.lock = obj == null ? this : obj;
    }

    public /* synthetic */ LazySetImpl(Function0 function0, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i2 & 2) != 0 ? null : obj);
    }

    @Override // com.facebook.imagepipeline.utils.LazySet
    public T getValue() {
        T t;
        T t2 = (T) this._value;
        if (t2 != UNINITIALIZED_VALUE.INSTANCE) {
            return t2;
        }
        synchronized (this.lock) {
            t = (T) this._value;
            if (t == UNINITIALIZED_VALUE.INSTANCE) {
                Function0<? extends T> function0 = this.initializer;
                if (function0 == null) {
                    Intrinsics.throwNpe();
                }
                t = function0.invoke();
                this._value = t;
                Function0 function02 = null;
                this.initializer = null;
            }
        }
        return t;
    }

    @Override // com.facebook.imagepipeline.utils.LazySet
    public void setValue(T t) {
        if (Intrinsics.areEqual(this._value, UNINITIALIZED_VALUE.INSTANCE)) {
            synchronized (this.lock) {
                this._value = t;
                Function0 function0 = null;
                this.initializer = null;
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        this._value = t;
    }

    @Override // com.facebook.imagepipeline.utils.LazySet
    public boolean isInitialized() {
        return this._value != UNINITIALIZED_VALUE.INSTANCE;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
