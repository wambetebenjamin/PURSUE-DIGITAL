package com.oasis.Logger;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.oasis.android.R;
import com.oasis.android.e;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes9.dex */
public class Logger {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f17328a = false;

    /* renamed from: b  reason: collision with root package name */
    private static final SimpleDateFormat f17329b = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);

    /* renamed from: c  reason: collision with root package name */
    private static final SimpleDateFormat f17330c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);

    /* renamed from: d  reason: collision with root package name */
    private static int f17331d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static int f17332e = 1024;

    /* renamed from: f  reason: collision with root package name */
    private static FileWriter f17333f;

    /* renamed from: g  reason: collision with root package name */
    private static File f17334g;

    /* renamed from: h  reason: collision with root package name */
    private static int f17335h;

    /* renamed from: i  reason: collision with root package name */
    private static int f17336i;

    /* renamed from: j  reason: collision with root package name */
    private static String f17337j;
    private static WeakReference<Context> k;

    private static void a() {
        int i2 = f17332e;
        if (i2 != 0 && f17331d >= i2) {
            FileWriter fileWriter = f17333f;
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e2) {
                    Log.e("Logger", "close logFile '" + f17334g.getAbsolutePath() + "' has an exception: " + e2);
                    e2.printStackTrace();
                }
            }
            b();
        }
    }

    private static void a(String str, String str2, String str3) {
        String format = f17330c.format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(format).append(' ').append(str).append('/').append(str2).append(": ").append(str3).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        FileWriter fileWriter = f17333f;
        if (fileWriter != null) {
            try {
                fileWriter.write(sb.toString());
                f17333f.flush();
                f17331d++;
            } catch (IOException e2) {
                Log.e("Logger", "write&flush logFile '" + f17334g.getAbsolutePath() + "' has an exception: " + e2);
                e2.printStackTrace();
            }
            a();
        }
    }

    private static boolean a(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    private static void b() {
        String str;
        f17331d = 0;
        if (k.get() == null) {
            str = "error: context is null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
        } else {
            File externalFilesDir = k.get().getExternalFilesDir("OasisNativeLogs");
            if (externalFilesDir != null) {
                String absolutePath = externalFilesDir.getAbsolutePath();
                String str2 = File.separator;
                if (!absolutePath.endsWith(str2)) {
                    absolutePath = absolutePath + str2;
                }
                Log.d("Logger", "logFilePath is " + absolutePath);
                if (!externalFilesDir.exists()) {
                    Log.d("Logger", externalFilesDir.mkdirs() ? "create Oasis_Native_Logs success" : "create Oasis_Native_logs failed");
                }
                String str3 = f17329b.format(new Date()) + "_" + f17337j.replace(CertificateUtil.DELIMITER, "_") + "_" + f17335h;
                if (f17336i != 0) {
                    str3 = str3 + "-" + f17336i;
                }
                f17336i++;
                String str4 = str3 + ".log";
                File file = new File(absolutePath, str4);
                f17334g = file;
                if (file.exists()) {
                    f17334g = new File(absolutePath, str4);
                }
                try {
                    if (f17334g.createNewFile()) {
                        Log.d("Logger", "create logFile '" + f17334g.getCanonicalPath() + "'success");
                    } else {
                        Log.e("Logger", "create logFile '" + f17334g.getCanonicalPath() + "'failed");
                    }
                } catch (IOException e2) {
                    Log.e("Logger", "create logFile '" + f17334g.getAbsolutePath() + "' has an exception: " + e2);
                    e2.printStackTrace();
                }
                try {
                    f17333f = new FileWriter(f17334g, true);
                    return;
                } catch (Exception e3) {
                    Log.e("Logger", "write logFile '" + f17334g.getAbsolutePath() + "' has an exception: " + e3);
                    e3.printStackTrace();
                    return;
                }
            }
            str = "getExternalFilesDir(\"OasisNativeLogs\") failed!";
        }
        Log.e("Logger", str);
    }

    private static boolean b(Context context) {
        boolean z;
        try {
            z = context.getResources().getBoolean(R.bool.enable_logger_file);
        } catch (Exception e2) {
            e2.printStackTrace();
            z = false;
        }
        Log.i("Logger", z ? "initialize with logger file." : "initialize no logger file.");
        return z;
    }

    public static void d(String str, String str2) {
        if (f17328a) {
            Log.d(str, str2);
        }
        if (f17333f != null) {
            a("D", str, str2);
        }
    }

    public static void destroy() {
        f17328a = false;
        f17332e = 1000;
        k = null;
        f17334g = null;
        try {
            f17333f.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
        if (f17333f != null) {
            a(ExifInterface.LONGITUDE_EAST, str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
        if (f17333f != null) {
            a(ExifInterface.LONGITUDE_EAST, str, str2);
        }
    }

    public static boolean getLoggerEnable() {
        Log.i("Logger", "getNativeLogState: " + f17328a);
        return f17328a;
    }

    public static void i(String str, String str2) {
        if (f17328a) {
            Log.i(str, str2);
        }
        if (f17333f != null) {
            a("I", str, str2);
        }
    }

    public static void initialize(Context context) {
        k = new WeakReference<>(context);
        f17335h = Process.myPid();
        String a2 = e.a(context);
        f17337j = a2;
        if (a2 == null) {
            Log.w("Logger", "warning: get process name fault, now use empty string as process name!!!!!!!!!!!");
            f17337j = "";
        }
        boolean z = false;
        f17328a = false;
        f17336i = 0;
        try {
            f17328a = context.getResources().getBoolean(R.bool.openLog);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        z = (f17328a || a(context)) ? true : true;
        f17328a = z;
        Log.i("Logger", z ? "initialize in debug" : "initialize in release");
        if (b(context)) {
            b();
        }
    }

    public static void setLoggerEnable(boolean z) {
        Log.i("Logger", "setNativeLogState:" + z);
        f17328a = z;
    }

    public static void v(String str, String str2) {
        if (f17328a) {
            Log.v(str, str2);
        }
        if (f17333f != null) {
            a(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, str, str2);
        }
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
        if (f17333f != null) {
            a(ExifInterface.LONGITUDE_WEST, str, str2);
        }
    }
}
