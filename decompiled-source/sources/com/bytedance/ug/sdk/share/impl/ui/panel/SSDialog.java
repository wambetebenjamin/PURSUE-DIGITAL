package com.bytedance.ug.sdk.share.impl.ui.panel;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
/* loaded from: classes8.dex */
public class SSDialog extends Dialog {
    protected Activity mContext;

    public SSDialog(Activity activity) {
        super(activity);
        this.mContext = activity;
    }

    public SSDialog(Activity activity, int i2) {
        super(activity, i2);
        this.mContext = activity;
    }

    protected SSDialog(Activity activity, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(activity, z, onCancelListener);
        this.mContext = activity;
    }

    public boolean isViewValid() {
        return !this.mContext.isFinishing();
    }

    @Override // android.app.Dialog
    public void show() {
        try {
            if (isViewValid()) {
                super.show();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface, com.bytedance.ug.sdk.share.impl.ui.panel.c
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
