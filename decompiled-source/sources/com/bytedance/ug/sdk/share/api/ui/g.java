package com.bytedance.ug.sdk.share.api.ui;

import android.net.Uri;
import java.util.ArrayList;
/* compiled from: ISystemOptShareTokenDialog.java */
/* loaded from: classes8.dex */
public interface g {

    /* compiled from: ISystemOptShareTokenDialog.java */
    /* loaded from: classes8.dex */
    public interface a {
        void onClick(boolean z, ArrayList<Uri> arrayList);

        void onDismiss();
    }

    void dismiss();

    void initTokenDialog(com.bytedance.ug.sdk.share.api.entity.h hVar, ArrayList<Uri> arrayList, a aVar);

    boolean isShowing();

    void show();
}
