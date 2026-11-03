package com.bytedance.ttgame.sdk.module.core.internal;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.util.FlavorUtilKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import timber.log.Timber;
/* loaded from: classes8.dex */
public class RocketConstants {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ADID = "adid";
    public static final String ADJUST_ID = "adjust_id";
    public static final String AID = "aid";
    public static final int AMERICA_HOST_TAG = 20;
    public static String API_BSDK_HOST = null;
    public static final String APP_VERSION_MINOR = "app_version_minor";
    public static final String BAN_ODIN = "ban_odin";
    public static String BSDK_HOST = "bsdk_host";
    public static String BSDK_HOST_VA = "bsdk_host_va";
    public static List<String> BSDK_SERVER_HOST_URL_TOKEN_LIST = new ArrayList();
    public static String BSDK_SERVER_URL = null;
    public static String BYTE_TOKEN_HOST_VA = "byte_token_host_va";
    public static final String CHANNEL_ID = "bsdkintl";
    public static final String CHANNEL_OP = "channel_op";
    public static final int CHINA_HOST_TAG = 0;
    public static String COMMON_TOKEN_HOST = "common_token_host";
    public static final String EMULATOR_TYPE = "emulator_type";
    public static final String GSDK_ENVIRONMENT = "environment";
    public static String GSDK_HOST = "gsdk_host";
    public static String GSDK_HOST_SANDBOX = "gsdk_host_sandbox";
    public static String GSDK_HOST_VA = "gsdk_host_va";
    public static String GSDK_HOST_VA_SANDBOX = "gsdk_host_va_sandbox";
    public static String GSDK_SERVER_URL = null;
    public static final String GSDK_VERSION = "gsdk_version";
    public static final String IS_GUEST_ENTRANCE = "is_guest_entrance";
    public static final String IS_PAID = "is_paid";
    public static final String KEY_EVENT_SEND_HOST = "event_send_host";
    public static final String PARAMS_FOR_SPECIAL = "params_for_special";
    public static final String PATCH_VERSION = "gm_patch_version";
    public static final String REAL_PACKAGE_NAME = "real_package_name";
    public static final String SDK_APP_ID = "sdk_app_id";
    public static final String SDK_VERSION = "sdk_version";
    public static final int SIG_HOST_TAG = 10;
    private static final String TAG = "gsdk_host";
    public static final String UNIQUE_ID = "unique_id";
    public static final String UNIQUE_ID_LAST = "unique_id_last";
    private static HashMap<String, Object> URLS = null;
    public static final String USER_IS_LOGIN = "user_is_login";
    public static final String XUANWU = "xuanwu";
    public static final String XUANWU_OVERSEAS = "xuanwu_overseas";
    public static SdkConfig sdkConfig;
    private static HashMap<String, Object> sdkURLS;

    public static void init(SdkConfig sdkConfig2, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        sdkConfig = sdkConfig2;
        URLS = hashMap;
        sdkURLS = hashMap2;
        if (sdkConfig2.serverRegion == 20) {
            BSDK_SERVER_URL = getUrl(getURL(BSDK_HOST_VA));
            GSDK_SERVER_URL = getUrl(getGSDKHost(sdkConfig));
            API_BSDK_HOST = getURL(BSDK_HOST_VA);
            BSDK_SERVER_HOST_URL_TOKEN_LIST = getListURL(BYTE_TOKEN_HOST_VA);
        } else {
            BSDK_SERVER_URL = getUrl(getURL(BSDK_HOST));
            GSDK_SERVER_URL = getUrl(getGSDKHost(sdkConfig));
            API_BSDK_HOST = getURL(BSDK_HOST);
            BSDK_SERVER_HOST_URL_TOKEN_LIST = getListURL(COMMON_TOKEN_HOST);
        }
        Timber.tag(TAG).d("bsdk:" + BSDK_SERVER_URL, new Object[0]);
        Timber.tag(TAG).d("gsdk:" + GSDK_SERVER_URL, new Object[0]);
    }

    private static String getURL(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        HashMap<String, Object> hashMap = sdkURLS;
        if (hashMap != null && hashMap.containsKey(str)) {
            return (String) sdkURLS.get(str);
        }
        return (String) URLS.get(str);
    }

    private static ArrayList getListURL(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        HashMap<String, Object> hashMap = sdkURLS;
        if (hashMap != null && hashMap.containsKey(str)) {
            Object obj = sdkURLS.get(str);
            return (obj == null || !(obj instanceof ArrayList)) ? arrayList : (ArrayList) obj;
        }
        Object obj2 = URLS.get(str);
        return (obj2 == null || !(obj2 instanceof ArrayList)) ? arrayList : (ArrayList) obj2;
    }

    private static String getGSDKHost(SdkConfig sdkConfig2) {
        if (FlavorUtilKt.isCnFlavor()) {
            if (sdkConfig2.mIsSandBox) {
                return getURL(GSDK_HOST_SANDBOX);
            }
            return getURL(GSDK_HOST);
        } else if (sdkConfig2.mIsSandBox) {
            if (sdkConfig2.serverRegion == 20) {
                return getURL(GSDK_HOST_VA_SANDBOX);
            }
            return getURL(GSDK_HOST_SANDBOX);
        } else if (sdkConfig2.serverRegion == 20) {
            return getURL(GSDK_HOST_VA);
        } else {
            return getURL(GSDK_HOST);
        }
    }

    public static List<String> getTokenHosts() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(BSDK_SERVER_HOST_URL_TOKEN_LIST);
        return arrayList;
    }

    public static String getHostToPassport() {
        return API_BSDK_HOST;
    }

    private static String getUrl(String str) {
        return "https://" + str + c.a.SEPARATOR;
    }
}
