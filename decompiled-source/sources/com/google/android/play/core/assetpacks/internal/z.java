package com.google.android.play.core.assetpacks.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class z {

    /* renamed from: a */
    private static final Map f16840a = new HashMap();

    /* renamed from: b */
    private final Context f16841b;

    /* renamed from: c */
    private final o f16842c;

    /* renamed from: d */
    private final String f16843d;

    /* renamed from: h */
    private boolean f16847h;

    /* renamed from: i */
    private final Intent f16848i;
    private ServiceConnection m;
    private IInterface n;
    private final com.google.android.play.core.assetpacks.z o;

    /* renamed from: e */
    private final List f16844e = new ArrayList();

    /* renamed from: f */
    private final Set f16845f = new HashSet();

    /* renamed from: g */
    private final Object f16846g = new Object();
    private final IBinder.DeathRecipient k = new IBinder.DeathRecipient() { // from class: com.google.android.play.core.assetpacks.internal.r
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            z.j(z.this);
        }
    };
    private final AtomicInteger l = new AtomicInteger(0);

    /* renamed from: j */
    private final WeakReference f16849j = new WeakReference(null);

    public z(Context context, o oVar, String str, Intent intent, com.google.android.play.core.assetpacks.z zVar, u uVar) {
        this.f16841b = context;
        this.f16842c = oVar;
        this.f16843d = str;
        this.f16848i = intent;
        this.o = zVar;
    }

    public static /* synthetic */ void j(z zVar) {
        zVar.f16842c.d("reportBinderDeath", new Object[0]);
        u uVar = (u) zVar.f16849j.get();
        if (uVar == null) {
            zVar.f16842c.d("%s : Binder has died.", zVar.f16843d);
            for (p pVar : zVar.f16844e) {
                pVar.c(zVar.v());
            }
            zVar.f16844e.clear();
        } else {
            zVar.f16842c.d("calling onBinderDied", new Object[0]);
            uVar.a();
        }
        synchronized (zVar.f16846g) {
            zVar.w();
        }
    }

    public static /* bridge */ /* synthetic */ void n(z zVar, final TaskCompletionSource taskCompletionSource) {
        zVar.f16845f.add(taskCompletionSource);
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.play.core.assetpacks.internal.q
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                z.this.t(taskCompletionSource, task);
            }
        });
    }

    public static /* bridge */ /* synthetic */ void q(z zVar) {
        zVar.f16842c.d("linkToDeath", new Object[0]);
        try {
            zVar.n.asBinder().linkToDeath(zVar.k, 0);
        } catch (RemoteException e2) {
            zVar.f16842c.c(e2, "linkToDeath failed", new Object[0]);
        }
    }

    public static /* bridge */ /* synthetic */ void r(z zVar) {
        zVar.f16842c.d("unlinkToDeath", new Object[0]);
        zVar.n.asBinder().unlinkToDeath(zVar.k, 0);
    }

    private final RemoteException v() {
        return Build.VERSION.SDK_INT < 15 ? new RemoteException() : new RemoteException(String.valueOf(this.f16843d).concat(" : Binder has died."));
    }

    public final void w() {
        for (TaskCompletionSource taskCompletionSource : this.f16845f) {
            taskCompletionSource.trySetException(v());
        }
        this.f16845f.clear();
    }

    public final Handler c() {
        Handler handler;
        Map map = f16840a;
        synchronized (map) {
            if (!map.containsKey(this.f16843d)) {
                HandlerThread handlerThread = new HandlerThread(this.f16843d, 10);
                handlerThread.start();
                map.put(this.f16843d, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.f16843d);
        }
        return handler;
    }

    public final IInterface e() {
        return this.n;
    }

    public final void s(p pVar, TaskCompletionSource taskCompletionSource) {
        c().post(new s(this, pVar.b(), taskCompletionSource, pVar));
    }

    public final /* synthetic */ void t(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.f16846g) {
            this.f16845f.remove(taskCompletionSource);
        }
    }

    public final void u(TaskCompletionSource taskCompletionSource) {
        synchronized (this.f16846g) {
            this.f16845f.remove(taskCompletionSource);
        }
        c().post(new t(this));
    }

    public static /* bridge */ /* synthetic */ void p(z zVar, p pVar) {
        if (zVar.n != null || zVar.f16847h) {
            if (zVar.f16847h) {
                zVar.f16842c.d("Waiting to bind to the service.", new Object[0]);
                zVar.f16844e.add(pVar);
                return;
            }
            pVar.run();
            return;
        }
        zVar.f16842c.d("Initiate binding to the service.", new Object[0]);
        zVar.f16844e.add(pVar);
        y yVar = new y(zVar, null);
        zVar.m = yVar;
        zVar.f16847h = true;
        if (zVar.f16841b.bindService(zVar.f16848i, yVar, 1)) {
            return;
        }
        zVar.f16842c.d("Failed to bind to the service.", new Object[0]);
        zVar.f16847h = false;
        for (p pVar2 : zVar.f16844e) {
            pVar2.c(new aa());
        }
        zVar.f16844e.clear();
    }
}
