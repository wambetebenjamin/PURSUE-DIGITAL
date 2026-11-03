package com.bytedance.push.proxy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bytedance.push.utils.h;
import com.bytedance.push.utils.t;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: ActivityThreadHooker.java */
/* loaded from: classes2.dex */
public class a extends b implements Handler.Callback {

    /* renamed from: d  reason: collision with root package name */
    private static volatile a f11679d;

    /* renamed from: e  reason: collision with root package name */
    private Method f11680e;

    /* renamed from: f  reason: collision with root package name */
    private Object f11681f;

    @Override // com.bytedance.push.proxy.b
    protected String c() {
        return "ActivityThreadHooker";
    }

    public static a a() {
        if (f11679d == null) {
            synchronized (a.class) {
                if (f11679d == null) {
                    f11679d = new a();
                }
            }
        }
        return f11679d;
    }

    private a() {
    }

    @Override // com.bytedance.push.proxy.b
    protected boolean b() {
        try {
        } catch (Throwable th) {
            h.e(this.f11685a, "error when hook ActivityThread ", th);
        }
        if (this.f11687c) {
            return true;
        }
        Object d2 = d();
        this.f11681f = d2;
        if (d2 != null) {
            Field b2 = t.b(d2.getClass(), "mH");
            b2.setAccessible(true);
            Handler handler = (Handler) b2.get(this.f11681f);
            Field b3 = t.b(Handler.class, "mCallback");
            b3.setAccessible(true);
            this.f11686b = b3.get(handler);
            this.f11680e = t.b(Handler.Callback.class, "handleMessage", Message.class);
            b3.set(handler, this);
            h.d(this.f11685a, "success replace ActivityThread#H#mCallback");
            return true;
        }
        h.e(this.f11685a, "sActivityThread is null!");
        return false;
    }

    private synchronized Object d() {
        if (this.f11681f == null) {
            final Method b2 = t.b(Class.forName("android.app.ActivityThread"), "currentActivityThread", new Class[0]);
            b2.setAccessible(true);
            Object invoke = b2.invoke(null, new Object[0]);
            this.f11681f = invoke;
            if (invoke == null && Looper.myLooper() != Looper.getMainLooper()) {
                final Object obj = new Object();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.push.proxy.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            a.this.f11681f = b2.invoke(null, new Object[0]);
                            synchronized (obj) {
                                obj.notify();
                            }
                        } finally {
                            Object obj2 = obj;
                        }
                    }
                });
                if (this.f11681f == null) {
                    synchronized (obj) {
                        try {
                            obj.wait();
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
        }
        return this.f11681f;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        h.d(this.f11685a, "handleMessage:" + message.what);
        try {
            Object invoke = invoke(this.f11686b, this.f11680e, new Object[]{message});
            if (invoke != null) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }
}
