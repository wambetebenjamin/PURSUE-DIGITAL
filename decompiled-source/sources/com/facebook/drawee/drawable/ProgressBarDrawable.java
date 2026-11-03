package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
/* loaded from: classes8.dex */
public class ProgressBarDrawable extends Drawable implements CloneableDrawable {
    private final Paint mPaint = new Paint(1);
    private final Path mPath = new Path();
    private final RectF mRect = new RectF();
    private int mBackgroundColor = Integer.MIN_VALUE;
    private int mColor = -2147450625;
    private int mPadding = 10;
    private int mBarWidth = 20;
    private int mLevel = 0;
    private int mRadius = 0;
    private boolean mHideWhenZero = false;
    private boolean mIsVertical = false;

    public void setColor(int i2) {
        if (this.mColor != i2) {
            this.mColor = i2;
            invalidateSelf();
        }
    }

    public int getColor() {
        return this.mColor;
    }

    public void setBackgroundColor(int i2) {
        if (this.mBackgroundColor != i2) {
            this.mBackgroundColor = i2;
            invalidateSelf();
        }
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setPadding(int i2) {
        if (this.mPadding != i2) {
            this.mPadding = i2;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int i2 = this.mPadding;
        rect.set(i2, i2, i2, i2);
        return this.mPadding != 0;
    }

    public void setBarWidth(int i2) {
        if (this.mBarWidth != i2) {
            this.mBarWidth = i2;
            invalidateSelf();
        }
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public void setHideWhenZero(boolean z) {
        this.mHideWhenZero = z;
    }

    public boolean getHideWhenZero() {
        return this.mHideWhenZero;
    }

    public void setRadius(int i2) {
        if (this.mRadius != i2) {
            this.mRadius = i2;
            invalidateSelf();
        }
    }

    public int getRadius() {
        return this.mRadius;
    }

    public void setIsVertical(boolean z) {
        if (this.mIsVertical != z) {
            this.mIsVertical = z;
            invalidateSelf();
        }
    }

    public boolean getIsVertical() {
        return this.mIsVertical;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        this.mLevel = i2;
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mPaint.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(this.mPaint.getColor());
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mHideWhenZero && this.mLevel == 0) {
            return;
        }
        if (this.mIsVertical) {
            drawVerticalBar(canvas, 10000, this.mBackgroundColor);
            drawVerticalBar(canvas, this.mLevel, this.mColor);
            return;
        }
        drawHorizontalBar(canvas, 10000, this.mBackgroundColor);
        drawHorizontalBar(canvas, this.mLevel, this.mColor);
    }

    @Override // com.facebook.drawee.drawable.CloneableDrawable
    public Drawable cloneDrawable() {
        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.mBackgroundColor = this.mBackgroundColor;
        progressBarDrawable.mColor = this.mColor;
        progressBarDrawable.mPadding = this.mPadding;
        progressBarDrawable.mBarWidth = this.mBarWidth;
        progressBarDrawable.mLevel = this.mLevel;
        progressBarDrawable.mRadius = this.mRadius;
        progressBarDrawable.mHideWhenZero = this.mHideWhenZero;
        progressBarDrawable.mIsVertical = this.mIsVertical;
        return progressBarDrawable;
    }

    private void drawHorizontalBar(Canvas canvas, int i2, int i3) {
        Rect bounds = getBounds();
        int i4 = bounds.left + this.mPadding;
        int i5 = bounds.bottom - this.mPadding;
        int i6 = this.mBarWidth;
        int i7 = i5 - i6;
        this.mRect.set(i4, i7, i4 + (((bounds.width() - (this.mPadding * 2)) * i2) / 10000), i7 + i6);
        drawBar(canvas, i3);
    }

    private void drawVerticalBar(Canvas canvas, int i2, int i3) {
        Rect bounds = getBounds();
        int i4 = bounds.left + this.mPadding;
        int i5 = bounds.top + this.mPadding;
        this.mRect.set(i4, i5, i4 + this.mBarWidth, i5 + (((bounds.height() - (this.mPadding * 2)) * i2) / 10000));
        drawBar(canvas, i3);
    }

    private void drawBar(Canvas canvas, int i2) {
        this.mPaint.setColor(i2);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPath.reset();
        this.mPath.setFillType(Path.FillType.EVEN_ODD);
        this.mPath.addRoundRect(this.mRect, Math.min(this.mRadius, this.mBarWidth / 2), Math.min(this.mRadius, this.mBarWidth / 2), Path.Direction.CW);
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
