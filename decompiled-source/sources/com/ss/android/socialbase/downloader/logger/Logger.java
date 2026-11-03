package com.ss.android.socialbase.downloader.logger;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.librarian.c;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.ss.android.socialbase.downloader.impls.DownloadProxy;
/* loaded from: classes9.dex */
public class Logger {
    public static final boolean DEBUG = false;
    private static final String DOWNLOAD_TAG_PREFIX = "Downloader-";
    private static final String TAG = "DownloaderLogger";
    private static int mLevel = 4;
    private static ILogWritter sLogWritter;

    /* loaded from: classes9.dex */
    public static abstract class ILogWritter {
        public void logD(String str, String str2) {
        }

        public void logE(String str, String str2) {
        }

        public void logE(String str, String str2, Throwable th) {
        }

        public void logI(String str, String str2) {
        }

        public void logI(String str, String str2, Throwable th) {
        }

        public void logK(String str, String str2) {
        }

        public void logV(String str, String str2) {
        }

        public void logW(String str, String str2) {
        }

        public void logW(String str, String str2, Throwable th) {
        }
    }

    public static void setLogLevel(int i2) {
        mLevel = i2;
    }

    public static void setLogLevelInDownloaderProcess(int i2) {
        if (DownloadProxy.get(true) != null) {
            mLevel = i2;
        }
    }

    public static int getLogLevel() {
        return mLevel;
    }

    public static boolean debug() {
        return mLevel <= 3;
    }

    public static void v(String str) {
        v(TAG, str);
    }

    public static void v(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 2) {
            Log.v(str, str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logV(downloaderTag(str), str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 2) {
            Log.v(str, str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logV(downloaderTag(str), str2 + th);
        }
    }

    public static void d(String str) {
        d(TAG, str);
    }

    public static String downloaderTag(String str) {
        return !TextUtils.isEmpty(str) ? DOWNLOAD_TAG_PREFIX + str : TAG;
    }

    public static void d(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 3) {
            Log.d(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logD(downloaderTag(str), str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 3) {
            Log.d(downloaderTag(str), str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logD(downloaderTag(str), str2 + th);
        }
    }

    public static void i(String str) {
        i(TAG, str);
    }

    public static void i(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 4) {
            Log.i(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logI(downloaderTag(str), str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 4) {
            Log.i(downloaderTag(str), str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logI(downloaderTag(str), str2, th);
        }
    }

    public static void w(String str) {
        w(TAG, str);
    }

    public static void w(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 5) {
            Log.w(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logW(downloaderTag(str), str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 5) {
            Log.w(downloaderTag(str), str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logW(downloaderTag(str), str2, th);
        }
    }

    public static void e(String str) {
        e(TAG, str);
    }

    public static void e(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 6) {
            Log.e(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logE(downloaderTag(str), str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 6) {
            Log.e(downloaderTag(str), str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logE(downloaderTag(str), str2, th);
        }
    }

    public static void k(String str) {
        k(TAG, str);
    }

    public static void k(String str, String str2) {
        if (mLevel <= 3) {
            Log.d(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logK(downloaderTag(str), str2);
        }
    }

    public static void st(String str, int i2) {
        try {
            throw new Exception();
        } catch (Exception e2) {
            StackTraceElement[] stackTrace = e2.getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (int i3 = 1; i3 < Math.min(i2, stackTrace.length); i3++) {
                if (i3 > 1) {
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                sb.append(getSimpleClassName(stackTrace[i3].getClassName()));
                sb.append(c.a.DOT);
                sb.append(stackTrace[i3].getMethodName());
            }
            v(downloaderTag(str), sb.toString());
        }
    }

    private static String getSimpleClassName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf < 0 ? str : str.substring(lastIndexOf + 1);
    }

    public static void throwException(Throwable th) {
        if (th == null) {
            return;
        }
        th.printStackTrace();
        if (debug()) {
            throw new RuntimeException("Error! Now in debug, we alert to you to correct it !", th);
        }
    }

    public static void alertErrorInfo(String str) {
        if (debug()) {
            throw new IllegalStateException(str);
        }
    }

    public static void registerLogHandler(ILogWritter iLogWritter) {
        sLogWritter = iLogWritter;
    }
}
