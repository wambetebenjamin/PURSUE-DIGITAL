package androidx.core.content.res;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes.dex */
final class ViewingConditions {
    static final ViewingConditions DEFAULT = make(CamUtils.WHITE_POINT_D65, (float) ((CamUtils.yFromLStar(50.0f) * 63.66197723675813d) / 100.0d), 50.0f, 2.0f, false);
    private final float mAw;
    private final float mC;
    private final float mFl;
    private final float mFlRoot;
    private final float mN;
    private final float mNbb;
    private final float mNc;
    private final float mNcb;
    private final float[] mRgbD;
    private final float mZ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getAw() {
        return this.mAw;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getN() {
        return this.mN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getNbb() {
        return this.mNbb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getNcb() {
        return this.mNcb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getC() {
        return this.mC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getNc() {
        return this.mNc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float[] getRgbD() {
        return this.mRgbD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFl() {
        return this.mFl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFlRoot() {
        return this.mFlRoot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getZ() {
        return this.mZ;
    }

    private ViewingConditions(float f2, float f3, float f4, float f5, float f6, float f7, float[] fArr, float f8, float f9, float f10) {
        this.mN = f2;
        this.mAw = f3;
        this.mNbb = f4;
        this.mNcb = f5;
        this.mC = f6;
        this.mNc = f7;
        this.mRgbD = fArr;
        this.mFl = f8;
        this.mFlRoot = f9;
        this.mZ = f10;
    }

    static ViewingConditions make(float[] fArr, float f2, float f3, float f4, boolean z) {
        float[][] fArr2 = CamUtils.XYZ_TO_CAM16RGB;
        float f5 = (fArr[0] * fArr2[0][0]) + (fArr[1] * fArr2[0][1]) + (fArr[2] * fArr2[0][2]);
        float f6 = (fArr[0] * fArr2[1][0]) + (fArr[1] * fArr2[1][1]) + (fArr[2] * fArr2[1][2]);
        float f7 = (fArr[0] * fArr2[2][0]) + (fArr[1] * fArr2[2][1]) + (fArr[2] * fArr2[2][2]);
        float f8 = (f4 / 10.0f) + 0.8f;
        float lerp = ((double) f8) >= 0.9d ? CamUtils.lerp(0.59f, 0.69f, (f8 - 0.9f) * 10.0f) : CamUtils.lerp(0.525f, 0.59f, (f8 - 0.8f) * 10.0f);
        float exp = z ? 1.0f : (1.0f - (((float) Math.exp(((-f2) - 42.0f) / 92.0f)) * 0.2777778f)) * f8;
        double d2 = exp;
        if (d2 > 1.0d) {
            exp = 1.0f;
        } else if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            exp = 0.0f;
        }
        float[] fArr3 = {(((100.0f / f5) * exp) + 1.0f) - exp, (((100.0f / f6) * exp) + 1.0f) - exp, (((100.0f / f7) * exp) + 1.0f) - exp};
        float f9 = 1.0f / ((5.0f * f2) + 1.0f);
        float f10 = f9 * f9 * f9 * f9;
        float f11 = 1.0f - f10;
        float cbrt = (f10 * f2) + (0.1f * f11 * f11 * ((float) Math.cbrt(f2 * 5.0d)));
        float yFromLStar = CamUtils.yFromLStar(f3) / fArr[1];
        double d3 = yFromLStar;
        float sqrt = ((float) Math.sqrt(d3)) + 1.48f;
        float pow = 0.725f / ((float) Math.pow(d3, 0.2d));
        float[] fArr4 = {(float) Math.pow(((fArr3[0] * cbrt) * f5) / 100.0d, 0.42d), (float) Math.pow(((fArr3[1] * cbrt) * f6) / 100.0d, 0.42d), (float) Math.pow(((fArr3[2] * cbrt) * f7) / 100.0d, 0.42d)};
        float[] fArr5 = {(fArr4[0] * 400.0f) / (fArr4[0] + 27.13f), (fArr4[1] * 400.0f) / (fArr4[1] + 27.13f), (fArr4[2] * 400.0f) / (fArr4[2] + 27.13f)};
        return new ViewingConditions(yFromLStar, ((fArr5[0] * 2.0f) + fArr5[1] + (fArr5[2] * 0.05f)) * pow, pow, pow, lerp, f8, fArr3, cbrt, (float) Math.pow(cbrt, 0.25d), sqrt);
    }
}
