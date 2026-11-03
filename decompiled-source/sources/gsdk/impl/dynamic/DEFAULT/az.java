package gsdk.impl.dynamic.DEFAULT;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.VelocityTracker;
import java.util.HashMap;
/* compiled from: CrossVerImpl.java */
/* loaded from: classes10.dex */
class az implements ba {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<Integer, Integer> f20122a = new HashMap<>();

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int a(int i2, int i3) {
        return i3;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int a(int i2, int i3, int i4) {
        return i3;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int a(int i2, int i3, int i4, int i5) {
        return (int) ((((i2 + 1) * i5) + ((i2 - 1) * i3)) / 3.141592653589793d);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int b(int i2, int i3, int i4) {
        return i2 * 2;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int a(ax axVar) {
        return axVar.d();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(ax axVar, int i2, int i3) {
        axVar.a(0, i2, 0, i3, 300);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Camera camera, int i2) {
        camera.rotateX(-i2);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Matrix matrix, int i2, int i3, int i4) {
        int i5 = i4 + i2;
        matrix.preTranslate(-i3, -i5);
        matrix.postTranslate(i3, i5);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Canvas canvas, TextPaint textPaint, String str, int i2, int i3, int i4) {
        canvas.drawText(str, i3, i4 + i2, textPaint);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int c(int i2, int i3, int i4) {
        if (this.f20122a.containsKey(Integer.valueOf(i3))) {
            return this.f20122a.get(Integer.valueOf(i3)).intValue();
        }
        int degrees = (int) Math.toDegrees(Math.asin((i3 * 1.0d) / i4));
        this.f20122a.put(Integer.valueOf(i3), Integer.valueOf(degrees));
        return degrees;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(ax axVar, VelocityTracker velocityTracker, int i2, int i3, int i4, int i5) {
        axVar.a(0, i2, 0, (int) velocityTracker.getYVelocity(), 0, 0, i3, i4, 0, i5);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Rect rect, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        int i13 = (i6 + i2) / 2;
        rect.set(0, i8 - i13, i3, i8 + i13);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a() {
        this.f20122a.clear();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Rect rect, int i2, int i3, int i4, int i5) {
        rect.set(rect.left + i2, rect.top, rect.right - i4, rect.bottom);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Rect rect, Rect rect2, Rect rect3, int i2, int i3, int i4, int i5) {
        rect.set(0, rect3.top, i2, rect3.bottom);
        rect2.set(rect3.right - i4, rect3.top, rect3.right, rect3.bottom);
    }
}
