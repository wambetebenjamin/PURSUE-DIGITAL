package net.openid.appauth.internal;

import android.util.Log;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import net.openid.appauth.Preconditions;
/* loaded from: classes10.dex */
public final class Logger {
    static final String LOG_TAG = "AppAuth";
    private static Logger sInstance;
    private final LogWrapper mLog;
    private final int mLogLevel;

    /* loaded from: classes10.dex */
    public interface LogWrapper {
        String getStackTraceString(Throwable th);

        boolean isLoggable(String str, int i2);

        void println(int i2, String str, String str2);
    }

    public static synchronized Logger getInstance() {
        Logger logger;
        synchronized (Logger.class) {
            if (sInstance == null) {
                sInstance = new Logger(AndroidLogWrapper.INSTANCE);
            }
            logger = sInstance;
        }
        return logger;
    }

    public static synchronized void setInstance(Logger logger) {
        synchronized (Logger.class) {
            sInstance = logger;
        }
    }

    Logger(LogWrapper logWrapper) {
        this.mLog = (LogWrapper) Preconditions.checkNotNull(logWrapper);
        int i2 = 7;
        while (i2 >= 2 && this.mLog.isLoggable(LOG_TAG, i2)) {
            i2--;
        }
        this.mLogLevel = i2 + 1;
    }

    public static void verbose(String str, Object... objArr) {
        getInstance().log(2, null, str, objArr);
    }

    public static void verboseWithStack(Throwable th, String str, Object... objArr) {
        getInstance().log(2, th, str, objArr);
    }

    public static void debug(String str, Object... objArr) {
        getInstance().log(3, null, str, objArr);
    }

    public static void debugWithStack(Throwable th, String str, Object... objArr) {
        getInstance().log(3, th, str, objArr);
    }

    public static void info(String str, Object... objArr) {
        getInstance().log(4, null, str, objArr);
    }

    public static void infoWithStack(Throwable th, String str, Object... objArr) {
        getInstance().log(4, th, str, objArr);
    }

    public static void warn(String str, Object... objArr) {
        getInstance().log(5, null, str, objArr);
    }

    public static void warnWithStack(Throwable th, String str, Object... objArr) {
        getInstance().log(5, th, str, objArr);
    }

    public static void error(String str, Object... objArr) {
        getInstance().log(6, null, str, objArr);
    }

    public static void errorWithStack(Throwable th, String str, Object... objArr) {
        getInstance().log(6, th, str, objArr);
    }

    public void log(int i2, Throwable th, String str, Object... objArr) {
        if (this.mLogLevel > i2) {
            return;
        }
        if (objArr != null && objArr.length >= 1) {
            str = String.format(str, objArr);
        }
        if (th != null) {
            str = str + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + this.mLog.getStackTraceString(th);
        }
        this.mLog.println(i2, LOG_TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class AndroidLogWrapper implements LogWrapper {
        private static final AndroidLogWrapper INSTANCE = new AndroidLogWrapper();

        private AndroidLogWrapper() {
        }

        @Override // net.openid.appauth.internal.Logger.LogWrapper
        public void println(int i2, String str, String str2) {
            Log.println(i2, str, str2);
        }

        @Override // net.openid.appauth.internal.Logger.LogWrapper
        public boolean isLoggable(String str, int i2) {
            return Log.isLoggable(str, i2);
        }

        @Override // net.openid.appauth.internal.Logger.LogWrapper
        public String getStackTraceString(Throwable th) {
            return Log.getStackTraceString(th);
        }
    }
}
