package gsdk.impl.dynamic.DEFAULT;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.VelocityTracker;
import java.util.HashMap;
/* compiled from: CrossHorImpl.java */
/* loaded from: classes10.dex */
class ay implements ba {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<Integer, Integer> f20121a = new HashMap<>();

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int a(int i2, int i3) {
        return i2;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int a(int i2, int i3, int i4) {
        return i2 * 2;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int a(int i2, int i3, int i4, int i5) {
        return (int) ((((i2 + 1) * i4) + ((i2 - 1) * i3)) / 3.141592653589793d);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int b(int i2, int i3, int i4) {
        return i4;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int a(ax axVar) {
        return axVar.c();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(ax axVar, int i2, int i3) {
        axVar.a(i2, 0, i3, 0, 300);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Camera camera, int i2) {
        camera.rotateY(i2);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Matrix matrix, int i2, int i3, int i4) {
        int i5 = i3 + i2;
        matrix.preTranslate(-i5, -i4);
        matrix.postTranslate(i5, i4);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Canvas canvas, TextPaint textPaint, String str, int i2, int i3, int i4) {
        canvas.drawText(str, i3 + i2, i4, textPaint);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public int c(int i2, int i3, int i4) {
        if (this.f20121a.containsKey(Integer.valueOf(i2))) {
            return this.f20121a.get(Integer.valueOf(i2)).intValue();
        }
        int degrees = (int) Math.toDegrees(Math.asin((i2 * 1.0d) / i4));
        this.f20121a.put(Integer.valueOf(i2), Integer.valueOf(degrees));
        return degrees;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(ax axVar, VelocityTracker velocityTracker, int i2, int i3, int i4, int i5) {
        axVar.a(i2, 0, (int) velocityTracker.getXVelocity(), 0, i3, i4, 0, 0, i5, 0);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Rect rect, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        int i13 = (i5 + i2) / 2;
        rect.set(i7 - i13, 0, i7 + i13, i4);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a() {
        this.f20121a.clear();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Rect rect, int i2, int i3, int i4, int i5) {
        rect.set(rect.left, rect.top + i3, rect.right, rect.bottom - i5);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.ba
    public void a(Rect rect, Rect rect2, Rect rect3, int i2, int i3, int i4, int i5) {
        rect.set(rect3.left, 0, rect3.right, i3);
        rect2.set(rect3.left, rect3.bottom - i5, rect3.right, rect3.bottom);
    }
}
