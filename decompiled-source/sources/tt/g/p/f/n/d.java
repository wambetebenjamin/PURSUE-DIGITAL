package tt.g.p.f.n;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.bytedance.gameprotect.IlIlllIiI;
/* loaded from: classes10.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static int f21218a;

    /* renamed from: b  reason: collision with root package name */
    private static Application.ActivityLifecycleCallbacks f21219b = new a();

    /* loaded from: classes10.dex */
    class a implements Application.ActivityLifecycleCallbacks {
        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (d.f21218a < 1) {
                int unused = d.f21218a = 1;
            }
            d.d();
            if (d.f21218a == 0) {
                IlIlllIiI.a(0);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            d.b();
            if (d.f21218a == 1) {
                IlIlllIiI.a(1);
            }
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
    }

    public static void a() {
        ((Application) ai.a().getApplicationContext()).registerActivityLifecycleCallbacks(f21219b);
    }

    static /* synthetic */ int b() {
        int i2 = f21218a;
        f21218a = i2 + 1;
        return i2;
    }

    static /* synthetic */ int d() {
        int i2 = f21218a;
        f21218a = i2 - 1;
        return i2;
    }
}
