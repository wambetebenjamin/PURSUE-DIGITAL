package com.ss.android.deviceregister;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.common.utility.Logger;
import com.ss.android.common.applog.NetUtil;
import com.ss.android.deviceregister.k;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class DeviceRegisterManager {
    private static final String TAG = "DeviceRegisterManager";
    private static t api = null;
    private static volatile int egdiRetryInterval = 0;
    private static volatile boolean enableGetEgdi = false;
    private static volatile boolean enableNetCommOpt = false;
    private static volatile boolean forceOpenNewUserMode = false;
    private static t mBdtrackerApi = null;
    private static t oldApi = null;
    private static volatile k sAdIdConfig = null;
    private static volatile com.bytedance.bdinstall.intf.a sAppTraitCallback = null;
    private static volatile String sAppVersionMinor = "";
    private static boolean sCheckPermissionBeforeCallSensitiveApi = false;
    private static Context sContext = null;
    private static volatile boolean sDeleteSharedStorage = true;
    private static volatile boolean sInitGuard = false;
    private static volatile DeviceRegisterManager sInstance = null;
    private static boolean sIsBoe = false;
    private static volatile boolean sIsTouristMode = false;
    private static volatile v sMacAddressApiCallback = null;
    private static volatile boolean sNeedSharedStorage = false;
    private static volatile boolean sOpenBpea = false;
    private static int sRetryCount = -1;
    private static volatile x sSensitiveApiCallback = null;
    private static volatile int sSwitchToBdtracker = -1;

    /* loaded from: classes9.dex */
    public interface a {
        void onDeviceRegistrationInfoChanged(String str, String str2);

        void onDidLoadLocally(boolean z);

        void onRemoteConfigUpdate(boolean z, boolean z2);
    }

    @Deprecated
    public static void setUseGoogleAdId(boolean z) {
    }

    public static boolean getSwitchToBdtracker() {
        if (sSwitchToBdtracker < 0) {
            com.ss.android.common.util.b.e("DeviceRegisterManager SwitchToBdtracker has not been set!");
        }
        return sSwitchToBdtracker > 0;
    }

    public static void setSwitchToBdtracker(boolean z) {
        sSwitchToBdtracker = z ? 1 : 0;
        if (z) {
            api = mBdtrackerApi;
        }
    }

    static {
        w wVar = new w();
        oldApi = wVar;
        api = wVar;
        mBdtrackerApi = new p();
        sIsBoe = false;
    }

    public static void setIsBoe(boolean z) {
        sIsBoe = z;
        p.a(z);
    }

    public static void setChildModeBeforeInit(boolean z) {
        api.g(z);
    }

    public static boolean isChildMode() {
        return api.v();
    }

    public static void setContext(Context context) {
        sContext = context.getApplicationContext();
    }

    public static boolean hasInit() {
        return sInitGuard;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Context context, boolean z, boolean z2) {
        if (context == null) {
            throw new IllegalArgumentException("context = null");
        }
        sInitGuard = true;
        if (context instanceof Activity) {
            setInitWithActivity(true);
        }
        sContext = context.getApplicationContext();
        if (sInstance == null) {
            synchronized (DeviceRegisterManager.class) {
                if (sInstance == null) {
                    sInstance = new DeviceRegisterManager(z, z2);
                    sInstance.onCreate(context);
                }
            }
        }
        if (Logger.debug()) {
            com.ss.android.common.util.b.d("DeviceRegister init, DeviceRegister : " + sInstance.toString() + ", process : " + Process.myPid());
        }
    }

    private void onCreate(Context context) {
        api.n(context);
    }

    public static void setInitWithActivity(boolean z) {
        api.b(z);
    }

    public static void setAppContext(com.ss.android.common.a aVar) {
        api.a(aVar);
        if (getSwitchToBdtracker()) {
            oldApi.a(aVar);
        }
        NetUtil.setAppContext(aVar);
    }

    public static void setAntiCheatingSwitch(boolean z) {
        api.c(z);
    }

    public static void setDeviceRegisterURL(String[] strArr, String[] strArr2) {
        api.a(strArr, strArr2);
    }

    public static void setAccount(Context context, Account account) {
        api.a(context, account);
    }

    public static String getSigHash(Context context) {
        return com.ss.android.deviceregister.base.e.getSigHash(context);
    }

    public static void addCustomerHeaser(Bundle bundle) {
        api.a(bundle);
    }

    public static void setAppId(int i2) {
        api.a(i2);
        if (getSwitchToBdtracker()) {
            oldApi.a(i2);
        }
    }

    public static void setChannel(String str) {
        api.a(str);
        if (getSwitchToBdtracker()) {
            oldApi.a(str);
        }
    }

    public static void setCustomMonitor(com.ss.android.deviceregister.base.c cVar) {
        api.a(cVar);
    }

    public static void setSDKVersion(String str) {
        api.f(str);
    }

    public static String getAppVersionMinor() {
        return sAppVersionMinor;
    }

    public static void setAppVersionMinor(String str) {
        sAppVersionMinor = str;
        api.c(str);
    }

    private DeviceRegisterManager(boolean z, boolean z2) {
        try {
            api.a(sContext, sIsBoe, z, z2);
        } catch (Throwable th) {
            Log.e("BDInstall", "error when init ", th);
            th.printStackTrace();
        }
    }

    public static void setPreInstallChannelCallback(o oVar) {
        api.a(oVar);
    }

    public static void setRetryCount(int i2) {
        sRetryCount = i2;
    }

    public static int getRetryCount() {
        return sRetryCount;
    }

    public static void setTouristMode(boolean z) {
        sIsTouristMode = z;
    }

    public static boolean isTouristMode() {
        return sIsTouristMode;
    }

    public static void setAppTraitCallback(com.bytedance.bdinstall.intf.a aVar) {
        sAppTraitCallback = aVar;
    }

    public static com.bytedance.bdinstall.intf.a getAppTraitCallback() {
        return sAppTraitCallback;
    }

    public static String getInstallId() {
        return api.f();
    }

    public static String getDeviceId() {
        return api.g();
    }

    public static String getOpenUdId() {
        return api.h();
    }

    public static String getClientUDID() {
        return api.i();
    }

    public static void onPause(Context context) {
        api.e(context);
    }

    public static void onResume(Context context) {
        api.d(context);
    }

    public static void saveAppTrack(Context context, JSONObject jSONObject) {
        api.a(context, jSONObject);
    }

    public static void addOnDeviceConfigUpdateListener(a aVar) {
        api.a(aVar);
    }

    public static void getSSIDs(Map<String, String> map) {
        api.a(map, sContext);
    }

    public static String getDeviceIdWithBackup() {
        return api.f(sContext);
    }

    public static String getOpenIdWithBackup() {
        return api.g(sContext);
    }

    public static String getClientUDIDWithBackup() {
        return api.h(sContext);
    }

    public static String getInstallIdWithBackup() {
        return api.i(sContext);
    }

    public static void setCustomVersion(String str) {
        api.b(str);
    }

    public static void setForceOpenNewUserMode(boolean z) {
        forceOpenNewUserMode = z;
    }

    public static boolean isForceOpenNewUserMode() {
        return forceOpenNewUserMode;
    }

    public static void enableEarlyRegisterNewUserModeService(Context context, String str) {
        try {
            if (TextUtils.equals(str, "local_test") || forceOpenNewUserMode) {
                synchronized (s.class) {
                    if (com.ss.android.deviceregister.service.b.a(s.class) == null) {
                        com.ss.android.deviceregister.service.b.a(s.class, (s) Class.forName(d.f18211a).getConstructor(Context.class).newInstance(context));
                        Log.d("AppLog# ", "enable early register NewUserModeManager to ServiceManager.");
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setNewUserMode(Context context, boolean z) {
        api.a(context, z);
    }

    public static boolean isNewUserMode(Context context) {
        return api.c(context);
    }

    public static void clearDidAndIid(Context context, String str) {
        api.a(context, str);
    }

    public static void setForbidReportPhoneDetailInfo(boolean z) {
        api.f(z);
    }

    public static void setAnonymous(boolean z) {
        api.e(z);
    }

    public static boolean isLocalTest() {
        return api.l();
    }

    public static void setLocalTest(boolean z) {
        api.d(z);
    }

    public static void activeUser(Context context, String str, String str2) {
        api.a(context, str, str2);
    }

    public static t getBdtrackerImpl() {
        return mBdtrackerApi;
    }

    public static void setFakePackage(String str) {
        api.d(str);
    }

    public static String getFakePackage() {
        return api.o();
    }

    public static boolean getHeader(Context context, JSONObject jSONObject, boolean z) {
        return api.a(context, jSONObject, z);
    }

    public static void setReleaseBuild(String str) {
        api.e(str);
        if (getSwitchToBdtracker()) {
            oldApi.e(str);
        }
    }

    public static void addCustomHeader(String str, Object obj) {
        api.a(str, obj);
    }

    public static String getCustomVersion() {
        return api.n();
    }

    public static int getAppId() {
        return api.q();
    }

    public static String getVersionName() {
        return api.r();
    }

    public static int getVersionCode() {
        return api.s();
    }

    public static String getUserAgent(Context context) {
        return api.j(context);
    }

    public static void updateUserAgentString(Context context, String str) {
        api.b(context, str);
    }

    public static void filterHeader(JSONObject jSONObject) {
        api.a(jSONObject);
    }

    public static String getChannel(Context context) {
        return api.a(context);
    }

    static String getCdid(Context context) {
        return api.k(context);
    }

    public static void setAppLanguage(String str) {
        api.g(str);
    }

    public static void setAppRegion(String str) {
        api.h(str);
    }

    public static void setDeviceCategory(q qVar) {
        api.a(qVar);
    }

    public static void setEnableMigrate(boolean z) {
        api.i(z);
    }

    public static void tryWaitDeviceIdInit() {
        api.b(sContext);
    }

    public static void setILogDepend(com.ss.android.deviceregister.base.d dVar) {
        api.a(dVar);
    }

    public static void updateDeviceInfo() {
        api.k();
    }

    public static String getRequestId() {
        return api.j();
    }

    public static boolean clearWhenSwitchChildMode(boolean z) {
        DeviceRegisterManager deviceRegisterManager = sInstance;
        if (!hasInit() || deviceRegisterManager == null) {
            api.g(z);
            return false;
        }
        return api.h(z);
    }

    public static void resetDidWhenSwitchChildMode(boolean z, long j2, n nVar) {
        DeviceRegisterManager deviceRegisterManager = sInstance;
        if (!hasInit() || deviceRegisterManager == null) {
            api.g(z);
        } else {
            api.a(z, j2, nVar);
        }
    }

    public static void updateDidAndIid() {
        DeviceRegisterManager deviceRegisterManager = sInstance;
        if (!hasInit() || deviceRegisterManager == null) {
            return;
        }
        api.w();
        com.ss.android.common.util.b.d("updateDidAndIid call  device_register");
    }

    public static Map<String, String> getRequestHeader() {
        return api.m(sContext);
    }

    public static boolean isNeedSharedStorage() {
        return sNeedSharedStorage;
    }

    public static boolean isDeleteSharedStorage() {
        return sDeleteSharedStorage;
    }

    public static void setSharedStorageConfig(boolean z, boolean z2) {
        sNeedSharedStorage = z;
        sDeleteSharedStorage = z2;
    }

    public static void setOpenBpea(boolean z) {
        sOpenBpea = z;
    }

    public static k getAdIdConfig() {
        if (sAdIdConfig == null) {
            sAdIdConfig = new k.a();
        }
        return sAdIdConfig;
    }

    public static x getBpeaApiCallback() {
        return sSensitiveApiCallback;
    }

    public static boolean isOpenBpe() {
        return sOpenBpea;
    }

    public static void setSensitiveApiCallback(x xVar) {
        sSensitiveApiCallback = xVar;
    }

    public static void setMacAddressApiCallback(v vVar) {
        sMacAddressApiCallback = vVar;
    }

    public static v getMacAddressApiCallback() {
        return sMacAddressApiCallback;
    }

    public static void setAdIdConfig(k kVar) {
        if (kVar == null) {
            return;
        }
        sAdIdConfig = kVar;
    }

    public static void checkPermissionBeforeCallSensitiveApi(boolean z) {
        sCheckPermissionBeforeCallSensitiveApi = z;
    }

    public static boolean checkPermissionBeforeCallSensitiveApi() {
        return sCheckPermissionBeforeCallSensitiveApi;
    }

    public static void setEnableNetCommOpt(boolean z) {
        enableNetCommOpt = z;
    }

    public static boolean isEnableNetCommOpt() {
        return enableNetCommOpt;
    }

    public static void setEnableGetEdgi(boolean z) {
        enableGetEgdi = z;
    }

    public static void setEdgiRetryInterval(int i2) {
        egdiRetryInterval = i2;
    }

    public static boolean isEnableGetEgdi() {
        return enableGetEgdi;
    }

    public static int getEgdiRetryInterval() {
        if (egdiRetryInterval > 0) {
            return egdiRetryInterval;
        }
        return 10000;
    }
}
