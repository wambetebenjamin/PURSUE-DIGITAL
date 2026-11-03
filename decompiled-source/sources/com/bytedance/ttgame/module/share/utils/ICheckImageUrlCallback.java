package com.bytedance.ttgame.module.share.utils;

import com.bytedance.ttgame.module.share.api.entity.TTShareResult;
/* loaded from: classes7.dex */
public interface ICheckImageUrlCallback {
    void onFail(TTShareResult tTShareResult);

    void onSuccess();
}
