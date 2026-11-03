package com.bytedance.ttgame.module.compliance.impl.agegate.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import com.bytedance.ttgame.module.compliance.impl.agegate.R;
import gsdk.impl.compliance.agegate.q;
import gsdk.impl.compliance.agegate.r;
import java.util.List;
/* loaded from: classes5.dex */
public class StringScrollPicker extends r<String> {

    /* renamed from: a  reason: collision with root package name */
    private int f14365a;

    /* renamed from: b  reason: collision with root package name */
    private int f14366b;

    /* renamed from: c  reason: collision with root package name */
    private TextPaint f14367c;

    /* renamed from: d  reason: collision with root package name */
    private int f14368d;

    /* renamed from: e  reason: collision with root package name */
    private int f14369e;

    /* renamed from: f  reason: collision with root package name */
    private int f14370f;

    /* renamed from: g  reason: collision with root package name */
    private int f14371g;

    /* renamed from: h  reason: collision with root package name */
    private int f14372h;

    /* renamed from: i  reason: collision with root package name */
    private Layout.Alignment f14373i;

    public StringScrollPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StringScrollPicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f14368d = 24;
        this.f14369e = 32;
        this.f14370f = ViewCompat.MEASURED_STATE_MASK;
        this.f14371g = -7829368;
        this.f14372h = -1;
        this.f14373i = Layout.Alignment.ALIGN_CENTER;
        TextPaint textPaint = new TextPaint(1);
        this.f14367c = textPaint;
        textPaint.setStyle(Paint.Style.FILL);
        this.f14367c.setColor(ViewCompat.MEASURED_STATE_MASK);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.gsdk_account_string_scroll_picker);
            this.f14368d = obtainStyledAttributes.getDimensionPixelSize(R.styleable.gsdk_account_string_scroll_picker_min_text_size, this.f14368d);
            this.f14369e = obtainStyledAttributes.getDimensionPixelSize(R.styleable.gsdk_account_string_scroll_picker_max_text_size, this.f14369e);
            this.f14370f = obtainStyledAttributes.getColor(R.styleable.gsdk_account_string_scroll_picker_start_color, this.f14370f);
            this.f14371g = obtainStyledAttributes.getColor(R.styleable.gsdk_account_string_scroll_picker_end_color, this.f14371g);
            this.f14372h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.gsdk_account_string_scroll_picker_max_line_width, this.f14372h);
            int i2 = obtainStyledAttributes.getInt(R.styleable.gsdk_account_string_scroll_picker_alignment, 1);
            if (i2 == 2) {
                this.f14373i = Layout.Alignment.ALIGN_NORMAL;
            } else if (i2 == 3) {
                this.f14373i = Layout.Alignment.ALIGN_OPPOSITE;
            } else {
                this.f14373i = Layout.Alignment.ALIGN_CENTER;
            }
            obtainStyledAttributes.recycle();
        }
    }

    public int getStartColor() {
        return this.f14370f;
    }

    public int getEndColor() {
        return this.f14371g;
    }

    public int getMinTextSize() {
        return this.f14368d;
    }

    public int getMaxTextSize() {
        return this.f14369e;
    }

    public int getMaxLineWidth() {
        return this.f14372h;
    }

    public void setMaxLineWidth(int i2) {
        this.f14372h = i2;
    }

    public Layout.Alignment getAlignment() {
        return this.f14373i;
    }

    public void setAlignment(Layout.Alignment alignment) {
        this.f14373i = alignment;
    }

    @Override // gsdk.impl.compliance.agegate.r, android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f14365a = getMeasuredWidth();
        this.f14366b = getMeasuredHeight();
        if (this.f14372h < 0) {
            this.f14372h = getItemWidth();
        }
    }

    @Override // gsdk.impl.compliance.agegate.r
    public void a(Canvas canvas, List<String> list, int i2, int i3, float f2, float f3) {
        int i4;
        int i5;
        float itemWidth;
        float itemHeight;
        int i6;
        String str = list.get(i2);
        int itemSize = getItemSize();
        if (i3 == -1) {
            if (f2 < 0.0f) {
                this.f14367c.setTextSize(this.f14368d);
            } else {
                this.f14367c.setTextSize(this.f14368d + (((this.f14369e - i6) * f2) / itemSize));
            }
        } else if (i3 == 0) {
            float f4 = itemSize;
            this.f14367c.setTextSize(this.f14368d + (((this.f14369e - i5) * (f4 - Math.abs(f2))) / f4));
        } else if (i3 != 1) {
            this.f14367c.setTextSize(this.f14368d);
        } else if (f2 > 0.0f) {
            this.f14367c.setTextSize(this.f14368d);
        } else {
            this.f14367c.setTextSize(this.f14368d + (((this.f14369e - i4) * (-f2)) / itemSize));
        }
        StaticLayout staticLayout = new StaticLayout(str, 0, str.length(), this.f14367c, this.f14372h, this.f14373i, 1.0f, 0.0f, true, null, 0);
        float width = staticLayout.getWidth();
        if (f()) {
            itemWidth = f3 + ((getItemWidth() - width) / 2.0f);
            itemHeight = (getItemHeight() - staticLayout.getHeight()) / 2;
        } else {
            itemWidth = (getItemWidth() - width) / 2.0f;
            itemHeight = f3 + ((getItemHeight() - staticLayout.getHeight()) / 2);
        }
        a(i3, itemSize, f2);
        canvas.save();
        canvas.translate(itemWidth, itemHeight);
        staticLayout.draw(canvas);
        canvas.restore();
    }

    private void a(int i2, int i3, float f2) {
        int i4 = this.f14371g;
        if (i2 == -1 || i2 == 1) {
            if ((i2 != -1 || f2 >= 0.0f) && (i2 != 1 || f2 <= 0.0f)) {
                float f3 = i3;
                i4 = q.a(this.f14370f, this.f14371g, (f3 - Math.abs(f2)) / f3);
            }
        } else if (i2 == 0) {
            i4 = q.a(this.f14370f, this.f14371g, Math.abs(f2) / i3);
        }
        this.f14367c.setColor(i4);
    }

    public void setCurrentSelectedItem(String str) {
        List<String> data;
        if (str == null || (data = getData()) == null || data.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < data.size(); i2++) {
            if (str.equals(data.get(i2))) {
                setSelectedPosition(i2);
            }
        }
    }
}
