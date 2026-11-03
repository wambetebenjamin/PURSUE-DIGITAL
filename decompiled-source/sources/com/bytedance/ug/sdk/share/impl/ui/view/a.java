package com.bytedance.ug.sdk.share.impl.ui.view;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
/* compiled from: AnimatedRotateDrawable.java */
/* loaded from: classes8.dex */
public class a extends Drawable implements Animatable, Drawable.Callback, Runnable {

    /* renamed from: a  reason: collision with root package name */
    private C0262a f16152a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f16153b;

    /* renamed from: c  reason: collision with root package name */
    private float f16154c;

    /* renamed from: d  reason: collision with root package name */
    private float f16155d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f16156e;

    public a(Drawable drawable) {
        C0262a c0262a = new C0262a(null, this, null);
        this.f16152a = c0262a;
        c0262a.f16157a = drawable;
        this.f16152a.f16160d = 0.5f;
        this.f16152a.f16159c = true;
        this.f16152a.f16162f = 0.5f;
        this.f16152a.f16161e = true;
        setFramesCount(12);
        setFramesDuration(100);
        a();
    }

    private a(C0262a c0262a, Resources resources) {
        this.f16152a = new C0262a(c0262a, this, resources);
        a();
    }

    private void a() {
        C0262a c0262a = this.f16152a;
        this.f16155d = 360.0f / c0262a.f16164h;
        Drawable drawable = c0262a.f16157a;
        if (drawable != null) {
            drawable.setFilterBitmap(true);
            if (drawable instanceof BitmapDrawable) {
                ((BitmapDrawable) drawable).setAntiAlias(true);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int save = canvas.save();
        C0262a c0262a = this.f16152a;
        Drawable drawable = c0262a.f16157a;
        Rect bounds = drawable.getBounds();
        canvas.rotate(this.f16154c, (c0262a.f16159c ? (bounds.right - bounds.left) * c0262a.f16160d : c0262a.f16160d) + bounds.left, (c0262a.f16161e ? (bounds.bottom - bounds.top) * c0262a.f16162f : c0262a.f16162f) + bounds.top);
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (this.f16156e) {
            return;
        }
        this.f16156e = true;
        b();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f16156e = false;
        unscheduleSelf(this);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f16156e;
    }

    private void b() {
        unscheduleSelf(this);
        scheduleSelf(this, SystemClock.uptimeMillis() + this.f16152a.f16163g);
    }

    @Override // java.lang.Runnable
    public void run() {
        float f2 = this.f16154c;
        float f3 = this.f16155d;
        float f4 = f2 + f3;
        this.f16154c = f4;
        if (f4 > 360.0f - f3) {
            this.f16154c = 0.0f;
        }
        invalidateSelf();
        b();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        this.f16152a.f16157a.setVisible(z, z2);
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            unscheduleSelf(this);
        } else if (visible || z2) {
            this.f16154c = 0.0f;
            b();
        }
        return visible;
    }

    public Drawable getDrawable() {
        return this.f16152a.f16157a;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f16152a.f16158b | this.f16152a.f16157a.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f16152a.f16157a.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f16152a.f16157a.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f16152a.f16157a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.f16152a.f16157a.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.f16152a.f16157a.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f16152a.f16157a.setBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f16152a.f16157a.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f16152a.f16157a.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f16152a.canConstantState()) {
            this.f16152a.f16158b = getChangingConfigurations();
            return this.f16152a;
        }
        return null;
    }

    public void setFramesCount(int i2) {
        this.f16152a.f16164h = i2;
        this.f16155d = 360.0f / this.f16152a.f16164h;
    }

    public void setFramesDuration(int i2) {
        this.f16152a.f16163g = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f16153b && super.mutate() == this) {
            this.f16152a.f16157a.mutate();
            this.f16153b = true;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AnimatedRotateDrawable.java */
    /* renamed from: com.bytedance.ug.sdk.share.impl.ui.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public static final class C0262a extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        Drawable f16157a;

        /* renamed from: b  reason: collision with root package name */
        int f16158b;

        /* renamed from: c  reason: collision with root package name */
        boolean f16159c;

        /* renamed from: d  reason: collision with root package name */
        float f16160d;

        /* renamed from: e  reason: collision with root package name */
        boolean f16161e;

        /* renamed from: f  reason: collision with root package name */
        float f16162f;

        /* renamed from: g  reason: collision with root package name */
        int f16163g;

        /* renamed from: h  reason: collision with root package name */
        int f16164h;

        /* renamed from: i  reason: collision with root package name */
        private boolean f16165i;

        /* renamed from: j  reason: collision with root package name */
        private boolean f16166j;

        public C0262a(C0262a c0262a, a aVar, Resources resources) {
            if (c0262a != null) {
                if (resources != null) {
                    this.f16157a = c0262a.f16157a.getConstantState().newDrawable(resources);
                } else {
                    this.f16157a = c0262a.f16157a.getConstantState().newDrawable();
                }
                this.f16157a.setCallback(aVar);
                this.f16159c = c0262a.f16159c;
                this.f16160d = c0262a.f16160d;
                this.f16161e = c0262a.f16161e;
                this.f16162f = c0262a.f16162f;
                this.f16164h = c0262a.f16164h;
                this.f16163g = c0262a.f16163g;
                this.f16166j = true;
                this.f16165i = true;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new a(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f16158b;
        }

        public boolean canConstantState() {
            if (!this.f16166j) {
                this.f16165i = this.f16157a.getConstantState() != null;
                this.f16166j = true;
            }
            return this.f16165i;
        }
    }
}
