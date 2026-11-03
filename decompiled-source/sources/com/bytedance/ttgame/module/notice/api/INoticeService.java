package com.bytedance.ttgame.module.notice.api;

import android.content.Context;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.util.Map;
/* loaded from: classes6.dex */
public interface INoticeService extends IModuleApi {

    /* loaded from: classes6.dex */
    public interface FetchNoticesCallback {
        void onFail(GSDKError gSDKError);

        void onSuccess(ResponseData responseData);
    }

    void fetchNotices(Context context, Map<String, String> map, FetchNoticesCallback fetchNoticesCallback);
}
