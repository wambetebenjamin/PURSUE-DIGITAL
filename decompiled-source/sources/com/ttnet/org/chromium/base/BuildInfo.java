package com.ttnet.org.chromium.base;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.ttnet.org.chromium.base.compat.ApiHelperForP;
import com.ttnet.org.chromium.build.BuildConfig;
import java.util.Locale;
/* loaded from: classes9.dex */
public class BuildInfo {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_FINGERPRINT_LENGTH = 128;
    private static final String TAG = "BuildInfo";
    private static ApplicationInfo sBrowserApplicationInfo = null;
    private static PackageInfo sBrowserPackageInfo = null;
    private static String sFirebaseAppId = "";
    private static boolean sInitialized;
    public String abiString;
    public String androidBuildFingerprint;
    public String hostPackageLabel;
    public long hostVersionCode;
    public String installerPackageName;
    public boolean isAutomotive;
    public boolean isTV;
    public String packageName;
    public String resourcesVersion;
    public long versionCode;
    public String versionName;
    public String gmsVersionCode = "";
    public String customThemes = "";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Holder {
        private static BuildInfo sInstance = new BuildInfo();

        private Holder() {
        }
    }

    private static String[] getAll() {
        return getInstance().getAllProperties();
    }

    String[] getAllProperties() {
        String packageName = ContextUtils.getApplicationContext().getPackageName();
        String[] strArr = new String[28];
        strArr[0] = Build.BRAND;
        strArr[1] = Build.DEVICE;
        strArr[2] = Build.ID;
        strArr[3] = Build.MANUFACTURER;
        strArr[4] = Build.MODEL;
        strArr[5] = String.valueOf(Build.VERSION.SDK_INT);
        strArr[6] = Build.TYPE;
        strArr[7] = Build.BOARD;
        strArr[8] = packageName;
        strArr[9] = String.valueOf(this.hostVersionCode);
        strArr[10] = this.hostPackageLabel;
        strArr[11] = this.packageName;
        strArr[12] = String.valueOf(this.versionCode);
        strArr[13] = this.versionName;
        strArr[14] = this.androidBuildFingerprint;
        strArr[15] = this.gmsVersionCode;
        strArr[16] = this.installerPackageName;
        strArr[17] = this.abiString;
        strArr[18] = sFirebaseAppId;
        strArr[19] = this.customThemes;
        strArr[20] = this.resourcesVersion;
        strArr[21] = String.valueOf(ContextUtils.getApplicationContext().getApplicationInfo().targetSdkVersion);
        strArr[22] = isDebugAndroid() ? "1" : "0";
        strArr[23] = this.isTV ? "1" : "0";
        strArr[24] = Build.VERSION.INCREMENTAL;
        strArr[25] = Build.HARDWARE;
        strArr[26] = isAtLeastT() ? "1" : "0";
        strArr[27] = this.isAutomotive ? "1" : "0";
        return strArr;
    }

    private static String nullToEmpty(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence.toString();
    }

    public static long packageVersionCode(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return ApiHelperForP.getLongVersionCode(packageInfo);
        }
        return packageInfo.versionCode;
    }

    public static void setBrowserPackageInfo(PackageInfo packageInfo) {
        sBrowserPackageInfo = packageInfo;
    }

    public ApplicationInfo getBrowserApplicationInfo() {
        return sBrowserApplicationInfo;
    }

    public static BuildInfo getInstance() {
        return Holder.sInstance;
    }

    BuildInfo() {
        this.hostPackageLabel = "";
        this.packageName = "";
        this.versionName = "";
        this.installerPackageName = "";
        this.abiString = "";
        this.androidBuildFingerprint = "";
        this.resourcesVersion = "";
        boolean z = true;
        sInitialized = true;
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            String packageName = applicationContext.getPackageName();
            PackageManager packageManager = applicationContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            long packageVersionCode = packageVersionCode(packageInfo);
            this.hostVersionCode = packageVersionCode;
            PackageInfo packageInfo2 = sBrowserPackageInfo;
            if (packageInfo2 != null) {
                this.packageName = packageInfo2.packageName;
                this.versionCode = packageVersionCode(sBrowserPackageInfo);
                this.versionName = nullToEmpty(sBrowserPackageInfo.versionName);
                sBrowserApplicationInfo = sBrowserPackageInfo.applicationInfo;
                sBrowserPackageInfo = null;
            } else {
                this.packageName = packageName;
                this.versionCode = packageVersionCode;
                this.versionName = nullToEmpty(packageInfo.versionName);
                sBrowserApplicationInfo = applicationContext.getApplicationInfo();
            }
            this.hostPackageLabel = nullToEmpty(packageManager.getApplicationLabel(packageInfo.applicationInfo));
            this.installerPackageName = nullToEmpty(packageManager.getInstallerPackageName(this.packageName));
            String str = "Not Enabled";
            if (BuildConfig.R_STRING_PRODUCT_VERSION != 0) {
                try {
                    str = ContextUtils.getApplicationContext().getString(BuildConfig.R_STRING_PRODUCT_VERSION);
                } catch (Exception unused) {
                    str = "Not found";
                }
            }
            this.resourcesVersion = str;
            if (Build.VERSION.SDK_INT >= 21) {
                this.abiString = TextUtils.join(", ", Build.SUPPORTED_ABIS);
            } else {
                this.abiString = String.format("ABI1: %s, ABI2: %s", Build.CPU_ABI, Build.CPU_ABI2);
            }
            this.androidBuildFingerprint = Build.FINGERPRINT.substring(0, Math.min(Build.FINGERPRINT.length(), 128));
            UiModeManager uiModeManager = (UiModeManager) applicationContext.getSystemService("uimode");
            if (uiModeManager == null || uiModeManager.getCurrentModeType() != 4) {
                z = false;
            }
            this.isTV = z;
            this.isAutomotive = applicationContext.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
        } catch (Exception e2) {
            Log.e(TAG, "NameNotFoundException: " + e2, new Object[0]);
        }
    }

    public static boolean isDebugAndroid() {
        return "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
    }

    private static boolean isAtLeastPreReleaseCodename(String str, String str2) {
        return !"REL".equals(str2) && str2.toUpperCase(Locale.ROOT).compareTo(str.toUpperCase(Locale.ROOT)) >= 0;
    }

    public static boolean isAtLeastT() {
        return Build.VERSION.SDK_INT >= 32 && isAtLeastPreReleaseCodename("Tiramisu", Build.VERSION.CODENAME);
    }

    public static boolean targetsAtLeastT() {
        return ContextUtils.getApplicationContext().getApplicationInfo().targetSdkVersion >= 33;
    }

    public static void setFirebaseAppId(String str) {
        sFirebaseAppId = str;
    }

    public static String getFirebaseAppId() {
        return sFirebaseAppId;
    }
}
