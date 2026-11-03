package com.bytedance.common.jato.crashhook;

import android.os.Looper;
import com.bytedance.common.jato.crashhook.a;
/* compiled from: DefaultCrashHookListener.java */
/* loaded from: classes.dex */
public class b implements a.InterfaceC0057a {

    /* renamed from: a  reason: collision with root package name */
    long f4649a;

    @Override // com.bytedance.common.jato.crashhook.a.InterfaceC0057a
    public boolean a(Thread thread, Throwable th) {
        if (Looper.getMainLooper().getThread() != thread && System.currentTimeMillis() - this.f4649a >= 10000) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (stackTraceElement.toString().startsWith("java.util.concurrent.ThreadPoolExecutor$Worker.run")) {
                    this.f4649a = System.currentTimeMillis();
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
