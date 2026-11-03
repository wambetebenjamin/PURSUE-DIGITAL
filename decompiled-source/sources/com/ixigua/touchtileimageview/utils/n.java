package com.ixigua.touchtileimageview.utils;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;
/* compiled from: PropertyValuesHolderUtilsApi21.java */
/* loaded from: classes9.dex */
class n implements o {
    @Override // com.ixigua.touchtileimageview.utils.o
    public PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
    }
}
