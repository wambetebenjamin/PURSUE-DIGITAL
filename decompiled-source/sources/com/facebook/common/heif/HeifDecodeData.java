package com.facebook.common.heif;
/* loaded from: classes8.dex */
public class HeifDecodeData {
    private boolean mHasAlpha;
    private int mHeifDecError;
    private int mHeight;
    private int mWidth;

    public int getHeifDecError() {
        return this.mHeifDecError;
    }

    public void setHeifDecError(int i2) {
        this.mHeifDecError = i2;
    }

    public boolean hasAlpha() {
        return this.mHasAlpha;
    }

    public void setHasAlpha(boolean z) {
        this.mHasAlpha = z;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void setWidth(int i2) {
        this.mWidth = i2;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setHeight(int i2) {
        this.mHeight = i2;
    }
}
