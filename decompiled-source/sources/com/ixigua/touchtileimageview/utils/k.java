package com.ixigua.touchtileimageview.utils;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
/* compiled from: PatternPathMotion.java */
/* loaded from: classes9.dex */
public class k extends i {

    /* renamed from: a  reason: collision with root package name */
    private Path f17219a;

    /* renamed from: b  reason: collision with root package name */
    private final Path f17220b;

    /* renamed from: c  reason: collision with root package name */
    private final Matrix f17221c;

    public k() {
        Path path = new Path();
        this.f17220b = path;
        this.f17221c = new Matrix();
        path.lineTo(1.0f, 0.0f);
        this.f17219a = path;
    }

    public k(Context context, AttributeSet attributeSet) {
        this.f17220b = new Path();
        this.f17221c = new Matrix();
    }

    public k(Path path) {
        this.f17220b = new Path();
        this.f17221c = new Matrix();
        setPatternPath(path);
    }

    public Path getPatternPath() {
        return this.f17219a;
    }

    public void setPatternPath(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float[] fArr = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength(), fArr, null);
        float f2 = fArr[0];
        float f3 = fArr[1];
        pathMeasure.getPosTan(0.0f, fArr, null);
        float f4 = fArr[0];
        float f5 = fArr[1];
        if (f4 == f2 && f5 == f3) {
            throw new IllegalArgumentException("pattern must not end at the starting point");
        }
        this.f17221c.setTranslate(-f4, -f5);
        float f6 = f2 - f4;
        float f7 = f3 - f5;
        float a2 = 1.0f / a(f6, f7);
        this.f17221c.postScale(a2, a2);
        this.f17221c.postRotate((float) Math.toDegrees(-Math.atan2(f7, f6)));
        path.transform(this.f17221c, this.f17220b);
        this.f17219a = path;
    }

    @Override // com.ixigua.touchtileimageview.utils.i
    public Path getPath(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        float a2 = a(f6, f7);
        double atan2 = Math.atan2(f7, f6);
        this.f17221c.setScale(a2, a2);
        this.f17221c.postRotate((float) Math.toDegrees(atan2));
        this.f17221c.postTranslate(f2, f3);
        Path path = new Path();
        this.f17220b.transform(this.f17221c, path);
        return path;
    }

    private static float a(float f2, float f3) {
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }
}
