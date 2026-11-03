package com.oasis.android;

import android.os.Handler;
import android.os.Looper;
import com.oasis.Logger.Logger;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class CrashTrigger {

    /* loaded from: classes9.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(10000L);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void a() {
        a();
    }

    public static void triggerANR() {
        Logger.w("CrashTrigger", "trigger application not responding");
        new Handler(Looper.getMainLooper()).post(new a());
    }

    public static void triggerNullPointer() {
        Logger.w("CrashTrigger", "triggerNullPointer");
        throw null;
    }

    public static void triggerOOM() {
        Logger.w("CrashTrigger", "trigger out of memory");
        while (true) {
            new ArrayList().add(1);
        }
    }

    public static void triggerRuntimeException() {
        Logger.w("CrashTrigger", "trigger runtime exception");
        throw new RuntimeException("dummy runtime exception!");
    }

    public static void triggerStackOverflow() {
        Logger.w("CrashTrigger", "trigger stack overflow");
        a();
    }
}
