package com.bytedance.monitor.collector;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Choreographer;
/* compiled from: DoFrameBooster.java */
/* loaded from: classes2.dex */
public class d {
    public static final int SEND_AFTER_CURRENT = 1;
    public static final int SEND_AT_FRONT = 0;

    /* renamed from: a  reason: collision with root package name */
    private static final String f9525a = "doFrameBooster";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f9526b = false;

    /* renamed from: c  reason: collision with root package name */
    private static Handler f9527c;

    /* renamed from: d  reason: collision with root package name */
    private static final a f9528d = new a(Looper.getMainLooper());

    /* renamed from: e  reason: collision with root package name */
    private static Object f9529e = null;

    /* renamed from: f  reason: collision with root package name */
    private static volatile boolean f9530f = false;

    /* renamed from: g  reason: collision with root package name */
    private static int f9531g = 0;

    /* renamed from: h  reason: collision with root package name */
    private static int f9532h = 1;

    public static synchronized void init() {
        synchronized (d.class) {
            init(Choreographer.getInstance());
        }
    }

    public static synchronized void init(Choreographer choreographer) {
        synchronized (d.class) {
            if (f9526b) {
                return;
            }
            try {
                f9527c = (Handler) com.bytedance.monitor.collector.service.a.readField(choreographer, "mHandler");
                f9529e = com.bytedance.monitor.collector.service.a.readField(choreographer, "mDisplayEventReceiver");
                com.bytedance.monitor.util.unsafe.b.putObject(choreographer, com.bytedance.monitor.util.unsafe.b.objectFieldOffset(com.bytedance.monitor.collector.service.a.getField(Choreographer.class, "mHandler")), f9528d);
                Log.i(f9525a, "hack Choreographer Success");
                f9526b = true;
            } catch (Exception e2) {
                Log.e(f9525a, "hack Choreographer Error", e2);
            }
        }
    }

    public static void setNeedHook(boolean z) {
        f9530f = z;
    }

    public static void setMessageGap(int i2) {
        f9531g = i2;
    }

    public static void setRunMode(int i2) {
        f9532h = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DoFrameBooster.java */
    /* loaded from: classes2.dex */
    public static class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            d.f9527c.handleMessage(message);
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j2) {
            if (message.getCallback() == d.f9529e && d.f9530f) {
                if (d.f9532h == 0) {
                    return sendMessageAtFrontOfQueue(message);
                }
                Message messageObject = l.getMessageObject(l.getMainMessageQueue());
                if (messageObject != null) {
                    return super.sendMessageAtTime(message, Math.min(messageObject.getWhen() + d.f9531g, j2));
                }
                return sendMessageAtFrontOfQueue(message);
            }
            return super.sendMessageAtTime(message, j2);
        }
    }
}
