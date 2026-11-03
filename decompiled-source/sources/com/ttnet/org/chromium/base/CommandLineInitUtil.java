package com.ttnet.org.chromium.base;

import android.content.Context;
import android.provider.Settings;
import com.ttnet.org.chromium.base.supplier.Supplier;
import java.io.File;
/* loaded from: classes9.dex */
public final class CommandLineInitUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String COMMAND_LINE_FILE_PATH = "/data/local";
    private static final String COMMAND_LINE_FILE_PATH_DEBUG_APP = "/data/local/tmp";

    private CommandLineInitUtil() {
    }

    public static void initCommandLine(String str) {
        initCommandLine(str, null);
    }

    public static void initCommandLine(String str, Supplier<Boolean> supplier) {
        File file = new File(COMMAND_LINE_FILE_PATH_DEBUG_APP, str);
        if (!file.exists() || !shouldUseDebugCommandLine(supplier)) {
            file = new File(COMMAND_LINE_FILE_PATH, str);
        }
        CommandLine.initFromFile(file.getPath());
    }

    private static boolean shouldUseDebugCommandLine(Supplier<Boolean> supplier) {
        if (supplier == null || !supplier.get().booleanValue()) {
            Context applicationContext = ContextUtils.getApplicationContext();
            return applicationContext.getPackageName().equals(getDebugApp(applicationContext)) || BuildInfo.isDebugAndroid();
        }
        return true;
    }

    private static String getDebugApp(Context context) {
        if (Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) == 1) {
            return Settings.Global.getString(context.getContentResolver(), "debug_app");
        }
        return null;
    }
}
