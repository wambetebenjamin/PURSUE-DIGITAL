package com.bytedance.common.wschannel;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.collection.WeakHandler;
/* compiled from: LifeCycleMonitor.java */
/* loaded from: classes2.dex */
class e implements Application.ActivityLifecycleCallbacks {

    /* renamed from: b  reason: collision with root package name */
    private static WeakHandler f5612b = new WeakHandler(Looper.getMainLooper(), new WeakHandler.IHandler() { // from class: com.bytedance.common.wschannel.e.1
        @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
        public void handleMsg(Message message) {
        }
    });

    /* renamed from: c  reason: collision with root package name */
    private a f5614c;

    /* renamed from: a  reason: collision with root package name */
    private boolean f5613a = false;

    /* renamed from: d  reason: collision with root package name */
    private Runnable f5615d = new Runnable() { // from class: com.bytedance.common.wschannel.e.2
        @Override // java.lang.Runnable
        public void run() {
            if (Logger.debug()) {
                Logger.d("WsChannelSdk", "sRunnable AppAlive = " + e.this.f5613a);
            }
            if (e.this.f5613a) {
                e.this.f5613a = false;
                if (e.this.f5614c != null) {
                    e.this.f5614c.b();
                }
            }
        }
    };

    /* compiled from: LifeCycleMonitor.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a();

        void b();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (!this.f5613a) {
            this.f5613a = true;
            a aVar = this.f5614c;
            if (aVar != null) {
                aVar.a();
            }
        }
        if (Logger.debug()) {
            Logger.d("WsChannelSdk", "onResume sAppAlive = " + this.f5613a);
        }
        f5612b.removeCallbacks(this.f5615d);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (this.f5613a) {
            f5612b.postDelayed(this.f5615d, WsConstants.EXIT_DELAY_TIME);
        }
    }

    public void a(a aVar) {
        this.f5614c = aVar;
    }
}
