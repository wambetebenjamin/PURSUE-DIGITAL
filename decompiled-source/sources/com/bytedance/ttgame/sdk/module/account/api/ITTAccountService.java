package com.bytedance.ttgame.sdk.module.account.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channelapi.pojo.SwitchInfo;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.account.toutiao.account.api.AccountManageResult;
import com.bytedance.ttgame.module.account.toutiao.account.api.ILoginStatusListener;
import com.bytedance.ttgame.module.account.toutiao.account.api.ReleaseGuestResult;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTAccountCodeResult;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTRealNameInfo;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTRealNameStatus;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTSuccessionCodeInfo;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTSuccessionCodeResult;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTUserInfo;
import com.bytedance.ttgame.module.database.api.UserInfoData;
import com.bytedance.ttgame.rocketapi.account.AccountInfo;
import com.bytedance.ttgame.rocketapi.account.BindInfoResult;
import com.bytedance.ttgame.rocketapi.account.ExtraThirdInfo;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.account.LatestUserInfoForCloudGame;
import com.bytedance.ttgame.rocketapi.account.RegisterInfo;
import com.bytedance.ttgame.rocketapi.account.VisitorStatusResult;
import com.bytedance.ttgame.rocketapi.account.internal.BindInfo;
import java.util.List;
/* loaded from: classes3.dex */
public interface ITTAccountService extends IModuleApi {
    default void accountCodeLogin(Activity activity, String str, String str2, IAccountCallback<TTUserInfoResult> iAccountCallback) {
    }

    default void accountLoginWithUserType(Activity activity, int i2, String str, String str2, ICallback<TTUserInfoResult> iCallback) {
    }

    void afterLogout(Context context);

    default void authBindWithoutUI(Activity activity, int i2, String str, ICallback<TTUserInfoResult> iCallback) {
    }

    default void autoLoginWithPoorNetwork(ICallback<TTUserInfoResult> iCallback) {
    }

    default void autoLoginWithoutUI(ICallback<TTUserInfoResult> iCallback) {
    }

    default void changeSuccessionCode(Activity activity, String str, ICallback<TTSuccessionCodeResult> iCallback) {
    }

    default void checkRealNameResult(ICallback<TTRealNameInfo> iCallback) {
    }

    default void clearLocalAccount() {
    }

    default void cloudGameLoginV4(Activity activity, ICallback<TTUserInfoResult> iCallback) {
    }

    default void connectAccount(Activity activity, int i2, ICallback<TTUserInfoResult> iCallback) {
    }

    default void createAccountCode(String str, String str2, ICallback<TTAccountCodeResult> iCallback) {
    }

    default void createSuccessionCode(String str, ICallback<TTSuccessionCodeResult> iCallback) {
    }

    default void createVisitor(Activity activity, ICallback<TTUserInfoResult> iCallback) {
    }

    default void deleteHistoryAccount(UserInfoData userInfoData, ICallback<Boolean> iCallback) {
    }

    default void forceRebindWithoutUI(Context context, BindInfo bindInfo, ICallback<TTUserInfoResult> iCallback) {
    }

    default void getAccountInfo(ICallback<AccountInfo> iCallback) {
    }

    default void getEmailCode(Activity activity, String str, int i2, ICallback<GSDKError> iCallback) {
    }

    default void getExtraThirdInfo(Activity activity, int i2, ICallback<ExtraThirdInfo> iCallback) {
    }

    default void getHistoryAccounts(ICallback<List<UserInfoData>> iCallback) {
    }

    default void getLatestUserInfoForCloudGame(String str, ICallback<LatestUserInfoForCloudGame> iCallback) {
    }

    default void getSMSCode(Activity activity, String str, int i2, ICallback<GSDKError> iCallback) {
    }

    TTUserInfo getUserInfo();

    UserInfoData getUserInfoData();

    void init(TTAccountConfig tTAccountConfig);

    default void initOnHomeActivity(Activity activity) {
    }

    default void isAccountRegistered(int i2, String str, ICallback<RegisterInfo> iCallback) {
    }

