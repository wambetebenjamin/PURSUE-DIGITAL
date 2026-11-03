package com.bytedance.ug.sdk.share.api.callback;

import com.bytedance.ug.sdk.share.impl.network.model.ShareInfo;
import java.util.List;
/* compiled from: ShareInfoCallback.java */
/* loaded from: classes8.dex */
public interface m {
    void onFailed();

    void onSuccess(List<ShareInfo> list);
}
