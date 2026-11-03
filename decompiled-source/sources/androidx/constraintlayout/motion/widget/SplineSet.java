package androidx.constraintlayout.motion.widget;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.utils.CurveFit;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.core.app.NotificationCompat;
import com.facebook.react.uimanager.ViewProps;
import com.meituan.robust.Constants;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;
/* loaded from: classes.dex */
public abstract class SplineSet {
    private static final String TAG = "SplineSet";
    private int count;
    protected CurveFit mCurveFit;
    private String mType;
    protected int[] mTimePoints = new int[10];
    protected float[] mValues = new float[10];

    public abstract void setProperty(View view, float f2);

    public String toString() {
        String str = this.mType;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.count; i2++) {
            str = str + Constants.ARRAY_TYPE + this.mTimePoints[i2] + " , " + decimalFormat.format(this.mValues[i2]) + "] ";
        }
        return str;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public float get(float f2) {
        return (float) this.mCurveFit.getPos(f2, 0);
    }

    public float getSlope(float f2) {
        return (float) this.mCurveFit.getSlope(f2, 0);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SplineSet makeCustomSpline(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new CustomSet(str, sparseArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SplineSet makeSpline(String str) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1249320806:
                if (str.equals("rotationX")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1001078227:
                if (str.equals(NotificationCompat.CATEGORY_PROGRESS)) {
                    c2 = 5;
                    break;
                }
                break;
            case -908189618:
                if (str.equals(ViewProps.SCALE_X)) {
                    c2 = 6;
                    break;
                }
                break;
            case -908189617:
                if (str.equals(ViewProps.SCALE_Y)) {
                    c2 = 7;
                    break;
                }
                break;
            case -797520672:
                if (str.equals("waveVariesBy")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -760884510:
                if (str.equals("transformPivotX")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -760884509:
                if (str.equals("transformPivotY")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -40300674:
                if (str.equals(ViewProps.ROTATION)) {
                    c2 = 11;
                    break;
                }
                break;
            case -4379043:
                if (str.equals(ViewProps.ELEVATION)) {
                    c2 = '\f';
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c2 = 14;
                    break;
                }
                break;
            case 156108012:
                if (str.equals("waveOffset")) {
                    c2 = 15;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return new RotationXset();
            case 1:
                return new RotationYset();
            case 2:
                return new TranslationXset();
            case 3:
                return new TranslationYset();
            case 4:
                return new TranslationZset();
            case 5:
                return new ProgressSet();
            case 6:
                return new ScaleXset();
            case 7:
                return new ScaleYset();
            case '\b':
                return new AlphaSet();
            case '\t':
                return new PivotXset();
            case '\n':
                return new PivotYset();
            case 11:
                return new RotationSet();
            case '\f':
                return new ElevationSet();
            case '\r':
                return new PathRotate();
            case 14:
                return new AlphaSet();
            case 15:
                return new AlphaSet();
            default:
                return null;
        }
    }

    public void setPoint(int i2, float f2) {
        int[] iArr = this.mTimePoints;
        if (iArr.length < this.count + 1) {
            this.mTimePoints = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.mValues;
            this.mValues = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.mTimePoints;
        int i3 = this.count;
        iArr2[i3] = i2;
        this.mValues[i3] = f2;
        this.count = i3 + 1;
    }

    public void setup(int i2) {
        int i3;
        int i4 = this.count;
        if (i4 == 0) {
            return;
        }
        Sort.doubleQuickSort(this.mTimePoints, this.mValues, 0, i4 - 1);
        int i5 = 1;
        for (int i6 = 1; i6 < this.count; i6++) {
            int[] iArr = this.mTimePoints;
            if (iArr[i6 - 1] != iArr[i6]) {
                i5++;
            }
        }
        double[] dArr = new double[i5];
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, i5, 1);
        int i7 = 0;
        for (i3 = 0; i3 < this.count; i3 = i3 + 1) {
            if (i3 > 0) {
                int[] iArr2 = this.mTimePoints;
                i3 = iArr2[i3] == iArr2[i3 - 1] ? i3 + 1 : 0;
            }
            dArr[i7] = this.mTimePoints[i3] * 0.01d;
            dArr2[i7][0] = this.mValues[i3];
            i7++;
        }
        this.mCurveFit = CurveFit.get(i2, dArr, dArr2);
    }

    /* loaded from: classes.dex */
    static class ElevationSet extends SplineSet {
        ElevationSet() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(get(f2));
            }
        }
    }

    /* loaded from: classes.dex */
    static class AlphaSet extends SplineSet {
        AlphaSet() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setAlpha(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class RotationSet extends SplineSet {
        RotationSet() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setRotation(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class RotationXset extends SplineSet {
        RotationXset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setRotationX(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class RotationYset extends SplineSet {
        RotationYset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setRotationY(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class PivotXset extends SplineSet {
        PivotXset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setPivotX(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class PivotYset extends SplineSet {
        PivotYset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setPivotY(get(f2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class PathRotate extends SplineSet {
        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
        }

        PathRotate() {
        }

        public void setPathRotate(View view, float f2, double d2, double d3) {
            view.setRotation(get(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* loaded from: classes.dex */
    static class ScaleXset extends SplineSet {
        ScaleXset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setScaleX(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class ScaleYset extends SplineSet {
        ScaleYset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setScaleY(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class TranslationXset extends SplineSet {
        TranslationXset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setTranslationX(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class TranslationYset extends SplineSet {
        TranslationYset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            view.setTranslationY(get(f2));
        }
    }

    /* loaded from: classes.dex */
    static class TranslationZset extends SplineSet {
        TranslationZset() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(get(f2));
            }
        }
    }

    /* loaded from: classes.dex */
    static class CustomSet extends SplineSet {
        String mAttributeName;
        SparseArray<ConstraintAttribute> mConstraintAttributeList;
        float[] mTempValues;

        public CustomSet(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.mAttributeName = str.split(",")[1];
            this.mConstraintAttributeList = sparseArray;
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setup(int i2) {
            float[] fArr;
            int size = this.mConstraintAttributeList.size();
            int noOfInterpValues = this.mConstraintAttributeList.valueAt(0).noOfInterpValues();
            double[] dArr = new double[size];
            this.mTempValues = new float[noOfInterpValues];
            double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, noOfInterpValues);
            for (int i3 = 0; i3 < size; i3++) {
                dArr[i3] = this.mConstraintAttributeList.keyAt(i3) * 0.01d;
                this.mConstraintAttributeList.valueAt(i3).getValuesToInterpolate(this.mTempValues);
                int i4 = 0;
                while (true) {
                    if (i4 < this.mTempValues.length) {
                        dArr2[i3][i4] = fArr[i4];
                        i4++;
                    }
                }
            }
            this.mCurveFit = CurveFit.get(i2, dArr, dArr2);
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setPoint(int i2, float f2) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        public void setPoint(int i2, ConstraintAttribute constraintAttribute) {
            this.mConstraintAttributeList.append(i2, constraintAttribute);
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            this.mCurveFit.getPos(f2, this.mTempValues);
            this.mConstraintAttributeList.valueAt(0).setInterpolatedValue(view, this.mTempValues);
        }
    }

    /* loaded from: classes.dex */
    static class ProgressSet extends SplineSet {
        boolean mNoMethod = false;

        ProgressSet() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f2) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f2));
            } else if (this.mNoMethod) {
            } else {
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.mNoMethod = true;
                }
                if (method != null) {
                    try {
                        method.invoke(view, Float.valueOf(get(f2)));
                    } catch (IllegalAccessException e2) {
                        Log.e(SplineSet.TAG, "unable to setProgress", e2);
                    } catch (InvocationTargetException e3) {
                        Log.e(SplineSet.TAG, "unable to setProgress", e3);
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Sort {
        private Sort() {
        }

        static void doubleQuickSort(int[] iArr, float[] fArr, int i2, int i3) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i3;
            iArr2[1] = i2;
            int i4 = 2;
            while (i4 > 0) {
                int i5 = i4 - 1;
                int i6 = iArr2[i5];
                i4 = i5 - 1;
                int i7 = iArr2[i4];
                if (i6 < i7) {
                    int partition = partition(iArr, fArr, i6, i7);
                    int i8 = i4 + 1;
                    iArr2[i4] = partition - 1;
                    int i9 = i8 + 1;
                    iArr2[i8] = i6;
                    int i10 = i9 + 1;
                    iArr2[i9] = i7;
                    i4 = i10 + 1;
                    iArr2[i10] = partition + 1;
                }
            }
        }

        private static int partition(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i3];
            int i5 = i2;
            while (i2 < i3) {
                if (iArr[i2] <= i4) {
                    swap(iArr, fArr, i5, i2);
                    i5++;
                }
                i2++;
            }
            swap(iArr, fArr, i5, i3);
            return i5;
        }

        private static void swap(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float f2 = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = f2;
        }
    }
}
