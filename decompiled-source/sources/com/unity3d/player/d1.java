package com.unity3d.player;

import android.content.Context;
import java.util.concurrent.Semaphore;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class d1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f18957a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ int f18958b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f18959c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ int f18960d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ boolean f18961e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ long f18962f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ long f18963g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ i1 f18964h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d1(i1 i1Var, String str, int i2, int i3, int i4, boolean z, long j2, long j3) {
        this.f18964h = i1Var;
        this.f18957a = str;
        this.f18958b = i2;
        this.f18959c = i3;
        this.f18960d = i4;
        this.f18961e = z;
        this.f18962f = j2;
        this.f18963g = j3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a1 a1Var;
        Context context;
        UnityPlayer unityPlayer;
        a1 a1Var2;
        UnityPlayer unityPlayer2;
        UnityPlayer unityPlayer3;
        a1 a1Var3;
        Semaphore semaphore;
        i1 i1Var = this.f18964h;
        a1Var = i1Var.f18996f;
        if (a1Var != null) {
            AbstractC0445z.Log(5, "Video already playing");
            i1 i1Var2 = this.f18964h;
            i1Var2.f18997g = 2;
            semaphore = i1Var2.f18994d;
            semaphore.release();
            return;
        }
        i1 i1Var3 = this.f18964h;
        context = i1Var3.f18992b;
        unityPlayer = i1Var3.f18991a;
        i1Var.f18996f = new a1(context, unityPlayer, this.f18957a, this.f18958b, this.f18959c, this.f18960d, this.f18961e, this.f18962f, this.f18963g, new c1(this));
        i1 i1Var4 = this.f18964h;
        a1Var2 = i1Var4.f18996f;
        if (a1Var2 != null) {
            unityPlayer2 = i1Var4.f18991a;
            unityPlayer2.bringToFront();
            i1 i1Var5 = this.f18964h;
            unityPlayer3 = i1Var5.f18991a;
            a1Var3 = i1Var5.f18996f;
            unityPlayer3.addView(a1Var3);
        }
    }
}
