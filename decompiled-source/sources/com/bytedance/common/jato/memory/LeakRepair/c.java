package com.bytedance.common.jato.memory.LeakRepair;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* compiled from: LeakDetectStrategy.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f4829a = new Handler(Looper.getMainLooper());

    /* renamed from: b  reason: collision with root package name */
    private static final d f4830b = new d();

    public static d a() {
        return f4830b;
    }

    public static void a(List<f> list) {
        final ArrayList arrayList = new ArrayList();
        for (f fVar : list) {
            fVar.h();
            if (fVar.c() || fVar.d()) {
                list.remove(fVar);
            } else if (fVar.e()) {
                arrayList.add(fVar);
                list.remove(fVar);
                if (d.f4834b) {
                    Log.d(d.f4833a, "checkAndFixLeakedRefWatchers isTargetLeak:" + fVar.b().get());
                }
            }
        }
        f4829a.post(new Runnable() { // from class: com.bytedance.common.jato.memory.LeakRepair.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.c(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(final List<f> list) {
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.bytedance.common.jato.memory.LeakRepair.c.2
            @Override // android.os.MessageQueue.IdleHandler
            public boolean queueIdle() {
                for (f fVar : list) {
                    try {
                        c.f4830b.a(fVar);
                    } catch (Throwable th) {
                        Log.e(d.f4833a, "reparir Failed: " + th.getMessage());
                        fVar.g();
                    }
                }
                return false;
            }
        });
    }
}
