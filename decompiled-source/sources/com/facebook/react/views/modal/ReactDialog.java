package com.facebook.react.views.modal;

import android.app.Dialog;
import android.content.Context;
import android.window.OnBackInvokedCallback;
import androidx.core.os.BuildCompat;
import com.facebook.react.views.modal.ReactModalHostView;
/* loaded from: classes8.dex */
public class ReactDialog extends Dialog {
    private ReactModalHostView.OnRequestCloseListener mOnRequestCloseListener;

    public ReactDialog(Context context, int i2) {
        super(context, i2);
    }

    public ReactDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (BuildCompat.isAtLeastT()) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, new OnBackInvokedCallback() { // from class: com.facebook.react.views.modal.ReactDialog.1
                public void onBackInvoked() {
                    if (ReactDialog.this.mOnRequestCloseListener != null) {
                        ReactDialog.this.mOnRequestCloseListener.onRequestClose(ReactDialog.this);
                    }
                }
            });
        }
    }

    public void setOncancellLisner(ReactModalHostView.OnRequestCloseListener onRequestCloseListener) {
        this.mOnRequestCloseListener = onRequestCloseListener;
    }
}
