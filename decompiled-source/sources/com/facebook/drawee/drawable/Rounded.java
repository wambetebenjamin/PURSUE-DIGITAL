package com.facebook.drawee.drawable;
/* loaded from: classes8.dex */
public interface Rounded {
    int getBorderColor();

    float getBorderWidth();

    float getPadding();

    float[] getRadii();

    boolean getScaleDownInsideBorders();

    boolean isCircle();

    void setBorder(int i2, float f2);

    void setCircle(boolean z);

    void setPadding(float f2);

    void setRadii(float[] fArr);

    void setRadius(float f2);

    void setScaleDownInsideBorders(boolean z);
}
