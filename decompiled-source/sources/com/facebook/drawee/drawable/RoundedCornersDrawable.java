package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class RoundedCornersDrawable extends ForwardingDrawable implements Rounded {
    private int mBorderColor;
    private final Path mBorderPath;
    final float[] mBorderRadii;
    private float mBorderWidth;
    private final RectF mBounds;
    @Nullable
    private RectF mInsideBorderBounds;
    @Nullable
    private Matrix mInsideBorderTransform;
    private boolean mIsCircle;
    private int mOverlayColor;
    private float mPadding;
    final Paint mPaint;
    private final Path mPath;
    private final float[] mRadii;
    private boolean mScaleDownInsideBorders;
    private final RectF mTempRectangle;
    Type mType;

    /* loaded from: classes8.dex */
    public enum Type {
        OVERLAY_COLOR,
        CLIPPING
    }

    public RoundedCornersDrawable(Drawable drawable) {
        super((Drawable) Preconditions.checkNotNull(drawable));
        this.mType = Type.OVERLAY_COLOR;
        this.mBounds = new RectF();
        this.mRadii = new float[8];
        this.mBorderRadii = new float[8];
        this.mPaint = new Paint(1);
        this.mIsCircle = false;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = 0;
        this.mOverlayColor = 0;
        this.mPadding = 0.0f;
        this.mScaleDownInsideBorders = false;
        this.mPath = new Path();
        this.mBorderPath = new Path();
        this.mTempRectangle = new RectF();
    }

    public void setType(Type type) {
        this.mType = type;
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setCircle(boolean z) {
        this.mIsCircle = z;
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public boolean isCircle() {
        return this.mIsCircle;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setRadius(float f2) {
        Arrays.fill(this.mRadii, f2);
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setRadii(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.mRadii, 0.0f);
        } else {
            Preconditions.checkArgument(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.mRadii, 0, 8);
        }
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public float[] getRadii() {
        return this.mRadii;
    }

    public void setOverlayColor(int i2) {
        this.mOverlayColor = i2;
        invalidateSelf();
    }

    public int getOverlayColor() {
        return this.mOverlayColor;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setBorder(int i2, float f2) {
        this.mBorderColor = i2;
        this.mBorderWidth = f2;
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public int getBorderColor() {
        return this.mBorderColor;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setPadding(float f2) {
        this.mPadding = f2;
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public float getPadding() {
        return this.mPadding;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setScaleDownInsideBorders(boolean z) {
        this.mScaleDownInsideBorders = z;
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public boolean getScaleDownInsideBorders() {
        return this.mScaleDownInsideBorders;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updatePath();
    }

    private void updatePath() {
        float[] fArr;
        this.mPath.reset();
        this.mBorderPath.reset();
        this.mTempRectangle.set(getBounds());
        RectF rectF = this.mTempRectangle;
        float f2 = this.mPadding;
        rectF.inset(f2, f2);
        this.mPath.addRect(this.mTempRectangle, Path.Direction.CW);
        if (this.mIsCircle) {
            this.mPath.addCircle(this.mTempRectangle.centerX(), this.mTempRectangle.centerY(), Math.min(this.mTempRectangle.width(), this.mTempRectangle.height()) / 2.0f, Path.Direction.CW);
        } else {
            this.mPath.addRoundRect(this.mTempRectangle, this.mRadii, Path.Direction.CW);
        }
        RectF rectF2 = this.mTempRectangle;
        float f3 = this.mPadding;
        rectF2.inset(-f3, -f3);
        RectF rectF3 = this.mTempRectangle;
        float f4 = this.mBorderWidth;
        rectF3.inset(f4 / 2.0f, f4 / 2.0f);
        if (this.mIsCircle) {
            this.mBorderPath.addCircle(this.mTempRectangle.centerX(), this.mTempRectangle.centerY(), Math.min(this.mTempRectangle.width(), this.mTempRectangle.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i2 = 0;
            while (true) {
                fArr = this.mBorderRadii;
                if (i2 >= fArr.length) {
                    break;
                }
                fArr[i2] = (this.mRadii[i2] + this.mPadding) - (this.mBorderWidth / 2.0f);
                i2++;
            }
            this.mBorderPath.addRoundRect(this.mTempRectangle, fArr, Path.Direction.CW);
        }
        RectF rectF4 = this.mTempRectangle;
        float f5 = this.mBorderWidth;
        rectF4.inset((-f5) / 2.0f, (-f5) / 2.0f);
    }

    /* renamed from: com.facebook.drawee.drawable.RoundedCornersDrawable$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$drawee$drawable$RoundedCornersDrawable$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$facebook$drawee$drawable$RoundedCornersDrawable$Type = iArr;
            try {
                iArr[Type.CLIPPING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$drawee$drawable$RoundedCornersDrawable$Type[Type.OVERLAY_COLOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.mBounds.set(getBounds());
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$drawee$drawable$RoundedCornersDrawable$Type[this.mType.ordinal()];
        if (i2 == 1) {
            int save = canvas.save();
            this.mPath.setFillType(Path.FillType.EVEN_ODD);
            canvas.clipPath(this.mPath);
            super.draw(canvas);
            canvas.restoreToCount(save);
        } else if (i2 == 2) {
            if (this.mScaleDownInsideBorders) {
                RectF rectF = this.mInsideBorderBounds;
                if (rectF == null) {
                    this.mInsideBorderBounds = new RectF(this.mBounds);
                    this.mInsideBorderTransform = new Matrix();
                } else {
                    rectF.set(this.mBounds);
                }
                RectF rectF2 = this.mInsideBorderBounds;
                float f2 = this.mBorderWidth;
                rectF2.inset(f2, f2);
                this.mInsideBorderTransform.setRectToRect(this.mBounds, this.mInsideBorderBounds, Matrix.ScaleToFit.FILL);
                int save2 = canvas.save();
                canvas.clipRect(this.mBounds);
                canvas.concat(this.mInsideBorderTransform);
                super.draw(canvas);
                canvas.restoreToCount(save2);
            } else {
                super.draw(canvas);
            }
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mOverlayColor);
            this.mPaint.setStrokeWidth(0.0f);
            this.mPath.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(this.mPath, this.mPaint);
            if (this.mIsCircle) {
                float width = ((this.mBounds.width() - this.mBounds.height()) + this.mBorderWidth) / 2.0f;
                float height = ((this.mBounds.height() - this.mBounds.width()) + this.mBorderWidth) / 2.0f;
                if (width > 0.0f) {
                    canvas.drawRect(this.mBounds.left, this.mBounds.top, this.mBounds.left + width, this.mBounds.bottom, this.mPaint);
                    canvas.drawRect(this.mBounds.right - width, this.mBounds.top, this.mBounds.right, this.mBounds.bottom, this.mPaint);
                }
                if (height > 0.0f) {
                    canvas.drawRect(this.mBounds.left, this.mBounds.top, this.mBounds.right, this.mBounds.top + height, this.mPaint);
                    canvas.drawRect(this.mBounds.left, this.mBounds.bottom - height, this.mBounds.right, this.mBounds.bottom, this.mPaint);
                }
            }
        }
        if (this.mBorderColor != 0) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(this.mBorderColor);
            this.mPaint.setStrokeWidth(this.mBorderWidth);
            this.mPath.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(this.mBorderPath, this.mPaint);
        }
    }
}
