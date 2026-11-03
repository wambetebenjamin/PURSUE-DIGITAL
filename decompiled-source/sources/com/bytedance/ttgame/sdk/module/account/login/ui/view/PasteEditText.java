package com.bytedance.ttgame.sdk.module.account.login.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
/* loaded from: classes3.dex */
public class PasteEditText extends AppCompatEditText {
    public PasteEditText(Context context) {
        super(context);
    }

    public PasteEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PasteEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @Override // android.widget.TextView
    protected void onSelectionChanged(int i2, int i3) {
        super.onSelectionChanged(i2, i3);
        if (i2 == i3) {
            setSelection(getText().length());
        }
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView
    public boolean onTextContextMenuItem(int i2) {
        if (i2 == 16908322) {
            setText("");
        }
        return super.onTextContextMenuItem(i2);
    }
}
