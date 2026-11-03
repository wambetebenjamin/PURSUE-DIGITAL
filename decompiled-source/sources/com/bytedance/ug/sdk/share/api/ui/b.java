package com.bytedance.ug.sdk.share.api.ui;

import android.content.DialogInterface;
/* compiled from: IDownloadProgressDialog.java */
/* loaded from: classes8.dex */
public interface b {
    void dismiss();

    void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener);

    void setProgress(int i2);

    void show();
}
