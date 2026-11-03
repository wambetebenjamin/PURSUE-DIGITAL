package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;
/* loaded from: classes10.dex */
public class ToggleImageButton extends ImageButton {
    private static final int[] STATE_TOGGLED_ON = {R.attr.state_toggled_on};
    String contentDescriptionOff;
    String contentDescriptionOn;
    boolean isToggledOn;
    final boolean toggleOnClick;

    public ToggleImageButton(Context context) {
        this(context, null);
    }

    public ToggleImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ToggleImageButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray typedArray = null;
        try {
            typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ToggleImageButton, i2, 0);
            String string = typedArray.getString(R.styleable.ToggleImageButton_contentDescriptionOn);
            String string2 = typedArray.getString(R.styleable.ToggleImageButton_contentDescriptionOff);
            this.contentDescriptionOn = string == null ? (String) getContentDescription() : string;
            this.contentDescriptionOff = string2 == null ? (String) getContentDescription() : string2;
            this.toggleOnClick = typedArray.getBoolean(R.styleable.ToggleImageButton_toggleOnClick, true);
            setToggledOn(false);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (this.isToggledOn) {
            mergeDrawableStates(onCreateDrawableState, STATE_TOGGLED_ON);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public boolean performClick() {
        if (this.toggleOnClick) {
            toggle();
        }
        return super.performClick();
    }

    public void setToggledOn(boolean z) {
        this.isToggledOn = z;
        setContentDescription(z ? this.contentDescriptionOn : this.contentDescriptionOff);
        refreshDrawableState();
    }

    public void toggle() {
        setToggledOn(!this.isToggledOn);
    }

    public boolean isToggledOn() {
        return this.isToggledOn;
    }
}
