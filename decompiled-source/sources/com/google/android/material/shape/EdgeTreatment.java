package com.google.android.material.shape;
/* loaded from: classes6.dex */
public class EdgeTreatment {
    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean forceIntersection() {
        return false;
    }

    @Deprecated
    public void getEdgePath(float f2, float f3, ShapePath shapePath) {
        getEdgePath(f2, f2 / 2.0f, f3, shapePath);
    }

    public void getEdgePath(float f2, float f3, float f4, ShapePath shapePath) {
        shapePath.lineTo(f2, 0.0f);
    }
}
