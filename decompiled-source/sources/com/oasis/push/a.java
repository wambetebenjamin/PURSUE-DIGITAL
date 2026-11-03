package com.oasis.push;

import com.oasis.Logger.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class a extends PushAgent {

    /* renamed from: d  reason: collision with root package name */
    private PushListener f17592d;

    @Override // com.oasis.push.PushAgent
    public void setPushCallback(PushListener pushListener) {
        Logger.i("Oasis.Push", "DefaultPushAgent.setPushCallback");
        this.f17592d = pushListener;
    }

    @Override // com.oasis.push.PushAgent
    public void tryEnableNotification(boolean z) {
        Logger.i("Oasis.Push", "DefaultPushAgent.tryEnableNotification: isShowDialog: " + z);
    }
}
