package com.oasis.channel;
/* loaded from: classes9.dex */
public interface PayListener {
    void onExtraPayRegister(boolean z, String str);

    void onPayCancel();

    void onPayFail(String str);

    void onPayProgress();

    void onPaySuccess(String str);

    void onPayUnknown();
}
