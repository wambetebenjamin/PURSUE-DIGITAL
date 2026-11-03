package com.google.android.material.shape;
/* loaded from: classes6.dex */
public final class OffsetEdgeTreatment extends EdgeTreatment {
    private final float offset;
    private final EdgeTreatment other;

    public OffsetEdgeTreatment(EdgeTreatment edgeTreatment, float f2) {
        this.other = edgeTreatment;
        this.offset = f2;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f2, float f3, float f4, ShapePath shapePath) {
        this.other.getEdgePath(f2, f3 - this.offset, f4, shapePath);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.shape.EdgeTreatment
    public boolean forceIntersection() {
        return this.other.forceIntersection();
    }
}
