package com.bytedance.common.jato.interpreter;

import android.os.Build;
import android.util.Log;
import com.bytedance.common.jato.c;
/* loaded from: classes.dex */
public class InterpreterBridge {

    /* renamed from: a  reason: collision with root package name */
    private static String f4756a = "InterpreterBridge";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f4757b;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f4758c;

    /* renamed from: d  reason: collision with root package name */
    private static c f4759d;

    private static native int initInterpreterBridgeInternal(int i2);

    private static boolean b() {
        return f4757b && !f4758c;
    }

    public static void a() {
        if (Build.VERSION.SDK_INT > 33) {
            Log.d(f4756a, "InterpreterBridge support less than android 13");
        } else if (Build.VERSION.SDK_INT < 24) {
            Log.d(f4756a, "InterpreterBridge support more than android 7");
        } else if (f4757b || f4758c) {
        } else {
            try {
                int initInterpreterBridgeInternal = initInterpreterBridgeInternal(Build.VERSION.SDK_INT);
                if (initInterpreterBridgeInternal != 0) {
                    c cVar = f4759d;
                    if (cVar != null) {
                        cVar.onDebugInfo("InterpreterBridge init failed:" + initInterpreterBridgeInternal);
                    }
                    f4758c = true;
                }
                f4757b = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(c cVar) {
        f4759d = cVar;
    }
}
