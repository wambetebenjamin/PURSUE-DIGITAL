package com.bytedance.ttgame.sdk.module.account.platform.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.bytedance.sdk.account.platform.base.a;
import com.bytedance.sdk.account.platform.base.b;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.rocketapi.account.ExtraThirdInfo;
import com.bytedance.ttgame.rocketapi.account.internal.FriendsInfoResult;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public interface IThirdAuthorizeService extends IModuleApi {
    public static final String TAG = "gsdk_account_third_auth_service";

    /* loaded from: classes7.dex */
    public interface AuthType {
        public static final String AuthToken = "auth_token";
        public static final String PhoneInfo = "phone_info";
    }

    /* loaded from: classes7.dex */
    public interface DataKey {
        public static final String Carrier = "carrier";
        public static final String GRANTED_PERMISSION = "granted_permission";
        public static final String OptionalScopeNotSelected = "optional_scope_not_selected";
        public static final String OptionalScopeSelected = "optional_scope_selected";
        public static final String Scope = "scope";
    }

    /* loaded from: classes7.dex */
    public interface OperationType {
        public static final String Logout = "logout";
    }

    /* loaded from: classes7.dex */
    public interface Param {
        public static final String AuthType = "auth_type";
        public static final String Extra = "extra";
        public static final String OperationType = "operation_type";
        public static final String OptionalScopeNotSelected = "optional_scope_not_selected";
        public static final String OptionalScopeSelected = "optional_scope_selected";
        public static final String Scope = "scope";
    }

    /* loaded from: classes7.dex */
    public interface ThirdVariant {
        public static final String Douyin = "douyin";
        public static final String Facebook = "facebook";
        public static final String Google = "google";
        public static final String GooglePlay = "google_play";
        public static final String Line = "line";
        public static final String Meta = "meta";
        public static final String Onekey = "onekey";
        public static final String Pass = "pass";
        public static final String Phone = "phone";
        public static final String Pico = "pico";
        public static final String Taptap = "taptap";
        public static final String Tiktok = "tiktok";
        public static final String Toutiao = "toutiao";
        public static final String Twitter = "twitter";
        public static final String Vk = "vk";
    }

    default Map<String, Object> getAuthExtraParams() {
        return null;
    }

    default Bundle getData() {
        return null;
    }

    default Map<String, Object> getThirdExtraInfo() {
        return null;
    }

    default HashMap<String, Object> getUserInfo() {
        return null;
    }

    void init(Context context);

    default boolean isAppInstalledAndSupportAuthorization(Activity activity) {
        return false;
    }

    boolean isParamsConfigured();

    default boolean isPassportPlatform() {
        return true;
    }

    default boolean isSupportQuickLogin(Activity activity) {
        return false;
    }

    default void onDestroy() {
    }

    default a sendAuth(Activity activity, Map<String, Object> map, b bVar) {
        return null;
    }

    default void setAuthExtraParams(HashMap<String, Object> hashMap) {
    }

    default void getLoggedInUserFriends(boolean z, ICallback<FriendsInfoResult> iCallback) {
        iCallback.onFailed(new FriendsInfoResult(null, false, new GSDKError(-105999, "not support")));
    }

    default void sendCode(String str, ICallback<GSDKError> iCallback) {
        iCallback.onFailed(new GSDKError(-105999, ""));
    }

    default void getThirdExtraInfo(ICallback<ExtraThirdInfo> iCallback) {
        iCallback.onSuccess(new ExtraThirdInfo());
    }
}
