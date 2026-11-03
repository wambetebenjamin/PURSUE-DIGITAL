package gsdk.impl.webview.DEFAULT;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.bytedance.ttgame.module.webview.aidl.service.mainproaidl.MainProAidlService;
import gsdk.impl.webview.DEFAULT.ah;
import java.util.concurrent.CountDownLatch;
import timber.log.Timber;
/* compiled from: RemoteWebViewBinderPool.java */
/* loaded from: classes10.dex */
public class as {

    /* renamed from: a  reason: collision with root package name */
    public static final int f20902a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static final int f20903b = 2;

    /* renamed from: c  reason: collision with root package name */
    public static final int f20904c = 3;

    /* renamed from: d  reason: collision with root package name */
    private static final String f20905d = "RemoteWebViewBinderPool";

    /* renamed from: g  reason: collision with root package name */
    private static volatile as f20906g;

    /* renamed from: e  reason: collision with root package name */
    private Context f20907e;

    /* renamed from: f  reason: collision with root package name */
    private ah f20908f;

    /* renamed from: h  reason: collision with root package name */
    private CountDownLatch f20909h;

    /* renamed from: i  reason: collision with root package name */
    private IBinder.DeathRecipient f20910i = new IBinder.DeathRecipient() { // from class: gsdk.impl.webview.DEFAULT.as.1
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            as.this.f20908f.asBinder().unlinkToDeath(as.this.f20910i, 0);
            as.this.f20908f = null;
            as.this.a();
        }
    };

    /* renamed from: j  reason: collision with root package name */
    private ServiceConnection f20911j = new ServiceConnection() { // from class: gsdk.impl.webview.DEFAULT.as.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            as.this.f20908f = ah.b.a(iBinder);
            Timber.tag(as.f20905d).d("onServiceConnected", new Object[0]);
            try {
                as.this.f20908f.asBinder().linkToDeath(as.this.f20910i, 0);
            } catch (Exception e2) {
                Timber.tag(as.f20905d).d(e2.toString(), new Object[0]);
            }
            as.this.f20909h.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(as.f20905d, "onServiceDisconnected: " + componentName.toString());
        }
    };

    private as(Context context) {
        this.f20907e = context.getApplicationContext();
        a();
    }

    public static as a(Context context) {
        if (f20906g == null) {
            synchronized (as.class) {
                if (f20906g == null) {
                    f20906g = new as(context);
                }
            }
        }
        return f20906g;
    }

    public IBinder a(int i2) {
        ah ahVar = this.f20908f;
        if (ahVar != null) {
            try {
                return ahVar.a(i2);
            } catch (RemoteException e2) {
                Timber.tag(f20905d).d(e2.toString(), new Object[0]);
            }
        } else {
            Timber.tag(f20905d).d("WebBinderPool is null", new Object[0]);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a() {
        this.f20909h = new CountDownLatch(1);
        this.f20907e.bindService(new Intent(this.f20907e, MainProAidlService.class), this.f20911j, 1);
        try {
            this.f20909h.await();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    /* compiled from: RemoteWebViewBinderPool.java */
    /* loaded from: classes10.dex */
    public static class a extends ah.b {

        /* renamed from: c  reason: collision with root package name */
        private Context f20914c;

        public a(Context context) {
            this.f20914c = context;
        }

        @Override // gsdk.impl.webview.DEFAULT.ah
        public IBinder a(int i2) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return null;
                    }
                    return new at(this.f20914c);
                }
                return new au(this.f20914c);
            }
            return new av(this.f20914c);
        }
    }
}
