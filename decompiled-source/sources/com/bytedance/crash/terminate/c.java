package com.bytedance.crash.terminate;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import android.os.Build;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.crash.Global;
import com.facebook.login.widget.ToolTipPopup;
import java.util.Comparator;
import java.util.List;
/* compiled from: AppExitInfoUtils.java */
/* loaded from: classes2.dex */
public final class c {
    private static final int A = 9;
    private static final int B = 10;
    private static final int C = 11;
    private static final int D = 12;
    private static final int E = 13;
    private static final int F = 14;
    private static final int G = 15;
    private static final int H = 16;
    private static final int I = 17;

    /* renamed from: J  reason: collision with root package name */
    private static final int f7101J = 18;
    private static final int K = 19;
    private static final int L = 20;
    private static final int M = 21;
    private static final int N = 22;
    private static final int O = 23;
    private static final int P = 24;
    private static final int Q = 25;
    private static volatile boolean R = false;
    private static volatile Object S = new Object();
    private static List<ApplicationExitInfo> T = null;

    /* renamed from: a  reason: collision with root package name */
    public static final int f7102a = 1024;

    /* renamed from: b  reason: collision with root package name */
    public static final String f7103b = "app_exit";

    /* renamed from: c  reason: collision with root package name */
    private static final int f7104c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static final int f7105d = 1;

    /* renamed from: e  reason: collision with root package name */
    private static final int f7106e = 2;

    /* renamed from: f  reason: collision with root package name */
    private static final int f7107f = 3;

    /* renamed from: g  reason: collision with root package name */
    private static final int f7108g = 4;

    /* renamed from: h  reason: collision with root package name */
    private static final int f7109h = 5;

    /* renamed from: i  reason: collision with root package name */
    private static final int f7110i = 6;

    /* renamed from: j  reason: collision with root package name */
    private static final int f7111j = 7;
    private static final int k = 8;
    private static final int l = 9;
    private static final int m = 10;
    private static final int n = 11;
    private static final int o = 12;
    private static final int p = 13;
    private static final int q = 14;
    private static final int r = 0;
    private static final int s = 1;
    private static final int t = 2;
    private static final int u = 3;
    private static final int v = 4;
    private static final int w = 5;
    private static final int x = 6;
    private static final int y = 7;
    private static final int z = 8;

    public static String a(int i2) {
        switch (i2) {
            case 1:
                return "EXIT_SELF";
            case 2:
                return "SIGNALED";
            case 3:
                return "LOW_MEMORY";
            case 4:
                return "APP CRASH(EXCEPTION)";
            case 5:
                return "APP CRASH(NATIVE)";
            case 6:
                return "ANR";
            case 7:
                return "INITIALIZATION FAILURE";
            case 8:
                return "PERMISSION CHANGE";
            case 9:
                return "EXCESSIVE RESOURCE USAGE";
            case 10:
                return "USER REQUESTED";
            case 11:
                return "USER STOPPED";
            case 12:
                return "DEPENDENCY DIED";
            case 13:
                return "OTHER KILLS BY SYSTEM";
            case 14:
                return "FREEZER";
            default:
                return "UNKNOWN";
        }
    }

    public static String b(int i2) {
        switch (i2) {
            case 1:
                return "WAIT FOR DEBUGGER";
            case 2:
                return "TOO MANY CACHED PROCS";
            case 3:
                return "TOO MANY EMPTY PROCS";
            case 4:
                return "TRIM EMPTY";
            case 5:
                return "LARGE CACHED";
            case 6:
                return "MEMORY PRESSURE";
            case 7:
                return "EXCESSIVE CPU USAGE";
            case 8:
                return "SYSTEM UPDATE_DONE";
            case 9:
                return "KILL ALL FG";
            case 10:
                return "KILL ALL BG EXCEPT";
            case 11:
                return "KILL UID";
            case 12:
                return "KILL PID";
            case 13:
                return "INVALID START";
            case 14:
                return "INVALID STATE";
            case 15:
                return "IMPERCEPTIBLE";
            case 16:
                return "REMOVE LRU";
            case 17:
                return "ISOLATED NOT NEEDED";
            case 18:
                return "CACHED IDLE FORCED APP STANDBY";
            case 19:
                return "FREEZER BINDER IOCTL";
            case 20:
                return "FREEZER BINDER TRANSACTION";
            case 21:
                return "FORCE STOP";
            case 22:
                return "REMOVE TASK";
            case 23:
                return "STOP APP";
            case 24:
                return "KILL BACKGROUND";
            case 25:
                return "PACKAGE UPDATE";
            default:
                return "UNKNOWN";
        }
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 30;
    }

