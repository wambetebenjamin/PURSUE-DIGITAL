package com.bytedance.common.wschannel;

import android.os.HandlerThread;
import android.os.Message;
import com.bytedance.common.utility.collection.WeakHandler;
/* compiled from: SocketHandler.java */
/* loaded from: classes2.dex */
public class j implements WeakHandler.IHandler {

    /* renamed from: a  reason: collision with root package name */
    private static volatile j f5753a;

    /* renamed from: b  reason: collision with root package name */
    private static WeakHandler f5754b;

    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
    }

    public static void a(HandlerThread handlerThread) {
        synchronized (j.class) {
            if (f5754b == null) {
                f5754b = new WeakHandler(handlerThread.getLooper(), new WeakHandler.IHandler() { // from class: com.bytedance.common.wschannel.j.1
                    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
                    public void handleMsg(Message message) {
                    }
                });
            }
        }
    }

    public static j a() {
        if (f5753a == null) {
            synchronized (j.class) {
                if (f5753a == null) {
                    f5753a = new j();
                }
            }
        }
        return f5753a;
    }

    private j() {
        if (f5754b == null) {
            try {
                f5754b = com.ss.android.message.d.inst().getHandler();
            } catch (Throwable unused) {
                HandlerThread handlerThread = new HandlerThread("WsHT");
                handlerThread.start();
                f5754b = new WeakHandler(handlerThread.getLooper(), this);
            }
        }
    }

    public WeakHandler b() {
        return f5754b;
    }

    public void a(Runnable runnable) {
        a(runnable, 0L);
    }

    public void a(Runnable runnable, long j2) {
        if (j2 <= 0) {
            f5754b.post(runnable);
        } else {
            f5754b.postDelayed(runnable, j2);
        }
    }
}
