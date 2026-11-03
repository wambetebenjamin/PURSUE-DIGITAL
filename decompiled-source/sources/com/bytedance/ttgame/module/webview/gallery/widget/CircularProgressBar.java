package com.bytedance.ttgame.module.webview.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import com.bytedance.ttgame.module.webview.R;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes7.dex */
public class CircularProgressBar extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14683a = "CircularProgressBar";

    /* renamed from: b  reason: collision with root package name */
    private static final String f14684b = "saved_state";

    /* renamed from: c  reason: collision with root package name */
    private static final String f14685c = "progress";

    /* renamed from: d  reason: collision with root package name */
    private static final String f14686d = "marker_progress";

    /* renamed from: e  reason: collision with root package name */
    private static final String f14687e = "progress_background_color";

    /* renamed from: f  reason: collision with root package name */
    private static final String f14688f = "progress_color";

    /* renamed from: g  reason: collision with root package name */
    private static final String f14689g = "thumb_visible";

    /* renamed from: h  reason: collision with root package name */
    private static final String f14690h = "marker_visible";
    private float A;
    private float B;
    private int C;
    private float D;
    private float E;
    private int F;

    /* renamed from: i  reason: collision with root package name */
    private final RectF f14691i;

    /* renamed from: j  reason: collision with root package name */
    private final RectF f14692j;
    private Paint k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private Paint r;
    private float s;
    private boolean t;
    private float u;
    private int v;
    private int w;
    private Paint x;
    private float y;
    private Paint z;

    public CircularProgressBar(Context context) {
        this(context, null);
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.circularProgressBarStyle);
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f14691i = new RectF();
        this.f14692j = new RectF();
        this.k = new Paint();
        this.l = 10;
        this.m = 17;
        this.n = 0;
        this.o = true;
        this.p = false;
        this.q = true;
        this.s = 0.0f;
        this.t = false;
        this.u = 0.3f;
        this.z = new Paint();
        this.C = 20;
        this.F = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularProgressBar, i2, 0);
        if (obtainStyledAttributes != null) {
            try {
                setProgressColor(obtainStyledAttributes.getColor(R.styleable.CircularProgressBar_progress_color, -16711681));
                setProgressBackgroundColor(obtainStyledAttributes.getColor(R.styleable.CircularProgressBar_progress_background_color, -16711936));
                setProgress(obtainStyledAttributes.getFloat(R.styleable.CircularProgressBar_progress, 0.0f));
                setMarkerProgress(obtainStyledAttributes.getFloat(R.styleable.CircularProgressBar_marker_progress, 0.0f));
                setWheelSize((int) obtainStyledAttributes.getDimension(R.styleable.CircularProgressBar_stroke_width, 10.0f));
                setThumbEnabled(obtainStyledAttributes.getBoolean(R.styleable.CircularProgressBar_thumb_visible, true));
                setMarkerEnabled(obtainStyledAttributes.getBoolean(R.styleable.CircularProgressBar_marker_visible, true));
                this.m = obtainStyledAttributes.getInt(R.styleable.CircularProgressBar_android_gravity, 17);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.C = this.l * 2;
        c();
        d();
        e();
        this.o = false;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.translate(this.D, this.E);
        float currentRotation = getCurrentRotation();
        if (!this.t) {
            canvas.drawArc(this.f14691i, 270.0f, -(360.0f - currentRotation), false, this.k);
        }
        canvas.drawArc(this.f14691i, 270.0f, this.t ? 360.0f : currentRotation, false, this.x);
        if (this.p) {
            float markerRotation = getMarkerRotation();
            canvas.save();
            canvas.rotate(markerRotation - 90.0f);
            float f2 = this.A;
            int i2 = this.C;
            float f3 = this.B;
            canvas.drawLine((float) (f2 + ((i2 / 2) * 1.4d)), f3, (float) (f2 - ((i2 / 2) * 1.4d)), f3, this.r);
            canvas.restore();
        }
        if (b()) {
            canvas.save();
            canvas.rotate(currentRotation - 90.0f);
            canvas.rotate(45.0f, this.A, this.B);
            this.f14692j.left = this.A - (this.C / 3);
            this.f14692j.right = this.A + (this.C / 3);
            this.f14692j.top = this.B - (this.C / 3);
            this.f14692j.bottom = this.B + (this.C / 3);
            canvas.drawRect(this.f14692j, this.z);
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        float f2;
        float f3;
        float f4;
        int defaultSize = getDefaultSize(getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom(), i3);
        int defaultSize2 = getDefaultSize(getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight(), i2);
        if (i3 == 0) {
            a(0, 0);
            defaultSize = defaultSize2;
        } else if (i2 == 0) {
            a(0, 0);
        } else {
            int min = Math.min(defaultSize2, defaultSize);
            a(defaultSize2 - min, defaultSize - min);
            defaultSize = min;
        }
        setMeasuredDimension(defaultSize, defaultSize);
        float f5 = defaultSize * 0.5f;
        if (b()) {
            f3 = this.C;
            f4 = 0.8333333f;
        } else if (a()) {
            f3 = this.l;
            f4 = 1.4f;
        } else {
            f2 = this.l / 2.0f;
            float f6 = (f5 - f2) - 0.5f;
            this.y = f6;
            this.f14691i.set(-f6, -f6, f6, f6);
            this.A = (float) (this.y * Math.cos(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
            this.B = (float) (this.y * Math.sin(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
            this.D = this.n + f5;
            this.E = f5 + this.F;
        }
        f2 = f3 * f4;
        float f62 = (f5 - f2) - 0.5f;
        this.y = f62;
        this.f14691i.set(-f62, -f62, f62, f62);
        this.A = (float) (this.y * Math.cos(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        this.B = (float) (this.y * Math.sin(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        this.D = this.n + f5;
        this.E = f5 + this.F;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            setProgress(bundle.getFloat("progress"));
            setMarkerProgress(bundle.getFloat(f14686d));
            int i2 = bundle.getInt(f14688f);
            if (i2 != this.w) {
                this.w = i2;
                e();
            }
            int i3 = bundle.getInt(f14687e);
            if (i3 != this.v) {
                this.v = i3;
                c();
            }
            this.q = bundle.getBoolean(f14689g);
            this.p = bundle.getBoolean(f14690h);
            super.onRestoreInstanceState(bundle.getParcelable(f14684b));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(f14684b, super.onSaveInstanceState());
        bundle.putFloat("progress", this.u);
        bundle.putFloat(f14686d, this.s);
        bundle.putInt(f14688f, this.w);
        bundle.putInt(f14687e, this.v);
        bundle.putBoolean(f14689g, this.q);
        bundle.putBoolean(f14690h, this.p);
        return bundle;
    }

    public int getCircleStrokeWidth() {
        return this.l;
    }

    public float getMarkerProgress() {
        return this.s;
    }

    public float getProgress() {
        return this.u;
    }

    public int getProgressColor() {
        return this.w;
    }

    public boolean a() {
        return this.p;
    }

    public boolean b() {
        return this.q;
    }

    public void setMarkerEnabled(boolean z) {
        this.p = z;
    }

    public void setMarkerProgress(float f2) {
        this.p = true;
        this.s = f2;
    }

    public void setProgress(float f2) {
        if (f2 == this.u) {
            return;
        }
        int i2 = (f2 > 1.0f ? 1 : (f2 == 1.0f ? 0 : -1));
        if (i2 == 0) {
            this.t = false;
            this.u = 1.0f;
        } else {
            if (i2 >= 0) {
                this.t = true;
            } else {
                this.t = false;
            }
            this.u = f2 % 1.0f;
        }
        if (this.o) {
            return;
        }
        invalidate();
    }

    public void setProgressBackgroundColor(int i2) {
        this.v = i2;
        d();
        c();
    }

    public void setProgressColor(int i2) {
        this.w = i2;
        e();
    }

    public void setThumbEnabled(boolean z) {
        this.q = z;
    }

    public void setWheelSize(int i2) {
        this.l = i2;
        c();
        d();
        e();
    }

    private void a(int i2, int i3) {
        int i4 = this.m;
        if (Build.VERSION.SDK_INT >= 16) {
            i4 = Gravity.getAbsoluteGravity(this.m, getLayoutDirection());
        }
        int i5 = i4 & 7;
        if (i5 == 3) {
            this.n = 0;
        } else if (i5 == 5) {
            this.n = i2;
        } else {
            this.n = i2 / 2;
        }
        int i6 = i4 & 112;
        if (i6 == 48) {
            this.F = 0;
        } else if (i6 == 80) {
            this.F = i3;
        } else {
            this.F = i3 / 2;
        }
    }

    private float getCurrentRotation() {
        return this.u * 360.0f;
    }

    private float getMarkerRotation() {
        return this.s * 360.0f;
    }

    private void c() {
        Paint paint = new Paint(1);
        this.k = paint;
        paint.setColor(this.v);
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setStrokeWidth(this.l);
        invalidate();
    }

    private void d() {
        Paint paint = new Paint(1);
        this.r = paint;
        paint.setColor(this.v);
        this.r.setStyle(Paint.Style.STROKE);
        this.r.setStrokeWidth(this.l / 2);
        invalidate();
    }

    private void e() {
        Paint paint = new Paint(1);
        this.x = paint;
        paint.setColor(this.w);
        this.x.setStyle(Paint.Style.STROKE);
        this.x.setStrokeWidth(this.l);
        Paint paint2 = new Paint(1);
        this.z = paint2;
        paint2.setColor(this.w);
        this.z.setStyle(Paint.Style.FILL_AND_STROKE);
        this.z.setStrokeWidth(this.l);
        invalidate();
    }
}
