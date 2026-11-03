package com.unity3d.player;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
/* loaded from: classes10.dex */
public final class U0 extends Thread {

    /* renamed from: a */
    Handler f18905a;

    /* renamed from: b */
    boolean f18906b;

    /* renamed from: c */
    boolean f18907c;

    /* renamed from: d */
    int f18908d;

    /* renamed from: e */
    int f18909e;

    /* renamed from: f */
    int f18910f;

    /* renamed from: g */
    int f18911g;

    /* renamed from: h */
    int f18912h;

    /* renamed from: i */
    final /* synthetic */ UnityPlayer f18913i;

    /* renamed from: -$$Nest$ma */
    public static /* bridge */ /* synthetic */ void m759$$Nest$ma(U0 u0, S0 s0) {
        u0.a(s0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public U0(UnityPlayer unityPlayer) {
        this.f18913i = unityPlayer;
        this.f18906b = false;
        this.f18907c = false;
        this.f18908d = 2;
        this.f18909e = 0;
        this.f18912h = 5;
    }

    public void a(S0 s0) {
        Handler handler = this.f18905a;
        if (handler != null) {
            Message.obtain(handler, 2269, s0).sendToTarget();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        setName("UnityMain");
        Looper.prepare();
        this.f18905a = new Handler(Looper.myLooper(), new T0(this));
        Looper.loop();
    }
}
