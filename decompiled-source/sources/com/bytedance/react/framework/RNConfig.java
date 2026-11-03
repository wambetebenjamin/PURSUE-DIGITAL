package com.bytedance.react.framework;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.react.framework.core.BRNLoaderManager;
import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.react.framework.core.BRNPreloadFullPack;
import com.bytedance.react.framework.core.BRNPreloadViewManager;
import com.bytedance.ttgame.gecko.GsdkGeckoConfig;
import com.bytedance.ttgame.gecko.GsdkGeckoManager;
import java.io.File;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class RNConfig {
    public static boolean ASSET_SPLIT_MODE = false;
    public static volatile int BUNDLE_MODE = -1;
    public static final String BUNDLE_NAME = "bundleName";
    public static final String COMMON_BUNDLE = "commonBundle";
    public static String COMMON_FILE_INDEX = "common.bundle";
    public static final String COMMON_INDEX = "common.bundle";
    public static final String COMMON_INFO = "commonInfo";
    public static final String DEBUG_RN_BUNDLE_DIR_NAME = "debugBundles";
    public static final String FRORM_WHERE = "fromWhere";
    public static final int FULLPACK_MODE_ASSET = 0;
    public static final int FULLPACK_MODE_FILE = 2;
    public static final String GUMIHO_VERSION = "gumiho_version";
    public static final String INGAMEID = "inGameID";
    public static final String IN_GAME_ID = "inGameID";
    public static final String IS_SIMULATOR = "isRnModel";
    public static final String JS_MAIN_MODULE_NAME = "index";
    public static final int MODE_ASSET = 2;
    public static final int MODE_DATA = 1;
    public static final int MODE_NOT_FOUND = 0;
    public static final String MODULE_INDEX = ".bundle";
    public static final String MODULE_NAME = "moduleName";
    public static final String MODULE_VERSION = "moduleVersion";
    public static final String ORIENTATION_KEY = "screenOrientation";
    public static final String PARAM_BUNDLE = "ParamBundle";
    public static final String RN_ASSET_COMMON_PATH = "common";
    public static final String RN_ASSET_PATH = "react";
    public static String RN_BUNDLE_DIR_NAME = null;
    public static final String RN_BUNDLE_ZIP_FILE_NAME = "rn_bundle.zip";
    public static final String RN_COMPONENT = "rctModule";
    public static final String RN_COMPONENT_DEGRADE = "degrade";
    public static final String RN_COMPONENT_FALLBACK_H5 = "fallbackurl";
    public static final String RN_COMPONENT_MODULE = "rctModuleName";
    public static final String RN_COMPONENT_PARAMS = "rctModuleParams";
    public static final String RN_DIR_NAME = "bundles";
    public static final String RN_NEED_HIDE = "RN_NEED_HIDE";
    public static final String RN_PAGE_START_TIME = "rnPageStartTime";
    public static final String RN_PRELOAD_VIEW = "rn_preload_view";
    public static final String RN_SCENE_TYPE = "rnSceneType";
    public static final String RN_URL = "rnUrl";
    public static final String RN_VERSION_FILE_NAME = "rn.version";
    public static final int SPLIT_MODE_ASSET = 1;
    public static final int SPLIT_MODE_FILE = 3;
    public static final long UPDATE_CHECK_RANGE = 5000;
    public static boolean USE_RELEASE_BUNDLE = true;
    public static final String WINDOW_ID = "windowId";
    public static String sGeckoRNPath;
    public static String sRNPath;
    private static final ArrayList<String> PRRELOAD_PACKAGE = new ArrayList<>();
    public static String RN_FILE_COMMON_PATH = "common";
    public static String RN_FILE_COMMON_MODULE = "";
    public static boolean PRELAOD_FULL_PACKAGE = false;
    public static boolean PRELAOD_COMMON = false;
    public static boolean WEAK_REFER = false;
    private static String RN_LOAD_DIR_SUFFIX = "-load";
    private static String commonPackage = "";

    /* loaded from: classes2.dex */
    public enum ScriptType {
        ASSET,
        FILE
    }

    public static final void addFullPreloadPackage(String str) {
        if (PRELAOD_FULL_PACKAGE && !TextUtils.isEmpty(str)) {
            PRRELOAD_PACKAGE.add(str);
        }
    }

    public static String getGeckoRnPath() {
        if (TextUtils.isEmpty(sGeckoRNPath)) {
            sGeckoRNPath = GsdkGeckoConfig.offlineRootDir(BRNManager.newInstance().getAppContext());
        }
        return sGeckoRNPath;
    }

    public static String getRnPath() {
        if (TextUtils.isEmpty(sRNPath)) {
            sRNPath = GsdkGeckoConfig.offlineRootDir(BRNManager.newInstance().getAppContext()) + RN_LOAD_DIR_SUFFIX;
        }
        return sRNPath;
    }

    public static String getRnBundleDirName() {
        if (TextUtils.isEmpty(RN_BUNDLE_DIR_NAME)) {
            RN_BUNDLE_DIR_NAME = GsdkGeckoConfig.getGeckoAdAccessKey();
        }
        return RN_BUNDLE_DIR_NAME;
    }

    public static ArrayList<String> getFullPreloadPackage() {
        return PRRELOAD_PACKAGE;
    }

    public static boolean isPreloadPackage(String str) {
        return PRRELOAD_PACKAGE.contains(str);
    }

    public static void setCommonPackage(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        commonPackage = str;
        RN_FILE_COMMON_PATH = str;
    }

    public static String getDebugPath(String str) {
        File file = new File(BRNManager.newInstance().getApplication().getFilesDir(), "bundles" + File.separator + "debugs" + File.separator + str);
        if (file.exists()) {
            return file.getPath();
        }
        return null;
    }

    public static String getLoadPath(String str) {
        return getRnPath() + File.separator + GsdkGeckoConfig.getGeckoAdAccessKey() + File.separator + str;
    }

    public static String getModulePath(String str) {
        String debugPath = getDebugPath(str);
        if (TextUtils.isEmpty(debugPath)) {
            String loadPath = getLoadPath(str);
            if (TextUtils.isEmpty(loadPath) || !new File(loadPath).exists()) {
                Context appContext = BRNManager.newInstance().getAppContext();
                loadPath = GsdkGeckoManager.getInstance(appContext).getChannelPath(new File(GsdkGeckoConfig.offlineRootDir(appContext)), str);
            }
            return loadPath == null ? "" : loadPath;
        }
        return debugPath;
    }

    public static void setPrelodPackage(ArrayList<String> arrayList) {
        if (arrayList != null) {
            ArrayList<String> arrayList2 = PRRELOAD_PACKAGE;
            arrayList2.clear();
            arrayList2.addAll(arrayList);
            if (arrayList2.size() > 0) {
                if (!TextUtils.isEmpty(commonPackage)) {
                    if (arrayList2.contains(commonPackage)) {
                        PRELAOD_COMMON = true;
                        PRELAOD_FULL_PACKAGE = false;
                        return;
                    }
                    PRELAOD_COMMON = false;
                    PRELAOD_FULL_PACKAGE = true;
                    return;
                }
                PRELAOD_COMMON = false;
                PRELAOD_FULL_PACKAGE = true;
            }
        }
    }

    public static void perload(String str) {
        perload(str, false);
    }

    public static void perload(String str, boolean z) {
        if (BRNPreloadViewManager.getInstance().needPreload()) {
            BRNPreloadViewManager.getInstance().preloadView(str, null);
            return;
        }
        ArrayList<String> arrayList = PRRELOAD_PACKAGE;
        if (arrayList == null || arrayList.size() <= 0 || TextUtils.isEmpty(str) || !arrayList.contains(str)) {
            return;
        }
        if (str.equals(commonPackage)) {
            if (PRELAOD_COMMON && BUNDLE_MODE == 3) {
                BRNLoaderManager.preloadCommon(BRNManager.newInstance().getApplication(), 1, true, z);
            }
        } else if (TextUtils.isEmpty(commonPackage) && PRELAOD_FULL_PACKAGE) {
            if (BUNDLE_MODE == 1 || BUNDLE_MODE == 2) {
                BRNPreloadFullPack.newInstance().preloadReact(str, BRNManager.newInstance().getApplication(), true, z);
            }
        }
    }
}
