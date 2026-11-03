package com.bytedance.ttnet;

import android.os.Handler;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* compiled from: NQEManager.java */
/* loaded from: classes8.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15328a = "j";

    /* renamed from: b  reason: collision with root package name */
    private static Map<a, Handler> f15329b = new HashMap();

    /* compiled from: NQEManager.java */
    /* loaded from: classes8.dex */
    public interface a {
        void a(int i2);
    }

    public static void a(a aVar, Handler handler) {
        Objects.requireNonNull(aVar, "observer == null");
        Objects.requireNonNull(handler, "handler == null");
        synchronized (j.class) {
            if (!f15329b.containsKey(aVar)) {
                f15329b.put(aVar, handler);
            }
        }
    }

    public static void a(a aVar) {
        Objects.requireNonNull(aVar, "observer == null");
        synchronized (j.class) {
            f15329b.remove(aVar);
        }
    }

    private static void a(final int i2) {
        synchronized (j.class) {
            for (Map.Entry<a, Handler> entry : f15329b.entrySet()) {
                final a key = entry.getKey();
                entry.getValue().post(new Runnable() { // from class: com.bytedance.ttnet.j.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.a(i2);
                    }
                });
            }
        }
    }
}
