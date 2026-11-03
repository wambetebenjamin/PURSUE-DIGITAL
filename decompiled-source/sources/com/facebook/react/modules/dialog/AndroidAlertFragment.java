package com.facebook.react.modules.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.facebook.react.modules.dialog.DialogModule;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class AndroidAlertFragment extends DialogFragment implements DialogInterface.OnClickListener {
    @Nullable
    private final DialogModule.AlertFragmentListener mListener;

    public AndroidAlertFragment() {
        this.mListener = null;
    }

    public AndroidAlertFragment(@Nullable DialogModule.AlertFragmentListener alertFragmentListener, Bundle bundle) {
        this.mListener = alertFragmentListener;
        setArguments(bundle);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return AlertFragment.createDialog(getActivity(), getArguments(), this);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        DialogModule.AlertFragmentListener alertFragmentListener = this.mListener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onClick(dialogInterface, i2);
        }
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogModule.AlertFragmentListener alertFragmentListener = this.mListener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onDismiss(dialogInterface);
        }
    }
}
