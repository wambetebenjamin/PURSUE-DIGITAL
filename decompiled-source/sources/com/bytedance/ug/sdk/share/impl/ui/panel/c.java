package com.bytedance.ug.sdk.share.impl.ui.panel;

import android.view.View;
import java.io.Serializable;
import java.util.List;
/* compiled from: ISharePanel.java */
/* loaded from: classes8.dex */
public interface c {

    /* compiled from: ISharePanel.java */
    /* loaded from: classes8.dex */
    public interface a extends Serializable {
        void onClick(View view, boolean z, com.bytedance.ug.sdk.share.api.panel.a aVar);

        void onDismiss();
    }

    void dismiss();

    void dismissLoadingView();

    void initSharePanel(com.bytedance.ug.sdk.share.api.panel.b bVar, List<List<com.bytedance.ug.sdk.share.api.panel.a>> list, a aVar);

    boolean isShowing();

    void show();

    void showLoadingView();
}
