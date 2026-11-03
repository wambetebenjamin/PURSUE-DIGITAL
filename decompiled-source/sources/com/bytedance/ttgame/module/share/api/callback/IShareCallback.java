package com.bytedance.ttgame.module.share.api.callback;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.ttgame.base.GSDKError;
import kotlin.Metadata;
/* compiled from: IShareCallback.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/share/api/callback/IShareCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "onFailed", "", "error", "Lcom/bytedance/ttgame/base/GSDKError;", "onSuccess", "result", "(Ljava/lang/Object;)V", "share_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface IShareCallback<T> {
    void onFailed(GSDKError gSDKError);

    void onSuccess(T t);
}
