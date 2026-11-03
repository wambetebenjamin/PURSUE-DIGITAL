package com.bytedance.apm.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.HashSet;
import java.util.Iterator;
/* compiled from: ApmHandlerThread.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static HashSet<HandlerThread> f1455a = new HashSet<>();

    /* renamed from: b  reason: collision with root package name */
    private static volatile Handler f1456b = new Handler(Looper.getMainLooper());

    public static Handler getDefaultMainHandler() {
        return f1456b;
    }

    public static HandlerThread getNewHandlerThread(String str) {
        return getNewHandlerThread(str, -1);
    }

    public static HandlerThread getNewHandlerThread(String str, int i2) {
        Iterator<HandlerThread> it = f1455a.iterator();
        while (it.hasNext()) {
            if (!it.next().isAlive()) {
                it.remove();
            }
        }
        HandlerThread handlerThread = new HandlerThread(str);
        if (i2 != -1) {
            handlerThread.setPriority(i2);
        }
        handlerThread.start();
        f1455a.add(handlerThread);
        return handlerThread;
    }

    public static d getThreadWithHandler(String str) {
        d dVar = new d(str);
        dVar.start();
        return dVar;
    }
}
