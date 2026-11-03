package com.vk.api.sdk.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* compiled from: ThreadLocalDelegate.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u001a4\u0010\u0005\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0080\u0002¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"threadLocal", "Lcom/vk/api/sdk/utils/ThreadLocalDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "factory", "Lkotlin/Function0;", "getValue", "any", "", "property", "Lkotlin/reflect/KProperty;", "(Lcom/vk/api/sdk/utils/ThreadLocalDelegate;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "libapi-sdk-core_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class ThreadLocalDelegateKt {
    public static final <T> ThreadLocalDelegate<T> threadLocal(Function0<? extends T> factory) {
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        return new ThreadLocalDelegateImpl(factory);
    }

    public static final <T> T getValue(ThreadLocalDelegate<T> receiver$0, Object obj, KProperty<?> property) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(property, "property");
        return receiver$0.get();
    }
}
