package com.ixigua.touchtileimageview.utils;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;
/* compiled from: PropertyValuesHolderUtilsApi14.java */
/* loaded from: classes9.dex */
class m implements o {
    @Override // com.ixigua.touchtileimageview.utils.o
    public PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        return PropertyValuesHolder.ofFloat(new j(property, path), 0.0f, 1.0f);
    }
}
