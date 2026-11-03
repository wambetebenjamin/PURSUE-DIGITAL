package com.ixigua.touchtileimageview.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
/* compiled from: ClipAndRoundCornerSupportDrawable.java */
/* loaded from: classes9.dex */
public class d<T extends Drawable> extends Drawable implements Drawable.Callback {

    /* renamed from: a  reason: collision with root package name */
    private T f17102a;

    /* renamed from: b  reason: collision with root package name */
    private float f17103b = 0.0f;

    /* renamed from: c  reason: collision with root package name */
    private float f17104c = 0.0f;

    /* renamed from: d  reason: collision with root package name */
    private float f17105d = 0.0f;

    /* renamed from: e  reason: collision with root package name */
    private float f17106e = 0.0f;

    /* renamed from: f  reason: collision with root package name */
    private final RectF f17107f = new RectF();

    /* renamed from: g  reason: collision with root package name */
    private float f17108g = 0.0f;

    /* renamed from: h  reason: collision with root package name */
    private final RectF f17109h = new RectF();

    /* renamed from: i  reason: collision with root package name */
    private final Path f17110i = new Path();

    /* renamed from: j  reason: collision with root package name */
    private boolean f17111j = false;
    private boolean k = false;

    public d(T t) {
        this.f17102a = t;
        t.setCallback(this);
    }

    public T getWrapDrawable() {
        return this.f17102a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f17102a != null) {
            boolean z = this.f17111j || this.k;
            if (z) {
                canvas.save();
            }
            if (this.f17111j) {
                canvas.clipPath(this.f17110i);
            } else if (this.k) {
                canvas.clipRect(this.f17107f);
            }
            this.f17102a.draw(canvas);
            if (z) {
                canvas.restore();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        T t = this.f17102a;
        if (t != null) {
            return t.getIntrinsicWidth();
        }
        return super.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        T t = this.f17102a;
        if (t != null) {
            return t.getIntrinsicHeight();
        }
        return super.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i2, int i3, int i4, int i5) {
        super.setBounds(i2, i3, i4, i5);
        T t = this.f17102a;
        if (t != null) {
            t.setBounds(i2, i3, i4, i5);
        }
        this.f17109h.set(i2, i3, i4, i5);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect rect) {
        super.setBounds(rect);
        T t = this.f17102a;
        if (t != null) {
            t.setBounds(rect);
        }
        this.f17109h.set(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        T t = this.f17102a;
        if (t != null) {
            t.setAlpha(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        T t = this.f17102a;
        if (t != null) {
            t.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        T t = this.f17102a;
        if (t != null) {
            return t.getOpacity();
        }
        return -1;
    }

    public void setClipPercent(float f2, float f3, float f4, float f5) {
        this.f17103b = f2;
        this.f17104c = f3;
        this.f17105d = f4;
        this.f17106e = f5;
        a();
    }

    public void setRadiusPercent(float f2) {
        this.f17108g = f2;
        a();
    }

    private void a() {
        if (!(this.f17103b > 0.0f || this.f17104c > 0.0f || this.f17105d > 0.0f || this.f17106e > 0.0f || this.f17108g > 0.0f)) {
            this.f17111j = false;
            this.k = false;
            invalidateSelf();
            return;
        }
        this.f17107f.set((int) (getIntrinsicWidth() * this.f17103b), (int) (getIntrinsicHeight() * this.f17104c), (int) (getIntrinsicWidth() - (getIntrinsicWidth() * this.f17105d)), (int) (getIntrinsicHeight() - (getIntrinsicHeight() * this.f17106e)));
        if (Build.VERSION.SDK_INT >= 18) {
            this.f17110i.rewind();
            Path path = this.f17110i;
            RectF rectF = this.f17107f;
            path.addRoundRect(rectF, (rectF.width() / 2.0f) * this.f17108g, (this.f17107f.height() / 2.0f) * this.f17108g, Path.Direction.CCW);
            this.f17111j = true;
        } else {
            this.k = true;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(drawable);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(drawable, runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(drawable, runnable);
        }
    }
}
