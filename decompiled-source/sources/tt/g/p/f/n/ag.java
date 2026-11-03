package tt.g.p.f.n;

import com.bytedance.gameprotect.IFrameCaptureCallBack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes10.dex */
public final class ag {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ag f21143a;

    /* renamed from: c  reason: collision with root package name */
    private String f21145c;

    /* renamed from: b  reason: collision with root package name */
    private volatile int f21144b = 0;

    /* renamed from: d  reason: collision with root package name */
    private List<IFrameCaptureCallBack> f21146d = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ IFrameCaptureCallBack f21147a;

        a(IFrameCaptureCallBack iFrameCaptureCallBack) {
            this.f21147a = iFrameCaptureCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            ag.a().c(this.f21147a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ IFrameCaptureCallBack f21148a;

        b(IFrameCaptureCallBack iFrameCaptureCallBack) {
            this.f21148a = iFrameCaptureCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            ag.a().a(this.f21148a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f21149a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ int f21150b;

        c(String str, int i2) {
            this.f21149a = str;
            this.f21150b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ag.a().a(this.f21149a, this.f21150b);
        }
    }

    private ag() {
    }

    static /* synthetic */ ag a() {
        return b();
    }

    public static void a(int i2, int i3, String str) {
        if (i2 != 5) {
            return;
        }
        ai.a(new c(str, i3));
    }

    public static void a(IFrameCaptureCallBack iFrameCaptureCallBack, long j2) {
        ai.a(new a(iFrameCaptureCallBack));
        if (j2 != 0) {
            ai.a(new b(iFrameCaptureCallBack), j2);
        }
    }

    private static synchronized ag b() {
        ag agVar;
        synchronized (ag.class) {
            if (f21143a == null) {
                f21143a = new ag();
            }
            agVar = f21143a;
        }
        return agVar;
    }

    private void b(IFrameCaptureCallBack iFrameCaptureCallBack) {
        String str;
        boolean z = true;
        if (this.f21144b == 1) {
            str = this.f21145c;
            z = false;
        } else if (this.f21144b != 2) {
            iFrameCaptureCallBack.onFailure(this.f21145c);
            return;
        } else {
            str = this.f21145c;
        }
        iFrameCaptureCallBack.onSuccess(z, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IFrameCaptureCallBack iFrameCaptureCallBack) {
        synchronized (aa.class) {
            if (this.f21144b == 0) {
                this.f21146d.add(iFrameCaptureCallBack);
            } else {
                b(iFrameCaptureCallBack);
            }
        }
    }

    public void a(IFrameCaptureCallBack iFrameCaptureCallBack) {
        synchronized (ag.class) {
            if (this.f21144b == 0) {
                iFrameCaptureCallBack.onFailure("GP_FrameCapture_Err_TimeOut");
                this.f21146d.remove(iFrameCaptureCallBack);
            }
        }
    }

    public void a(String str, int i2) {
        synchronized (ag.class) {
            this.f21144b = i2;
            this.f21145c = str;
            Iterator<IFrameCaptureCallBack> it = this.f21146d.iterator();
            while (it.hasNext()) {
                b(it.next());
                it.remove();
            }
        }
    }
}
