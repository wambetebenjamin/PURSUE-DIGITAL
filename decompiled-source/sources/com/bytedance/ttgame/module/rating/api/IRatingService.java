package com.bytedance.ttgame.module.rating.api;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.rating.api.callback.ICustomRatingDialog;
import com.bytedance.ttgame.module.rating.api.configration.IRatingConfig;
/* loaded from: classes6.dex */
public interface IRatingService extends IModuleApi {
    boolean checkIfNeedTapCustomUI();

    @Deprecated
    void dismissRatingView();

    void setCustomUICallback(ICustomRatingDialog iCustomRatingDialog);

    @Deprecated
    void showRatingView(Activity activity, IRatingViewCallback iRatingViewCallback);

    void showRatingView(Activity activity, IRatingConfig iRatingConfig);

    void showTapTapReview();
}
