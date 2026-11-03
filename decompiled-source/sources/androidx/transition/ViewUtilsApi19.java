package androidx.transition;

import android.view.View;
/* loaded from: classes.dex */
class ViewUtilsApi19 extends ViewUtilsBase {
    private static boolean sTryHiddenTransitionAlpha = true;

    @Override // androidx.transition.ViewUtilsBase
    public void clearNonTransitionAlpha(View view) {
    }

    @Override // androidx.transition.ViewUtilsBase
    public void saveNonTransitionAlpha(View view) {
    }

    @Override // androidx.transition.ViewUtilsBase
    public void setTransitionAlpha(View view, float f2) {
        if (sTryHiddenTransitionAlpha) {
            try {
                view.setTransitionAlpha(f2);
                return;
            } catch (NoSuchMethodError unused) {
                sTryHiddenTransitionAlpha = false;
            }
        }
        view.setAlpha(f2);
    }

    @Override // androidx.transition.ViewUtilsBase
    public float getTransitionAlpha(View view) {
        if (sTryHiddenTransitionAlpha) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                sTryHiddenTransitionAlpha = false;
            }
        }
        return view.getAlpha();
    }
}
