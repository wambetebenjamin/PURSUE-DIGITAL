package com.bytedance.ttgame.rocketapi.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.rocketapi.account.internal.BindInfo;
import com.bytedance.ttgame.rocketapi.account.pojo.OSAccountData;
import com.bytedance.ttgame.rocketapi.account.pojo.SwitchData;
import com.bytedance.ttgame.rocketapi.callback.QuerySdkOpenIdCallback;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public interface IAccountService extends IModuleApi {
    void accountCodeLogin(Activity activity, String str, String str2, IAccountCallback<UserInfoResult> iAccountCallback);

    void accountLoginWithUserType(Activity activity, int i2, String str, String str2, IAccountCallback<UserInfoResult> iAccountCallback);

    void ageGate(Activity activity, IAgeGateCallback iAgeGateCallback);

    void authBindNoUI(Activity activity, int i2, IAccountCallback<UserInfoResult> iAccountCallback);

    void authLoginUsingQRCode(Activity activity, IQRCodeLoginCallback iQRCodeLoginCallback);

    void autoLoginNoUI(IAccountCallback<UserInfoResult> iAccountCallback);

    void autoLoginWithPoorNetwork(Activity activity, IAccountCallback<PeerNetworkUserInfoResult> iAccountCallback);

    void bindEmail(Activity activity, String str, String str2, String str3, IAccountCallback<UserInfoResult> iAccountCallback);

    @Deprecated
    int bindUser(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback);

    int bindUser(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback, IPanelCallback iPanelCallback);

    void cancelLogoff(IAccountCallback<GSDKError> iAccountCallback);

    void changeSuccessionCode(Activity activity, String str, IAccountCallback<SuccessionCodeResult> iAccountCallback);

    void cloudGameLogin(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback);

    void connectAccount(Activity activity, int i2, IAccountCallback<UserInfoResult> iAccountCallback);

    void createAccountCode(String str, String str2, ICallback<AccountCodeResult> iCallback);

    void createSuccessionCode(String str, IAccountCallback<SuccessionCodeResult> iAccountCallback);

    void createVisitor(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback);

    void deleteHistoryAccount(long j2, IAccountCallback<Boolean> iAccountCallback);

    BsdkUserInfoContext fetchBsdkUserInfoContext();

    void forceRebindNoUI(Activity activity, int i2, int i3, IAccountCallback<UserInfoResult> iAccountCallback);

    void forceRebindNoUI(Activity activity, BindInfo bindInfo, IAccountCallback<UserInfoResult> iAccountCallback);

    void generalAction(JSONObject jSONObject);

    void getAccountInfo(IAccountCallback<AccountInfo> iAccountCallback);

    void getEmailCode(Activity activity, String str, int i2, IAccountCallback<GSDKError> iAccountCallback);

    void getExtraThirdInfo(Activity activity, int i2, IAccountCallback<ExtraThirdInfo> iAccountCallback);

    void getHistoryAccount(IAccountCallback<List<ExtraData>> iAccountCallback);

    void getLatestUserInfo(Activity activity, ILoginInfoCallback iLoginInfoCallback);

    void getLatestUserInfoForCloudGame(String str, IAccountCallback<LatestUserInfoForCloudGame> iAccountCallback);

    LatestUserInfo getLatestUserInfoSync();

    void getSMSCode(Activity activity, String str, int i2, ICallback<GSDKError> iCallback);

    String getSdkOpenId(Context context);

    @InternalApi
    void init(Context context);

    void initAfterDidReady(Context context, String str);

    void initOnHomeActivity(Context context);

    void isAccountRegistered(int i2, String str, IAccountCallback<RegisterInfo> iAccountCallback);

    boolean isBsdkAccount();

    boolean isBsdkPay();

    boolean isCanAutoLoginNoUI();

    boolean isCloudRuntime();

    boolean isLogin();

    boolean isTiktokInstalledAndSupportAuthorization(Activity activity);

    void judgeAgeGate(IAccountCallback<AgeGateResult> iAccountCallback);

    void lastAccountLogin(IAccountCallback<UserInfoResult> iAccountCallback);

    @Deprecated
    void login(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback);

    void login(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback, IPanelCallback iPanelCallback);

    void loginNoUI(Activity activity, int i2, IAccountCallback<UserInfoResult> iAccountCallback);

    void loginNoUIWithSuccessionCode(Activity activity, SuccessionCodeInfo successionCodeInfo, IAccountCallback<UserInfoResult> iAccountCallback);

    void loginWithToken(Activity activity, int i2, String str, IAccountCallback<UserInfoResult> iAccountCallback);

    void logout(Context context, IAccountCallback<UserInfoResult> iAccountCallback);

    default void modifyAccountCode(String str, String str2, IAccountCallback<GSDKError> iAccountCallback) {
    }

    void modifyAccountPassword(Activity activity, String str, String str2, ICallback<AccountCodeResult> iCallback);

    void modifyAgeGateStatus(boolean z);

    void modifyCookieSwitchStatus(boolean z, ICallback<CookieResult> iCallback);

    void modifyPasswordWithUserType(int i2, String str, String str2, String str3, IAccountCallback<GSDKError> iAccountCallback);

    void onActivityResult(int i2, int i3, Intent intent);

    default void onNewIntent(Activity activity, Intent intent) {
    }

    void passCancelLogoff(IAccountCallback<GSDKError> iAccountCallback);

    void phoneBind(Activity activity, String str, String str2, IAccountCallback<UserInfoResult> iAccountCallback);

    void phoneLogin(Activity activity, String str, String str2, IAccountCallback<UserInfoResult> iAccountCallback);

    boolean queryAgeGateStatus();

    void queryAllOSAccountsList(IAccountCallback<OSAccountData> iAccountCallback);

    void queryCookieSwitchStatus(ICallback<CookieResult> iCallback);

    void querySdkOpenIdWithUserId(long j2, QuerySdkOpenIdCallback querySdkOpenIdCallback);

    void querySuccessionCode(IAccountCallback<SuccessionCodeResult> iAccountCallback);

    void queryVisitorStatus(IAccountCallback<VisitorStatusResult> iAccountCallback);

    void registerAccountWithUserType(Activity activity, int i2, String str, String str2, String str3, IAccountCallback<GSDKError> iAccountCallback);

    void registerGeneralCallback(ICallback<JSONObject> iCallback);

    void releaseGuest(ICallback<ReleaseResult> iCallback);

    @Deprecated
    void setupFacebookAutoEnabled(boolean z);

    void switchAccountNoUI(long j2, ISwitchCallback<UserInfoResult> iSwitchCallback);

    @Deprecated
    void switchLogin(Activity activity, ISwitchCallback<UserInfoResult> iSwitchCallback);

    void switchLogin(Activity activity, ISwitchCallback<UserInfoResult> iSwitchCallback, IPanelCallback iPanelCallback);

    void switchLogin(Activity activity, SwitchData switchData, ISwitchCallback<UserInfoResult> iSwitchCallback);

    void unBindNoUI(Activity activity, int i2, IAccountCallback<UserInfoResult> iAccountCallback);

    void unbindEmail(Activity activity, String str, String str2, IAccountCallback<UserInfoResult> iAccountCallback);

    void unbindPhone(Activity activity, String str, String str2, IAccountCallback<UserInfoResult> iAccountCallback);

    void updateLatestUserInfoSync(LatestUserInfo latestUserInfo);
}
