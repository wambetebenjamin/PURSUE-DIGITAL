package com.unity3d.player;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class C {

    /* renamed from: b  reason: collision with root package name */
    protected Runnable f18806b;

    /* renamed from: a  reason: collision with root package name */
    protected com.unity3d.player.a.c f18805a = null;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f18807c = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public C(Runnable runnable) {
        this.f18806b = runnable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerOnBackPressedCallback() {
        if (this.f18805a != null) {
            return;
        }
        this.f18805a = new B(this.f18806b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void unregisterOnBackPressedCallback() {
        this.f18805a = null;
    }
}
