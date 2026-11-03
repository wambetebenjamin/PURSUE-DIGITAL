package com.bytedance.common.wschannel.server;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.common.wschannel.client.AbsWsClientService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: ClientMsgSender.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final b f5916a;

    /* renamed from: b  reason: collision with root package name */
    private final Class<? extends AbsWsClientService> f5917b;

    /* compiled from: ClientMsgSender.java */
    /* loaded from: classes2.dex */
    public interface b {
        void a();

        void a(Intent intent);
    }

    public c(Context context, Class<? extends AbsWsClientService> cls) {
        if (Build.VERSION.SDK_INT >= 26 && context.getApplicationInfo().targetSdkVersion >= 26) {
            this.f5916a = new C0085c(context);
        } else {
            this.f5916a = new a(context);
        }
        this.f5917b = cls;
    }

    public void a(Intent intent) {
        this.f5916a.a(intent);
    }

    public void a() {
        this.f5916a.a();
    }

    /* compiled from: ClientMsgSender.java */
    /* loaded from: classes2.dex */
    private class a implements b {

        /* renamed from: b  reason: collision with root package name */
        private final Context f5919b;

        @Override // com.bytedance.common.wschannel.server.c.b
        public void a() {
        }

        a(Context context) {
            this.f5919b = context;
        }

        @Override // com.bytedance.common.wschannel.server.c.b
        public void a(Intent intent) {
            try {
                intent.setComponent(new ComponentName(this.f5919b, c.this.f5917b));
                this.f5919b.startService(intent);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ClientMsgSender.java */
    /* renamed from: com.bytedance.common.wschannel.server.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class C0085c implements b {

        /* renamed from: c  reason: collision with root package name */
        private final Context f5922c;

        /* renamed from: d  reason: collision with root package name */
        private Messenger f5923d;

        /* renamed from: f  reason: collision with root package name */
        private ServiceConnection f5925f;

        /* renamed from: e  reason: collision with root package name */
        private final LinkedBlockingDeque<Intent> f5924e = new LinkedBlockingDeque<>();

        /* renamed from: g  reason: collision with root package name */
        private boolean f5926g = false;

        /* renamed from: h  reason: collision with root package name */
        private final Object f5927h = new Object();

        /* renamed from: i  reason: collision with root package name */
        private final Runnable f5928i = new Runnable() { // from class: com.bytedance.common.wschannel.server.c.c.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (C0085c.this.f5927h) {
                    Logger.d("WsChannelSdk", "mBindServiceTimeoutRunnable run");
                    if (C0085c.this.f5926g) {
                        C0085c.this.f5926g = false;
                    }
                }
            }
        };

        /* renamed from: j  reason: collision with root package name */
        private final Runnable f5929j = new Runnable() { // from class: com.bytedance.common.wschannel.server.c.c.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (C0085c.this.f5927h) {
                    try {
                        if (C0085c.this.f5925f != null && com.bytedance.common.wschannel.utils.e.a(C0085c.this.f5922c, c.this.f5917b)) {
                            C0085c.this.f5922c.unbindService(C0085c.this.f5925f);
                        }
                        C0085c.this.f5925f = null;
                        C0085c.this.f5923d = null;
                        Logger.d("WsChannelSdk", "mDisconnectRunnable timeout");
                    }
                }
            }
        };

        /* renamed from: a  reason: collision with root package name */
        final AtomicInteger f5920a = new AtomicInteger(0);

        C0085c(Context context) {
            this.f5922c = context;
        }

        @Override // com.bytedance.common.wschannel.server.c.b
        public void a(Intent intent) {
            if (intent == null) {
                return;
            }
            if (Logger.debug()) {
                intent.putExtra(WsConstants.MSG_COUNT, this.f5920a.addAndGet(1));
            }
            synchronized (this.f5927h) {
                g();
                this.f5924e.offer(intent);
                if (this.f5923d == null) {
                    if (this.f5926g) {
                        return;
                    }
                    c();
                } else {
                    b();
                }
            }
        }

        @Override // com.bytedance.common.wschannel.server.c.b
        public void a() {
            if (this.f5924e.size() <= 0 || this.f5923d != null) {
                return;
            }
            Logger.d("WsChannelSdk", "tryResendMsg try reconnect");
            synchronized (this.f5927h) {
                if (this.f5924e.size() > 0 && this.f5923d == null) {
                    c();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            while (this.f5924e.peek() != null) {
                try {
                    Intent poll = this.f5924e.poll();
                    if (poll == null) {
                        return;
                    }
                    try {
                        b(poll);
                    } catch (DeadObjectException unused) {
                        Logger.d("WsChannelSdk", "onServiceConnected DeadObjectException");
                        this.f5923d = null;
                        this.f5924e.offerFirst(poll);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return;
                }
            }
            f();
        }

        private void b(Intent intent) {
            if (intent == null) {
                return;
            }
            if (Logger.debug()) {
                Logger.d("WsChannelSdk", "sendMsg msg = " + intent);
            }
            Messenger messenger = this.f5923d;
            Message message = new Message();
            message.what = 10123;
            message.getData().putParcelable(WsConstants.DATA_INTENT, intent);
            if (messenger == null) {
                return;
            }
            messenger.send(message);
        }

        private void c() {
            if (Logger.debug()) {
                Logger.d("WsChannelSdk", "doBindService");
            }
            try {
                this.f5922c.startService(new Intent(this.f5922c, c.this.f5917b));
            } catch (Throwable unused) {
            }
            try {
                this.f5925f = new a();
                this.f5922c.bindService(new Intent(this.f5922c, c.this.f5917b), this.f5925f, 1);
                d();
                this.f5926g = true;
                Logger.d("WsChannelSdk", "doBindService, set mBinding = true");
            } catch (Throwable th) {
                e();
                this.f5926g = false;
                Logger.d("WsChannelSdk", "doBindService error, set Unbind = false");
                th.printStackTrace();
            }
        }

        private void d() {
            e();
            com.bytedance.common.wschannel.j.a().b().postDelayed(this.f5928i, TimeUnit.SECONDS.toMillis(7L));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            com.bytedance.common.wschannel.j.a().b().removeCallbacks(this.f5928i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: ClientMsgSender.java */
        /* renamed from: com.bytedance.common.wschannel.server.c$c$a */
        /* loaded from: classes2.dex */
        public class a implements ServiceConnection {
            private a() {
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                if (Logger.debug()) {
                    Logger.d("WsChannelSdk", "onServiceDisconnected name = " + componentName.toString());
                }
                synchronized (C0085c.this.f5927h) {
                    if (componentName == null) {
                        return;
                    }
                    Logger.d("WsChannelSdk", "onServiceDisconnected set service = null");
                    C0085c.this.f5923d = null;
                    C0085c.this.f5925f = null;
                    Logger.d("WsChannelSdk", "onServiceDisconnected, set Unbind = false");
                    C0085c.this.f5926g = false;
                    C0085c.this.g();
                    C0085c.this.e();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                if (Logger.debug()) {
                    Logger.d("WsChannelSdk", "onServiceConnected name = " + componentName.toString());
                }
                synchronized (C0085c.this.f5927h) {
                    C0085c.this.e();
                    if (componentName == null || iBinder == null) {
                        return;
                    }
                    C0085c.this.f5923d = new Messenger(iBinder);
                    Logger.d("WsChannelSdk", "onServiceConnected set service");
                    C0085c.this.b();
                    Logger.d("WsChannelSdk", "onServiceConnected, set Unbind = false");
                    C0085c.this.f5926g = false;
                }
            }
        }

        private synchronized void f() {
            g();
            com.bytedance.common.wschannel.j.a().b().postDelayed(this.f5929j, TimeUnit.SECONDS.toMillis(10L));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g() {
            try {
                com.bytedance.common.wschannel.j.a().b().removeCallbacks(this.f5929j);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
