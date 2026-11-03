package com.ixigua.touchtileimageview.gesture;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
/* compiled from: AppScaleGestureDetector.java */
/* loaded from: classes9.dex */
public class b {
    private static final int A = 1;
    private static final int B = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f17146a = "AppScaleGestureDetector";
    private static final long x = 128;
    private static final float y = 0.5f;
    private static final int z = 0;
    private GestureDetector C;
    private boolean D;

    /* renamed from: b  reason: collision with root package name */
    private final Context f17147b;

    /* renamed from: c  reason: collision with root package name */
    private final a f17148c;

    /* renamed from: d  reason: collision with root package name */
    private float f17149d;

    /* renamed from: e  reason: collision with root package name */
    private float f17150e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f17151f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f17152g;

    /* renamed from: h  reason: collision with root package name */
    private float f17153h;

    /* renamed from: i  reason: collision with root package name */
    private float f17154i;

    /* renamed from: j  reason: collision with root package name */
    private float f17155j;
    private float k;
    private float l;
    private float m;
    private float n;
    private long o;
    private long p;
    private boolean q;
    private int r;
    private int s;
    private final Handler t;
    private float u;
    private float v;
    private int w;

    /* compiled from: AppScaleGestureDetector.java */
    /* loaded from: classes9.dex */
    public interface a {
        boolean onScale(b bVar);

        boolean onScaleBegin(b bVar);

        void onScaleEnd(b bVar);
    }

