package androidx.constraintlayout.motion.widget;

import android.graphics.RectF;
import android.view.View;
import java.util.HashSet;
/* loaded from: classes.dex */
abstract class KeyPositionBase extends Key {
    protected static final float SELECTION_SLOPE = 20.0f;
    int mCurveFit = UNSET;

    abstract void calcPosition(int i2, int i3, float f2, float f3, float f4, float f5);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
    }

    abstract float getPositionX();

    abstract float getPositionY();

    public abstract boolean intersects(int i2, int i3, RectF rectF, RectF rectF2, float f2, float f3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void positionAttributes(View view, RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr);
}
