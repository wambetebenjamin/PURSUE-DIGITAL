package com.bytedance.ttgame.module.cloudgame.api;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
import org.json.JSONObject;
/* loaded from: classes4.dex */
public interface ICloudGameService extends IModuleApi {
    void cloudGameLogin(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback);

    void cloudGameLoginV2(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback);

    void cloudGamePay(Activity activity, JSONObject jSONObject, IInnerPayListener iInnerPayListener);

    String getCloudGameClientType();

    void getCloudGameConfigInfo(ICloudGameConfigListener iCloudGameConfigListener);

    void init(Context context, String str, String str2);

    boolean isCloudRuntime();

    void sendCloudGameMessage(String str);

    void setCloudGameMessageListener(ICloudGameMessageListener iCloudGameMessageListener);
}
