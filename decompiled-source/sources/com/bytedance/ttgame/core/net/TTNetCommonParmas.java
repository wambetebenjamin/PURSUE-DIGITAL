package com.bytedance.ttgame.core.net;

import com.bytedance.ttgame.core.applog.ApplogUtils;
import com.bytedance.ttgame.module.account.api.Constant;
import com.bytedance.ttgame.sdk.module.core.internal.RocketConstants;
import com.ss.android.pushmanager.e;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class TTNetCommonParmas {
    private static ArrayList<String> commonParmas_LEVEL1;
    private static ArrayList<String> commonParmas_LEVEL2;
    private static ArrayList<String> commonParmas_LEVEL3;
    private static ArrayList<String> commonParmas_LEVEL4;
    private static ArrayList<String> commonParmas_LEVEL5;
    private static ArrayList<String> customParmas_LEVEL1;
    private static ArrayList<String> customParmas_LEVEL2;
    private static ArrayList<String> customParmas_LEVEL3;
    private static ArrayList<String> customParmas_LEVEL4;
    private static ArrayList<String> customParmas_LEVEL5;
    private static ArrayList<String> intl_Parmas_LEVEL1;
    private static ArrayList<String> intl_Parmas_LEVEL2;
    private static ArrayList<String> intl_Parmas_LEVEL3;
    private static ArrayList<String> intl_Parmas_LEVEL4;
    private static ArrayList<String> intl_Parmas_LEVEL5;

    public static void init() {
        ArrayList<String> arrayList = commonParmas_LEVEL1;
        if (arrayList == null) {
            commonParmas_LEVEL1 = new ArrayList<>();
            customParmas_LEVEL1 = new ArrayList<>();
            intl_Parmas_LEVEL1 = new ArrayList<>();
        } else {
            arrayList.clear();
            customParmas_LEVEL1.clear();
            intl_Parmas_LEVEL1.clear();
        }
        ArrayList<String> arrayList2 = commonParmas_LEVEL2;
        if (arrayList2 == null) {
            commonParmas_LEVEL2 = new ArrayList<>();
            customParmas_LEVEL2 = new ArrayList<>();
            intl_Parmas_LEVEL2 = new ArrayList<>();
        } else {
            arrayList2.clear();
            customParmas_LEVEL2.clear();
            intl_Parmas_LEVEL2.clear();
        }
        ArrayList<String> arrayList3 = commonParmas_LEVEL3;
        if (arrayList3 == null) {
            commonParmas_LEVEL3 = new ArrayList<>();
            customParmas_LEVEL3 = new ArrayList<>();
            intl_Parmas_LEVEL3 = new ArrayList<>();
        } else {
            arrayList3.clear();
            customParmas_LEVEL3.clear();
            intl_Parmas_LEVEL3.clear();
        }
        ArrayList<String> arrayList4 = commonParmas_LEVEL4;
        if (arrayList4 == null) {
            commonParmas_LEVEL4 = new ArrayList<>();
            customParmas_LEVEL4 = new ArrayList<>();
            intl_Parmas_LEVEL4 = new ArrayList<>();
        } else {
            arrayList4.clear();
            customParmas_LEVEL4.clear();
            intl_Parmas_LEVEL4.clear();
        }
        ArrayList<String> arrayList5 = commonParmas_LEVEL5;
        if (arrayList5 == null) {
            commonParmas_LEVEL5 = new ArrayList<>();
            customParmas_LEVEL5 = new ArrayList<>();
            intl_Parmas_LEVEL5 = new ArrayList<>();
        } else {
            arrayList5.clear();
            customParmas_LEVEL5.clear();
            intl_Parmas_LEVEL5.clear();
        }
        commonParmas_LEVEL1.add("openudid");
        commonParmas_LEVEL1.add("device_name");
        commonParmas_LEVEL2.add(RocketConstants.ADID);
        commonParmas_LEVEL2.add(e.f18492a);
        commonParmas_LEVEL2.add(RocketConstants.ADJUST_ID);
        commonParmas_LEVEL2.add(ApplogUtils.APPSFLYER_ID);
        customParmas_LEVEL2.add("google_aid");
        customParmas_LEVEL2.add(RocketConstants.ADJUST_ID);
        customParmas_LEVEL2.add(ApplogUtils.APPSFLYER_ID);
        commonParmas_LEVEL3.add("channel");
        commonParmas_LEVEL3.add(RocketConstants.CHANNEL_OP);
        commonParmas_LEVEL3.add("iid");
        commonParmas_LEVEL3.add("device_id");
        commonParmas_LEVEL3.add("access_token");
        commonParmas_LEVEL3.add(com.bytedance.bdinstall.e.F);
        intl_Parmas_LEVEL3.add("carrier_region");
        intl_Parmas_LEVEL3.add("gps_country");
        intl_Parmas_LEVEL3.add("gps_city");
        customParmas_LEVEL3.add(RocketConstants.UNIQUE_ID);
        customParmas_LEVEL3.add("country");
        customParmas_LEVEL3.add(ApplogUtils.PROVINCE);
        customParmas_LEVEL3.add(ApplogUtils.CITY);
        customParmas_LEVEL3.add(RocketConstants.CHANNEL_OP);
        customParmas_LEVEL3.add("open_udid");
        customParmas_LEVEL3.add("userid_b");
        customParmas_LEVEL3.add(ApplogUtils.CLIENT_IP);
        customParmas_LEVEL3.add(ApplogUtils.COUNTRY_NEW);
        customParmas_LEVEL3.add(ApplogUtils.CITY_NEW);
        customParmas_LEVEL3.add(ApplogUtils.PROVINCE_NEW);
        customParmas_LEVEL3.add("player_device_id");
        commonParmas_LEVEL4.add("os_version");
        commonParmas_LEVEL4.add(com.bytedance.bdinstall.e.aD);
        commonParmas_LEVEL4.add("os");
        commonParmas_LEVEL4.add("device_platform");
        commonParmas_LEVEL4.add("sdk_version");
        commonParmas_LEVEL4.add("version_code");
        commonParmas_LEVEL4.add("app_version");
        commonParmas_LEVEL4.add(com.bytedance.bdinstall.e.z);
        commonParmas_LEVEL4.add("sdk_language");
        commonParmas_LEVEL4.add("app_name");
        commonParmas_LEVEL4.add("aid");
        commonParmas_LEVEL4.add(RocketConstants.SDK_APP_ID);
        commonParmas_LEVEL4.add(com.bytedance.bdinstall.e.aB);
        commonParmas_LEVEL4.add(com.bytedance.bdinstall.e.aE);
        commonParmas_LEVEL4.add("version_name");
        commonParmas_LEVEL4.add("device_brand");
        commonParmas_LEVEL4.add("os_api");
        commonParmas_LEVEL4.add("manifest_version_code");
        commonParmas_LEVEL4.add("update_version_code");
        commonParmas_LEVEL4.add("app_package");
        commonParmas_LEVEL4.add("device_model");
        commonParmas_LEVEL4.add(com.bytedance.bdinstall.e.aA);
        commonParmas_LEVEL4.add("cdid");
        commonParmas_LEVEL4.add("g_download_source");
        commonParmas_LEVEL4.add("shark_extra");
        commonParmas_LEVEL4.add("carrier_region");
        commonParmas_LEVEL4.add("sys_region");
        commonParmas_LEVEL4.add(ApplogUtils.GAME_ID);
        intl_Parmas_LEVEL4.add("app_region");
        intl_Parmas_LEVEL4.add("app_language");
        intl_Parmas_LEVEL4.add("sys_language");
        intl_Parmas_LEVEL4.add("sys_region");
        intl_Parmas_LEVEL4.add("timezone_name");
        customParmas_LEVEL4.add(RocketConstants.BAN_ODIN);
        customParmas_LEVEL4.add("real_package_name");
        customParmas_LEVEL4.add("version_code");
        customParmas_LEVEL4.add("build_number");
        customParmas_LEVEL4.add("device_platform");
        customParmas_LEVEL4.add(RocketConstants.PATCH_VERSION);
        customParmas_LEVEL4.add(RocketConstants.GSDK_VERSION);
        customParmas_LEVEL4.add(RocketConstants.GSDK_ENVIRONMENT);
        customParmas_LEVEL4.add("app_version_minor");
        customParmas_LEVEL4.add("is_emulator");
        customParmas_LEVEL4.add(RocketConstants.EMULATOR_TYPE);
        customParmas_LEVEL4.add("store_region_user");
        customParmas_LEVEL4.add("store_region_device");
        customParmas_LEVEL4.add(ApplogUtils.GAME_ID);
        customParmas_LEVEL4.add("encrypt_ticket_id");
        customParmas_LEVEL4.add("g_download_source");
        customParmas_LEVEL4.add("process_name");
        commonParmas_LEVEL5.add("resolution");
        commonParmas_LEVEL5.add(com.bytedance.bdinstall.e.D);
        commonParmas_LEVEL5.add("build_number");
        commonParmas_LEVEL5.add("tz_name");
        commonParmas_LEVEL5.add("tz_offset");
        commonParmas_LEVEL5.add("login_way");
        commonParmas_LEVEL5.add("login_type");
        commonParmas_LEVEL5.add(Constant.LOGIN_EVENT_SEQ);
        commonParmas_LEVEL5.add("attribution_ex");
        commonParmas_LEVEL5.add("login_channel");
        customParmas_LEVEL5.add("user_is_login");
        customParmas_LEVEL5.add("login_way");
        customParmas_LEVEL5.add("launch_type");
    }

    public static ArrayList<String> getCommonParmas(int i2) {
        return collectCommonParams(i2, commonParmas_LEVEL1, commonParmas_LEVEL2, commonParmas_LEVEL3, commonParmas_LEVEL4, commonParmas_LEVEL5);
    }

    public static ArrayList<String> collectCommonParams(int i2, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, ArrayList arrayList5) {
        ArrayList<String> arrayList6 = new ArrayList<>();
        i2 = (i2 <= 0 || i2 >= 32) ? 31 : 31;
        if (i2 >= 16) {
            arrayList6.addAll(arrayList5);
            i2 -= 16;
        }
        if (i2 >= 8) {
            arrayList6.addAll(arrayList4);
            i2 -= 8;
        }
        if (i2 >= 4) {
            arrayList6.addAll(arrayList3);
            i2 -= 4;
        }
        if (i2 >= 2) {
            arrayList6.addAll(arrayList2);
            i2 -= 2;
        }
        if (i2 >= 1) {
            arrayList6.addAll(arrayList);
        }
        return arrayList6;
    }

    public static ArrayList<String> getCustomParmas(int i2) {
        return collectCommonParams(i2, customParmas_LEVEL1, customParmas_LEVEL2, customParmas_LEVEL3, customParmas_LEVEL4, customParmas_LEVEL5);
    }

    public static ArrayList<String> getIntlParmas(int i2) {
        return collectCommonParams(i2, intl_Parmas_LEVEL1, intl_Parmas_LEVEL2, intl_Parmas_LEVEL3, intl_Parmas_LEVEL4, intl_Parmas_LEVEL5);
    }
}
