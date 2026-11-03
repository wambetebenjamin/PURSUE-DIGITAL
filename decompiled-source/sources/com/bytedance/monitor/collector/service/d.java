package com.bytedance.monitor.collector.service;

import android.app.Service;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;
import java.util.Map;
/* compiled from: ServiceTrackMonitor.java */
/* loaded from: classes2.dex */
public class d implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    private static d f9655a = new d();

    /* renamed from: b  reason: collision with root package name */
    private static c f9656b = null;

    /* renamed from: c  reason: collision with root package name */
    private static Map<IBinder, Service> f9657c;

    /* renamed from: d  reason: collision with root package name */
    private Handler.Callback f9658d;

    private d() {
    }

    public static d getInstance() {
        return f9655a;
    }

    public synchronized String getServiceComponentName(IBinder iBinder) {
        String str;
        if (f9657c == null) {
            try {
                Object currentActivityThread = com.bytedance.monitor.util.a.currentActivityThread();
                f9657c = (Map) a.getField(currentActivityThread.getClass(), "mServices").get(currentActivityThread);
            } catch (Throwable unused) {
            }
        }
        Map<IBinder, Service> map = f9657c;
        str = null;
        if (map != null && map.get(iBinder) != null) {
            str = f9657c.get(iBinder).getClass().getName();
        }
        return str;
    }

    public void start() {
        try {
            Handler handler = (Handler) com.bytedance.monitor.util.a.getHInActivityThread();
            Field field = a.getField(Handler.class, "mCallback");
            this.f9658d = (Handler.Callback) field.get(handler);
            field.set(handler, this);
        } catch (Throwable unused) {
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            if (b.isServiceMessage(message)) {
                f9656b = c.fetchServiceName(message);
            }
        } catch (Throwable unused) {
        }
        Handler.Callback callback = this.f9658d;
        if (callback != null) {
            return callback.handleMessage(message);
        }
        return false;
    }

    public static c getCurrentService() {
        return f9656b;
    }

    public static void clearCurrentService() {
        f9656b = null;
    }
}
