package com.vk.api.sdk.utils;

import android.util.MalformedJsonException;
import androidx.collection.LongSparseArray;
import androidx.exifinterface.media.ExifInterface;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.internal.VKErrorUtils;
import gsdk.impl.account.toutiao.dh;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ApiExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aI\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00022\u001d\u0010\u0005\u001a\u0019\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0080\b¢\u0006\u0002\u0010\t\u001a\u0016\u0010\n\u001a\u00020\u000b*\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0000\u001a\f\u0010\u000f\u001a\u00020\u000b*\u00020\fH\u0000\u001a\f\u0010\u0010\u001a\u00020\u000b*\u0004\u0018\u00010\u0011\u001a\f\u0010\u0012\u001a\u00020\u000b*\u0004\u0018\u00010\u0011\u001a.\u0010\u0013\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0014*\b\u0012\u0004\u0012\u0002H\u00140\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u0002H\u0014H\u0086\u0002¢\u0006\u0002\u0010\u0018\u001a\u001e\u0010\u0019\u001a\u00020\u001a*\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0000\u001a\u0018\u0010\u001c\u001a\u00020\u001a*\u00020\f2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\fH\u0000¨\u0006\u001d"}, d2 = {"applyPos", ExifInterface.GPS_DIRECTION_TRUE, "N", "", "value", "block", "Lkotlin/Function2;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/lang/Number;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "hasExecuteError", "", "", "ignoredErrors", "", "hasSimpleError", "isInterruptedByThreadInterrupt", "Ljava/io/IOException;", "isMalformedJson", dh.z, ExifInterface.LONGITUDE_EAST, "Landroidx/collection/LongSparseArray;", "key", "", "(Landroid/support/v4/util/LongSparseArray;JLjava/lang/Object;)V", "toExecuteError", "Lcom/vk/api/sdk/exceptions/VKApiException;", "method", "toSimpleError", "libapi-sdk-core_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class ApiExtKt {
    public static final <T, N extends Number> T applyPos(T t, N value, Function2<? super T, ? super N, Unit> block) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(block, "block");
        if (value.doubleValue() > 0) {
            block.invoke(t, value);
            return t;
        }
        throw new IllegalArgumentException("Value is negative " + value + '!');
    }

    public static final boolean isInterruptedByThreadInterrupt(IOException iOException) {
        return (iOException == null || !(iOException instanceof InterruptedIOException) || (iOException instanceof SocketTimeoutException)) ? false : true;
    }

    public static final boolean isMalformedJson(IOException iOException) {
        if (iOException == null) {
            return false;
        }
        return iOException instanceof MalformedJsonException;
    }

    public static final boolean hasExecuteError(String receiver$0, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return VKErrorUtils.INSTANCE.hasExecuteError(receiver$0, iArr);
    }

    public static final boolean hasSimpleError(String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return VKErrorUtils.INSTANCE.hasSimpleError(receiver$0);
    }

    public static final VKApiException toSimpleError(String receiver$0, String str) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return VKErrorUtils.INSTANCE.parseSimpleError(receiver$0, str);
    }

    public static /* synthetic */ VKApiException toSimpleError$default(String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str2 = null;
        }
        return toSimpleError(str, str2);
    }

    public static final VKApiException toExecuteError(String receiver$0, String method, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(method, "method");
        return VKErrorUtils.INSTANCE.parseExecuteError(receiver$0, method, iArr);
    }

    public static final <E> void set(LongSparseArray<E> receiver$0, long j2, E e2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.put(j2, e2);
    }
}
