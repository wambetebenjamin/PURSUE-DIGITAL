package com.google.android.play.core.assetpacks.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.google.android.play.core.listener.StateUpdatedListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    protected final o f16821a;

    /* renamed from: c  reason: collision with root package name */
    private final IntentFilter f16823c;

    /* renamed from: d  reason: collision with root package name */
    private final Context f16824d;

    /* renamed from: b  reason: collision with root package name */
    protected final Set f16822b = new HashSet();

    /* renamed from: e  reason: collision with root package name */
    private m f16825e = null;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f16826f = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public n(o oVar, IntentFilter intentFilter, Context context) {
        this.f16821a = oVar;
        this.f16823c = intentFilter;
        this.f16824d = ag.a(context);
    }

    private final void a() {
        m mVar;
        if ((this.f16826f || !this.f16822b.isEmpty()) && this.f16825e == null) {
            this.f16825e = new m(this, null);
            if (Build.VERSION.SDK_INT >= 33) {
                this.f16824d.registerReceiver(this.f16825e, this.f16823c, 2);
            } else {
                this.f16824d.registerReceiver(this.f16825e, this.f16823c);
            }
        }
        if (this.f16826f || !this.f16822b.isEmpty() || (mVar = this.f16825e) == null) {
            return;
        }
        this.f16824d.unregisterReceiver(mVar);
        this.f16825e = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void c(Context context, Intent intent);

    public final synchronized void e() {
        this.f16821a.d("clearListeners", new Object[0]);
        this.f16822b.clear();
        a();
    }

    public final synchronized void f(StateUpdatedListener stateUpdatedListener) {
        this.f16821a.d("registerListener", new Object[0]);
        aj.a(stateUpdatedListener, "Registered Play Core listener should not be null.");
        this.f16822b.add(stateUpdatedListener);
        a();
    }

    public final synchronized void g(boolean z) {
        this.f16826f = z;
        a();
    }

    public final synchronized void h(StateUpdatedListener stateUpdatedListener) {
        this.f16821a.d("unregisterListener", new Object[0]);
        aj.a(stateUpdatedListener, "Unregistered Play Core listener should not be null.");
        this.f16822b.remove(stateUpdatedListener);
        a();
    }

    public final synchronized void i(Object obj) {
        Iterator it = new HashSet(this.f16822b).iterator();
        while (it.hasNext()) {
            ((StateUpdatedListener) it.next()).onStateUpdate(obj);
        }
    }

    public final synchronized boolean j() {
        return this.f16825e != null;
    }
}
