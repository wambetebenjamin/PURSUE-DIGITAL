package com.vk.api.sdk.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ThreadLocalDelegate.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/vk/api/sdk/utils/ThreadLocalDelegateImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/vk/api/sdk/utils/ThreadLocalDelegate;", "factory", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "getFactory", "()Lkotlin/jvm/functions/Function0;", "value", "Ljava/lang/ThreadLocal;", "get", "()Ljava/lang/Object;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class ThreadLocalDelegateImpl<T> implements ThreadLocalDelegate<T> {
    private final Function0<T> factory;
    private final ThreadLocal<T> value;

    /* JADX WARN: Multi-variable type inference failed */
    public ThreadLocalDelegateImpl(Function0<? extends T> factory) {
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        this.factory = factory;
        this.value = new ThreadLocal<T>() { // from class: com.vk.api.sdk.utils.ThreadLocalDelegateImpl$value$1
            @Override // java.lang.ThreadLocal
            protected T initialValue() {
                return ThreadLocalDelegateImpl.this.getFactory().invoke();
            }
        };
    }

    public final Function0<T> getFactory() {
        return this.factory;
    }

    @Override // com.vk.api.sdk.utils.ThreadLocalDelegate
    public T get() {
        return this.value.get();
    }
}
