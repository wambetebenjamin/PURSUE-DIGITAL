package com.ixigua.touchtileimageview.utils;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;
/* compiled from: PropertyValuesHolderUtils.java */
/* loaded from: classes9.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private static final o f17222a;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            f17222a = new n();
        } else {
            f17222a = new m();
        }
    }

    public static PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        return f17222a.ofPointF(property, path);
    }
}
