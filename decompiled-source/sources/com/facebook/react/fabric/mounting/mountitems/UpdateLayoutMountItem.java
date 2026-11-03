package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes8.dex */
public class UpdateLayoutMountItem implements MountItem {
    private final int mHeight;
    private final int mLayoutDirection;
    private final int mReactTag;
    private final int mWidth;
    private final int mX;
    private final int mY;

    public UpdateLayoutMountItem(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mReactTag = i2;
        this.mX = i3;
        this.mY = i4;
        this.mWidth = i5;
        this.mHeight = i6;
        this.mLayoutDirection = convertLayoutDirection(i7);
    }

    private static int convertLayoutDirection(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return 1;
                }
                throw new IllegalArgumentException("Unsupported layout direction: " + i2);
            }
            return 0;
        }
        return 2;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.updateLayout(this.mReactTag, this.mX, this.mY, this.mWidth, this.mHeight);
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getLayoutDirection() {
        return this.mLayoutDirection;
    }

    public String toString() {
        return "UpdateLayoutMountItem [" + this.mReactTag + "] - x: " + this.mX + " - y: " + this.mY + " - height: " + this.mHeight + " - width: " + this.mWidth + " - layoutDirection: " + this.mLayoutDirection;
    }
}
