package com.bytedance.sdk.open.tiktok.api;

import android.content.Intent;
import com.bytedance.sdk.open.tiktok.authorize.model.Authorization;
import com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler;
import com.bytedance.sdk.open.tiktok.share.Share;
import com.bytedance.sdk.open.tiktok.share.ShareRequest;
/* loaded from: classes3.dex */
public interface TikTokOpenApi {
    boolean authorize(Authorization.Request request);

    String getSdkVersion();

    boolean handleIntent(Intent intent, IApiEventHandler iApiEventHandler);

    boolean isAppInstalled();

    boolean isAppSupportAuthorization();

    boolean isAppSupportShare();

    boolean isShareSupportFileProvider();

    boolean isSupportLiteAuthorize();

    boolean share(Share.Request request);

    boolean share(ShareRequest shareRequest);
}
