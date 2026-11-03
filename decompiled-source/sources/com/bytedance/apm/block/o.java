package com.bytedance.apm.block;

import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.util.Printer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/* compiled from: LooperObserverMonitor.java */
/* loaded from: classes.dex */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    public static final String f741a = ">>>>> Dispatching to Handler (com.bytedance.apm.block.LooperObserverMonitor) {57c1969} com.bytedance.apm.block.LooperObserverMonitor@5caffee: 0";

    /* renamed from: b  reason: collision with root package name */
    public static final String f742b = "<<<<< Finished to Handler (com.bytedance.apm.block.LooperObserverMonitor) {57c1969} com.bytedance.apm.block.LooperObserverMonitor@5caffee";

    /* renamed from: c  reason: collision with root package name */
    public static final char f743c = '>';

    /* renamed from: d  reason: collision with root package name */
    public static final char f744d = '<';

    /* renamed from: e  reason: collision with root package name */
    public static Object f745e = null;

    /* renamed from: f  reason: collision with root package name */
    public static Method f746f = null;

    /* renamed from: g  reason: collision with root package name */
    public static Method f747g = null;

    /* renamed from: h  reason: collision with root package name */
    public static Method f748h = null;

    /* renamed from: i  reason: collision with root package name */
    public static final Thread f749i = Looper.getMainLooper().getThread();

    /* renamed from: j  reason: collision with root package name */
    public static a f750j = null;
    private static volatile boolean k = false;

    private o() {
    }

    public static synchronized boolean a() {
        synchronized (o.class) {
            if (k) {
                return true;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                Object a2 = a(Looper.class, (Object) null, "sObserver");
                f745e = a2;
                if (a2 != null) {
                    f746f = a(a2.getClass(), "messageDispatchStarting", new Class[0]);
                    f747g = a(f745e.getClass(), "messageDispatched", Object.class, Message.class);
                    f748h = a(f745e.getClass(), "dispatchingThrewException", Object.class, Message.class, Exception.class);
                }
                if (f746f == null || f747g == null || f748h == null) {
                    f745e = null;
                }
                f750j = (a) Class.forName("com.bytedance.observer.ObserverWrapper").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                if (f745e != null) {
                    System.out.println("LooperObserverMonitor.init: " + f745e);
                    f750j.setup(f745e);
                }
                a(Looper.class, "setObserver", Class.forName("android.os.Looper$Observer")).invoke(null, f750j);
                k = true;
                return k;
            }
            f750j = new a();
            f745e = null;
            Looper.getMainLooper().setMessageLogging(new Printer() { // from class: com.bytedance.apm.block.o.1
                @Override // android.util.Printer
                public void println(String str) {
                    if (str.charAt(0) == '>') {
                        o.f750j.messageDispatchStarting(str);
                    } else if (str.charAt(0) == '<') {
                        o.f750j.messageDispatched(str, null);
                    }
                }
            });
            k = true;
            return k;
        }
    }

    public static void a(n nVar) {
        a aVar = f750j;
        if (aVar != null) {
            aVar.addMessageObserver(nVar);
        }
    }

    public static void b(n nVar) {
        a aVar = f750j;
        if (aVar != null) {
            aVar.removeMessageObserver(nVar);
        }
    }

    public static Method a(Class cls, String str, Class<?>... clsArr) {
        try {
            Method method = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class).invoke(cls, str, clsArr);
            method.setAccessible(true);
            return method;
        } catch (Exception unused) {
            return null;
        }
    }

    private static <T> T a(Class cls, Object obj, String str) {
        try {
            Field field = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(cls, str);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* compiled from: LooperObserverMonitor.java */
    /* loaded from: classes.dex */
    public static class a {
        final List<n> mObservers = new ArrayList();
        List<n> mRemoveObservers = new ArrayList();
        List<n> mAddObservers = new ArrayList();
        volatile boolean haveRemove = false;
        volatile boolean haveAdd = false;

        protected void setup(Object obj) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void messageDispatchStarting(String str) {
            if (this.haveAdd) {
                synchronized (this.mObservers) {
                    for (n nVar : this.mAddObservers) {
                        if (!this.mObservers.contains(nVar)) {
                            this.mObservers.add(nVar);
                        }
                    }
                    this.mAddObservers.clear();
                    this.haveAdd = false;
                }
            }
            for (n nVar2 : this.mObservers) {
                if (nVar2 != null) {
                    nVar2.a(str);
                }
            }
        }

        public void messageDispatched(String str, Message message) {
            for (n nVar : this.mObservers) {
                if (nVar != null) {
                    nVar.a(str, message);
                }
            }
            if (this.haveRemove) {
                synchronized (this.mObservers) {
                    for (n nVar2 : this.mRemoveObservers) {
                        this.mObservers.remove(nVar2);
                        this.mAddObservers.remove(nVar2);
                    }
                    this.mRemoveObservers.clear();
                    this.haveRemove = false;
                }
            }
        }

        public void addMessageObserver(n nVar) {
            synchronized (this.mObservers) {
                if (nVar != null) {
                    if (!this.mAddObservers.contains(nVar)) {
                        this.mAddObservers.add(nVar);
                        this.haveAdd = true;
                    }
                }
            }
        }

        public void removeMessageObserver(n nVar) {
            synchronized (this.mObservers) {
                if (nVar != null) {
                    if (!this.mRemoveObservers.contains(nVar)) {
                        this.mRemoveObservers.add(nVar);
                        this.haveRemove = true;
                    }
                }
            }
        }
    }
}
