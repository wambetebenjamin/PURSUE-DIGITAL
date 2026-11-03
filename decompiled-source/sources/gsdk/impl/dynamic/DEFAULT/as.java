package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import androidx.core.view.ViewCompat;
import com.bytedance.ttgame.module.dynamic.impl.R;
import com.vk.api.sdk.exceptions.VKApiCodes;
import java.util.Arrays;
import java.util.List;
/* compiled from: AbstractWheelPicker.java */
/* loaded from: classes10.dex */
public abstract class as extends View {
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected boolean F;
    protected boolean G;

    /* renamed from: a  reason: collision with root package name */
    protected VelocityTracker f20107a;

    /* renamed from: b  reason: collision with root package name */
    protected ax f20108b;

    /* renamed from: c  reason: collision with root package name */
    protected TextPaint f20109c;

    /* renamed from: d  reason: collision with root package name */
    protected Paint f20110d;

    /* renamed from: e  reason: collision with root package name */
    protected Rect f20111e;

    /* renamed from: f  reason: collision with root package name */
    protected Rect f20112f;

    /* renamed from: g  reason: collision with root package name */
    protected Handler f20113g;

    /* renamed from: h  reason: collision with root package name */
    protected a f20114h;

    /* renamed from: i  reason: collision with root package name */
    protected ar f20115i;

    /* renamed from: j  reason: collision with root package name */
    protected List<String> f20116j;
    protected String k;
    protected int l;
    protected int m;
    protected int n;
    protected int o;
    protected int p;
    protected int q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    protected int v;
    protected int w;
    protected int x;
    protected int y;
    protected int z;

    /* compiled from: AbstractWheelPicker.java */
    /* loaded from: classes10.dex */
    public interface a {
        void a(float f2, float f3);

        void a(int i2);

        void a(int i2, String str);
    }

    protected abstract void a(Canvas canvas);

    protected abstract void a(MotionEvent motionEvent);

    protected abstract void b(Canvas canvas);

    protected abstract void b(MotionEvent motionEvent);

    protected abstract void c(Canvas canvas);

    protected abstract void c(MotionEvent motionEvent);

    public as(Context context) {
        super(context);
        this.l = 0;
        b((AttributeSet) null);
    }

    private void b(AttributeSet attributeSet) {
        a(attributeSet);
        a();
        b();
        c();
    }

