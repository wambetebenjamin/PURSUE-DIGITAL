package com.facebook.react.modules.timepicker;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
/* loaded from: classes8.dex */
public class DismissableTimePickerDialog extends TimePickerDialog {
    public DismissableTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener, int i2, int i3, boolean z) {
        super(context, onTimeSetListener, i2, i3, z);
    }

    public DismissableTimePickerDialog(Context context, int i2, TimePickerDialog.OnTimeSetListener onTimeSetListener, int i3, int i4, boolean z) {
        super(context, i2, onTimeSetListener, i3, i4, z);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        if (Build.VERSION.SDK_INT > 19) {
            super.onStop();
        }
    }
}
