package com.twitter.sdk.android.tweetui.internal;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
/* loaded from: classes10.dex */
public abstract class ClickableLinkSpan extends ClickableSpan implements HighlightedClickableSpan {
    private final boolean colored;
    public final int linkColor;
    private boolean selected;
    private final int selectedColor;
    private final boolean underlined;

    public ClickableLinkSpan(int i2, int i3, boolean z) {
        this(i2, i3, true, z);
    }

    ClickableLinkSpan(int i2, int i3, boolean z, boolean z2) {
        this.selectedColor = i2;
        this.linkColor = i3;
        this.colored = z;
        this.underlined = z2;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        if (this.colored) {
            textPaint.setColor(this.linkColor);
        } else {
            textPaint.setColor(textPaint.linkColor);
        }
        if (this.selected) {
            textPaint.bgColor = this.selectedColor;
        } else {
            textPaint.bgColor = 0;
        }
        if (this.underlined) {
            textPaint.setUnderlineText(true);
        }
    }

    @Override // com.twitter.sdk.android.tweetui.internal.HighlightedClickableSpan
    public void select(boolean z) {
        this.selected = z;
    }

    @Override // com.twitter.sdk.android.tweetui.internal.HighlightedClickableSpan
    public boolean isSelected() {
        return this.selected;
    }
}
