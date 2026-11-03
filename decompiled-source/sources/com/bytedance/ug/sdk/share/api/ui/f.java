package com.bytedance.ug.sdk.share.api.ui;
/* compiled from: IShareTokenDialog.java */
/* loaded from: classes8.dex */
public interface f {

    /* compiled from: IShareTokenDialog.java */
    /* loaded from: classes8.dex */
    public interface a {
        void onClick(boolean z);

        void onDismiss();
    }

    void dismiss();

    void initTokenDialog(com.bytedance.ug.sdk.share.api.entity.h hVar, a aVar);

    boolean isShowing();

    void show();
}
