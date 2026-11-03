package tt.g.p.f.n;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.bytedance.gameprotect.IlIlllIiI;
import com.ss.cgpa.NativeDataHelp;
import java.io.File;
/* loaded from: classes10.dex */
public class ai {

    /* renamed from: a  reason: collision with root package name */
    private static Context f21151a;

    /* renamed from: b  reason: collision with root package name */
    private static Handler f21152b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k.a(ai.f21151a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ss.cgpa.c.h().b(ai.f21151a.getPackageName());
        }
    }

    public static Context a() {
        return f21151a;
    }

    private static void a(int i2, String str) {
        IlIlllIiI.a(i2, str);
    }

    public static void a(Context context, Boolean bool) {
        f21151a = context.getApplicationContext();
        f21152b = new Handler(Looper.getMainLooper());
        b();
        e();
        if (bool.booleanValue()) {
            d();
        }
    }

    @Deprecated
    public static void a(Runnable runnable) {
        if (f()) {
            runnable.run();
        } else {
            f21152b.post(runnable);
        }
    }

    @Deprecated
    public static void a(Runnable runnable, long j2) {
        f21152b.postDelayed(runnable, j2);
    }

    public static void b() {
        File externalFilesDir;
        ApplicationInfo applicationInfo = f21151a.getApplicationInfo();
        a(2, tt.g.p.f.n.a.b(f21151a));
        a(3, applicationInfo.nativeLibraryDir);
        a(4, applicationInfo.sourceDir);
        a(5, applicationInfo.packageName);
        a(9, applicationInfo.dataDir);
        int i2 = Build.VERSION.SDK_INT;
        a(10, String.valueOf(i2));
        a(11, k.a());
        a(6, tt.g.p.f.n.a.d(f21151a));
        a(7, tt.g.p.f.n.a.a(f21151a));
        a(8, String.valueOf(tt.g.p.f.n.a.c(f21151a)));
        a(13, tt.g.p.f.n.a.e(f21151a));
        a(14, tt.g.p.f.n.a.f(f21151a));
        a(15, tt.g.p.f.n.a.g(f21151a));
        if ((i2 >= 19 || w.a()) && (externalFilesDir = f21151a.getExternalFilesDir(null)) != null) {
            a(12, externalFilesDir.getPath());
        }
    }

    private static void d() {
        try {
            new Thread(new a()).start();
        } catch (Exception unused) {
            new NativeDataHelp().stopReport();
        }
    }

    private static void e() {
        try {
            f21151a.getPackageManager().getPackageInfo("com.bytedance.gameprotect.profiler", 0);
            new Thread(new b()).start();
        } catch (Exception unused) {
            new NativeDataHelp().stopReport();
        }
    }

    private static boolean f() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
