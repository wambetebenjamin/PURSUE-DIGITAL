package com.bytedance.ttgame.module.share.api.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
/* compiled from: FileUtils.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/module/share/api/utils/MediaCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "onFail", "", "onSuccess", "t", "(Ljava/lang/Object;)V", "share_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface MediaCallback<T> {
    void onFail();

    void onSuccess(T t);
}
