package gsdk.impl.compliance.agegate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.bytedance.ttgame.module.compliance.impl.agegate.R;
import java.util.ArrayList;
import java.util.List;
/* compiled from: ScrollPickerView.java */
/* loaded from: classes10.dex */
public abstract class r<T> extends View {

    /* renamed from: J  reason: collision with root package name */
    private static final c f19894J = new c(null);
    private Drawable A;
    private Rect B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private int G;
    private boolean H;
    private ValueAnimator I;

    /* renamed from: a  reason: collision with root package name */
    private int f19895a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f19896b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f19897c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f19898d;

    /* renamed from: e  reason: collision with root package name */
    private int f19899e;

    /* renamed from: f  reason: collision with root package name */
    private List<T> f19900f;

    /* renamed from: g  reason: collision with root package name */
    private int f19901g;

    /* renamed from: h  reason: collision with root package name */
    private int f19902h;

    /* renamed from: i  reason: collision with root package name */
    private int f19903i;

    /* renamed from: j  reason: collision with root package name */
    private int f19904j;
    private int k;
    private int l;
    private int m;
    private float n;
    private float o;
    private float p;
    private GestureDetector q;
    private b r;
    private Scroller s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private boolean x;
    private Paint y;
    private Paint z;

    /* compiled from: ScrollPickerView.java */
    /* loaded from: classes10.dex */
    public interface b {
        void a(r rVar, int i2);
    }

    public abstract void a(Canvas canvas, List<T> list, int i2, int i3, float f2, float f3);

    public r(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public r(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f19895a = 3;
        this.f19896b = true;
        this.f19897c = true;
        this.f19898d = false;
        this.f19901g = 0;
        this.f19902h = 0;
        this.f19904j = -1;
        this.p = 0.0f;
        this.v = 0;
        this.w = 0;
        this.x = false;
        this.A = null;
        this.C = true;
        this.D = false;
        this.E = false;
        this.F = false;
        this.H = false;
        this.q = new GestureDetector(getContext(), new a(this, null));
        this.s = new Scroller(getContext());
        this.I = ValueAnimator.ofInt(0, 0);
        Paint paint = new Paint(1);
        this.y = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.z = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.z.setColor(-1315861);
        this.z.setStrokeWidth(s.a(context, 1.0f));
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.gsdk_account_scroll_picker_view);
            if (obtainStyledAttributes.hasValue(R.styleable.gsdk_account_scroll_picker_view_center_item_background)) {
                setCenterItemBackground(obtainStyledAttributes.getDrawable(R.styleable.gsdk_account_scroll_picker_view_center_item_background));
            }
            setVisibleItemCount(obtainStyledAttributes.getInt(R.styleable.gsdk_account_scroll_picker_view_visible_item_count, getVisibleItemCount()));
            setCenterPosition(obtainStyledAttributes.getInt(R.styleable.gsdk_account_scroll_picker_view_center_item_position, getCenterPosition()));
            setIsCirculation(obtainStyledAttributes.getBoolean(R.styleable.gsdk_account_scroll_picker_view_is_circulation, c()));
            setDisallowInterceptTouch(obtainStyledAttributes.getBoolean(R.styleable.gsdk_account_scroll_picker_view_disallow_intercept_touch, d()));
            setHorizontal(obtainStyledAttributes.getInt(R.styleable.gsdk_account_scroll_picker_view_orientation, this.D ? 1 : 2) == 1);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        List<T> list = this.f19900f;
        if (list == null || list.size() <= 0) {
            return;
        }
        Drawable drawable = this.A;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        a(canvas);
        int i2 = this.f19904j;
        int min = Math.min(Math.max(i2 + 1, this.f19895a - i2), this.f19900f.size());
        if (this.E) {
            min = this.f19900f.size();
        }
        while (min >= 1) {
            if (this.E || min <= this.f19904j + 1) {
                int i3 = this.f19899e;
                if (i3 - min < 0) {
                    i3 = this.f19900f.size() + this.f19899e;
                }
                int i4 = i3 - min;
                if (this.f19897c) {
                    float f2 = this.p;
                    a(canvas, this.f19900f, i4, -min, f2, (this.m + f2) - (this.f19903i * min));
                } else if (this.f19899e - min >= 0) {
                    float f3 = this.p;
                    a(canvas, this.f19900f, i4, -min, f3, (this.m + f3) - (this.f19903i * min));
                }
            }
            if (this.E || min <= this.f19895a - this.f19904j) {
                int size = this.f19899e + min >= this.f19900f.size() ? (this.f19899e + min) - this.f19900f.size() : this.f19899e + min;
                if (this.f19897c) {
                    List<T> list2 = this.f19900f;
                    float f4 = this.p;
                    a(canvas, list2, size, min, f4, this.m + f4 + (this.f19903i * min));
                } else if (this.f19899e + min < this.f19900f.size()) {
                    List<T> list3 = this.f19900f;
                    float f5 = this.p;
                    a(canvas, list3, size, min, f5, this.m + f5 + (this.f19903i * min));
                }
            }
            min--;
        }
        List<T> list4 = this.f19900f;
        int i5 = this.f19899e;
        float f6 = this.p;
        a(canvas, list4, i5, 0, f6, this.m + f6);
    }

