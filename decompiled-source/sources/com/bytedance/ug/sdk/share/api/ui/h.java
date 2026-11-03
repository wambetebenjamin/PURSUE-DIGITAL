package com.bytedance.ug.sdk.share.api.ui;
/* compiled from: IVideoGuideDialog.java */
/* loaded from: classes8.dex */
public interface h {

    /* compiled from: IVideoGuideDialog.java */
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
