package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
/* loaded from: classes.dex */
public class KeyPosition extends KeyPositionBase {
    static final int KEY_TYPE = 2;
    static final String NAME = "KeyPosition";
    private static final String PERCENT_X = "percentX";
    private static final String PERCENT_Y = "percentY";
    private static final String TAG = "KeyPosition";
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;
    String mTransitionEasing = null;
    int mPathMotionArc = UNSET;
    int mDrawPath = 0;
    float mPercentWidth = Float.NaN;
    float mPercentHeight = Float.NaN;
    float mPercentX = Float.NaN;
    float mPercentY = Float.NaN;
    float mAltPercentX = Float.NaN;
    float mAltPercentY = Float.NaN;
    int mPositionType = 0;
    private float mCalculatedPositionX = Float.NaN;
    private float mCalculatedPositionY = Float.NaN;

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    public KeyPosition() {
        this.mType = 2;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyPosition));
    }

    @Override // androidx.constraintlayout.motion.widget.KeyPositionBase
    void calcPosition(int i2, int i3, float f2, float f3, float f4, float f5) {
        int i4 = this.mPositionType;
        if (i4 == 1) {
            calcPathPosition(f2, f3, f4, f5);
        } else if (i4 == 2) {
            calcScreenPosition(i2, i3);
        } else {
            calcCartesianPosition(f2, f3, f4, f5);
        }
    }

    private void calcScreenPosition(int i2, int i3) {
        float f2 = this.mPercentX;
        float f3 = 0;
        this.mCalculatedPositionX = ((i2 - 0) * f2) + f3;
        this.mCalculatedPositionY = ((i3 - 0) * f2) + f3;
    }

    private void calcPathPosition(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        float f8 = this.mPercentX;
        float f9 = this.mPercentY;
        this.mCalculatedPositionX = f2 + (f6 * f8) + ((-f7) * f9);
        this.mCalculatedPositionY = f3 + (f7 * f8) + (f6 * f9);
    }

    private void calcCartesianPosition(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        float f8 = Float.isNaN(this.mPercentX) ? 0.0f : this.mPercentX;
        float f9 = Float.isNaN(this.mAltPercentY) ? 0.0f : this.mAltPercentY;
        float f10 = Float.isNaN(this.mPercentY) ? 0.0f : this.mPercentY;
        this.mCalculatedPositionX = (int) (f2 + (f8 * f6) + ((Float.isNaN(this.mAltPercentX) ? 0.0f : this.mAltPercentX) * f7));
        this.mCalculatedPositionY = (int) (f3 + (f6 * f9) + (f7 * f10));
    }

    @Override // androidx.constraintlayout.motion.widget.KeyPositionBase
    float getPositionX() {
        return this.mCalculatedPositionX;
    }

    @Override // androidx.constraintlayout.motion.widget.KeyPositionBase
    float getPositionY() {
        return this.mCalculatedPositionY;
    }

    @Override // androidx.constraintlayout.motion.widget.KeyPositionBase
    public void positionAttributes(View view, RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr) {
        int i2 = this.mPositionType;
        if (i2 == 1) {
            positionPathAttributes(rectF, rectF2, f2, f3, strArr, fArr);
        } else if (i2 == 2) {
            positionScreenAttributes(view, rectF, rectF2, f2, f3, strArr, fArr);
        } else {
            positionCartAttributes(rectF, rectF2, f2, f3, strArr, fArr);
        }
    }

    void positionPathAttributes(RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        float hypot = (float) Math.hypot(centerX2, centerY2);
        if (hypot < 1.0E-4d) {
            System.out.println("distance ~ 0");
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            return;
        }
        float f4 = centerX2 / hypot;
        float f5 = centerY2 / hypot;
        float f6 = f3 - centerY;
        float f7 = f2 - centerX;
        float f8 = ((f4 * f6) - (f7 * f5)) / hypot;
        float f9 = ((f4 * f7) + (f5 * f6)) / hypot;
        if (strArr[0] != null) {
            if (PERCENT_X.equals(strArr[0])) {
                fArr[0] = f9;
                fArr[1] = f8;
                return;
            }
            return;
        }
        strArr[0] = PERCENT_X;
        strArr[1] = PERCENT_Y;
        fArr[0] = f9;
        fArr[1] = f8;
    }

    void positionScreenAttributes(View view, RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr) {
        rectF.centerX();
        rectF.centerY();
        rectF2.centerX();
        rectF2.centerY();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        if (strArr[0] != null) {
            if (PERCENT_X.equals(strArr[0])) {
                fArr[0] = f2 / width;
                fArr[1] = f3 / height;
                return;
            }
            fArr[1] = f2 / width;
            fArr[0] = f3 / height;
            return;
        }
        strArr[0] = PERCENT_X;
        fArr[0] = f2 / width;
        strArr[1] = PERCENT_Y;
        fArr[1] = f3 / height;
    }

    void positionCartAttributes(RectF rectF, RectF rectF2, float f2, float f3, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        if (strArr[0] != null) {
            if (PERCENT_X.equals(strArr[0])) {
                fArr[0] = (f2 - centerX) / centerX2;
                fArr[1] = (f3 - centerY) / centerY2;
                return;
            }
            fArr[1] = (f2 - centerX) / centerX2;
            fArr[0] = (f3 - centerY) / centerY2;
            return;
        }
        strArr[0] = PERCENT_X;
        fArr[0] = (f2 - centerX) / centerX2;
        strArr[1] = PERCENT_Y;
        fArr[1] = (f3 - centerY) / centerY2;
    }

    @Override // androidx.constraintlayout.motion.widget.KeyPositionBase
    public boolean intersects(int i2, int i3, RectF rectF, RectF rectF2, float f2, float f3) {
        calcPosition(i2, i3, rectF.centerX(), rectF.centerY(), rectF2.centerX(), rectF2.centerY());
        return Math.abs(f2 - this.mCalculatedPositionX) < 20.0f && Math.abs(f3 - this.mCalculatedPositionY) < 20.0f;
    }

    /* loaded from: classes.dex */
    private static class Loader {
        private static final int CURVE_FIT = 4;
        private static final int DRAW_PATH = 5;
        private static final int FRAME_POSITION = 2;
        private static final int PATH_MOTION_ARC = 10;
        private static final int PERCENT_HEIGHT = 12;
        private static final int PERCENT_WIDTH = 11;
        private static final int PERCENT_X = 6;
        private static final int PERCENT_Y = 7;
        private static final int SIZE_PERCENT = 8;
        private static final int TARGET_ID = 1;
        private static final int TRANSITION_EASING = 3;
        private static final int TYPE = 9;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyPosition_motionTarget, 1);
            mAttrMap.append(R.styleable.KeyPosition_framePosition, 2);
            mAttrMap.append(R.styleable.KeyPosition_transitionEasing, 3);
            mAttrMap.append(R.styleable.KeyPosition_curveFit, 4);
            mAttrMap.append(R.styleable.KeyPosition_drawPath, 5);
            mAttrMap.append(R.styleable.KeyPosition_percentX, 6);
            mAttrMap.append(R.styleable.KeyPosition_percentY, 7);
            mAttrMap.append(R.styleable.KeyPosition_keyPositionType, 9);
            mAttrMap.append(R.styleable.KeyPosition_sizePercent, 8);
            mAttrMap.append(R.styleable.KeyPosition_percentWidth, 11);
            mAttrMap.append(R.styleable.KeyPosition_percentHeight, 12);
            mAttrMap.append(R.styleable.KeyPosition_pathMotionArc, 10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void read(KeyPosition keyPosition, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (mAttrMap.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            keyPosition.mTargetId = typedArray.getResourceId(index, keyPosition.mTargetId);
                            if (keyPosition.mTargetId == -1) {
                                keyPosition.mTargetString = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyPosition.mTargetString = typedArray.getString(index);
                            break;
                        } else {
                            keyPosition.mTargetId = typedArray.getResourceId(index, keyPosition.mTargetId);
                            break;
                        }
                    case 2:
                        keyPosition.mFramePosition = typedArray.getInt(index, keyPosition.mFramePosition);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            keyPosition.mTransitionEasing = typedArray.getString(index);
                            break;
                        } else {
                            keyPosition.mTransitionEasing = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        keyPosition.mCurveFit = typedArray.getInteger(index, keyPosition.mCurveFit);
                        break;
                    case 5:
                        keyPosition.mDrawPath = typedArray.getInt(index, keyPosition.mDrawPath);
                        break;
                    case 6:
                        keyPosition.mPercentX = typedArray.getFloat(index, keyPosition.mPercentX);
                        break;
                    case 7:
                        keyPosition.mPercentY = typedArray.getFloat(index, keyPosition.mPercentY);
                        break;
                    case 8:
                        float f2 = typedArray.getFloat(index, keyPosition.mPercentHeight);
                        keyPosition.mPercentWidth = f2;
                        keyPosition.mPercentHeight = f2;
                        break;
                    case 9:
                        keyPosition.mPositionType = typedArray.getInt(index, keyPosition.mPositionType);
                        break;
                    case 10:
                        keyPosition.mPathMotionArc = typedArray.getInt(index, keyPosition.mPathMotionArc);
                        break;
                    case 11:
                        keyPosition.mPercentWidth = typedArray.getFloat(index, keyPosition.mPercentWidth);
                        break;
                    case 12:
                        keyPosition.mPercentHeight = typedArray.getFloat(index, keyPosition.mPercentHeight);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        break;
                }
            }
            if (keyPosition.mFramePosition == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1127236479:
                if (str.equals("percentWidth")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1017587252:
                if (str.equals("percentHeight")) {
                    c2 = 2;
                    break;
                }
                break;
            case -827014263:
                if (str.equals("drawPath")) {
                    c2 = 3;
                    break;
                }
                break;
            case -200259324:
                if (str.equals("sizePercent")) {
                    c2 = 4;
                    break;
                }
                break;
            case 428090547:
                if (str.equals(PERCENT_X)) {
                    c2 = 5;
                    break;
                }
                break;
            case 428090548:
                if (str.equals(PERCENT_Y)) {
                    c2 = 6;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.mTransitionEasing = obj.toString();
                return;
            case 1:
                this.mPercentWidth = toFloat(obj);
                return;
            case 2:
                this.mPercentHeight = toFloat(obj);
                return;
            case 3:
                this.mDrawPath = toInt(obj);
                return;
            case 4:
                float f2 = toFloat(obj);
                this.mPercentWidth = f2;
                this.mPercentHeight = f2;
                return;
            case 5:
                this.mPercentX = toFloat(obj);
                return;
            case 6:
                this.mPercentY = toFloat(obj);
                return;
            default:
                return;
        }
    }
}
