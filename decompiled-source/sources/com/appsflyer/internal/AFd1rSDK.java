package com.appsflyer.internal;

import android.content.pm.PackageItemInfo;
import android.content.res.Resources;
import android.os.Bundle;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
/* loaded from: classes.dex */
public final class AFd1rSDK {
    private static String values = "281";
    public final AFd1xSDK AFInAppEventParameterName;
    public final AFd1lSDK AFKeystoreWrapper;
    private Bundle valueOf = null;

    public AFd1rSDK(AFd1lSDK aFd1lSDK, AFd1xSDK aFd1xSDK) {
        this.AFKeystoreWrapper = aFd1lSDK;
        this.AFInAppEventParameterName = aFd1xSDK;
    }

    public static String AFInAppEventParameterName() {
        return AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
    }

    public final String values(String str) {
        Object obj;
        try {
            if (this.valueOf == null) {
                this.valueOf = ((PackageItemInfo) this.AFKeystoreWrapper.AFInAppEventParameterName.getPackageManager().getApplicationInfo(this.AFKeystoreWrapper.AFInAppEventParameterName.getPackageName(), 128)).metaData;
            }
            Bundle bundle = this.valueOf;
            if (bundle == null || (obj = bundle.get(str)) == null) {
                return null;
            }
            return obj.toString();
        } catch (Throwable th) {
            AFLogger.afErrorLog(new StringBuilder("Could not load manifest metadata!").append(th.getMessage()).toString(), th);
            return null;
        }
    }

    public final boolean AFKeystoreWrapper(String str) {
        String values2 = values(str);
        if (values2 != null) {
            return Boolean.parseBoolean(values2);
        }
        return false;
    }

    public static String AFKeystoreWrapper() {
        return new StringBuilder("version: 6.13.0 (build ").append(values).append(")").toString();
    }

    public final String values() {
        String string = AppsFlyerProperties.getInstance().getString("channel");
        if (string == null) {
            string = values("CHANNEL");
        }
        if (string == null || !string.equals("")) {
            return string;
        }
        return null;
    }

    public final String AFInAppEventType(String str) {
        try {
            int identifier = this.AFKeystoreWrapper.AFInAppEventParameterName.getResources().getIdentifier(str, "string", this.AFKeystoreWrapper.AFInAppEventParameterName.getPackageName());
            if (identifier != 0) {
                return this.AFKeystoreWrapper.AFInAppEventParameterName.getString(identifier);
            }
            return null;
        } catch (Resources.NotFoundException e2) {
            AFLogger.afErrorLog(new StringBuilder("Could not load string resource!").append(e2.getMessage()).toString(), e2);
            return null;
        }
    }
}
