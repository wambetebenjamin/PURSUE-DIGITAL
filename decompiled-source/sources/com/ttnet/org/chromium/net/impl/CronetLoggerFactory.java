package com.ttnet.org.chromium.net.impl;

import android.content.Context;
import android.os.Build;
import com.ttnet.org.chromium.net.impl.CronetLogger;
/* loaded from: classes9.dex */
public final class CronetLoggerFactory {
    private static final String CRONET_LOGGER_IMPL_CLASS = "com.google.net.cronet.telemetry.CronetLoggerImpl";
    private static final int SAMPLE_RATE_PER_SECOND = 1;
    private static final CronetLogger sDefaultLogger = new NoOpLogger();
    private static CronetLogger sTestingLogger;

    private CronetLoggerFactory() {
    }

    public static CronetLogger createNoOpLogger() {
        return sDefaultLogger;
    }

    public static CronetLogger createLogger(Context context, CronetLogger.CronetSource cronetSource) {
        CronetLogger cronetLogger = sTestingLogger;
        if (cronetLogger != null) {
            return cronetLogger;
        }
        if (!CronetManifest.isAppOptedInForTelemetry(context, cronetSource) || Build.VERSION.SDK_INT < 30) {
            return sDefaultLogger;
        }
        Class<? extends CronetLogger> fetchLoggerImplClass = fetchLoggerImplClass();
        if (fetchLoggerImplClass == null) {
            return sDefaultLogger;
        }
        try {
            return fetchLoggerImplClass.getConstructor(Integer.TYPE).newInstance(1);
        } catch (Exception unused) {
            return sDefaultLogger;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setLoggerForTesting(CronetLogger cronetLogger) {
        sTestingLogger = cronetLogger;
    }

    /* loaded from: classes9.dex */
    public static class SwapLoggerForTesting implements AutoCloseable {
        public SwapLoggerForTesting(CronetLogger cronetLogger) {
            CronetLoggerFactory.setLoggerForTesting(cronetLogger);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            CronetLoggerFactory.setLoggerForTesting(null);
        }
    }

    private static Class<? extends CronetLogger> fetchLoggerImplClass() {
        try {
            return CronetLoggerFactory.class.getClassLoader().loadClass(CRONET_LOGGER_IMPL_CLASS).asSubclass(CronetLogger.class);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
