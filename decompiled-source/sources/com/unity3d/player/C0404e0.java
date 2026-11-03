package com.unity3d.player;

import android.database.ContentObserver;
import android.os.Handler;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.e0  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0404e0 extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    private InterfaceC0402d0 f18965a;

    public C0404e0(Handler handler, InterfaceC0402d0 interfaceC0402d0) {
        super(handler);
        this.f18965a = interfaceC0402d0;
    }

    @Override // android.database.ContentObserver
    public final boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        InterfaceC0402d0 interfaceC0402d0 = this.f18965a;
        if (interfaceC0402d0 != null) {
            ((OrientationLockListener) interfaceC0402d0).b();
        }
    }
}