    private void a(Canvas canvas) {
        Drawable drawable;
        if (canvas == null || (drawable = this.A) == null) {
            return;
        }
        Rect bounds = drawable.getBounds();
        this.B = bounds;
        canvas.drawLine(bounds.left, this.B.top, this.B.right, this.B.top, this.z);
        canvas.drawLine(this.B.left, this.B.bottom, this.B.right, this.B.bottom, this.z);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        h();
    }

    private void h() {
        if (this.f19904j < 0) {
            this.f19904j = this.f19895a / 2;
        }
        if (this.D) {
            this.f19901g = getMeasuredHeight();
            int measuredWidth = getMeasuredWidth() / this.f19895a;
            this.f19902h = measuredWidth;
            this.k = 0;
            int i2 = this.f19904j * measuredWidth;
            this.l = i2;
            this.f19903i = measuredWidth;
            this.m = i2;
        } else {
            this.f19901g = getMeasuredHeight() / this.f19895a;
            this.f19902h = getMeasuredWidth();
            int i3 = this.f19904j;
            int i4 = this.f19901g;
            int i5 = i3 * i4;
            this.k = i5;
            this.l = 0;
            this.f19903i = i4;
            this.m = i5;
        }
        Drawable drawable = this.A;
        if (drawable != null) {
            int i6 = this.l;
            int i7 = this.k;
            drawable.setBounds(i6, i7, this.f19902h + i6, this.f19901g + i7);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.x) {
            return true;
        }
        if (motionEvent.getActionMasked() == 0) {
            this.G = this.f19899e;
        }
        if (this.q.onTouchEvent(motionEvent)) {
            return true;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            this.n = motionEvent.getY();
            this.o = motionEvent.getX();
            if (this.p == 0.0f) {
                if (this.G != this.f19899e) {
                    k();
                }
            } else {
                j();
            }
        } else if (actionMasked == 2) {
            if (this.D) {
                if (Math.abs(motionEvent.getX() - this.o) < 0.1f) {
                    return true;
                }
                this.p += motionEvent.getX() - this.o;
            } else if (Math.abs(motionEvent.getY() - this.n) < 0.1f) {
                return true;
            } else {
                this.p += motionEvent.getY() - this.n;
            }
            this.n = motionEvent.getY();
            this.o = motionEvent.getX();
            i();
            invalidate();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3, float f2) {
        if (f2 < 1.0f) {
            if (this.D) {
                this.p = (this.p + i2) - this.w;
                this.w = i2;
            } else {
                this.p = (this.p + i2) - this.v;
                this.v = i2;
            }
            i();
            invalidate();
            return;
        }
        this.u = false;
        this.v = 0;
        this.w = 0;
        float f3 = this.p;
        if (f3 > 0.0f) {
            int i4 = this.f19903i;
            if (f3 < i4 / 2) {
                this.p = 0.0f;
            } else {
                this.p = i4;
            }
        } else {
            int i5 = this.f19903i;
            if ((-f3) < i5 / 2) {
                this.p = 0.0f;
            } else {
                this.p = -i5;
            }
        }
        i();
        k();
        invalidate();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.s.computeScrollOffset()) {
            if (this.D) {
                this.p = (this.p + this.s.getCurrX()) - this.w;
            } else {
                this.p = (this.p + this.s.getCurrY()) - this.v;
            }
            this.v = this.s.getCurrY();
            this.w = this.s.getCurrX();
            i();
            invalidate();
        } else if (this.t) {
            this.t = false;
            if (this.p == 0.0f) {
                k();
            } else {
                j();
            }
        } else if (this.u) {
            k();
        }
    }

