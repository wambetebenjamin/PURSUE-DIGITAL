package com.bytedance.push.alive;

import android.content.Context;
import android.os.Looper;
import com.bytedance.common.utility.collection.WeakHandler;
import com.ss.android.message.d;
/* compiled from: AliveKeeperProxy.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f11189a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f11190b;

    /* renamed from: c  reason: collision with root package name */
    private final WeakHandler f11191c = d.inst().getHandler();

    public static a inst(Context context) {
        if (f11189a == null) {
            synchronized (a.class) {
                if (f11189a == null) {
                    f11189a = new a(context);
                }
            }
        }
        return f11189a;
    }

    private a(Context context) {
        this.f11190b = context.getApplicationContext();
    }

    public boolean checkConfig(String str) {
        b a2 = a();
        if (a2 != null) {
            return a2.checkConfig(this.f11190b, str);
        }
        return true;
    }

    public void doKeepAlive() {
        Runnable runnable = new Runnable() { // from class: com.bytedance.push.alive.a.1
            @Override // java.lang.Runnable
            public void run() {
                b a2 = a.this.a();
                if (a2 != null) {
                    a2.doKeepAlive(a.this.f11190b, a.this.f11191c);
                }
            }
        };
        if (Looper.getMainLooper() == Looper.myLooper()) {
            com.bytedance.common.push.d.a(runnable);
        } else {
            runnable.run();
        }
    }

    public void a(boolean z) {
        b a2 = a();
        if (a2 != null) {
            a2.a(this.f11190b, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized b a() {
        Class<?> cls;
        try {
            cls = Class.forName("com.a.b.AKeeperImpl");
        } catch (Throwable unused) {
            return null;
        }
        return (b) cls.getMethod("inst", new Class[0]).invoke(cls, new Object[0]);
    }

    public boolean isSswoActivityFinish() {
        b a2 = a();
        if (a2 != null) {
            return a2.isSsWoFinished();
        }
        return true;
    }
}
