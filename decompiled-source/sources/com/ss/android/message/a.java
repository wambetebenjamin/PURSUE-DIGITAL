package com.ss.android.message;

import android.app.Application;
import com.bytedance.push.utils.h;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: AppProvider.java */
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile Application f18311a;

    /* renamed from: b  reason: collision with root package name */
    private static final AtomicBoolean f18312b = new AtomicBoolean(false);

    public static void initApp(Application application) {
        if (application == null) {
            h.e("AppProvider", "AppProvider.initApp: application is null！！", new Throwable());
        } else if (f18312b.getAndSet(true)) {
        } else {
            f18311a = application;
            f18311a.registerActivityLifecycleCallbacks(com.bytedance.common.push.b.getIns());
        }
    }

    public static Application getApp() {
        return f18311a;
    }

    @Deprecated
    public static int getTargetSdkVersion() {
        return f18311a.getApplicationInfo().targetSdkVersion;
    }
}
