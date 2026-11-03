package com.bytedance.common.jato.threads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.bytedance.common.jato.d;
/* compiled from: ThreadSuspensionManager.java */
/* loaded from: classes2.dex */
public class c implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5026a = "thread_suspend_interval";

    /* renamed from: b  reason: collision with root package name */
    private static final int f5027b = 1000;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5028c = 1001;

    /* renamed from: d  reason: collision with root package name */
    private static final int f5029d = 1002;

    /* renamed from: e  reason: collision with root package name */
    private static final int f5030e = 1003;

    /* renamed from: f  reason: collision with root package name */
    private static final int f5031f = 1004;

    /* renamed from: g  reason: collision with root package name */
    private static final int f5032g = 1005;

    /* renamed from: h  reason: collision with root package name */
    private static final int f5033h = 1006;

    /* renamed from: i  reason: collision with root package name */
    private static final int f5034i = 1999;

    /* renamed from: j  reason: collision with root package name */
    private HandlerThread f5035j;
    private Handler k;

    private c() {
        this.f5035j = null;
        b();
    }

    public static c a() {
        return a.f5036a;
    }

    private void b() {
        if (d.loadLibrary() && this.f5035j == null) {
            HandlerThread handlerThread = new HandlerThread(f5026a);
            this.f5035j = handlerThread;
            handlerThread.start();
            this.k = new Handler(this.f5035j.getLooper(), this);
        }
    }

    public void a(long j2) {
        Handler handler = this.k;
        if (handler == null || handler.hasMessages(1002) || this.k.hasMessages(1001)) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1001;
        obtain.arg1 = (int) j2;
        this.k.sendMessage(obtain);
    }

    public void b(long j2) {
        Handler handler = this.k;
        if (handler == null || handler.hasMessages(1004) || this.k.hasMessages(1003)) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1003;
        obtain.arg1 = (int) j2;
        this.k.sendMessage(obtain);
    }

    public void a(long j2, String[] strArr) {
        Handler handler = this.k;
        if (handler == null || handler.hasMessages(1006) || this.k.hasMessages(1005)) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1005;
        obtain.arg1 = (int) j2;
        obtain.obj = strArr;
        this.k.sendMessage(obtain);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        switch (message.what) {
            case 1001:
                if (!this.k.hasMessages(1002) && (i2 = message.arg1) > 0 && com.bytedance.common.jato.threads.a.a()) {
                    Message obtain = Message.obtain();
                    obtain.what = 1002;
                    this.k.sendMessageDelayed(obtain, i2);
                    return true;
                }
                return true;
            case 1002:
                com.bytedance.common.jato.threads.a.b();
                return true;
            case 1003:
                if (!this.k.hasMessages(1004) && (i3 = message.arg1) > 0) {
                    com.bytedance.common.jato.threads.a.c();
                    Message obtain2 = Message.obtain();
                    obtain2.what = 1004;
                    this.k.sendMessageDelayed(obtain2, i3);
                    return true;
                }
                return true;
            case 1004:
                com.bytedance.common.jato.threads.a.d();
                return true;
            case 1005:
                if (!this.k.hasMessages(1006) && (i4 = message.arg1) > 0 && (message.obj instanceof String[])) {
                    String[] strArr = (String[]) message.obj;
                    int length = strArr.length;
                    while (i5 < length) {
                        String str = strArr[i5];
                        if (!f5026a.equals(str)) {
                            com.bytedance.common.jato.threads.a.a(str);
                        }
                        i5++;
                    }
                    Message obtain3 = Message.obtain();
                    obtain3.what = 1006;
                    obtain3.obj = strArr;
                    this.k.sendMessageDelayed(obtain3, i4);
                    return true;
                }
                return true;
            case 1006:
                if (message.obj instanceof String[]) {
                    String[] strArr2 = (String[]) message.obj;
                    int length2 = strArr2.length;
                    while (i5 < length2) {
                        String str2 = strArr2[i5];
                        if (!f5026a.equals(str2)) {
                            com.bytedance.common.jato.threads.a.b(str2);
                        }
                        i5++;
                    }
                    return true;
                }
                return true;
            default:
                return true;
        }
    }

    /* compiled from: ThreadSuspensionManager.java */
    /* loaded from: classes2.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final c f5036a = new c();

        private a() {
        }
    }
}
