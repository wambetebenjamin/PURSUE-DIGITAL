package com.bytedance.ttgame.module.share.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.module.share.api.callback.IShareCallback;
import com.bytedance.ttgame.module.share.api.callback.OnScreenshotShareListener;
import com.bytedance.ttgame.module.share.api.depend.ITTShareDownloadConfig;
import com.bytedance.ttgame.module.share.api.entity.AweShareVideoInfo;
import com.bytedance.ttgame.module.share.api.entity.TTShareData;
import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
import com.bytedance.ttgame.module.share.api.panel.TTPanelContent;
import com.bytedance.ttgame.module.share.api.utils.MediaCallback;
/* loaded from: classes7.dex */
public interface IShareService extends IModuleApi {
    @InternalApi
    String getDouyinKey();

    @InternalApi
    ITTShareDownloadConfig getDownloadConfig();

    @InternalApi
    Activity getShareActivity();

    void handleShareResultOnActivityResult(int i2, int i3, Intent intent);

    @InternalApi
    boolean isToutiaoChannel();

    void onRequestPermissionsResult(Activity activity, int i2, String[] strArr, int[] iArr);

    void pollRequestAweShareVideoInfo(String str, int i2, int i3, IShareCallback<AweShareVideoInfo> iShareCallback);

    void requestAweShareId(String str, IShareCallback<String> iShareCallback);

    void requestAweShareVideoInfo(String str, IShareCallback<AweShareVideoInfo> iShareCallback);

    @InternalApi
    void saveVideo(Context context, String str, MediaCallback<String> mediaCallback);

    void sdkInit(Application application);

    void setScreenshotShare(boolean z, boolean z2, OnScreenshotShareListener onScreenshotShareListener);

    @InternalApi
    void setWebShareBridgeResult(int i2, Bundle bundle);

    @Deprecated
    void share(Activity activity, TTShareModel tTShareModel);

    boolean shareBehindEnable();

    void shareContent(TTShareData tTShareData, boolean z);

    @Deprecated
    void shareContentBehind(Activity activity, TTShareModel tTShareModel, boolean z);

    void shareContentSilent(TTShareData tTShareData, boolean z);

    @Deprecated
    void showPanel(Activity activity, TTPanelContent tTPanelContent);
}
