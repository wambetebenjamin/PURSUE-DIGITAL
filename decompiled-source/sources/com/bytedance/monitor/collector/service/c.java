package com.bytedance.monitor.collector.service;

import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;
/* compiled from: ServiceFollowBean.java */
/* loaded from: classes2.dex */
public class c {
    public static final int BIND_SERVICE = 121;
    public static final int CREATE_SERVICE = 114;
    public static final int DUMP_SERVICE = 123;
    public static final int SERVICE_ARGS = 115;
    public static final int STOP_SERVICE = 116;
    public static final int UNBIND_SERVICE = 122;

    /* renamed from: a  reason: collision with root package name */
    private static Field f9649a;

    /* renamed from: b  reason: collision with root package name */
    private static Field f9650b;

    /* renamed from: c  reason: collision with root package name */
    private static Field f9651c;

    /* renamed from: d  reason: collision with root package name */
    private static Field f9652d;

    /* renamed from: e  reason: collision with root package name */
    private static Field f9653e;

    /* renamed from: f  reason: collision with root package name */
    private static Field f9654f;
    public String mServiceName;
    public String mThreadName;
    public long mTimeStamp;
    public String mToken;
    public int mWhat;

    public c() {
    }

    public c(String str, int i2, long j2, String str2) {
        this.mServiceName = str;
        this.mWhat = i2;
        this.mTimeStamp = j2;
        this.mToken = str2;
        this.mThreadName = Thread.currentThread().getName();
    }

    public static c fetchServiceName(Message message) {
        try {
            switch (message.what) {
                case 114:
                    if (f9649a == null) {
                        f9649a = a.getField(message.obj.getClass(), "info");
                    }
                    if (f9650b == null) {
                        f9650b = a.getField(message.obj.getClass(), "token");
                    }
                    ServiceInfo serviceInfo = (ServiceInfo) f9649a.get(message.obj);
                    return new c(serviceInfo != null ? serviceInfo.name : null, message.what, System.currentTimeMillis(), f9650b.get(message.obj).toString());
                case 115:
                    if (f9651c == null) {
                        f9651c = a.getField(message.obj.getClass(), "token");
                    }
                    IBinder iBinder = (IBinder) f9651c.get(message.obj);
                    if (iBinder != null) {
                        return new c(d.getInstance().getServiceComponentName(iBinder), message.what, System.currentTimeMillis(), iBinder.toString());
                    }
                    break;
                case 116:
                    return new c(d.getInstance().getServiceComponentName((IBinder) message.obj), message.what, System.currentTimeMillis(), message.obj.toString());
                case 117:
                case 118:
                case 119:
                case 120:
                default:
                    throw new IllegalArgumentException("message.what = " + message.what + " is not excepted!");
                case 121:
                    if (f9652d == null) {
                        f9652d = a.getField(message.obj.getClass(), "token");
                    }
                    IBinder iBinder2 = (IBinder) f9652d.get(message.obj);
                    if (iBinder2 != null) {
                        return new c(d.getInstance().getServiceComponentName(iBinder2), message.what, System.currentTimeMillis(), iBinder2.toString());
                    }
                    break;
                case 122:
                    if (f9653e == null) {
                        f9653e = a.getField(message.obj.getClass(), "token");
                    }
                    IBinder iBinder3 = (IBinder) f9653e.get(message.obj);
                    if (iBinder3 != null) {
                        return new c(d.getInstance().getServiceComponentName(iBinder3), message.what, System.currentTimeMillis(), iBinder3.toString());
                    }
                    break;
                case 123:
                    if (f9654f == null) {
                        f9654f = a.getField(message.obj.getClass(), "token");
                    }
                    IBinder iBinder4 = (IBinder) f9654f.get(message.obj);
                    if (iBinder4 != null) {
                        return new c(d.getInstance().getServiceComponentName(iBinder4), message.what, System.currentTimeMillis(), iBinder4.toString());
                    }
                    break;
            }
        } catch (Throwable unused) {
        }
        return new c("unknow", message.what, System.currentTimeMillis(), "unknow");
    }

    public String toString() {
        return "ServiceFollowBean{mServiceName='" + this.mServiceName + "', mWhat=" + this.mWhat + ", mTimeStamp=" + this.mTimeStamp + ", mToken='" + this.mToken + "', mThreadName='" + this.mThreadName + "'}";
    }
}
