package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
/* loaded from: classes6.dex */
public class ShapeAppearancePathProvider {
    private final ShapePath[] cornerPaths = new ShapePath[4];
    private final Matrix[] cornerTransforms = new Matrix[4];
    private final Matrix[] edgeTransforms = new Matrix[4];
    private final PointF pointF = new PointF();
    private final Path overlappedEdgePath = new Path();
    private final Path boundsPath = new Path();
    private final ShapePath shapePath = new ShapePath();
    private final float[] scratch = new float[2];
    private final float[] scratch2 = new float[2];
    private final Path edgePath = new Path();
    private final Path cornerPath = new Path();
    private boolean edgeIntersectionCheckEnabled = true;

    /* loaded from: classes6.dex */
    public interface PathListener {
        void onCornerPathCreated(ShapePath shapePath, Matrix matrix, int i2);

        void onEdgePathCreated(ShapePath shapePath, Matrix matrix, int i2);
    }

    private float angleOfEdge(int i2) {
        return (i2 + 1) * 90;
    }

    /* loaded from: classes6.dex */
    private static class Lazy {
        static final ShapeAppearancePathProvider INSTANCE = new ShapeAppearancePathProvider();

        private Lazy() {
        }
    }

    public ShapeAppearancePathProvider() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.cornerPaths[i2] = new ShapePath();
            this.cornerTransforms[i2] = new Matrix();
            this.edgeTransforms[i2] = new Matrix();
        }
    }

    public static ShapeAppearancePathProvider getInstance() {
        return Lazy.INSTANCE;
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f2, RectF rectF, Path path) {
        calculatePath(shapeAppearanceModel, f2, rectF, null, path);
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f2, RectF rectF, PathListener pathListener, Path path) {
        path.rewind();
        this.overlappedEdgePath.rewind();
        this.boundsPath.rewind();
        this.boundsPath.addRect(rectF, Path.Direction.CW);
        ShapeAppearancePathSpec shapeAppearancePathSpec = new ShapeAppearancePathSpec(shapeAppearanceModel, f2, rectF, pathListener, path);
        for (int i2 = 0; i2 < 4; i2++) {
            setCornerPathAndTransform(shapeAppearancePathSpec, i2);
            setEdgePathAndTransform(i2);
        }
        for (int i3 = 0; i3 < 4; i3++) {
            appendCornerPath(shapeAppearancePathSpec, i3);
            appendEdgePath(shapeAppearancePathSpec, i3);
        }
        path.close();
        this.overlappedEdgePath.close();
        if (Build.VERSION.SDK_INT < 19 || this.overlappedEdgePath.isEmpty()) {
            return;
        }
        path.op(this.overlappedEdgePath, Path.Op.UNION);
    }

    private void setCornerPathAndTransform(ShapeAppearancePathSpec shapeAppearancePathSpec, int i2) {
        getCornerTreatmentForIndex(i2, shapeAppearancePathSpec.shapeAppearanceModel).getCornerPath(this.cornerPaths[i2], 90.0f, shapeAppearancePathSpec.interpolation, shapeAppearancePathSpec.bounds, getCornerSizeForIndex(i2, shapeAppearancePathSpec.shapeAppearanceModel));
        float angleOfEdge = angleOfEdge(i2);
        this.cornerTransforms[i2].reset();
        getCoordinatesOfCorner(i2, shapeAppearancePathSpec.bounds, this.pointF);
        this.cornerTransforms[i2].setTranslate(this.pointF.x, this.pointF.y);
        this.cornerTransforms[i2].preRotate(angleOfEdge);
    }

    private void setEdgePathAndTransform(int i2) {
        this.scratch[0] = this.cornerPaths[i2].getEndX();
        this.scratch[1] = this.cornerPaths[i2].getEndY();
        this.cornerTransforms[i2].mapPoints(this.scratch);
        float angleOfEdge = angleOfEdge(i2);
        this.edgeTransforms[i2].reset();
        Matrix matrix = this.edgeTransforms[i2];
        float[] fArr = this.scratch;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.edgeTransforms[i2].preRotate(angleOfEdge);
    }

    private void appendCornerPath(ShapeAppearancePathSpec shapeAppearancePathSpec, int i2) {
        this.scratch[0] = this.cornerPaths[i2].getStartX();
        this.scratch[1] = this.cornerPaths[i2].getStartY();
        this.cornerTransforms[i2].mapPoints(this.scratch);
        if (i2 == 0) {
            Path path = shapeAppearancePathSpec.path;
            float[] fArr = this.scratch;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = shapeAppearancePathSpec.path;
            float[] fArr2 = this.scratch;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.cornerPaths[i2].applyToPath(this.cornerTransforms[i2], shapeAppearancePathSpec.path);
        if (shapeAppearancePathSpec.pathListener != null) {
            shapeAppearancePathSpec.pathListener.onCornerPathCreated(this.cornerPaths[i2], this.cornerTransforms[i2], i2);
        }
    }

    private void appendEdgePath(ShapeAppearancePathSpec shapeAppearancePathSpec, int i2) {
        int i3 = (i2 + 1) % 4;
        this.scratch[0] = this.cornerPaths[i2].getEndX();
        this.scratch[1] = this.cornerPaths[i2].getEndY();
        this.cornerTransforms[i2].mapPoints(this.scratch);
        this.scratch2[0] = this.cornerPaths[i3].getStartX();
        this.scratch2[1] = this.cornerPaths[i3].getStartY();
        this.cornerTransforms[i3].mapPoints(this.scratch2);
        float[] fArr = this.scratch;
        float f2 = fArr[0];
        float[] fArr2 = this.scratch2;
        float max = Math.max(((float) Math.hypot(f2 - fArr2[0], fArr[1] - fArr2[1])) - 0.001f, 0.0f);
        float edgeCenterForIndex = getEdgeCenterForIndex(shapeAppearancePathSpec.bounds, i2);
        this.shapePath.reset(0.0f, 0.0f);
        EdgeTreatment edgeTreatmentForIndex = getEdgeTreatmentForIndex(i2, shapeAppearancePathSpec.shapeAppearanceModel);
        edgeTreatmentForIndex.getEdgePath(max, edgeCenterForIndex, shapeAppearancePathSpec.interpolation, this.shapePath);
        this.edgePath.reset();
        this.shapePath.applyToPath(this.edgeTransforms[i2], this.edgePath);
        if (this.edgeIntersectionCheckEnabled && Build.VERSION.SDK_INT >= 19 && (edgeTreatmentForIndex.forceIntersection() || pathOverlapsCorner(this.edgePath, i2) || pathOverlapsCorner(this.edgePath, i3))) {
            Path path = this.edgePath;
            path.op(path, this.boundsPath, Path.Op.DIFFERENCE);
            this.scratch[0] = this.shapePath.getStartX();
            this.scratch[1] = this.shapePath.getStartY();
            this.edgeTransforms[i2].mapPoints(this.scratch);
            Path path2 = this.overlappedEdgePath;
            float[] fArr3 = this.scratch;
            path2.moveTo(fArr3[0], fArr3[1]);
            this.shapePath.applyToPath(this.edgeTransforms[i2], this.overlappedEdgePath);
        } else {
            this.shapePath.applyToPath(this.edgeTransforms[i2], shapeAppearancePathSpec.path);
        }
        if (shapeAppearancePathSpec.pathListener != null) {
            shapeAppearancePathSpec.pathListener.onEdgePathCreated(this.shapePath, this.edgeTransforms[i2], i2);
        }
    }

    private boolean pathOverlapsCorner(Path path, int i2) {
        this.cornerPath.reset();
        this.cornerPaths[i2].applyToPath(this.cornerTransforms[i2], this.cornerPath);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.cornerPath.computeBounds(rectF, true);
        path.op(this.cornerPath, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    private float getEdgeCenterForIndex(RectF rectF, int i2) {
        this.scratch[0] = this.cornerPaths[i2].endX;
        this.scratch[1] = this.cornerPaths[i2].endY;
        this.cornerTransforms[i2].mapPoints(this.scratch);
        if (i2 == 1 || i2 == 3) {
            return Math.abs(rectF.centerX() - this.scratch[0]);
        }
        return Math.abs(rectF.centerY() - this.scratch[1]);
    }

    private CornerTreatment getCornerTreatmentForIndex(int i2, ShapeAppearanceModel shapeAppearanceModel) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return shapeAppearanceModel.getTopLeftCorner();
                }
                return shapeAppearanceModel.getTopRightCorner();
            }
            return shapeAppearanceModel.getBottomLeftCorner();
        }
        return shapeAppearanceModel.getBottomRightCorner();
    }

    private CornerSize getCornerSizeForIndex(int i2, ShapeAppearanceModel shapeAppearanceModel) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return shapeAppearanceModel.getTopLeftCornerSize();
                }
                return shapeAppearanceModel.getTopRightCornerSize();
            }
            return shapeAppearanceModel.getBottomLeftCornerSize();
        }
        return shapeAppearanceModel.getBottomRightCornerSize();
    }

    private EdgeTreatment getEdgeTreatmentForIndex(int i2, ShapeAppearanceModel shapeAppearanceModel) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return shapeAppearanceModel.getTopEdge();
                }
                return shapeAppearanceModel.getRightEdge();
            }
            return shapeAppearanceModel.getLeftEdge();
        }
        return shapeAppearanceModel.getBottomEdge();
    }

    private void getCoordinatesOfCorner(int i2, RectF rectF, PointF pointF) {
        if (i2 == 1) {
            pointF.set(rectF.right, rectF.bottom);
        } else if (i2 == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i2 == 3) {
            pointF.set(rectF.left, rectF.top);
        } else {
            pointF.set(rectF.right, rectF.top);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEdgeIntersectionCheckEnable(boolean z) {
        this.edgeIntersectionCheckEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class ShapeAppearancePathSpec {
        public final RectF bounds;
        public final float interpolation;
        public final Path path;
        public final PathListener pathListener;
        public final ShapeAppearanceModel shapeAppearanceModel;

        ShapeAppearancePathSpec(ShapeAppearanceModel shapeAppearanceModel, float f2, RectF rectF, PathListener pathListener, Path path) {
            this.pathListener = pathListener;
            this.shapeAppearanceModel = shapeAppearanceModel;
            this.interpolation = f2;
            this.bounds = rectF;
            this.path = path;
        }
    }
}
