package com.bytedance.ttgame.main.internal;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import java.util.HashMap;
@InternalApi
/* loaded from: classes3.dex */
public interface IGameSdkConfigService extends IModuleApi {
    public static final int DOMESTIC_AID = 1781;
    public static final int DOMESTIC_SDK_APP_ID = 2292;
    public static final String LOGOUT_BROADCAST_ACTION = "com.bytedance.ttgame.module.account.LOGOUT";
    public static final String LOGOUT_BROADCAST_DATA_SDKOPENID = "gsdk_sdk_open_id";
    public static final int OVERSEA_AID = 1782;
    public static final int OVERSEA_SDK_APP_ID = 2293;

    String getAccessToken();

    String getAdid();

    int getAgeType();

    String getAppsFlyerUID();

    String getBoeHeader();

    long getFirstLoginShowTime();

    String getGMPatchVersion();

    String getGlobalSdkVersion();

    String getGoogleGiftId();

    String getLoginEventSeq();

    String getLoginId();

    String getLoginPage();

    String getLoginType();

    String getLoginWay();

    HashMap<String, Object> getPassInfoFromWebview();

    String getRoleId();

    String getRoleName();

    String getSdkVersion();

    String getServerId();

    long getStartSendLoginCallTime();

    String getStoreRegion();

    String getTtToken();

    int getUiFlag();

    String getUniqueId();

    String getUserCreateTime();

    boolean getsIsVerify();

    String getsUniqueIdLast();

    long getsUserId();

    void init(Context context);

    boolean isIsCreateVisitor();

    boolean isIsExitLogout();

    boolean isIsSwitchLogout();

    boolean isLogining();

    boolean isLogoff();

    void resetUserInfo();

    void setAccessToken(String str);

    void setAdid(String str);

    void setAgeType(int i2);

    void setAppsFlyerUID(String str);

    void setBoeHeader(String str);

    void setFirstLoginShowTime(long j2);

    void setGMPatchVersion(String str);

    void setGoogleGiftId(String str);

    void setIsCreateVisitor(boolean z);

    void setIsExitLogout(boolean z);

    void setIsLogining(boolean z);

    void setIsLogoff(boolean z);

    void setIsSwitchLogout(boolean z);

    void setLoginEventSeq();

    void setLoginId();

    void setLoginPage(String str);

    void setLoginType(String str);

    void setLoginWay(String str);

    void setPassInfoFromWebview(HashMap<String, Object> hashMap);

    void setRoleId(String str);

    void setRoleName(String str);

    void setServerId(String str);

    void setStartSendLoginCallTime(long j2);

    void setStoreRegion(String str);

    void setTtToken(String str);

    void setUiFlag(int i2);

    boolean setUniqueId(String str);

    void setUserCreateTime(String str);

    void setsIsVerify(boolean z);

    void setsUserId(long j2);
}
