package com.ss.android.ugc.quota.launch;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import com.ss.android.ugc.quota.BDNetworkTagManager;
/* compiled from: BDNetworkLaunchMonitor.java */
/* loaded from: classes9.dex */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18736a = "BDNetworkLaunchMonitor";

    /* renamed from: b  reason: collision with root package name */
    private int f18737b = 0;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18738c = false;

    /* renamed from: d  reason: collision with root package name */
    private boolean f18739d = true;

    /* renamed from: e  reason: collision with root package name */
    private int f18740e = com.ss.android.ugc.quota.tag.a.UNSPECIFIED;

    static /* synthetic */ int b(a aVar) {
        int i2 = aVar.f18737b;
        aVar.f18737b = i2 + 1;
        return i2;
    }

    static /* synthetic */ int e(a aVar) {
        int i2 = aVar.f18737b;
        aVar.f18737b = i2 - 1;
        return i2;
    }

    public a(Application application) {
        if (application == null) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.ss.android.ugc.quota.launch.a.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            /* JADX WARN: Type inference failed for: r5v1 */
            /* JADX WARN: Type inference failed for: r5v2, types: [boolean, int] */
            /* JADX WARN: Type inference failed for: r5v3 */
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ?? r5 = bundle != null ? 1 : 0;
                Log.d(a.f18736a, "onActivityCreated, Activity:" + activity + ",firstLaunch:" + a.this.f18739d + ",restore:" + ((boolean) r5));
                if (a.this.f18739d) {
                    a.this.f18739d = false;
                    a.this.a((int) r5);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                a.this.a("onActivityStarted enter", activity);
                a.b(a.this);
                if (a.this.f18737b == 1) {
                    if (a.this.f18738c) {
                        a.this.a(2);
                    }
                    a.this.f18738c = false;
                }
                a.this.a("onActivityStarted exit", activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                a.this.a("onActivityStopped enter", activity);
                a.e(a.this);
                if (a.this.f18737b == 0) {
                    a.this.f18738c = true;
                }
                a.this.a("onActivityStopped exit", activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        if (this.f18740e == i2) {
            return;
        }
        Log.d(f18736a, "Current launch type is " + i2);
        this.f18740e = i2;
        BDNetworkTagManager.getInstance().updateLaunchType(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Activity activity) {
        Log.d(f18736a, str + ", Activity:" + activity + ",fgActivityCount: " + this.f18737b + ",appInBackground: " + this.f18738c);
    }

    @Override // com.ss.android.ugc.quota.launch.b
    public int currentLaunchType() {
        return this.f18740e;
    }
}
