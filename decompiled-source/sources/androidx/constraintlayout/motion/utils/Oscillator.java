package androidx.constraintlayout.motion.utils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Arrays;
/* loaded from: classes.dex */
public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;
    double[] mArea;
    int mType;
    float[] mPeriod = new float[0];
    double[] mPosition = new double[0];
    double PI2 = 6.283185307179586d;
    private boolean mNormalized = false;

    public String toString() {
        return "pos =" + Arrays.toString(this.mPosition) + " period=" + Arrays.toString(this.mPeriod);
    }

    public void setType(int i2) {
        this.mType = i2;
    }

    public void addPoint(double d2, float f2) {
        int length = this.mPeriod.length + 1;
        int binarySearch = Arrays.binarySearch(this.mPosition, d2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        this.mPosition = Arrays.copyOf(this.mPosition, length);
        this.mPeriod = Arrays.copyOf(this.mPeriod, length);
        this.mArea = new double[length];
        double[] dArr = this.mPosition;
        System.arraycopy(dArr, binarySearch, dArr, binarySearch + 1, (length - binarySearch) - 1);
        this.mPosition[binarySearch] = d2;
        this.mPeriod[binarySearch] = f2;
        this.mNormalized = false;
    }

    public void normalize() {
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        int i2;
        int i3 = 0;
        double d2 = 0.0d;
        while (true) {
            if (i3 >= this.mPeriod.length) {
                break;
            }
            d2 += fArr[i3];
            i3++;
        }
        double d3 = 0.0d;
        int i4 = 1;
        while (true) {
            if (i4 >= this.mPeriod.length) {
                break;
            }
            double[] dArr = this.mPosition;
            d3 += (dArr[i4] - dArr[i4 - 1]) * ((fArr2[i2] + fArr2[i4]) / 2.0f);
            i4++;
        }
        int i5 = 0;
        while (true) {
            float[] fArr4 = this.mPeriod;
            if (i5 >= fArr4.length) {
                break;
            }
            fArr4[i5] = (float) (fArr4[i5] * (d2 / d3));
            i5++;
        }
        this.mArea[0] = 0.0d;
        int i6 = 1;
        while (true) {
            if (i6 < this.mPeriod.length) {
                int i7 = i6 - 1;
                double[] dArr2 = this.mPosition;
                double d4 = dArr2[i6] - dArr2[i7];
                double[] dArr3 = this.mArea;
                dArr3[i6] = dArr3[i7] + (d4 * ((fArr3[i7] + fArr3[i6]) / 2.0f));
                i6++;
            } else {
                this.mNormalized = true;
                return;
            }
        }
    }

    double getP(double d2) {
        if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d2 = 0.0d;
        } else if (d2 > 1.0d) {
            d2 = 1.0d;
        }
        int binarySearch = Arrays.binarySearch(this.mPosition, d2);
        if (binarySearch > 0) {
            return 1.0d;
        }
        if (binarySearch != 0) {
            int i2 = (-binarySearch) - 1;
            float[] fArr = this.mPeriod;
            int i3 = i2 - 1;
            double d3 = fArr[i2] - fArr[i3];
            double[] dArr = this.mPosition;
            double d4 = d3 / (dArr[i2] - dArr[i3]);
            return this.mArea[i3] + ((fArr[i3] - (dArr[i3] * d4)) * (d2 - dArr[i3])) + ((d4 * ((d2 * d2) - (dArr[i3] * dArr[i3]))) / 2.0d);
        }
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public double getValue(double d2) {
        double abs;
        switch (this.mType) {
            case 1:
                return Math.signum(0.5d - (getP(d2) % 1.0d));
            case 2:
                abs = Math.abs((((getP(d2) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((getP(d2) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                abs = ((getP(d2) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.PI2 * getP(d2));
            case 6:
                double abs2 = 1.0d - Math.abs(((getP(d2) * 4.0d) % 4.0d) - 2.0d);
                abs = abs2 * abs2;
                break;
            default:
                return Math.sin(this.PI2 * getP(d2));
        }
        return 1.0d - abs;
    }

    double getDP(double d2) {
        if (d2 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d2 = 1.0E-5d;
        } else if (d2 >= 1.0d) {
            d2 = 0.999999d;
        }
        int binarySearch = Arrays.binarySearch(this.mPosition, d2);
        if (binarySearch <= 0 && binarySearch != 0) {
            int i2 = (-binarySearch) - 1;
            float[] fArr = this.mPeriod;
            int i3 = i2 - 1;
            double d3 = fArr[i2] - fArr[i3];
            double[] dArr = this.mPosition;
            double d4 = d3 / (dArr[i2] - dArr[i3]);
            return (fArr[i3] - (d4 * dArr[i3])) + (d2 * d4);
        }
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public double getSlope(double d2) {
        double dp;
        double signum;
        double dp2;
        double dp3;
        double sin;
        switch (this.mType) {
            case 1:
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            case 2:
                dp = getDP(d2) * 4.0d;
                signum = Math.signum((((getP(d2) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return dp * signum;
            case 3:
                dp2 = getDP(d2);
                return dp2 * 2.0d;
            case 4:
                dp2 = -getDP(d2);
                return dp2 * 2.0d;
            case 5:
                dp3 = (-this.PI2) * getDP(d2);
                sin = Math.sin(this.PI2 * getP(d2));
                return dp3 * sin;
            case 6:
                dp = getDP(d2) * 4.0d;
                signum = (((getP(d2) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dp * signum;
            default:
                dp3 = this.PI2 * getDP(d2);
                sin = Math.cos(this.PI2 * getP(d2));
                return dp3 * sin;
        }
    }
}