    boolean isAutoLogin();

    boolean isFirstLogin();

    boolean isLogin();

    default boolean isTiktokInstalledAndSupportAuthorization(Activity activity) {
        return false;
    }

    default void isVerify(ICallback<TTRealNameStatus> iCallback) {
    }

    default void lastAccountLogin(ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginNoUIWithSuccessionCode(Activity activity, TTSuccessionCodeInfo tTSuccessionCodeInfo, ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginPhoneWithCode(Activity activity, String str, String str2, ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginPhoneWithPassword(Activity activity, String str, String str2, ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginVistor(Activity activity, ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginWithAuthCode(Activity activity, int i2, String str, ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginWithCloudGameAwemeToken(Activity activity, boolean z, String str, String str2, ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginWithCloudGameMobileToken(Activity activity, String str, ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginWithToken(Activity activity, int i2, String str, ICallback<TTUserInfoResult> iCallback) {
    }

    default void loginWithoutUI(Activity activity, int i2, ICallback<TTUserInfoResult> iCallback) {
    }

    default void logoutAndSetTokenExpired(ICallback<BaseResponse> iCallback) {
    }

    default void modifyAccountCode(String str, String str2, IAccountCallback<GSDKError> iAccountCallback) {
    }

    default void modifyAccountPassword(Activity activity, String str, String str2, ICallback<TTAccountCodeResult> iCallback) {
    }

    default void modifyPasswordWithUserType(int i2, String str, String str2, String str3, ICallback<GSDKError> iCallback) {
    }

    default void onActivityResult(int i2, int i3, Intent intent) {
    }

    default void openBindMobilePanel(Activity activity, ICallback<AccountManageResult> iCallback) {
    }

    void openLoginPanel(Activity activity, ICallback<TTUserInfoResult> iCallback);

    default void openSwitchAccountPanel(Context context, ICallback<TTUserInfoResult> iCallback) {
    }

    int openUserCenter(Context context, ICallback<TTUserInfoResult> iCallback);

    default void openUserCenterWithOperateType(Context context, ICallback<TTUserInfoResult> iCallback) {
    }

    void openVisitorBindPanel(Context context, ICallback<TTUserInfoResult> iCallback);

    default void passCancelLogoff(IAccountCallback<GSDKError> iAccountCallback) {
    }

    default void phoneLogin(Activity activity, String str, String str2, IAccountCallback<TTUserInfoResult> iAccountCallback) {
    }

    default void queryBindInfo(ICallback<BindInfoResult> iCallback) {
    }

    default void querySuccessionCode(ICallback<TTSuccessionCodeResult> iCallback) {
    }

    default void queryVisitorStatus(ICallback<VisitorStatusResult> iCallback) {
    }

    default void registerAccountWithUserType(Activity activity, int i2, String str, String str2, String str3, ICallback<GSDKError> iCallback) {
    }

    void registerLoginCallback(ILoginStatusListener iLoginStatusListener);

    void releaseGuest(ICallback<ReleaseGuestResult> iCallback);

    default void scanQRCodeLogin(Context context, String str, ICallback<GSDKError> iCallback) {
    }

    @Deprecated
    default void setupFacebookAutoEnabled(boolean z) {
    }

    default void switchAccountWithoutUI(UserInfoData userInfoData, ICallback<TTUserInfoResult> iCallback) {
    }

    default void switchLogin(Activity activity, SwitchInfo switchInfo, ICallback<TTUserInfoResult> iCallback) {
    }

    default void unBindPhoneWithoutUI(Activity activity, String str, String str2, ICallback<TTUserInfoResult> iCallback) {
    }

    default void unBindWithoutUI(Context context, int i2, ICallback<TTUserInfoResult> iCallback) {
    }

    default void unbindEmail(Activity activity, String str, String str2, ICallback<TTUserInfoResult> iCallback) {
    }

    default void visitorBindPhoneSuccess(Activity activity, ICallback<TTUserInfoResult> iCallback) {
    }
}
