package com.airbnb.lottie.model;
/* loaded from: classes.dex */
public class DocumentData {
    public float baselineShift;
    public int color;
    public String fontName;
    public Justification justification;
    public float lineHeight;
    public float size;
    public int strokeColor;
    public boolean strokeOverFill;
    public float strokeWidth;
    public String text;
    public int tracking;

    /* loaded from: classes.dex */
    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, float f2, Justification justification, int i2, float f3, float f4, int i3, int i4, float f5, boolean z) {
        set(str, str2, f2, justification, i2, f3, f4, i3, i4, f5, z);
    }

    public DocumentData() {
    }

    public void set(String str, String str2, float f2, Justification justification, int i2, float f3, float f4, int i3, int i4, float f5, boolean z) {
        this.text = str;
        this.fontName = str2;
        this.size = f2;
        this.justification = justification;
        this.tracking = i2;
        this.lineHeight = f3;
        this.baselineShift = f4;
        this.color = i3;
        this.strokeColor = i4;
        this.strokeWidth = f5;
        this.strokeOverFill = z;
    }

    public int hashCode() {
        int hashCode = (((((int) ((((this.text.hashCode() * 31) + this.fontName.hashCode()) * 31) + this.size)) * 31) + this.justification.ordinal()) * 31) + this.tracking;
        long floatToRawIntBits = Float.floatToRawIntBits(this.lineHeight);
        return (((hashCode * 31) + ((int) (floatToRawIntBits ^ (floatToRawIntBits >>> 32)))) * 31) + this.color;
    }
}
