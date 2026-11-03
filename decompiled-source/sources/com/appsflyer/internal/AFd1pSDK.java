package com.appsflyer.internal;

import android.content.SharedPreferences;
import com.appsflyer.AFLogger;
/* loaded from: classes.dex */
public final class AFd1pSDK implements AFd1xSDK {
    private final SharedPreferences AFInAppEventType;

    public AFd1pSDK(SharedPreferences sharedPreferences) {
        this.AFInAppEventType = sharedPreferences;
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final void values(String str, String str2) {
        this.AFInAppEventType.edit().putString(str, str2).apply();
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final String valueOf(String str, String str2) {
        try {
            return this.AFInAppEventType.getString(str, str2);
        } catch (ClassCastException e2) {
            AFLogger.afErrorLog("Unexpected data type found for key ".concat(String.valueOf(str)), e2);
            return str2;
        }
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final boolean valueOf(String str) {
        try {
            return this.AFInAppEventType.getBoolean(str, false);
        } catch (ClassCastException e2) {
            AFLogger.afErrorLog("Unexpected data type found for key ".concat(String.valueOf(str)), e2);
            return false;
        }
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final void AFInAppEventParameterName(String str, boolean z) {
        this.AFInAppEventType.edit().putBoolean(str, z).apply();
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final long AFInAppEventType(String str, long j2) {
        try {
            return this.AFInAppEventType.getLong(str, j2);
        } catch (ClassCastException e2) {
            AFLogger.afErrorLog("Unexpected data type found for key ".concat(String.valueOf(str)), e2);
            return j2;
        }
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final void AFInAppEventParameterName(String str, long j2) {
        this.AFInAppEventType.edit().putLong(str, j2).apply();
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final void AFInAppEventParameterName(String str, int i2) {
        this.AFInAppEventType.edit().putInt(str, i2).apply();
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final int valueOf(String str, int i2) {
        try {
            return this.AFInAppEventType.getInt(str, i2);
        } catch (ClassCastException e2) {
            AFLogger.afErrorLog("Unexpected data type found for key ".concat(String.valueOf(str)), e2);
            return i2;
        }
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final boolean AFInAppEventParameterName(String str) {
        return this.AFInAppEventType.contains(str);
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final void AFKeystoreWrapper(String str) {
        this.AFInAppEventType.edit().remove(str).apply();
    }
}
