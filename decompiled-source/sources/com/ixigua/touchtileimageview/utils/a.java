package com.ixigua.touchtileimageview.utils;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;
/* compiled from: ArcMotion.java */
/* loaded from: classes9.dex */
public class a extends i {

    /* renamed from: a  reason: collision with root package name */
    private static final float f17177a = 0.0f;

    /* renamed from: b  reason: collision with root package name */
    private static final float f17178b = 70.0f;

    /* renamed from: c  reason: collision with root package name */
    private static final float f17179c = (float) Math.tan(Math.toRadians(35.0d));

    /* renamed from: d  reason: collision with root package name */
    private float f17180d;

    /* renamed from: e  reason: collision with root package name */
    private float f17181e;

    /* renamed from: f  reason: collision with root package name */
    private float f17182f;

    /* renamed from: g  reason: collision with root package name */
    private float f17183g;

    /* renamed from: h  reason: collision with root package name */
    private float f17184h;

    /* renamed from: i  reason: collision with root package name */
    private float f17185i;

    public a() {
        this.f17180d = 0.0f;
        this.f17181e = 0.0f;
        this.f17182f = f17178b;
        this.f17183g = 0.0f;
        this.f17184h = 0.0f;
        this.f17185i = f17179c;
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17180d = 0.0f;
        this.f17181e = 0.0f;
        this.f17182f = f17178b;
        this.f17183g = 0.0f;
        this.f17184h = 0.0f;
        this.f17185i = f17179c;
    }

    public void setMinimumHorizontalAngle(float f2) {
        this.f17180d = f2;
        this.f17183g = a(f2);
    }

    public float getMinimumHorizontalAngle() {
        return this.f17180d;
    }

    public void setMinimumVerticalAngle(float f2) {
        this.f17181e = f2;
        this.f17184h = a(f2);
    }

    public float getMinimumVerticalAngle() {
        return this.f17181e;
    }

    public void setMaximumAngle(float f2) {
        this.f17182f = f2;
        this.f17185i = a(f2);
    }

    public float getMaximumAngle() {
        return this.f17182f;
    }

    private static float a(float f2) {
        if (f2 < 0.0f || f2 > 90.0f) {
            throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
        }
        return (float) Math.tan(Math.toRadians(f2 / 2.0f));
    }

    @Override // com.ixigua.touchtileimageview.utils.i
    public Path getPath(float f2, float f3, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        Path path = new Path();
        path.moveTo(f2, f3);
        float f9 = f4 - f2;
        float f10 = f5 - f3;
        float f11 = (f9 * f9) + (f10 * f10);
        float f12 = (f2 + f4) / 2.0f;
        float f13 = (f3 + f5) / 2.0f;
        float f14 = 0.25f * f11;
        boolean z = f3 > f5;
        if (Math.abs(f9) < Math.abs(f10)) {
            float abs = Math.abs(f11 / (f10 * 2.0f));
            if (z) {
                f7 = abs + f5;
                f6 = f4;
            } else {
                f7 = abs + f3;
                f6 = f2;
            }
            f8 = this.f17184h;
        } else {
            float f15 = f11 / (f9 * 2.0f);
            if (z) {
                f7 = f3;
                f6 = f15 + f2;
            } else {
                f6 = f4 - f15;
                f7 = f5;
            }
            f8 = this.f17183g;
        }
        float f16 = f14 * f8 * f8;
        float f17 = f12 - f6;
        float f18 = f13 - f7;
        float f19 = (f17 * f17) + (f18 * f18);
        float f20 = this.f17185i;
        float f21 = f14 * f20 * f20;
        if (f19 >= f16) {
            f16 = f19 > f21 ? f21 : 0.0f;
        }
        if (f16 != 0.0f) {
            float sqrt = (float) Math.sqrt(f16 / f19);
            f6 = ((f6 - f12) * sqrt) + f12;
            f7 = f13 + (sqrt * (f7 - f13));
        }
        path.cubicTo((f2 + f6) / 2.0f, (f3 + f7) / 2.0f, (f6 + f4) / 2.0f, (f7 + f5) / 2.0f, f4, f5);
        return path;
    }
}
