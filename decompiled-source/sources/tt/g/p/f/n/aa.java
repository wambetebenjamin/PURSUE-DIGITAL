package tt.g.p.f.n;

import com.bytedance.gameprotect.IEmulatorCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes10.dex */
public class aa {

    /* renamed from: b  reason: collision with root package name */
    private static volatile aa f21124b;

    /* renamed from: c  reason: collision with root package name */
    private String f21126c;

    /* renamed from: a  reason: collision with root package name */
    private volatile int f21125a = 0;

    /* renamed from: d  reason: collision with root package name */
    private List<IEmulatorCallback> f21127d = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ IEmulatorCallback f21128a;

        a(IEmulatorCallback iEmulatorCallback) {
            this.f21128a = iEmulatorCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            aa.a().c(this.f21128a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ IEmulatorCallback f21129a;

        b(IEmulatorCallback iEmulatorCallback) {
            this.f21129a = iEmulatorCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            aa.a().a(this.f21129a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f21130a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ int f21131b;

        c(String str, int i2) {
            this.f21130a = str;
            this.f21131b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            aa.a().a(this.f21130a, this.f21131b);
        }
    }

    private aa() {
    }

    static /* synthetic */ aa a() {
        return b();
    }

    public static void a(int i2, int i3, String str) {
        if (i2 != 1) {
            return;
        }
        ai.a(new c(str, i3));
    }

    public static void a(IEmulatorCallback iEmulatorCallback, long j2) {
        ai.a(new a(iEmulatorCallback));
        if (j2 != 0) {
            ai.a(new b(iEmulatorCallback), j2);
        }
    }

    private static synchronized aa b() {
        aa aaVar;
        synchronized (aa.class) {
            if (f21124b == null) {
                f21124b = new aa();
            }
            aaVar = f21124b;
        }
        return aaVar;
    }

    private void b(IEmulatorCallback iEmulatorCallback) {
        String str;
        boolean z = true;
        if (this.f21125a == 1) {
            str = this.f21126c;
            z = false;
        } else if (this.f21125a != 2) {
            iEmulatorCallback.OnFailure(this.f21126c);
            return;
        } else {
            str = this.f21126c;
        }
        iEmulatorCallback.OnSuccess(z, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IEmulatorCallback iEmulatorCallback) {
        synchronized (aa.class) {
            if (this.f21125a == 0) {
                this.f21127d.add(iEmulatorCallback);
            } else {
                b(iEmulatorCallback);
            }
        }
    }

    public void a(IEmulatorCallback iEmulatorCallback) {
        synchronized (aa.class) {
            if (this.f21125a == 0) {
                iEmulatorCallback.OnFailure("GP_Emulator_Err_TimeOut");
                this.f21127d.remove(iEmulatorCallback);
            }
        }
    }

    public void a(String str, int i2) {
        synchronized (aa.class) {
            this.f21125a = i2;
            this.f21126c = str;
            Iterator<IEmulatorCallback> it = this.f21127d.iterator();
            while (it.hasNext()) {
                b(it.next());
                it.remove();
            }
        }
    }
}