    public static List<ApplicationExitInfo> b() {
        List<ApplicationExitInfo> c2 = c();
        if (c2 == null) {
            return null;
        }
        return c2;
    }

    public static List<ApplicationExitInfo> c() {
        Context c2;
        ActivityManager activityManager;
        if (!a() || (c2 = Global.c()) == null || (activityManager = (ActivityManager) c2.getSystemService("activity")) == null) {
            return null;
        }
        if (R) {
            return T;
        }
        synchronized (S) {
            if (!R) {
                List<ApplicationExitInfo> historicalProcessExitReasons = activityManager.getHistoricalProcessExitReasons(c2.getPackageName(), 0, 0);
                T = historicalProcessExitReasons;
                historicalProcessExitReasons.sort(new Comparator<ApplicationExitInfo>() { // from class: com.bytedance.crash.terminate.c.1
                    @Override // java.util.Comparator
                    /* renamed from: a */
                    public int compare(ApplicationExitInfo applicationExitInfo, ApplicationExitInfo applicationExitInfo2) {
                        int i2 = ((applicationExitInfo2.getTimestamp() - applicationExitInfo.getTimestamp()) > 0L ? 1 : ((applicationExitInfo2.getTimestamp() - applicationExitInfo.getTimestamp()) == 0L ? 0 : -1));
                        if (i2 > 0) {
                            return 1;
                        }
                        return i2 == 0 ? 0 : -1;
                    }
                });
            }
            R = true;
        }
        return T;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(long j2) {
        try {
            long j3 = j2 / 1024;
            if (j3 >= 1) {
                if (j3 <= 30) {
                    return "[1~30MB]";
                }
                if (j3 <= 60) {
                    return "(30~100MB]";
                }
                if (j3 <= 100) {
                    return "(60~100MB]";
                }
                if (j3 <= 200) {
                    return "(100~150MB]";
                }
                if (j3 <= 300) {
                    return "(200~300MB]";
                }
                if (j3 <= 400) {
                    return "(300~400MB]";
                }
                if (j3 <= 500) {
                    return "(400~500MB]";
                }
                int i2 = (j3 > 600L ? 1 : (j3 == 600L ? 0 : -1));
                return i2 <= 0 ? "(500~600MB]" : (i2 <= 0 || j3 > 700) ? j3 <= 800 ? "(700~800MB]" : j3 <= 900 ? "(800~900MB]" : j3 <= 1000 ? "(900~1000MB]" : j3 <= 1500 ? "(1GB~1.5GB]" : j3 <= 2000 ? "(1.5GB~2GB]" : j3 <= WsConstants.EXIT_DELAY_TIME ? "(2GB~3GB]" : j3 <= 4000 ? "(3GB~4GB]" : j3 <= ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME ? "(4GB~6GB]" : j3 <= 8000 ? "(6GB~8GB]" : ">8G" : "(600~700MB]";
            }
            return "<1MB";
        } catch (Throwable unused) {
            return "invalid";
        }
    }

    static String a(String str) {
        if (str != null) {
            try {
                if (!"0.00".equals(str) && !"0".equals(str)) {
                    Long valueOf = Long.valueOf(Long.parseLong(str.substring(0, str.indexOf("MB"))));
                    return valueOf.longValue() >= 1 ? valueOf.longValue() <= 30 ? "[1~30MB]" : valueOf.longValue() <= 60 ? "(30~100MB]" : valueOf.longValue() <= 100 ? "(60~100MB]" : valueOf.longValue() <= 200 ? "(100~150MB]" : valueOf.longValue() <= 300 ? "(200~300MB]" : valueOf.longValue() <= 400 ? "(300~400MB]" : valueOf.longValue() <= 500 ? "(400~500MB]" : valueOf.longValue() <= 600 ? "(500~600MB]" : valueOf.longValue() <= 700 ? "(600~700MB]" : valueOf.longValue() <= 800 ? "(700~800MB]" : valueOf.longValue() <= 900 ? "(800~900MB]" : valueOf.longValue() <= 1000 ? "(900~1000MB]" : valueOf.longValue() <= 1500 ? "(1GB~1.5GB]" : valueOf.longValue() <= 2000 ? "(1.5GB~2GB]" : valueOf.longValue() <= WsConstants.EXIT_DELAY_TIME ? "(2GB~3GB]" : valueOf.longValue() <= 4000 ? "(3GB~4GB]" : valueOf.longValue() <= ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME ? "(4GB~6GB]" : valueOf.longValue() <= 8000 ? "(6GB~8GB]" : ">8G" : "< 1MB";
                }
                return "not obtained";
            } catch (Throwable unused) {
                return "invalid";
            }
        }
        return "not obtained";
    }
}
