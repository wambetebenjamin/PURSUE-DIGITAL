package com.facebook.drawee.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class RoundedBitmapDrawable extends RoundedDrawable {
    @Nullable
    private final Bitmap mBitmap;
    private final Paint mBorderPaint;
    private WeakReference<Bitmap> mLastBitmap;
    private final Paint mPaint;

    public RoundedBitmapDrawable(Resources resources, @Nullable Bitmap bitmap, @Nullable Paint paint) {
        super(new BitmapDrawable(resources, bitmap));
        Paint paint2 = new Paint();
        this.mPaint = paint2;
        Paint paint3 = new Paint(1);
        this.mBorderPaint = paint3;
        this.mBitmap = bitmap;
        if (Build.VERSION.SDK_INT >= 24 && (this.mDelegate instanceof BitmapDrawable) && bitmap != null && bitmap.getDensity() == DisplayMetrics.DENSITY_DEVICE_STABLE) {
            ((BitmapDrawable) this.mDelegate).setTargetDensity(DisplayMetrics.DENSITY_DEVICE_STABLE);
        }
        if (paint != null) {
            paint2.set(paint);
        }
        paint2.setFlags(1);
        paint3.setStyle(Paint.Style.STROKE);
    }

    public RoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
        this(resources, bitmap, null);
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            return bitmap.getHeight();
        }
        return super.getIntrinsicHeight();
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            return bitmap.getWidth();
        }
        return super.getIntrinsicWidth();
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("RoundedBitmapDrawable#draw");
        }
        if (!shouldRound()) {
            super.draw(canvas);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        updateTransform();
        updatePath();
        updatePaint();
        int save = canvas.save();
        canvas.concat(this.mInverseParentTransform);
        canvas.drawPath(this.mPath, this.mPaint);
        if (this.mBorderWidth > 0.0f) {
            this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
            this.mBorderPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.mPaint.getAlpha()));
            canvas.drawPath(this.mBorderPath, this.mBorderPaint);
        }
        canvas.restoreToCount(save);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    private void updatePaint() {
        WeakReference<Bitmap> weakReference = this.mLastBitmap;
        if (weakReference == null || weakReference.get() != this.mBitmap) {
            this.mLastBitmap = new WeakReference<>(this.mBitmap);
            this.mPaint.setFilterBitmap(true);
            this.mPaint.setShader(new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            this.mIsShaderTransformDirty = true;
        }
        if (this.mIsShaderTransformDirty) {
            this.mPaint.getShader().setLocalMatrix(this.mTransform);
            this.mIsShaderTransformDirty = false;
        }
    }

    public static RoundedBitmapDrawable fromBitmapDrawable(Resources resources, BitmapDrawable bitmapDrawable) {
        return new RoundedBitmapDrawable(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.facebook.drawee.drawable.RoundedDrawable
    public boolean shouldRound() {
        return super.shouldRound() && this.mBitmap != null;
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        super.setAlpha(i2);
        if (i2 != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i2);
            super.setAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        this.mPaint.setColorFilter(colorFilter);
    }

    Paint getPaint() {
        return this.mPaint;
    }
}
