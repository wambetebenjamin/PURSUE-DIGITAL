package com.bytedance.monitor.collector;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.view.Choreographer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: LooperMonitorUtil.java */
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f9596a;

    /* renamed from: b  reason: collision with root package name */
    private static Handler f9597b;

    /* renamed from: c  reason: collision with root package name */
    private static b f9598c = new b();

    /* renamed from: d  reason: collision with root package name */
    private static a f9599d = new a();
    public static Message mainMessages = Message.obtain();

    public static void init() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mH");
            declaredField.setAccessible(true);
            f9596a = (Handler) declaredField.get(invoke);
            com.bytedance.monitor.util.unsafe.b.putObject(invoke, com.bytedance.monitor.util.unsafe.b.objectFieldOffset(declaredField), f9598c);
            Choreographer choreographer = Choreographer.getInstance();
            Class<?> cls2 = choreographer.getClass();
            Method declaredMethod2 = Class.class.getDeclaredMethod("getDeclaredField", String.class);
            Field field = (Field) declaredMethod2.invoke(cls2, "mHandler");
            field.setAccessible(true);
            f9597b = (Handler) field.get(choreographer);
            com.bytedance.monitor.util.unsafe.b.putObject(choreographer, com.bytedance.monitor.util.unsafe.b.objectFieldOffset(field), f9599d);
            Looper mainLooper = Looper.getMainLooper();
            Field field2 = (Field) declaredMethod2.invoke(mainLooper.getClass(), "mQueue");
            field2.setAccessible(true);
            MessageQueue messageQueue = (MessageQueue) field2.get(mainLooper);
            Field field3 = (Field) declaredMethod2.invoke(messageQueue.getClass(), "mMessages");
            field3.setAccessible(true);
            mainMessages = (Message) field3.get(messageQueue);
        } catch (Exception e2) {
            com.bytedance.services.apm.api.b.getEnsureImpl().ensureNotReachHere(e2, "LooperMonitorUtil init error");
        }
    }

    /* compiled from: LooperMonitorUtil.java */
    /* loaded from: classes2.dex */
    static class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            k.f9597b.handleMessage(message);
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j2) {
            return super.sendMessageAtTime(message, j2);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            if (message.getCallback() != null) {
                super.dispatchMessage(message);
            } else {
                super.dispatchMessage(message);
            }
        }
    }

    /* compiled from: LooperMonitorUtil.java */
    /* loaded from: classes2.dex */
    static class b extends Handler {
        b() {
        }

        String a(int i2) {
            return Integer.toString(i2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            k.f9596a.handleMessage(message);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j2) {
            return super.sendMessageAtTime(message, j2);
        }
    }
}
