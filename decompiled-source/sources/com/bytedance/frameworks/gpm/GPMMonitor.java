package com.bytedance.frameworks.gpm;

import android.content.Context;
import com.bytedance.crash.general.HardwareInfo;
import com.bytedance.frameworks.gpm.util.CPUUtil;
import com.bytedance.frameworks.gpm.util.RAMUtil;
import com.bytedance.frameworks.gpm.util.d;
import com.bytedance.frameworks.gpm.util.e;
import com.bytedance.frameworks.gpm.util.h;
import com.bytedance.frameworks.gpm.util.i;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class GPMMonitor {
    private static final String GAME_SCENE = "game_scene";
    private static final String LOG_TAG = "gpm";
    private static final String SDK_SCENE = "sdk_scene";
    private static final String SDK_SCENE_REPORT = "sdk_scene_report";
    private static GPMConfig config;
    private static Context context;

    private static String checkNullStr(String str) {
        return str == null ? "" : str;
    }

    static native void cxxInit(String str);

    static native void cxxLogFrameEnd();

    static native void cxxLogGlobalInfoInt(String str, String str2, int i2);

    static native void cxxLogGlobalInfoStr(String str, String str2, String str3);

    static native void cxxLogMemoryUsage();

    static native void cxxLogSceneEnd(String str, String str2, boolean z);

    static native void cxxLogSceneInfoInt(String str, String str2, String str3, int i2);

    static native void cxxLogSceneInfoStr(String str, String str2, String str3, String str4);

    static native void cxxLogSceneReport(String str, String str2, String str3);

    static native void cxxLogSceneStart(String str, String str2);

    static native void cxxSetAPP(int i2, int i3, String str);

    static native void cxxSetInterval(int i2, int i3, int i4);

    static native void cxxSetSwitch(boolean z, boolean z2, boolean z3, boolean z4);

    static {
        System.loadLibrary(LOG_TAG);
        context = null;
        config = null;
    }

    public static String getDeviceBrand() {
        return d.getDeviceBrand();
    }

    public static String getDeviceModel() {
        return d.getDeviceModel();
    }

    public static String getOS() {
        return d.getOS();
    }

    public static String getOSVersion() {
        return d.getOSVersion();
    }

    public static int getBuildVersion() {
        return com.bytedance.frameworks.gpm.util.a.getBuildVersion(context);
    }

    public static String getAppVersion() {
        return com.bytedance.frameworks.gpm.util.a.getAppVersion(context);
    }

    public static String getSDKVersion() {
        return com.bytedance.frameworks.gpm.util.a.getSDKVersion();
    }

    public static int getCPUMaxFreq() {
        return CPUUtil.getCPUMaxFreq();
    }

    public static int getCPUCores() {
        return CPUUtil.getCPUCores();
    }

    public static String getCPUModel() {
        return CPUUtil.getCPUModel();
    }

    public static String getGPUVendor() {
        return e.getGPUVendor();
    }

    public static String getGPUModel() {
        return e.getGPUModel();
    }

    public static String getGLExtensions() {
        return e.b();
    }

    public static int getRAMSize() {
        return RAMUtil.getRAMSize();
    }

    public static int getROMSize() {
        return h.getROMSize();
    }

    public static int getROMSizeAvail() {
        return h.getROMSizeAvail();
    }

    public static String getResolution() {
        return d.getResolution();
    }

    public static void init(Context context2, GPMConfig gPMConfig) {
        if (context2 == null || gPMConfig == null) {
            throw new IllegalArgumentException("Must set non null context and config");
        }
        context = context2.getApplicationContext();
        config = gPMConfig;
        cxxSetSwitch(gPMConfig.getFPSSwitch(), config.getCPUSwitch(), config.getMemorySwitch(), config.getThreadSwitch());
        cxxSetInterval(config.getCpuInterval(), config.getMemoryInterval(), config.getThreadInterval());
        cxxSetAPP(config.getAID(), config.getRegion().ordinal(), config.getDID());
        cxxInit(context.getCacheDir().getAbsolutePath());
        addParameter(config.getParameter());
        logGlobalInfo("is_test", config.isTest() ? 1 : 0);
    }

    private static void addParameter(Map<String, String> map) {
        logGlobalInfo(GAME_SCENE, "device_brand", getDeviceBrand());
        logGlobalInfo(GAME_SCENE, "device_model", getDeviceModel());
        logGlobalInfo(GAME_SCENE, "os", getOS());
        logGlobalInfo(GAME_SCENE, "os_version", getOSVersion());
        logGlobalInfo(GAME_SCENE, "build_version", String.valueOf(getBuildVersion()));
        logGlobalInfo(GAME_SCENE, "app_version", getAppVersion());
        logGlobalInfo(GAME_SCENE, "cpu_max_freq", getCPUMaxFreq());
        logGlobalInfo(GAME_SCENE, "cpu_cores", getCPUCores());
        logGlobalInfo(GAME_SCENE, HardwareInfo.KEY_HW_CPU_MODEL, getCPUModel());
        logGlobalInfo(GAME_SCENE, HardwareInfo.KEY_HW_RAM_SIZE, getRAMSize());
        logGlobalInfo(GAME_SCENE, "rom_size", getROMSize());
        logGlobalInfo(GAME_SCENE, "resolution", getResolution());
        logGlobalInfo(GAME_SCENE, "gpu_model", getGPUModel());
        logGlobalInfo(GAME_SCENE, "gpu_vendor", getGPUVendor());
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                logGlobalInfo(GAME_SCENE, entry.getKey(), entry.getValue());
            }
        }
    }

    public static GPMConfig getConfig() {
        return config;
    }

    public static String httpGet(String str, String str2, String str3, String str4) {
        return b.b(str, str2, str3, str4);
    }

    public static String httpUploadFile(String str, String str2, String str3, String str4, String str5) {
        return b.a(str, str2, str3, str4, str5);
    }

    public static String getCPUUsage() {
        return CPUUtil.getCPUUsage();
    }

    public static String getMemoryUsage() {
        return RAMUtil.getMemoryUsage(context);
    }

    public static int getThreadCount() {
        return i.getThreadCount();
    }

    public static String getTIDs() {
        return i.getTIDs();
    }

    private static void upload(String str, String str2) {
        GPMConfig gPMConfig = config;
        if (gPMConfig == null || gPMConfig.getUploader() == null) {
            loge("uploader is null");
        } else {
            config.getUploader().upload(str, str2);
        }
    }

    private static void notifySceneStart(String str, String str2) {
        GPMConfig gPMConfig = config;
        if (gPMConfig == null || gPMConfig.getSceneNotifier() == null) {
            loge("sceneNotifier is null");
        } else {
            config.getSceneNotifier().notifySceneStart(str, str2);
        }
    }

    private static void notifySceneEnd(String str, String str2) {
        GPMConfig gPMConfig = config;
        if (gPMConfig == null || gPMConfig.getSceneNotifier() == null) {
            loge("sceneNotifier is null");
        } else {
            config.getSceneNotifier().notifySceneEnd(str, str2);
        }
    }

    public static void logGlobalInfo(String str, int i2) {
        logGlobalInfo(SDK_SCENE, str, i2);
        logGlobalInfo(SDK_SCENE_REPORT, str, i2);
    }

    public static void logGlobalInfo(String str, String str2, int i2) {
        cxxLogGlobalInfoInt(checkNullStr(str), checkNullStr(str2), i2);
    }

    public static void logGlobalInfo(String str, String str2) {
        logGlobalInfo(SDK_SCENE, str, str2);
        logGlobalInfo(SDK_SCENE_REPORT, str, str2);
    }

    public static void logGlobalInfo(String str, String str2, String str3) {
        cxxLogGlobalInfoStr(checkNullStr(str), checkNullStr(str2), checkNullStr(str3));
    }

    public static void logSceneInfo(String str, String str2, int i2) {
        logSceneInfo(SDK_SCENE, str, str2, i2);
    }

    public static void logSceneInfo(String str, String str2, String str3, int i2) {
        cxxLogSceneInfoInt(checkNullStr(str), checkNullStr(str2), checkNullStr(str3), i2);
    }

    public static void logSceneInfo(String str, String str2, String str3) {
        logSceneInfo(SDK_SCENE, str, str2, str3);
    }

    public static void logSceneInfo(String str, String str2, String str3, String str4) {
        cxxLogSceneInfoStr(checkNullStr(str), checkNullStr(str2), checkNullStr(str3), checkNullStr(str4));
    }

    public static void logSceneStart(String str) {
        logSceneStart(SDK_SCENE, str);
    }

    public static void logSceneStart(String str, String str2) {
        cxxLogSceneStart(checkNullStr(str), checkNullStr(str2));
    }

    public static void logSceneEnd(String str, boolean z) {
        logSceneEnd(SDK_SCENE, str, z);
    }

    public static void logSceneEnd(String str, String str2, boolean z) {
        GPMConfig gPMConfig = config;
        if (gPMConfig != null && gPMConfig.isTest()) {
            RAMUtil.gcAndFinalize();
        }
        cxxLogSceneEnd(checkNullStr(str), checkNullStr(str2), z);
    }

    public static void logSceneReport(String str, Map<String, Object> map) {
        try {
            String checkNullStr = checkNullStr(str);
            JSONObject jSONObject = new JSONObject();
            for (String str2 : map.keySet()) {
                jSONObject.put(str2, map.get(str2));
            }
            cxxLogSceneReport(SDK_SCENE_REPORT, checkNullStr, jSONObject.toString());
        } catch (Throwable th) {
            loge("scene report error:" + th.toString());
        }
    }

    public static void logFrameEnd() {
        cxxLogFrameEnd();
    }

    public static void logMemoryUsage() {
        cxxLogMemoryUsage();
    }

    public static void logd(String str) {
        GPMConfig gPMConfig = config;
        if (gPMConfig == null || gPMConfig.getLogger() == null) {
            return;
        }
        config.getLogger().d(LOG_TAG, str);
    }

    public static void loge(String str) {
        GPMConfig gPMConfig = config;
        if (gPMConfig == null || gPMConfig.getLogger() == null) {
            return;
        }
        config.getLogger().e(LOG_TAG, str);
    }

    public static void logw(String str) {
        GPMConfig gPMConfig = config;
        if (gPMConfig == null || gPMConfig.getLogger() == null) {
            return;
        }
        config.getLogger().w(LOG_TAG, str);
    }
}
