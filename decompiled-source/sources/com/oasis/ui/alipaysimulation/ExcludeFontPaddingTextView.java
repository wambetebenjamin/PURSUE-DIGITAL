package com.oasis.ui.alipaysimulation;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.SpannableStringBuilder;
import android.text.style.LineHeightSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
/* loaded from: classes9.dex */
public class ExcludeFontPaddingTextView extends AppCompatTextView {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class a implements LineHeightSpan {
        a() {
        }

        @Override // android.text.style.LineHeightSpan
        public void chooseHeight(CharSequence charSequence, int i2, int i3, int i4, int i5, Paint.FontMetricsInt fontMetricsInt) {
            Rect rect = new Rect();
            ExcludeFontPaddingTextView.this.getPaint().getTextBounds(charSequence.toString(), 0, charSequence.length(), rect);
            int i6 = fontMetricsInt.descent - fontMetricsInt.ascent;
            int max = Math.max((int) ExcludeFontPaddingTextView.this.getTextSize(), rect.bottom - rect.top);
            int abs = Math.abs(fontMetricsInt.ascent - rect.top);
            int i7 = fontMetricsInt.descent - rect.bottom;
            int i8 = (i6 - max) / 2;
            if (i8 < Math.min(abs, i7)) {
                fontMetricsInt.ascent += i8;
                fontMetricsInt.descent -= i8;
            } else if (abs < i7) {
                int i9 = rect.top;
                fontMetricsInt.ascent = i9;
                fontMetricsInt.descent = max + i9;
            } else {
                int i10 = rect.bottom;
                fontMetricsInt.descent = i10;
                fontMetricsInt.ascent = i10 - max;
            }
        }
    }

    public ExcludeFontPaddingTextView(Context context) {
        this(context, null);
    }

    public ExcludeFontPaddingTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExcludeFontPaddingTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    private CharSequence a(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new a(), 0, charSequence.length(), 33);
        return spannableStringBuilder;
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(a(charSequence), bufferType);
    }
}
