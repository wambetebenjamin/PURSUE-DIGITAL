package com.bytedance.crash;

import android.app.Application;
import android.content.Context;
import com.bytedance.crash.config.i;
import com.bytedance.crash.runtime.ConfigManager;
import com.bytedance.crash.runtime.d;
import com.bytedance.crash.runtime.r;
import java.util.Random;
@Deprecated
/* loaded from: classes2.dex */
public class NpthBus {
    public static final String UUID_SUFIX_3_1_3 = "G";
    private static r l;

    /* renamed from: h  reason: collision with root package name */
    private static final ConfigManager f6091h = new ConfigManager();

    /* renamed from: a  reason: collision with root package name */
    private static AppDataCenter f6090a = new AppDataCenter();
    private static volatile String m = null;
    private static Object n = new Object();

    public static long getAppStartUpTime() {
        return 0L;
    }

    public static ConfigManager getConfigManager() {
        return f6091h;
    }

    public static boolean isLocalDebug() {
        return i.i();
    }

    public static boolean isLocalTest() {
        return i.j();
    }

    public static Context getApplicationContext() {
        return Global.c();
    }

    public static Application getApplication() {
        return Global.d();
    }

    public static void setApplication(Application application) {
        Global.a(application);
    }

    public static long getAppStartTime() {
        return Global.g();
    }

    public static d getCommonParams() {
        return d.a();
    }

    public static AppDataCenter getCallCenter() {
        return f6090a;
    }

    public static String getNativeUUID() {
        if (m == null) {
            synchronized (n) {
                if (m == null) {
                    m = Long.toHexString(new Random().nextLong()) + '-' + getAppStartTime() + UUID_SUFIX_3_1_3;
                }
            }
        }
        return m;
    }

    public static r getSettingManager() {
        if (l == null) {
            synchronized (NpthBus.class) {
                l = new r();
            }
        }
        return l;
    }
}
