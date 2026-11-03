package com.vk.api.sdk;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.sdk.account.monitor.a;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import kotlin.Metadata;
/* compiled from: VKApiCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/vk/api/sdk/VKApiCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", a.InterfaceC0193a.RESULT_FAIL, "", "error", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "success", "result", "(Ljava/lang/Object;)V", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public interface VKApiCallback<T> {
    void fail(VKApiExecutionException vKApiExecutionException);

    void success(T t);
}
