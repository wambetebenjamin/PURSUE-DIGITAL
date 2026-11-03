package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;
/* compiled from: ScrollerCompat.java */
/* loaded from: classes10.dex */
class aw implements ax {

    /* renamed from: a  reason: collision with root package name */
    private Scroller f20120a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aw(Context context, Interpolator interpolator) {
        this.f20120a = new Scroller(context, interpolator);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ax
    public void a() {
        this.f20120a.abortAnimation();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ax
    public boolean b() {
        return this.f20120a.computeScrollOffset();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ax
    public void a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.f20120a.fling(i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ax
    public int c() {
        return this.f20120a.getCurrX();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ax
    public int d() {
        return this.f20120a.getCurrY();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ax
    public boolean e() {
        return this.f20120a.isFinished();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ax
    public void a(float f2) {
        this.f20120a.setFriction(f2);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ax
    public void a(int i2, int i3, int i4, int i5, int i6) {
        this.f20120a.startScroll(i2, i3, i4, i5, i6);
    }
}