    public void a() {
        this.v = 0;
        this.w = 0;
        this.u = false;
        this.t = false;
        this.s.abortAnimation();
        b();
    }

    private void i() {
        int size;
        int size2;
        float f2 = this.p;
        int i2 = this.f19903i;
        if (f2 >= i2) {
            int i3 = this.f19899e - ((int) (f2 / i2));
            this.f19899e = i3;
            if (i3 < 0) {
                if (this.f19897c) {
                    do {
                        size2 = this.f19900f.size() + this.f19899e;
                        this.f19899e = size2;
                    } while (size2 < 0);
                    float f3 = this.p;
                    int i4 = this.f19903i;
                    this.p = (f3 - i4) % i4;
                    return;
                }
                this.f19899e = 0;
                this.p = i2;
                if (this.t) {
                    this.s.forceFinished(true);
                }
                if (this.u) {
                    a(this.p, 0);
                    return;
                }
                return;
            }
            this.p = (f2 - i2) % i2;
        } else if (f2 <= (-i2)) {
            int i5 = this.f19899e + ((int) ((-f2) / i2));
            this.f19899e = i5;
            if (i5 >= this.f19900f.size()) {
                if (this.f19897c) {
                    do {
                        size = this.f19899e - this.f19900f.size();
                        this.f19899e = size;
                    } while (size >= this.f19900f.size());
                    float f4 = this.p;
                    int i6 = this.f19903i;
                    this.p = (f4 + i6) % i6;
                    return;
                }
                this.f19899e = this.f19900f.size() - 1;
                this.p = -this.f19903i;
                if (this.t) {
                    this.s.forceFinished(true);
                }
                if (this.u) {
                    a(this.p, 0);
                    return;
                }
                return;
            }
            float f5 = this.p;
            int i7 = this.f19903i;
            this.p = (f5 + i7) % i7;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (!this.s.isFinished() || this.t || this.p == 0.0f) {
            return;
        }
        a();
        float f2 = this.p;
        if (f2 > 0.0f) {
            if (this.D) {
                int i2 = this.f19902h;
                if (f2 < i2 / 2) {
                    a(f2, 0);
                    return;
                } else {
                    a(f2, i2);
                    return;
                }
            }
            int i3 = this.f19901g;
            if (f2 < i3 / 2) {
                a(f2, 0);
            } else {
                a(f2, i3);
            }
        } else if (this.D) {
            int i4 = this.f19902h;
            if ((-f2) < i4 / 2) {
                a(f2, 0);
            } else {
                a(f2, -i4);
            }
        } else {
            int i5 = this.f19901g;
            if ((-f2) < i5 / 2) {
                a(f2, 0);
            } else {
                a(f2, -i5);
            }
        }
    }

    private void a(float f2, int i2) {
        if (this.D) {
            int i3 = (int) f2;
            this.w = i3;
            this.u = true;
            this.s.startScroll(i3, 0, 0, 0);
            this.s.setFinalX(i2);
        } else {
            int i4 = (int) f2;
            this.v = i4;
            this.u = true;
            this.s.startScroll(0, i4, 0, 0);
            this.s.setFinalY(i2);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f2, float f3) {
        if (this.D) {
            int i2 = (int) f2;
            this.w = i2;
            this.t = true;
            int i3 = this.f19902h;
            this.s.fling(i2, 0, (int) f3, 0, i3 * (-10), i3 * 10, 0, 0);
        } else {
            int i4 = (int) f2;
            this.v = i4;
            this.t = true;
            int i5 = this.f19901g;
            this.s.fling(0, i4, 0, (int) f3, 0, 0, i5 * (-10), i5 * 10);
        }
        invalidate();
    }

    private void k() {
        this.p = 0.0f;
        a();
        b bVar = this.r;
        if (bVar != null) {
            bVar.a(this, this.f19899e);
        }
    }

    /* compiled from: ScrollPickerView.java */
    /* renamed from: gsdk.impl.compliance.agegate.r$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ int f19905a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ r f19906b;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f19906b.a(((Integer) valueAnimator.getAnimatedValue()).intValue(), this.f19905a, (((float) valueAnimator.getCurrentPlayTime()) * 1.0f) / ((float) valueAnimator.getDuration()));
        }
    }

    public void a(final int i2, long j2, Interpolator interpolator, boolean z) {
        if (this.H) {
            return;
        }
        final boolean z2 = this.x;
        this.x = !z;
        this.H = true;
        this.I.cancel();
        this.I.setIntValues(0, i2);
        this.I.setInterpolator(interpolator);
        this.I.setDuration(j2);
        this.I.removeAllUpdateListeners();
        this.I.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: gsdk.impl.compliance.agegate.r.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                r.this.a(((Integer) valueAnimator.getAnimatedValue()).intValue(), i2, (((float) valueAnimator.getCurrentPlayTime()) * 1.0f) / ((float) valueAnimator.getDuration()));
            }
        });
        this.I.removeAllListeners();
        this.I.addListener(new AnimatorListenerAdapter() { // from class: gsdk.impl.compliance.agegate.r.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                r.this.H = false;
                r.this.x = z2;
            }
        });
        this.I.start();
    }

    public void b() {
        this.H = false;
        this.I.cancel();
    }

    /* compiled from: ScrollPickerView.java */
    /* loaded from: classes10.dex */
    static class c implements Interpolator {
        private c() {
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            return ((float) (Math.cos((f2 + 1.0f) * 3.141592653589793d) / 2.0d)) + 0.5f;
        }
    }

    /* compiled from: ScrollPickerView.java */
    /* loaded from: classes10.dex */
    class a extends GestureDetector.SimpleOnGestureListener {

        /* renamed from: b  reason: collision with root package name */
        private boolean f19912b;

        private a() {
            this.f19912b = false;
        }

        /* synthetic */ a(r rVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            ViewParent parent;
            if (r.this.f19898d && (parent = r.this.getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            this.f19912b = r.this.e();
            r.this.a();
            r.this.n = motionEvent.getY();
            r.this.o = motionEvent.getX();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (r.this.f19896b) {
                r.this.a();
                if (r.this.D) {
                    r rVar = r.this;
                    rVar.a(rVar.p, f2);
                    return true;
                }
                r rVar2 = r.this;
                rVar2.a(rVar2.p, f3);
                return true;
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            float f2;
            r.this.n = motionEvent.getY();
            r.this.o = motionEvent.getX();
            if (r.this.f()) {
                r rVar = r.this;
                rVar.m = rVar.l;
                f2 = r.this.o;
            } else {
                r rVar2 = r.this;
                rVar2.m = rVar2.k;
                f2 = r.this.n;
            }
            if (!r.this.C || this.f19912b) {
                r.this.j();
                return true;
            } else if (f2 < r.this.m || f2 > r.this.m + r.this.f19903i) {
                if (f2 < r.this.m) {
                    r.this.a(r.this.f19903i, 150L, (Interpolator) r.f19894J, false);
                    return true;
                }
                r.this.a(-r.this.f19903i, 150L, (Interpolator) r.f19894J, false);
                return true;
            } else {
                r.this.performClick();
                return true;
            }
        }
    }

    public List<T> getData() {
        return this.f19900f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setData(List<? extends T> list) {
        if (list == 0) {
            this.f19900f = new ArrayList();
        } else {
            this.f19900f = list;
        }
        this.f19899e = this.f19900f.size() / 2;
        invalidate();
    }

    public T getSelectedItem() {
        return this.f19900f.get(this.f19899e);
    }

    public int getSelectedPosition() {
        return this.f19899e;
    }

    public void setSelectedPosition(int i2) {
        if (i2 < 0 || i2 > this.f19900f.size() - 1) {
            return;
        }
        if (i2 == this.f19899e && this.F) {
            return;
        }
        this.F = true;
        this.f19899e = i2;
        invalidate();
        k();
    }

    public void setOnSelectedListener(b bVar) {
        this.r = bVar;
    }

    public b getListener() {
        return this.r;
    }

    public void setInertiaScroll(boolean z) {
        this.f19896b = z;
    }

    public boolean c() {
        return this.f19897c;
    }

    public void setIsCirculation(boolean z) {
        this.f19897c = z;
    }

    public boolean d() {
        return this.f19898d;
    }

    public int getVisibleItemCount() {
        return this.f19895a;
    }

    public void setVisibleItemCount(int i2) {
        this.f19895a = i2;
        h();
        invalidate();
    }

    public void setDisallowInterceptTouch(boolean z) {
        this.f19898d = z;
    }

    public int getItemHeight() {
        return this.f19901g;
    }

    public int getItemWidth() {
        return this.f19902h;
    }

    public int getItemSize() {
        return this.f19903i;
    }

    public int getCenterX() {
        return this.l;
    }

    public int getCenterY() {
        return this.k;
    }

    public int getCenterPoint() {
        return this.m;
    }

    public void setDisallowTouch(boolean z) {
        this.x = z;
    }

    public void setCenterPosition(int i2) {
        if (i2 < 0) {
            this.f19904j = 0;
        } else {
            int i3 = this.f19895a;
            if (i2 >= i3) {
                this.f19904j = i3 - 1;
            } else {
                this.f19904j = i2;
            }
        }
        this.k = this.f19904j * this.f19901g;
        invalidate();
    }

    public int getCenterPosition() {
        return this.f19904j;
    }

    public void setCenterItemBackground(Drawable drawable) {
        this.A = drawable;
        int i2 = this.l;
        int i3 = this.k;
        drawable.setBounds(i2, i3, this.f19902h + i2, this.f19901g + i3);
        invalidate();
    }

    public void setCenterItemBackground(int i2) {
        ColorDrawable colorDrawable = new ColorDrawable(i2);
        this.A = colorDrawable;
        int i3 = this.l;
        int i4 = this.k;
        colorDrawable.setBounds(i3, i4, this.f19902h + i3, this.f19901g + i4);
        invalidate();
    }

    public Drawable getCenterItemBackground() {
        return this.A;
    }

    public boolean e() {
        return this.t || this.u || this.H;
    }

    public void setCanTap(boolean z) {
        this.C = z;
    }

    public boolean f() {
        return this.D;
    }

    public void setHorizontal(boolean z) {
        if (this.D == z) {
            return;
        }
        this.D = z;
        h();
        if (this.D) {
            this.f19903i = this.f19902h;
        } else {
            this.f19903i = this.f19901g;
        }
        invalidate();
    }

    public void setVertical(boolean z) {
        if (this.D == (!z)) {
            return;
        }
        this.D = !z;
        h();
        if (this.D) {
            this.f19903i = this.f19902h;
        } else {
            this.f19903i = this.f19901g;
        }
        invalidate();
    }

    public void setDrawAllItem(boolean z) {
        this.E = z;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (i2 == 0) {
            j();
        }
    }
}
