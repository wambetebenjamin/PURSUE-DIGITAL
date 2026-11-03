package com.oasis.channel;
/* loaded from: classes9.dex */
public interface BindAccountListener {
    void onBindAccountFail(String str);

    void onBindAccountSuccess(String str);

    void onChangeBind(boolean z, String str);

    void onUnbind(boolean z, String str);
}
