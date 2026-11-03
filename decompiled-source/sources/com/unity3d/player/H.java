package com.unity3d.player;
/* loaded from: classes10.dex */
final class H implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String[] f18821a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ PermissionFragment f18822b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public H(PermissionFragment permissionFragment, String[] strArr) {
        this.f18822b = permissionFragment;
        this.f18821a = strArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f18822b.reportAllDenied(this.f18821a);
    }
}
