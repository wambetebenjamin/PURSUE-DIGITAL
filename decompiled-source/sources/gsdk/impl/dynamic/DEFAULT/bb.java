package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import java.util.List;
/* compiled from: WheelCrossPicker.java */
/* loaded from: classes10.dex */
public abstract class bb extends as implements Runnable {
    protected ba H;
    protected Rect I;

    /* renamed from: J  reason: collision with root package name */
    protected Rect f20123J;
    protected Rect K;
    protected Rect L;
    protected int M;
    protected int N;
    protected int O;
    protected int P;
    protected int Q;
    protected int R;

    @Override // gsdk.impl.dynamic.DEFAULT.as
    protected void a(Canvas canvas) {
    }

    @Override // gsdk.impl.dynamic.DEFAULT.as
    protected void a(MotionEvent motionEvent) {
    }

    public void e() {
    }

    public bb(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void a() {
        super.a();
        this.H = new az();
        this.I = new Rect();
        this.f20123J = new Rect();
        this.K = new Rect();
        this.L = new Rect();
    }

    public void setOrientation(int i2) {
        this.H = i2 == 0 ? new ay() : new az();
        c();
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gsdk.impl.dynamic.DEFAULT.as, android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.H.a(this.f20123J, this.o, i2, i3, this.s, this.t, this.w, this.x, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        this.H.a(this.K, this.L, this.f20123J, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        this.I.set(this.f20123J);
        if (this.F) {
            return;
        }
        this.H.a(this.I, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void c(Canvas canvas) {
        if (this.f20115i != null) {
            canvas.save();
            canvas.clipRect(this.I);
            this.f20115i.a(canvas, this.K, this.L, this.f20110d);
            canvas.restore();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void b(MotionEvent motionEvent) {
        a(1);
        a(this.D + this.B, this.E + this.C);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void c(MotionEvent motionEvent) {
        this.H.a(this.f20108b, this.f20107a, this.N, this.O, this.P, this.R);
        a(2);
        this.f20113g.post(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f20108b.e()) {
            a(0);
            f();
            d();
        }
        if (this.f20108b.b()) {
            this.D = this.f20108b.c();
            this.E = this.f20108b.d();
            this.N = this.H.a(this.f20108b);
            a(this.D, this.E);
            postInvalidate();
            this.f20113g.postDelayed(this, 16L);
        }
    }

    private void d() {
        if (this.l != 0) {
            return;
        }
        int min = Math.min(this.f20116j.size() - 1, Math.max(0, this.n - (this.N / this.M)));
        String str = this.f20116j.get(min);
        if (this.k.equals(str)) {
            return;
        }
        this.k = str;
        a(min, str);
    }

    private void f() {
        int abs = Math.abs(this.N % this.M);
        if (abs != 0) {
            int i2 = this.M;
            if (abs >= i2 / 2.0f) {
                a(abs - i2, i2 - abs);
            } else {
                a(abs, -abs);
            }
            postInvalidate();
            this.f20113g.postDelayed(this, 16L);
        }
    }

    private void a(int i2, int i3) {
        if (this.N < 0) {
            this.H.a(this.f20108b, this.N, i2);
        } else {
            this.H.a(this.f20108b, this.N, i3);
        }
        a(2);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void setCurrentTextColor(int i2) {
        super.setCurrentTextColor(i2);
        invalidate(this.f20123J);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void setTextSize(int i2) {
        super.setTextSize(i2);
        e();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void setItemSpace(int i2) {
        super.setItemSpace(i2);
        e();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void setItemCount(int i2) {
        super.setItemCount(i2);
        e();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void setData(List<String> list) {
        super.setData(list);
        e();
    }
}
