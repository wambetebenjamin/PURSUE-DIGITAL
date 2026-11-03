package com.bytedance.robust.monitor;

import android.util.Log;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PatchDataReport {
    public static final String APPID = "app_id";
    public static final String APPLY_HOTFIX_PATCH = "mgame_apply_hotfix_patch";
    public static final String APPLY_PATCH_COST = "mgame_hotfix_patch_apply_cost";
    public static final String COMBINE_HOTFIX_PATCH = "mgame_combine_hotfix_patch";
    public static final String COST_MILLI = "cost_milli";
    public static final String DOWNLOAD_HOTFIX_INFO = "mgame_download_hotfix_info";
    public static final String DOWNLOAD_HOTFIX_INFO_START = "mgame_download_hotfix_info_start";
    public static final String DOWNLOAD_HOTFIX_PATCH = "mgame_download_hotfix_patch";
    public static final String DOWNLOAD_PATCH_COST = "mgame_hotfix_patch_download_cost";
    public static final String ERROR_DETAIL = "error_detail";
    public static final String ERROR_LOCATION = "error_location";
    public static final String HOTFIX_EXCEPTION = "mgame_hotfix_exception";
    public static final String HOTFIX_START = "mgame_hotfix_start";
    public static final String INSTALL_SO_PATCH_COST = "mgame_hotfix_so_patch_install_cost";
    private static final String LOG_TAG = "robust-reporter";
    public static final String PATCHVERSION = "hotfix_patch_version";
    public static final String PATCH_COUNT = "patch_count";
    public static final String PATCH_SO_INSTALL = "mgame_hotfix_so_install";
    public static final String RESTART_APP = "mgame_hotfix_restart_app";
    public static final String SDKPATCHNAME = "sdk_patch_name";
    public static final String SDKVERSION = "sdk_version";
    public static final String SO_COUNT = "so_count";
    public static final String SO_NAME = "so_name";
    public static final String STATUS = "status";
    public static final String VERIFY_HOTFIX_PATCH = "mgame_verify_hotfix_patch";
    public static ChangeQuickRedirect changeQuickRedirect;
    public static long sAppId;
    public static IPatchMonitor sExtraMonitor;
    public static IPatchMonitor sPatchMonitor;

    public static void init(long j2, IPatchMonitor iPatchMonitor) {
        if (PatchProxy.proxy(new Object[]{new Long(j2), iPatchMonitor}, null, changeQuickRedirect, true, "0504fa0899667120ac25c7c5e121289c") != null) {
            return;
        }
        sAppId = j2;
        sPatchMonitor = iPatchMonitor;
        if (iPatchMonitor == null) {
            Log.w(LOG_TAG, "IPatchMonitor is null");
        }
    }

    private static void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        if (PatchProxy.proxy(new Object[]{str, jSONObject, jSONObject2, jSONObject3}, null, changeQuickRedirect, true, "d4f08bf19d8bfff061b213c75a4f8094") != null) {
            return;
        }
        try {
            IPatchMonitor iPatchMonitor = sPatchMonitor;
            if (iPatchMonitor != null) {
                iPatchMonitor.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
            }
        } catch (Throwable th) {
            Log.e(LOG_TAG, "monitorEvent error", th);
        }
        try {
            IPatchMonitor iPatchMonitor2 = sExtraMonitor;
            if (iPatchMonitor2 == null || iPatchMonitor2 == sPatchMonitor) {
                return;
            }
            iPatchMonitor2.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
        } catch (Throwable th2) {
            Log.e(LOG_TAG, "extra monitorEvent error", th2);
        }
    }

    public static void reportHotfixStart(String str, int i2) {
        if (PatchProxy.proxy(new Object[]{str, new Integer(i2)}, null, changeQuickRedirect, true, "ee510619cc6a41a3fa7ee425eac0c2eb") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(SDKPATCHNAME, str);
            jSONObject.putOpt(PATCHVERSION, Integer.valueOf(i2));
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportHotfixStart error", th);
        }
        monitorEvent(HOTFIX_START, jSONObject, null, null);
    }

    public static void reportRestartApp(String str) {
        if (PatchProxy.proxy(new Object[]{str}, null, changeQuickRedirect, true, "b05e8b8787a83440e9246e0a433be4c2") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt("status", str);
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportRestartApp error", th);
        }
        monitorEvent(RESTART_APP, jSONObject, null, null);
    }

    public static void reportHotfixException(String str, String str2) {
        if (PatchProxy.proxy(new Object[]{str, str2}, null, changeQuickRedirect, true, "56633caeb6d7dac7006aa924a5c256c6") != null) {
            return;
        }
        Log.e(LOG_TAG, "reportHotfixException errorLocation:" + str + ", errorDetail:" + str2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            if (str != null) {
                jSONObject.putOpt(ERROR_LOCATION, str);
            }
            if (str2 != null) {
                jSONObject.putOpt(ERROR_DETAIL, str2);
            }
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportHotfixException error", th);
        }
        monitorEvent(HOTFIX_EXCEPTION, jSONObject, null, null);
    }

    public static void reportHotfixException(String str, Throwable th) {
        if (PatchProxy.proxy(new Object[]{str, th}, null, changeQuickRedirect, true, "f33a5eb9d6b405f7179b47faf1f2c349") != null) {
            return;
        }
        reportHotfixException(str, Log.getStackTraceString(th));
    }

    public static void reportPatchApply(int i2, String str, int i3, String str2) {
        if (PatchProxy.proxy(new Object[]{new Integer(i2), str, new Integer(i3), str2}, null, changeQuickRedirect, true, "569096562e77611f159d2d0552a6f55e") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("status", Integer.valueOf(i2));
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(SDKPATCHNAME, str);
            jSONObject.putOpt(PATCHVERSION, Integer.valueOf(i3));
            if (str2 != null) {
                jSONObject.putOpt(ERROR_DETAIL, str2);
            }
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportPatchApply error", th);
        }
        monitorEvent(APPLY_HOTFIX_PATCH, jSONObject, null, null);
    }

    public static void reportPatchVerified(boolean z, String str, int i2) {
        int i3 = 0;
        if (PatchProxy.proxy(new Object[]{new Byte(z ? (byte) 1 : (byte) 0), str, new Integer(i2)}, null, changeQuickRedirect, true, "43dd06609a3be9a7fb799e6cb86a87d5") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            i3 = 1;
        }
        try {
            jSONObject.putOpt("status", Integer.valueOf(i3));
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(SDKPATCHNAME, str);
            jSONObject.putOpt(PATCHVERSION, Integer.valueOf(i2));
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportPatchVerified error", th);
        }
        monitorEvent(VERIFY_HOTFIX_PATCH, jSONObject, null, null);
    }

    public static void reportPatchCombined(boolean z, String str) {
        int i2 = 0;
        if (PatchProxy.proxy(new Object[]{new Byte(z ? (byte) 1 : (byte) 0), str}, null, changeQuickRedirect, true, "84bbd1a8adb2c52ae8202b629d2cd889") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            i2 = 1;
        }
        try {
            jSONObject.putOpt("status", Integer.valueOf(i2));
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            if (str != null) {
                jSONObject.putOpt(ERROR_DETAIL, str);
            }
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportPatchCombined error", th);
        }
        monitorEvent(COMBINE_HOTFIX_PATCH, jSONObject, null, null);
    }

    public static void reportFetchPatchInfoStart() {
        if (PatchProxy.proxy(new Object[0], null, changeQuickRedirect, true, "14308c64b11a70dab13ac084a2f874b9") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportFetchPatchInfoStart error", th);
        }
        monitorEvent(DOWNLOAD_HOTFIX_INFO_START, jSONObject, null, null);
    }

    public static void reportPatchInfoFetched(String str, int i2) {
        if (PatchProxy.proxy(new Object[]{str, new Integer(i2)}, null, changeQuickRedirect, true, "8bd0cf37043ad20110b2746daffa9993") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(SDKPATCHNAME, str);
            jSONObject.putOpt(PATCHVERSION, Integer.valueOf(i2));
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportPatchInfoFetched error", th);
        }
        monitorEvent(DOWNLOAD_HOTFIX_INFO, jSONObject, null, null);
    }

    public static void reportPatchDownload(boolean z, String str, int i2, String str2) {
        int i3 = 0;
        if (PatchProxy.proxy(new Object[]{new Byte(z ? (byte) 1 : (byte) 0), str, new Integer(i2), str2}, null, changeQuickRedirect, true, "4bb00afa59654f59b864d53c3f0c7480") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            i3 = 1;
        }
        try {
            jSONObject.putOpt("status", Integer.valueOf(i3));
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(SDKPATCHNAME, str);
            jSONObject.putOpt(PATCHVERSION, Integer.valueOf(i2));
            if (str2 != null) {
                jSONObject.putOpt(ERROR_DETAIL, str2);
            }
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportPatchDownload error", th);
        }
        monitorEvent(DOWNLOAD_HOTFIX_PATCH, jSONObject, null, null);
    }

    public static void reportPatchSoInstall(boolean z, String str, int i2, String str2, String str3) {
        int i3 = 0;
        if (PatchProxy.proxy(new Object[]{new Byte(z ? (byte) 1 : (byte) 0), str, new Integer(i2), str2, str3}, null, changeQuickRedirect, true, "eff122c24e0fec955b7cc7679331f8cc") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            i3 = 1;
        }
        try {
            jSONObject.putOpt("status", Integer.valueOf(i3));
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(SDKPATCHNAME, str);
            jSONObject.putOpt(PATCHVERSION, Integer.valueOf(i2));
            jSONObject.putOpt(SO_NAME, str2);
            if (str3 != null) {
                jSONObject.putOpt(ERROR_DETAIL, str3);
            }
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportPatchSoInstall error", th);
        }
        monitorEvent(PATCH_SO_INSTALL, jSONObject, null, null);
    }

    public static void reportSoPatchInstallCost(String str, int i2, int i3, long j2) {
        if (PatchProxy.proxy(new Object[]{str, new Integer(i2), new Integer(i3), new Long(j2)}, null, changeQuickRedirect, true, "8670ac23e7b1f1b3c26610ce8edf2ba6") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(SDKPATCHNAME, str);
            jSONObject.putOpt(PATCHVERSION, Integer.valueOf(i2));
            jSONObject.putOpt(SO_COUNT, Integer.valueOf(i3));
            jSONObject.putOpt(COST_MILLI, Long.valueOf(j2));
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportSoPatchInstallCost error", th);
        }
        monitorEvent(INSTALL_SO_PATCH_COST, jSONObject, null, null);
    }

    public static void reportPatchDownloadCost(String str, int i2, long j2) {
        if (PatchProxy.proxy(new Object[]{str, new Integer(i2), new Long(j2)}, null, changeQuickRedirect, true, "ba8a78237f45bbeff59b07b53dede4f2") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(SDKPATCHNAME, str);
            jSONObject.putOpt(PATCHVERSION, Integer.valueOf(i2));
            jSONObject.putOpt(COST_MILLI, Long.valueOf(j2));
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportHotfixStart error", th);
        }
        monitorEvent(DOWNLOAD_PATCH_COST, jSONObject, null, null);
    }

    public static void reportPatchApplyCost(long j2, int i2) {
        if (PatchProxy.proxy(new Object[]{new Long(j2), new Integer(i2)}, null, changeQuickRedirect, true, "85ae2df4d13102b2b6bebf4774a37f0e") != null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("app_id", Long.valueOf(sAppId));
            jSONObject.putOpt(PATCH_COUNT, Integer.valueOf(i2));
            jSONObject.putOpt(COST_MILLI, Long.valueOf(j2));
        } catch (Throwable th) {
            Log.e(LOG_TAG, "reportHotfixStart error", th);
        }
        monitorEvent(APPLY_PATCH_COST, jSONObject, null, null);
    }
}
