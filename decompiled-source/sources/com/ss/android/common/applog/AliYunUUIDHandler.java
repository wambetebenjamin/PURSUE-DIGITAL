package com.ss.android.common.applog;

import android.text.TextUtils;
/* loaded from: classes9.dex */
public class AliYunUUIDHandler implements IAliYunHandler {
    private static AliYunUUIDHandler sAliYunUUIDHandler;
    private String mCloudUUID;

    private AliYunUUIDHandler() {
        if (isAliYunOs()) {
            String commonCloudUUID = getCommonCloudUUID();
            this.mCloudUUID = commonCloudUUID;
            if (TextUtils.isEmpty(commonCloudUUID)) {
                this.mCloudUUID = getMeiZuCloudUUID();
            }
        }
    }

    public static AliYunUUIDHandler inst() {
        if (sAliYunUUIDHandler == null) {
            synchronized (AliYunUUIDHandler.class) {
                if (sAliYunUUIDHandler == null) {
                    sAliYunUUIDHandler = new AliYunUUIDHandler();
                }
            }
        }
        return sAliYunUUIDHandler;
    }

    @Override // com.ss.android.common.applog.IAliYunHandler
    public String getCloudUUID() {
        return this.mCloudUUID;
    }

    private static String getCommonCloudUUID() {
        return getSystemProperty("ro.aliyun.clouduuid", "false");
    }

    private static String getMeiZuCloudUUID() {
        return getSystemProperty("ro.sys.aliyun.clouduuid", "false");
    }

    private static String getSystemProperty(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    private static boolean isAliYunOs() {
        try {
            if (System.getProperty("java.vm.name") == null || !System.getProperty("java.vm.name").toLowerCase().contains("lemur")) {
                return System.getProperty("ro.yunos.version") != null;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
