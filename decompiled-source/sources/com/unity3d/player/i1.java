package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class i1 {

    /* renamed from: a  reason: collision with root package name */
    private UnityPlayer f18991a;

    /* renamed from: c  reason: collision with root package name */
    private h1 f18993c;

    /* renamed from: b  reason: collision with root package name */
    private Context f18992b = null;

    /* renamed from: d  reason: collision with root package name */
    private final Semaphore f18994d = new Semaphore(0);

    /* renamed from: e  reason: collision with root package name */
    private final ReentrantLock f18995e = new ReentrantLock();

    /* renamed from: f  reason: collision with root package name */
    private a1 f18996f = null;

    /* renamed from: g  reason: collision with root package name */
    private int f18997g = 2;

    /* renamed from: h  reason: collision with root package name */
    private boolean f18998h = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f18999i = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: -$$Nest$fgetd  reason: not valid java name */
    public static /* bridge */ /* synthetic */ Semaphore m806$$Nest$fgetd(i1 i1Var) {
        return i1Var.f18994d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: -$$Nest$fgete  reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReentrantLock m807$$Nest$fgete(i1 i1Var) {
        return i1Var.f18995e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: -$$Nest$fgeti  reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m809$$Nest$fgeti(i1 i1Var) {
        return i1Var.f18999i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: -$$Nest$fputg  reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m811$$Nest$fputg(i1 i1Var, int i2) {
        i1Var.f18997g = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i1(UnityPlayer unityPlayer) {
        this.f18991a = null;
        this.f18991a = unityPlayer;
    }

    public final void a() {
        this.f18995e.lock();
        a1 a1Var = this.f18996f;
        if (a1Var != null) {
            a1Var.updateVideoLayout();
        }
        this.f18995e.unlock();
    }

    public final boolean a(Context context, String str, int i2, int i3, int i4, boolean z, long j2, long j3, h1 h1Var) {
        this.f18995e.lock();
        this.f18993c = h1Var;
        this.f18992b = context;
        this.f18994d.drainPermits();
        this.f18997g = 2;
        runOnUiThread(new d1(this, str, i2, i3, i4, z, j2, j3));
        boolean z2 = false;
        try {
            this.f18995e.unlock();
            this.f18994d.acquire();
            this.f18995e.lock();
            if (this.f18997g != 2) {
                z2 = true;
            }
        } catch (InterruptedException unused) {
        }
        runOnUiThread(new e1(this));
        runOnUiThread((!z2 || this.f18997g == 3) ? new g1(this) : new f1(this));
        this.f18995e.unlock();
        return z2;
    }

    public final void b() {
        this.f18995e.lock();
        a1 a1Var = this.f18996f;
        if (a1Var != null) {
            if (this.f18997g == 0) {
                a1Var.cancelOnPrepare();
            } else if (this.f18999i) {
                boolean a2 = a1Var.a();
                this.f18998h = a2;
                if (!a2) {
                    this.f18996f.pause();
                }
            }
        }
        this.f18995e.unlock();
    }

    public final void c() {
        this.f18995e.lock();
        a1 a1Var = this.f18996f;
        if (a1Var != null && this.f18999i && !this.f18998h) {
            a1Var.start();
        }
        this.f18995e.unlock();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        Context context = this.f18992b;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            AbstractC0445z.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
