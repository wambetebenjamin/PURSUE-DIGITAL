package com.bytedance.ttgame.sdk.module.account.platform.api;

import android.app.Activity;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.sdk.module.account.platform.pojo.SessionIdResult;
import kotlin.Metadata;
/* compiled from: IThirdSeamlessService.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H&J\"\u0010\t\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007H&¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/platform/api/IThirdSeamlessService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getSessionId", "", "activity", "Landroid/app/Activity;", "iCallback", "Lcom/bytedance/ttgame/framework/module/callback/ICallback;", "Lcom/bytedance/ttgame/sdk/module/account/platform/pojo/SessionIdResult;", "isAuthenticated", "Lcom/bytedance/ttgame/base/GSDKError;", "account_api_platform_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface IThirdSeamlessService extends IModuleApi {
    void getSessionId(Activity activity, ICallback<SessionIdResult> iCallback);

    void isAuthenticated(Activity activity, ICallback<GSDKError> iCallback);
}
