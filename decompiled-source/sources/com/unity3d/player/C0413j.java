package com.unity3d.player;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.OnSuccessListener;
/* renamed from: com.unity3d.player.j  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class C0413j implements OnSuccessListener {

    /* renamed from: a  reason: collision with root package name */
    private IAssetPackManagerMobileDataConfirmationCallback f19000a;

    /* renamed from: b  reason: collision with root package name */
    private Looper f19001b = Looper.myLooper();

    public C0413j(IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        this.f19000a = iAssetPackManagerMobileDataConfirmationCallback;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        if (this.f19000a != null) {
            new Handler(this.f19001b).post(new RunnableC0411i(this.f19000a, num.intValue() == -1));
        }
    }
}
