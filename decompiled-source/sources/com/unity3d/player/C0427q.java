package com.unity3d.player;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.q  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0427q {

    /* renamed from: a  reason: collision with root package name */
    private final Context f19037a;

    /* renamed from: b  reason: collision with root package name */
    private final AudioManager f19038b;

    /* renamed from: c  reason: collision with root package name */
    private C0423o f19039c;

    public C0427q(Context context) {
        this.f19037a = context;
        this.f19038b = (AudioManager) context.getSystemService("audio");
    }

    public final void a() {
        if (this.f19039c != null) {
            this.f19037a.getContentResolver().unregisterContentObserver(this.f19039c);
            this.f19039c = null;
        }
    }

    public final void a(InterfaceC0425p interfaceC0425p) {
        this.f19039c = new C0423o(new Handler(Looper.getMainLooper()), this.f19038b, interfaceC0425p);
        this.f19037a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.f19039c);
    }
}
