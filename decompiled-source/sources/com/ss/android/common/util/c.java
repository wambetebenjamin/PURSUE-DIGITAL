package com.ss.android.common.util;

import android.util.Log;
/* compiled from: TeaLog.java */
/* loaded from: classes9.dex */
public class c {

    /* compiled from: TeaLog.java */
    /* loaded from: classes9.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final String f18097a = "TeaLog_Task";
        public static boolean isSwitchOn = false;

        public static void w(String str) {
            if (isSwitchOn) {
                Log.w(f18097a, str);
            }
        }

        public static void i(String str) {
            if (isSwitchOn) {
                Log.i(f18097a, str);
            }
        }

        public static void d(String str) {
            if (isSwitchOn) {
                Log.d(f18097a, str);
            }
        }
    }
}
