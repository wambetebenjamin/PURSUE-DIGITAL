package com.ss.android.ug.bus;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: UgCallbackCenter.java */
/* loaded from: classes9.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f18617a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static final ConcurrentHashMap<Type, ConcurrentHashMap<a, Object>> f18618b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private static final Handler f18619c = new Handler(Looper.getMainLooper());

    /* compiled from: UgCallbackCenter.java */
    /* loaded from: classes9.dex */
    public interface a<T> {
        void onCall(T t);
    }

    public static <EVENT> void register(a<EVENT> aVar) {
        register(null, aVar);
    }

    public static <EVENT> void register(final LifecycleOwner lifecycleOwner, final a<EVENT> aVar) {
        if (aVar == null) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            f18619c.post(new Runnable() { // from class: com.ss.android.ug.bus.c.1
                @Override // java.lang.Runnable
                public void run() {
                    c.register(LifecycleOwner.this, aVar);
                }
            });
            return;
        }
        final Type type = ((ParameterizedType) aVar.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        ConcurrentHashMap<Type, ConcurrentHashMap<a, Object>> concurrentHashMap = f18618b;
        final ConcurrentHashMap<a, Object> concurrentHashMap2 = concurrentHashMap.get(type);
        if (concurrentHashMap2 == null) {
            concurrentHashMap2 = new ConcurrentHashMap<>();
            concurrentHashMap.put(type, concurrentHashMap2);
        }
        concurrentHashMap2.put(aVar, f18617a);
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(new LifecycleObserver() { // from class: com.ss.android.ug.bus.c.2
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                void a() {
                    concurrentHashMap2.remove(aVar);
                    if (concurrentHashMap2.isEmpty()) {
                        c.f18618b.remove(type);
                    }
                }
            });
        }
    }

    public static <EVENT> void unregister(final a<EVENT> aVar) {
        if (aVar == null) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            f18619c.post(new Runnable() { // from class: com.ss.android.ug.bus.c.3
                @Override // java.lang.Runnable
                public void run() {
                    c.unregister(a.this);
                }
            });
            return;
        }
        Type type = ((ParameterizedType) aVar.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        ConcurrentHashMap<Type, ConcurrentHashMap<a, Object>> concurrentHashMap = f18618b;
        ConcurrentHashMap<a, Object> concurrentHashMap2 = concurrentHashMap.get(type);
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.remove(aVar);
            if (concurrentHashMap2.isEmpty()) {
                concurrentHashMap.remove(type);
            }
        }
    }

    public static <Event> void postEvent(final Event event) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            f18619c.post(new Runnable() { // from class: com.ss.android.ug.bus.c.4
                @Override // java.lang.Runnable
                public void run() {
                    c.postEvent(event);
                }
            });
            return;
        }
        ConcurrentHashMap<a, Object> concurrentHashMap = f18618b.get(event.getClass());
        if (concurrentHashMap != null) {
            for (a aVar : concurrentHashMap.keySet()) {
                if (aVar != null) {
                    aVar.onCall(event);
                }
            }
        }
    }
}