    protected void a(AttributeSet attributeSet) {
        int i2 = R.array.WheelArrayDefault;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.RNWheelItemSpace);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.RNWheelTextSize);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ReactAbstractWheelPicker);
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.ReactAbstractWheelPicker_rn_wheel_data, 0);
            if (resourceId != 0) {
                i2 = resourceId;
            }
            this.f20116j = Arrays.asList(getContext().getResources().getStringArray(i2));
            this.n = obtainStyledAttributes.getInt(R.styleable.ReactAbstractWheelPicker_rn_wheel_item_index, 0);
            this.m = obtainStyledAttributes.getInt(R.styleable.ReactAbstractWheelPicker_rn_wheel_item_count, 7);
            this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ReactAbstractWheelPicker_rn_wheel_item_space, dimensionPixelSize);
            this.p = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ReactAbstractWheelPicker_rn_wheel_text_size, dimensionPixelSize2);
            this.q = obtainStyledAttributes.getColor(R.styleable.ReactAbstractWheelPicker_rn_wheel_text_color, ViewCompat.MEASURED_STATE_MASK);
            this.r = obtainStyledAttributes.getColor(R.styleable.ReactAbstractWheelPicker_rn_wheel_text_color_current, ViewCompat.MEASURED_STATE_MASK);
            this.G = obtainStyledAttributes.getBoolean(R.styleable.ReactAbstractWheelPicker_rn_wheel_item_same_size, false);
            obtainStyledAttributes.recycle();
            return;
        }
        this.f20116j = Arrays.asList(getContext().getResources().getStringArray(i2));
        this.n = 0;
        this.m = 7;
        this.o = dimensionPixelSize;
        this.p = dimensionPixelSize2;
        this.r = ViewCompat.MEASURED_STATE_MASK;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        TextPaint textPaint = new TextPaint(69);
        this.f20109c = textPaint;
        textPaint.setTextAlign(Paint.Align.CENTER);
        this.f20109c.setTextSize(this.p);
        this.f20110d = new Paint(5);
        this.f20111e = new Rect();
        this.f20112f = new Rect();
        this.f20113g = new Handler();
        if (Build.VERSION.SDK_INT >= 9) {
            this.f20108b = new av(getContext(), new DecelerateInterpolator());
        } else {
            this.f20108b = new aw(getContext(), new DecelerateInterpolator());
        }
        if (Build.VERSION.SDK_INT >= 11) {
            this.f20108b.a(ViewConfiguration.getScrollFriction() / 25.0f);
        }
    }

    protected void b() {
        this.k = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c() {
        this.D = 0;
        this.E = 0;
        this.s = 0;
        this.t = 0;
        if (this.G) {
            String str = this.f20116j.get(0);
            this.f20109c.getTextBounds(str, 0, str.length(), this.f20111e);
            this.s = Math.max(this.s, this.f20111e.width());
            this.t = Math.max(this.t, this.f20111e.height());
            return;
        }
        for (String str2 : this.f20116j) {
            this.f20109c.getTextBounds(str2, 0, str2.length(), this.f20111e);
            this.s = Math.max(this.s, this.f20111e.width());
            this.t = Math.max(this.t, this.f20111e.height());
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int i4 = this.u;
        int i5 = this.v;
        setMeasuredDimension(a(mode, size, i4 + getPaddingLeft() + getPaddingRight()), a(mode2, size2, i5 + getPaddingTop() + getPaddingBottom()));
    }

    private int a(int i2, int i3, int i4) {
        return i2 == 1073741824 ? i3 : i2 == Integer.MIN_VALUE ? Math.min(i4, i3) : i4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        int i6 = this.n;
        a(i6, this.f20116j.get(i6));
        this.f20112f.set(getPaddingLeft(), getPaddingTop(), i2 - getPaddingRight(), i3 - getPaddingBottom());
        this.w = this.f20112f.centerX();
        int centerY = this.f20112f.centerY();
        this.x = centerY;
        this.y = (int) (centerY - ((this.f20109c.ascent() + this.f20109c.descent()) / 2.0f));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        a(canvas);
        canvas.save();
        canvas.clipRect(this.f20112f);
        b(canvas);
        canvas.restore();
        c(canvas);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f20107a == null) {
            this.f20107a = VelocityTracker.obtain();
        }
        this.f20107a.addMovement(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            if (!this.f20108b.e()) {
                this.f20108b.a();
            }
            this.z = (int) motionEvent.getX();
            this.A = (int) motionEvent.getY();
            a(motionEvent);
        } else if (action == 1) {
            this.D += this.B;
            this.E += this.C;
            this.B = 0;
            this.C = 0;
            this.f20107a.computeCurrentVelocity(VKApiCodes.CODE_INVALID_TIMESTAMP);
            c(motionEvent);
            getParent().requestDisallowInterceptTouchEvent(false);
            this.f20107a.recycle();
            this.f20107a = null;
        } else if (action == 2) {
            this.B = (int) (this.B + (motionEvent.getX() - this.z));
            this.C = (int) (this.C + (motionEvent.getY() - this.A));
            this.z = (int) motionEvent.getX();
            this.A = (int) motionEvent.getY();
            b(motionEvent);
        } else if (action == 3) {
            getParent().requestDisallowInterceptTouchEvent(false);
            this.f20108b.a();
            this.f20107a.recycle();
            this.f20107a = null;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(float f2, float f3) {
        a aVar = this.f20114h;
        if (aVar != null) {
            aVar.a(f2, f3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i2, String str) {
        a aVar = this.f20114h;
        if (aVar != null) {
            aVar.a(i2, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i2) {
        if (this.l != i2) {
            this.l = i2;
            a aVar = this.f20114h;
            if (aVar != null) {
                aVar.a(i2);
            }
        }
    }

    public void setData(List<String> list) {
        this.f20116j = list;
        c();
        requestLayout();
    }

    public void setOnWheelChangeListener(a aVar) {
        this.f20114h = aVar;
    }

    public void setItemIndex(int i2) {
        this.n = i2;
        c();
        requestLayout();
    }

    public void setItemSpace(int i2) {
        this.o = i2;
        c();
        requestLayout();
    }

    public void setItemCount(int i2) {
        this.m = i2;
        c();
        requestLayout();
    }

    public void setTextColor(int i2) {
        this.q = i2;
        invalidate();
    }

    public void setTextSize(int i2) {
        this.p = i2;
        this.f20109c.setTextSize(i2);
        c();
        requestLayout();
    }

    public void setTypeface(Typeface typeface) {
        this.f20109c.setTypeface(typeface);
        c();
        requestLayout();
    }

    public void setCurrentTextColor(int i2) {
        this.r = i2;
    }
}
