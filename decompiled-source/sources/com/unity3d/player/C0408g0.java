package com.unity3d.player;

import android.os.Build;
import com.facebook.internal.AnalyticsEvents;
import java.lang.Thread;
import java.util.Arrays;
/* renamed from: com.unity3d.player.g0  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class C0408g0 implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private volatile Thread.UncaughtExceptionHandler f18979a;

    /* renamed from: b  reason: collision with root package name */
    private String f18980b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(String str) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler instanceof C0408g0) {
            C0408g0 c0408g0 = (C0408g0) defaultUncaughtExceptionHandler;
            int i2 = -1;
            int i3 = -1;
            while (true) {
                int indexOf = str.indexOf(47, i2 + 1);
                if (indexOf == -1) {
                    break;
                }
                i3 = i2;
                i2 = indexOf;
            }
            c0408g0.f18980b = i3 < 0 ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : str.substring(i3 + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == this) {
            return;
        }
        this.f18979a = defaultUncaughtExceptionHandler;
        this.f18980b = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        try {
            Error error = new Error(String.format("FATAL EXCEPTION [%s]\n", thread.getName()) + String.format("Unity version     : %s\n", "2022.3.62f2") + String.format("Device model      : %s %s\n", Build.MANUFACTURER, Build.MODEL) + String.format("Device fingerprint: %s\n", Build.FINGERPRINT) + String.format("CPU supported ABI : %s\n", Arrays.toString(Build.SUPPORTED_ABIS)) + String.format("Build Type        : %s\n", "Release") + String.format("Scripting Backend : %s\n", "IL2CPP") + String.format("Libs loaded from  : %s\n", this.f18980b) + String.format("Strip Engine Code : %s\n", Boolean.TRUE));
            error.setStackTrace(new StackTraceElement[0]);
            error.initCause(th);
            this.f18979a.uncaughtException(thread, error);
        } catch (Throwable unused) {
            this.f18979a.uncaughtException(thread, th);
        }
    }
}
