package com.bytedance.bdturing;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
/* compiled from: VerifyTaskHandler.java */
/* loaded from: classes.dex */
public class k {
    public static final int CLEAR_MOTION_LIST = 3;
    public static final int DIALOG_CLEAR_RESOURCE = 1;
    public static final int JS_CALL_GET_TOUCH = 2;

    /* renamed from: a  reason: collision with root package name */
    private HandlerThread f4220a;

    /* renamed from: b  reason: collision with root package name */
    private b f4221b;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: VerifyTaskHandler.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static k f4222a = new k();

        private a() {
        }
    }

    private k() {
    }

    public static k getInstance() {
        return a.f4222a;
    }

    public void startHandleMessage() {
        if (this.f4220a == null) {
            synchronized (k.class) {
                if (this.f4220a == null) {
                    HandlerThread handlerThread = new HandlerThread("TuringVerifyThread");
                    this.f4220a = handlerThread;
                    handlerThread.start();
                    this.f4221b = new b(this.f4220a.getLooper());
                }
            }
        }
    }

    public Looper getLooper() {
        return this.f4220a.getLooper();
    }

    public void sendMessage(int i2, Object obj) {
        b bVar = this.f4221b;
        if (bVar != null) {
            this.f4221b.sendMessage(bVar.obtainMessage(i2, obj));
        }
    }

    public void sendMessage(int i2, int i3, int i4, Object obj) {
        b bVar = this.f4221b;
        if (bVar != null) {
            this.f4221b.sendMessage(bVar.obtainMessage(i2, i3, i4, obj));
        }
    }

    public void sendMessageDelay(int i2, Object obj, long j2) {
        b bVar = this.f4221b;
        if (bVar != null) {
            this.f4221b.sendMessageDelayed(bVar.obtainMessage(i2, obj), j2);
        }
    }

    public void postRunnable(Runnable runnable) {
        b bVar = this.f4221b;
        if (bVar != null) {
            bVar.post(runnable);
        }
    }

    public void removeMessage(int i2) {
        b bVar = this.f4221b;
        if (bVar != null) {
            bVar.removeMessages(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Message message) {
        if (message.obj != null) {
            ((j) message.obj).clearResource();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: VerifyTaskHandler.java */
    /* loaded from: classes.dex */
    public class b extends Handler {
        private b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                k.this.b(message);
            } else if (i2 == 2) {
                p.a((com.bytedance.bdturing.methods.l) message.obj);
            } else if (i2 != 3) {
            } else {
                p.a();
            }
        }
    }
}
