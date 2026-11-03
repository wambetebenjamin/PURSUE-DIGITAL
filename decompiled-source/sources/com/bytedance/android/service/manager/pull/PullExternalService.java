package com.bytedance.android.service.manager.pull;

import android.content.Context;
import com.bytedance.android.push.service.manager.annotation.a;
@a
/* loaded from: classes.dex */
public interface PullExternalService {
    boolean enableSdkPull(Context context);

    void initOnApplication();

    boolean isNeedRequestOldComposeApi(int i2);

    boolean isUseNewAllianceLocalPushApi();

    boolean isUseNewAllianceRedBadgeApi();

    boolean isUseNewApi();

    boolean isUseNewApi(int i2);

    boolean isUseNewOnlineLocalPushApi();

    boolean isUseNewOnlineRedBadgeApi();

    boolean isUseV2Pull();

    void requestAndShowContent(String str);

    void start(PullConfiguration pullConfiguration);
}
