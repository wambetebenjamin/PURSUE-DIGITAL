package com.bytedance.ug.sdk.share.api.ui;

import com.bytedance.ug.sdk.share.api.entity.q;
/* compiled from: IRecognizeTokenDialog.java */
/* loaded from: classes8.dex */
public interface d {

    /* compiled from: IRecognizeTokenDialog.java */
    /* loaded from: classes8.dex */
    public interface a {
        void onClick(boolean z, com.bytedance.ug.sdk.share.api.entity.f fVar, q qVar);

        void onDismiss();
    }

    void dismiss();

    void initTokenDialog(q qVar, a aVar);

    boolean isShowing();

    void show();
}
