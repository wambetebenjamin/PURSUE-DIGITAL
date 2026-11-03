package com.google.androidgamesdk;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
/* loaded from: classes6.dex */
final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f16889a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ SwappyDisplayManager f16890b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(SwappyDisplayManager swappyDisplayManager, int i2) {
        this.f16890b = swappyDisplayManager;
        this.f16889a = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Activity activity;
        activity = this.f16890b.mActivity;
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.preferredDisplayModeId = this.f16889a;
        window.setAttributes(attributes);
    }
}
