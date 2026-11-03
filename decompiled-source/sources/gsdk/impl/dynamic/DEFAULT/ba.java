package gsdk.impl.dynamic.DEFAULT;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.VelocityTracker;
/* compiled from: ICrossOrientation.java */
/* loaded from: classes10.dex */
interface ba {
    int a(int i2, int i3);

    int a(int i2, int i3, int i4);

    int a(int i2, int i3, int i4, int i5);

    int a(ax axVar);

    void a();

    void a(Camera camera, int i2);

    void a(Canvas canvas, TextPaint textPaint, String str, int i2, int i3, int i4);

    void a(Matrix matrix, int i2, int i3, int i4);

    void a(Rect rect, int i2, int i3, int i4, int i5);

    void a(Rect rect, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12);

    void a(Rect rect, Rect rect2, Rect rect3, int i2, int i3, int i4, int i5);

    void a(ax axVar, int i2, int i3);

    void a(ax axVar, VelocityTracker velocityTracker, int i2, int i3, int i4, int i5);

    int b(int i2, int i3, int i4);

    int c(int i2, int i3, int i4);
}
