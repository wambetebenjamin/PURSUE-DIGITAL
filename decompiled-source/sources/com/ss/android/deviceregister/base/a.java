package com.ss.android.deviceregister.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.common.utility.o;
/* compiled from: AppLogConstants.java */
/* loaded from: classes9.dex */
public class a {
    public static final String KEY_AID = "aid";
    public static final String KEY_CLEAR_KEY_PREFIX = "clear_key_prefix";
    public static final String KEY_CLIENTUDID = "clientudid";
    public static final String KEY_DEVICE_ID = "device_id";
    public static final String KEY_FIRST_INSTALL_TIME = "apk_first_install_time";
    public static final String KEY_HEADER = "header";
    public static final String KEY_INSTALL_ID = "install_id";
    public static final String KEY_IS_SYSTEM_APP = "is_system_app";
    public static final String KEY_OPENUDID = "openudid";
    public static final String KEY_PRE_INSTALL_CHANNEL = "pre_installed_channel";
    public static final String KEY_UDID = "udid";
    public static final String KEY_UDID_LIST = "udid_list";
    public static final String KEY_USER_AGENT = "user_agent";
    public static final int MAX_UDID_LENGTH = 160;
    public static final int MIN_UDID_LENGTH = 13;
    public static final int SC_UNKNOWN = 1;

    /* renamed from: a  reason: collision with root package name */
    private static volatile String f18137a = "applog_stats";

    /* renamed from: b  reason: collision with root package name */
    private static String f18138b = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f18139c = false;

    /* renamed from: d  reason: collision with root package name */
    public static final String f18140d = "klink_egdi";

    public static void setSPName(String str) {
        if (o.isEmpty(str)) {
            return;
        }
        f18137a = str;
    }

    public static String getSPName() {
        return f18137a;
    }

    public static void setAnonymous(boolean z) {
        f18139c = z;
    }

    public static boolean isAnonymous() {
        return f18139c;
    }

    public static String getDeviceParamsSpName() {
        if (TextUtils.isEmpty(f18138b)) {
            f18138b = com.ss.android.deviceregister.core.cache.internal.c.base64DecodeToString("c25zc2RrX29wZW51ZGlk");
        }
        return f18138b;
    }

    public static SharedPreferences getApplogStatsSp(Context context) {
        return context.getSharedPreferences(getSPName(), 0);
    }
}
