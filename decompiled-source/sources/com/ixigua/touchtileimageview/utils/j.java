package com.ixigua.touchtileimageview.utils;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;
/* compiled from: PathProperty.java */
/* loaded from: classes9.dex */
class j<T> extends Property<T, Float> {

    /* renamed from: a  reason: collision with root package name */
    private final Property<T, PointF> f17213a;

    /* renamed from: b  reason: collision with root package name */
    private final PathMeasure f17214b;

    /* renamed from: c  reason: collision with root package name */
    private final float f17215c;

    /* renamed from: d  reason: collision with root package name */
    private final float[] f17216d;

    /* renamed from: e  reason: collision with root package name */
    private final PointF f17217e;

    /* renamed from: f  reason: collision with root package name */
    private float f17218f;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Property
    public /* bridge */ /* synthetic */ Float get(Object obj) {
        return get((j<T>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Property
    public /* bridge */ /* synthetic */ void set(Object obj, Float f2) {
        set2((j<T>) obj, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f17216d = new float[2];
        this.f17217e = new PointF();
        this.f17213a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f17214b = pathMeasure;
        this.f17215c = pathMeasure.getLength();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.util.Property
    public Float get(T t) {
        return Float.valueOf(this.f17218f);
    }

    /* renamed from: set  reason: avoid collision after fix types in other method */
    public void set2(T t, Float f2) {
        this.f17218f = f2.floatValue();
        this.f17214b.getPosTan(this.f17215c * f2.floatValue(), this.f17216d, null);
        this.f17217e.x = this.f17216d[0];
        this.f17217e.y = this.f17216d[1];
        this.f17213a.set(t, this.f17217e);
    }
}
