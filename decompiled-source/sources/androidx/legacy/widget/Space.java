package androidx.legacy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
@Deprecated
/* loaded from: classes.dex */
public class Space extends View {
    @Override // android.view.View
    @Deprecated
    public void draw(Canvas canvas) {
    }

    @Deprecated
    public Space(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        if (getVisibility() == 0) {
            setVisibility(4);
        }
    }

    @Deprecated
    public Space(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public Space(Context context) {
        this(context, null);
    }

    private static int getDefaultSize2(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? i2 : size;
        }
        return Math.min(i2, size);
    }

    @Override // android.view.View
    @Deprecated
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(getDefaultSize2(getSuggestedMinimumWidth(), i2), getDefaultSize2(getSuggestedMinimumHeight(), i3));
    }
}
