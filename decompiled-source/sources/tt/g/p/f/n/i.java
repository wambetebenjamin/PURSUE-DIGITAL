package tt.g.p.f.n;

import android.content.Context;
import com.bytedance.gameprotect.IlIlllIiI;
import com.bytedance.gameprotect.UserConfig;
/* loaded from: classes10.dex */
public abstract class i extends Thread {

    /* renamed from: a  reason: collision with root package name */
    protected final UserConfig f21223a;

    /* renamed from: b  reason: collision with root package name */
    protected final Context f21224b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m.a().a(ai.a());
        }
    }

    public i(Context context, UserConfig userConfig) {
        Context applicationContext = context.getApplicationContext();
        this.f21224b = applicationContext;
        this.f21223a = userConfig;
        a(userConfig);
        a(applicationContext, userConfig);
    }

    static void a() {
        d.a();
    }

    static void c() {
        ai.a(new a());
    }

    public abstract void a(Context context, UserConfig userConfig);

    public abstract void a(UserConfig userConfig);

    public abstract void b();

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        a();
        c();
        b();
        j.a(this.f21224b.getAssets());
        IlIlllIiI.a();
    }
}