    /* compiled from: AppScaleGestureDetector.java */
    /* renamed from: com.ixigua.touchtileimageview.gesture.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0309b implements a {
        @Override // com.ixigua.touchtileimageview.gesture.b.a
        public boolean onScale(b bVar) {
            return false;
        }

        @Override // com.ixigua.touchtileimageview.gesture.b.a
        public boolean onScaleBegin(b bVar) {
            return true;
        }

        @Override // com.ixigua.touchtileimageview.gesture.b.a
        public void onScaleEnd(b bVar) {
        }
    }

    public b(Context context, a aVar) {
        this(context, aVar, null);
    }

    public b(Context context, a aVar, Handler handler) {
        this.w = 0;
        this.f17147b = context;
        this.f17148c = aVar;
        this.r = ViewConfiguration.get(context).getScaledTouchSlop() * 2;
        context.getResources();
        this.s = 1;
        this.t = handler;
        int i2 = context.getApplicationInfo().targetSdkVersion;
        if (i2 > 18) {
            setQuickScaleEnabled(true);
        }
        if (i2 > 22) {
            setStylusScaleEnabled(true);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f2;
        float f3;
        this.o = motionEvent.getEventTime();
        int actionMasked = motionEvent.getActionMasked();
        if (this.f17151f) {
            this.C.onTouchEvent(motionEvent);
        }
        int pointerCount = motionEvent.getPointerCount();
        boolean z2 = Build.VERSION.SDK_INT >= 23 && (motionEvent.getButtonState() & 32) != 0;
        boolean z3 = this.w == 2 && !z2;
        boolean z4 = actionMasked == 1 || actionMasked == 3 || z3;
        float f4 = 0.0f;
        if (actionMasked == 0 || z4) {
            if (this.q) {
                this.f17148c.onScaleEnd(this);
                this.q = false;
                this.f17155j = 0.0f;
                this.w = 0;
            } else if (a() && z4) {
                this.q = false;
                this.f17155j = 0.0f;
                this.w = 0;
            }
            if (z4) {
                return true;
            }
        }
        if (!this.q && this.f17152g && !a() && !z4 && z2) {
            this.u = motionEvent.getX();
            this.v = motionEvent.getY();
            this.w = 2;
            this.f17155j = 0.0f;
        }
        boolean z5 = actionMasked == 0 || actionMasked == 6 || actionMasked == 5 || z3;
        boolean z6 = actionMasked == 6;
        int actionIndex = z6 ? motionEvent.getActionIndex() : -1;
        int i2 = z6 ? pointerCount - 1 : pointerCount;
        if (a()) {
            f3 = this.u;
            f2 = this.v;
            if (motionEvent.getY() < f2) {
                this.D = true;
            } else {
                this.D = false;
            }
        } else {
            float f5 = 0.0f;
            float f6 = 0.0f;
            for (int i3 = 0; i3 < pointerCount; i3++) {
                if (actionIndex != i3) {
                    f5 += motionEvent.getX(i3);
                    f6 += motionEvent.getY(i3);
                }
            }
            float f7 = i2;
            float f8 = f5 / f7;
            f2 = f6 / f7;
            f3 = f8;
        }
        float f9 = 0.0f;
        for (int i4 = 0; i4 < pointerCount; i4++) {
            if (actionIndex != i4) {
                f4 += Math.abs(motionEvent.getX(i4) - f3);
                f9 += Math.abs(motionEvent.getY(i4) - f2);
            }
        }
        float f10 = i2;
        float f11 = (f4 / f10) * 2.0f;
        float f12 = (f9 / f10) * 2.0f;
        float hypot = a() ? f12 : (float) Math.hypot(f11, f12);
        boolean z7 = this.q;
        this.f17149d = f3;
        this.f17150e = f2;
        if (!a() && this.q && (hypot < this.s || z5)) {
            this.f17148c.onScaleEnd(this);
            this.q = false;
            this.f17155j = hypot;
        }
        if (z5) {
            this.k = f11;
            this.m = f11;
            this.l = f12;
            this.n = f12;
            this.f17153h = hypot;
            this.f17154i = hypot;
            this.f17155j = hypot;
        }
        int i5 = a() ? this.r : this.s;
        if (!this.q && hypot >= i5 && (z7 || Math.abs(hypot - this.f17155j) > this.r)) {
            this.k = f11;
            this.m = f11;
            this.l = f12;
            this.n = f12;
            this.f17153h = hypot;
            this.f17154i = hypot;
            this.p = this.o;
            this.q = this.f17148c.onScaleBegin(this);
        }
        if (actionMasked == 2) {
            this.k = f11;
            this.l = f12;
            this.f17153h = hypot;
            if (this.q ? this.f17148c.onScale(this) : true) {
                this.m = this.k;
                this.n = this.l;
                this.f17154i = this.f17153h;
                this.p = this.o;
            }
        }
        return true;
    }

    private boolean a() {
        return this.w != 0;
    }

    public void setQuickScaleEnabled(boolean z2) {
        this.f17151f = z2;
        if (z2 && this.C == null) {
            this.C = new GestureDetector(this.f17147b, new GestureDetector.SimpleOnGestureListener() { // from class: com.ixigua.touchtileimageview.gesture.b.1
                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
                public boolean onDoubleTap(MotionEvent motionEvent) {
                    b.this.u = motionEvent.getX();
                    b.this.v = motionEvent.getY();
                    b.this.w = 1;
                    return true;
                }
            }, this.t);
        }
    }

    public boolean isQuickScaleEnabled() {
        return this.f17151f;
    }

    public void setStylusScaleEnabled(boolean z2) {
        this.f17152g = z2;
    }

    public boolean isStylusScaleEnabled() {
        return this.f17152g;
    }

    public boolean isInProgress() {
        return this.q;
    }

    public float getFocusX() {
        return this.f17149d;
    }

    public float getFocusY() {
        return this.f17150e;
    }

    public float getCurrentSpan() {
        return this.f17153h;
    }

    public float getCurrentSpanX() {
        return this.k;
    }

    public float getCurrentSpanY() {
        return this.l;
    }

    public float getPreviousSpan() {
        return this.f17154i;
    }

    public float getPreviousSpanX() {
        return this.m;
    }

    public float getPreviousSpanY() {
        return this.n;
    }

    public float getScaleFactor() {
        if (a()) {
            boolean z2 = this.D;
            boolean z3 = (z2 && this.f17153h < this.f17154i) || (!z2 && this.f17153h > this.f17154i);
            float abs = Math.abs(1.0f - (this.f17153h / this.f17154i)) * 0.5f;
            if (this.f17154i <= 0.0f) {
                return 1.0f;
            }
            return z3 ? 1.0f + abs : 1.0f - abs;
        }
        float f2 = this.f17154i;
        if (f2 > 0.0f) {
            return this.f17153h / f2;
        }
        return 1.0f;
    }

    public long getTimeDelta() {
        return this.o - this.p;
    }

    public long getEventTime() {
        return this.o;
    }
}
