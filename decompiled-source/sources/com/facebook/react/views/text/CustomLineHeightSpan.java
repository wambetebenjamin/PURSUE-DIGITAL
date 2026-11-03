package com.facebook.react.views.text;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;
/* loaded from: classes8.dex */
public class CustomLineHeightSpan implements LineHeightSpan, ReactSpan {
    private final int mHeight;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CustomLineHeightSpan(float f2) {
        this.mHeight = (int) Math.ceil(f2);
    }

    @Override // android.text.style.LineHeightSpan
    public void chooseHeight(CharSequence charSequence, int i2, int i3, int i4, int i5, Paint.FontMetricsInt fontMetricsInt) {
        int i6 = fontMetricsInt.descent;
        int i7 = this.mHeight;
        if (i6 > i7) {
            int min = Math.min(i7, fontMetricsInt.descent);
            fontMetricsInt.descent = min;
            fontMetricsInt.bottom = min;
            fontMetricsInt.ascent = 0;
            fontMetricsInt.top = 0;
        } else if ((-fontMetricsInt.ascent) + fontMetricsInt.descent > this.mHeight) {
            fontMetricsInt.bottom = fontMetricsInt.descent;
            int i8 = (-this.mHeight) + fontMetricsInt.descent;
            fontMetricsInt.ascent = i8;
            fontMetricsInt.top = i8;
        } else if ((-fontMetricsInt.ascent) + fontMetricsInt.bottom > this.mHeight) {
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = fontMetricsInt.ascent + this.mHeight;
        } else {
            int i9 = (-fontMetricsInt.top) + fontMetricsInt.bottom;
            int i10 = this.mHeight;
            if (i9 > i10) {
                fontMetricsInt.top = fontMetricsInt.bottom - this.mHeight;
                return;
            }
            double d2 = (i10 - ((-fontMetricsInt.top) + fontMetricsInt.bottom)) / 2.0f;
            fontMetricsInt.top = (int) (fontMetricsInt.top - Math.ceil(d2));
            fontMetricsInt.bottom = (int) (fontMetricsInt.bottom + Math.floor(d2));
            fontMetricsInt.ascent = fontMetricsInt.top;
            fontMetricsInt.descent = fontMetricsInt.bottom;
        }
    }
}
