package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.RotationOptions;
/* loaded from: classes8.dex */
public class OrientedDrawable extends ForwardingDrawable {
    private int mExifOrientation;
    private int mRotationAngle;
    final Matrix mRotationMatrix;
    private final Matrix mTempMatrix;
    private final RectF mTempRectF;

    public OrientedDrawable(Drawable drawable, int i2) {
        this(drawable, i2, 0);
    }

    public OrientedDrawable(Drawable drawable, int i2, int i3) {
        super(drawable);
        this.mTempMatrix = new Matrix();
        this.mTempRectF = new RectF();
        boolean z = true;
        Preconditions.checkArgument(i2 % 90 == 0);
        Preconditions.checkArgument((i3 < 0 || i3 > 8) ? false : z);
        this.mRotationMatrix = new Matrix();
        this.mRotationAngle = i2;
        this.mExifOrientation = i3;
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i2;
        if (this.mRotationAngle <= 0 && ((i2 = this.mExifOrientation) == 0 || i2 == 1)) {
            super.draw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.concat(this.mRotationMatrix);
        super.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int i2 = this.mExifOrientation;
        if (i2 == 5 || i2 == 7 || this.mRotationAngle % RotationOptions.ROTATE_180 != 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int i2 = this.mExifOrientation;
        if (i2 == 5 || i2 == 7 || this.mRotationAngle % RotationOptions.ROTATE_180 != 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        int i2;
        Drawable current = getCurrent();
        int i3 = this.mRotationAngle;
        if (i3 > 0 || ((i2 = this.mExifOrientation) != 0 && i2 != 1)) {
            int i4 = this.mExifOrientation;
            if (i4 == 2) {
                this.mRotationMatrix.setScale(-1.0f, 1.0f);
            } else if (i4 == 7) {
                this.mRotationMatrix.setRotate(270.0f, rect.centerX(), rect.centerY());
                this.mRotationMatrix.postScale(-1.0f, 1.0f);
            } else if (i4 == 4) {
                this.mRotationMatrix.setScale(1.0f, -1.0f);
            } else if (i4 == 5) {
                this.mRotationMatrix.setRotate(270.0f, rect.centerX(), rect.centerY());
                this.mRotationMatrix.postScale(1.0f, -1.0f);
            } else {
                this.mRotationMatrix.setRotate(i3, rect.centerX(), rect.centerY());
            }
            this.mTempMatrix.reset();
            this.mRotationMatrix.invert(this.mTempMatrix);
            this.mTempRectF.set(rect);
            this.mTempMatrix.mapRect(this.mTempRectF);
            current.setBounds((int) this.mTempRectF.left, (int) this.mTempRectF.top, (int) this.mTempRectF.right, (int) this.mTempRectF.bottom);
            return;
        }
        current.setBounds(rect);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix matrix) {
        getParentTransform(matrix);
        if (this.mRotationMatrix.isIdentity()) {
            return;
        }
        matrix.preConcat(this.mRotationMatrix);
    }
}
