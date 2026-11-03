package com.bytedance.react.framework.core;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
/* loaded from: classes2.dex */
public class DebugOptionsDialog {
    public static final int SHOW_DEBUG = 1;
    private static final String[] options = {"Display RN debug dialog"};
    private static final int[] options_id = {1};

    /* loaded from: classes2.dex */
    public interface OptionSelectListener {
        void onOptionsSelect(int i2);
    }

    public static Dialog show(Context context, final OptionSelectListener optionSelectListener) {
        AlertDialog create = new AlertDialog.Builder(context).setItems(options, new DialogInterface.OnClickListener() { // from class: com.bytedance.react.framework.core.DebugOptionsDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                OptionSelectListener optionSelectListener2;
                if (i2 < DebugOptionsDialog.options_id.length && (optionSelectListener2 = OptionSelectListener.this) != null) {
                    optionSelectListener2.onOptionsSelect(DebugOptionsDialog.options_id[i2]);
                }
                dialogInterface.dismiss();
            }
        }).create();
        create.show();
        return create;
    }
}
