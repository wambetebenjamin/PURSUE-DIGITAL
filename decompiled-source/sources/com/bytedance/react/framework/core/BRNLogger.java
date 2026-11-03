package com.bytedance.react.framework.core;

import com.bytedance.react.framework.core.BRNManager;
/* loaded from: classes2.dex */
public class BRNLogger {
    public static void e(String str, String str2) {
        BRNManager.LoggerHelper logger = BRNManager.newInstance().getLogger();
        if (logger == null) {
            return;
        }
        logger.e(str, str2);
    }

    public static void d(String str, String str2) {
        BRNManager.LoggerHelper logger = BRNManager.newInstance().getLogger();
        if (logger == null) {
            return;
        }
        logger.d(str, str2);
        logger.d(str, str2);
    }

    public static void i(String str, String str2) {
        BRNManager.LoggerHelper logger = BRNManager.newInstance().getLogger();
        if (logger == null) {
            return;
        }
        logger.i(str, str2);
    }
}
