package com.bytedance.react.framework.window;

import com.bytedance.react.framework.RNConfig;
import java.io.File;
/* loaded from: classes2.dex */
public class BRNConstants {
    public static final String INTENT_BUNDLE = "rctModuleParams";
    public static final String INTENT_EXTRA = "rctModuleName";
    public static final String RN_BUNDLE_END = ".bundle";
    public static final String RN_ASSETS_RELEASE_PREFIX = "bundles" + File.separator;
    public static final String RN_ASSETS_DEBUG = RNConfig.DEBUG_RN_BUNDLE_DIR_NAME + File.separator + "index.bundle";
    public static final String RN_COMMON_BUNDLE_RELATIVE_PATH = "common" + File.separator + RNConfig.COMMON_INDEX;
}
