package com.facebook.react.views.switchview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.SwitchCompat;
/* loaded from: classes8.dex */
class ReactSwitch extends SwitchCompat {
    private boolean mAllowChange;
    private Integer mTrackColorForFalse;
    private Integer mTrackColorForTrue;

    public ReactSwitch(Context context) {
        super(context);
        this.mAllowChange = true;
        this.mTrackColorForFalse = null;
        this.mTrackColorForTrue = null;
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.mAllowChange && isChecked() != z) {
            this.mAllowChange = false;
            super.setChecked(z);
            setTrackColor(z);
            return;
        }
        super.setChecked(isChecked());
    }

    void setColor(Drawable drawable, Integer num) {
        if (drawable == null) {
            return;
        }
        if (num == null) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY);
        }
    }

    public void setTrackColor(Integer num) {
        setColor(super.getTrackDrawable(), num);
    }

    public void setThumbColor(Integer num) {
        setColor(super.getThumbDrawable(), num);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOn(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            setTrackColor(z);
        }
        this.mAllowChange = true;
    }

    public void setTrackColorForTrue(Integer num) {
        if (num == this.mTrackColorForTrue) {
            return;
        }
        this.mTrackColorForTrue = num;
        if (isChecked()) {
            setTrackColor(this.mTrackColorForTrue);
        }
    }

    public void setTrackColorForFalse(Integer num) {
        if (num == this.mTrackColorForFalse) {
            return;
        }
        this.mTrackColorForFalse = num;
        if (isChecked()) {
            return;
        }
        setTrackColor(this.mTrackColorForFalse);
    }

    private void setTrackColor(boolean z) {
        Integer num = this.mTrackColorForTrue;
        if (num == null && this.mTrackColorForFalse == null) {
            return;
        }
        if (!z) {
            num = this.mTrackColorForFalse;
        }
        setTrackColor(num);
    }
}
