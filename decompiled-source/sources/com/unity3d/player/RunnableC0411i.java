package com.unity3d.player;
/* renamed from: com.unity3d.player.i  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class RunnableC0411i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private IAssetPackManagerMobileDataConfirmationCallback f18986a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f18987b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0411i(IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback, boolean z) {
        this.f18986a = iAssetPackManagerMobileDataConfirmationCallback;
        this.f18987b = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f18986a.onMobileDataConfirmationResult(this.f18987b);
    }
}
