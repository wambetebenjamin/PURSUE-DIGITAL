package com.bytedance.frameworks.baselib.network.http.retrofit;

import com.bytedance.frameworks.baselib.network.dispatcher.f;
import com.bytedance.frameworks.baselib.network.dispatcher.g;
import com.bytedance.frameworks.baselib.network.dispatcher.i;
import com.bytedance.frameworks.baselib.network.dispatcher.j;
import com.bytedance.retrofit2.SsRunnable;
import java.util.concurrent.Executor;
/* compiled from: SsHttpExecutor.java */
/* loaded from: classes2.dex */
public class c implements Executor {

    /* renamed from: a  reason: collision with root package name */
    private static volatile g f8281a;

    public static void a(i iVar) {
        f8281a = iVar;
    }

    public static void a(j jVar) {
        i.a(jVar);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        f.a aVar;
        int i2;
        f.a aVar2;
        if (runnable != null) {
            f.a aVar3 = f.a.NORMAL;
            boolean z = false;
            if (runnable instanceof SsRunnable) {
                SsRunnable ssRunnable = (SsRunnable) runnable;
                int priority = ssRunnable.priority();
                if (priority == 0) {
                    aVar2 = f.a.LOW;
                } else if (1 == priority) {
                    aVar2 = f.a.NORMAL;
                } else if (2 == priority) {
                    aVar2 = f.a.HIGH;
                } else if (3 == priority) {
                    aVar2 = f.a.IMMEDIATE;
                } else if (-1 == priority) {
                    aVar2 = f.a.LOWEST;
                } else {
                    aVar2 = f.a.NORMAL;
                }
                z = ssRunnable.isStreaming();
                i2 = ssRunnable.getRequestDelayTime();
                aVar = aVar2;
            } else {
                aVar = aVar3;
                i2 = 0;
            }
            if (f8281a == null) {
                f8281a = i.c();
            }
            com.bytedance.frameworks.baselib.network.dispatcher.b bVar = new com.bytedance.frameworks.baselib.network.dispatcher.b("NetExecutor", aVar, i2, runnable, z);
            if (z) {
                f8281a.a(bVar);
            } else {
                f8281a.b(bVar);
            }
        }
    }
}
