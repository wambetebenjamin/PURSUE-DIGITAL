package com.bytedance.react.framework.LinearGradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.PixelUtil;
/* loaded from: classes2.dex */
public class LinearGradientView extends View {
    private float mAngle;
    private float[] mAngleCenter;
    private float[] mBorderRadii;
    private int[] mColors;
    private float[] mEndPos;
    private float[] mLocations;
    private final Paint mPaint;
    private Path mPathForBorderRadius;
    private LinearGradient mShader;
    private int[] mSize;
    private float[] mStartPos;
    private RectF mTempRectForBorderRadius;
    private boolean mUseAngle;

    public LinearGradientView(Context context) {
        super(context);
        this.mPaint = new Paint(1);
        this.mStartPos = new float[]{0.0f, 0.0f};
        this.mEndPos = new float[]{0.0f, 1.0f};
        this.mUseAngle = false;
        this.mAngleCenter = new float[]{0.5f, 0.5f};
        this.mAngle = 45.0f;
        this.mSize = new int[]{0, 0};
        this.mBorderRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public void setStartPosition(ReadableArray readableArray) {
        this.mStartPos = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        drawGradient();
    }

    public void setEndPosition(ReadableArray readableArray) {
        this.mEndPos = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        drawGradient();
    }

    public void setColors(ReadableArray readableArray) {
        int size = readableArray.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = readableArray.getInt(i2);
        }
        this.mColors = iArr;
        drawGradient();
    }

    public void setLocations(ReadableArray readableArray) {
        int size = readableArray.size();
        float[] fArr = new float[size];
        for (int i2 = 0; i2 < size; i2++) {
            fArr[i2] = (float) readableArray.getDouble(i2);
        }
        this.mLocations = fArr;
        drawGradient();
    }

    public void setUseAngle(boolean z) {
        this.mUseAngle = z;
        drawGradient();
    }

    public void setAngleCenter(ReadableArray readableArray) {
        this.mAngleCenter = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        drawGradient();
    }

    public void setAngle(float f2) {
        this.mAngle = f2;
        drawGradient();
    }

    public void setBorderRadii(ReadableArray readableArray) {
        int size = readableArray.size();
        float[] fArr = new float[size];
        for (int i2 = 0; i2 < size; i2++) {
            fArr[i2] = PixelUtil.toPixelFromDIP((float) readableArray.getDouble(i2));
        }
        this.mBorderRadii = fArr;
        updatePath();
        drawGradient();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        this.mSize = new int[]{i2, i3};
        updatePath();
        drawGradient();
    }

    private float[] calculateGradientLocationWithAngle(float f2) {
        float sqrt = (float) Math.sqrt(2.0d);
        double d2 = (f2 - 90.0f) * 0.017453292f;
        return new float[]{((float) Math.cos(d2)) * sqrt, ((float) Math.sin(d2)) * sqrt};
    }

    private void drawGradient() {
        int[] iArr = this.mColors;
        if (iArr != null) {
            float[] fArr = this.mLocations;
            if (fArr == null || iArr.length == fArr.length) {
                float[] fArr2 = this.mStartPos;
                float[] fArr3 = this.mEndPos;
                if (this.mUseAngle && this.mAngleCenter != null) {
                    float[] calculateGradientLocationWithAngle = calculateGradientLocationWithAngle(this.mAngle);
                    float[] fArr4 = this.mAngleCenter;
                    float[] fArr5 = {fArr4[0] - (calculateGradientLocationWithAngle[0] / 2.0f), fArr4[1] - (calculateGradientLocationWithAngle[1] / 2.0f)};
                    fArr3 = new float[]{fArr4[0] + (calculateGradientLocationWithAngle[0] / 2.0f), fArr4[1] + (calculateGradientLocationWithAngle[1] / 2.0f)};
                    fArr2 = fArr5;
                }
                float f2 = fArr2[0];
                int[] iArr2 = this.mSize;
                LinearGradient linearGradient = new LinearGradient(iArr2[0] * f2, fArr2[1] * iArr2[1], fArr3[0] * iArr2[0], fArr3[1] * iArr2[1], this.mColors, this.mLocations, Shader.TileMode.CLAMP);
                this.mShader = linearGradient;
                this.mPaint.setShader(linearGradient);
                invalidate();
            }
        }
    }

    private void updatePath() {
        if (this.mPathForBorderRadius == null) {
            this.mPathForBorderRadius = new Path();
            this.mTempRectForBorderRadius = new RectF();
        }
        this.mPathForBorderRadius.reset();
        RectF rectF = this.mTempRectForBorderRadius;
        int[] iArr = this.mSize;
        rectF.set(0.0f, 0.0f, iArr[0], iArr[1]);
        this.mPathForBorderRadius.addRoundRect(this.mTempRectForBorderRadius, this.mBorderRadii, Path.Direction.CW);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.mPathForBorderRadius;
        if (path == null) {
            canvas.drawPaint(this.mPaint);
        } else {
            canvas.drawPath(path, this.mPaint);
        }
    }
}
