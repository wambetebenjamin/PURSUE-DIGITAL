package com.google.android.gms.common.images;

import com.bytedance.ttgame.module.webview.api.WebViewConfig;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public final class Size {
    private final int zaa;
    private final int zab;

    public Size(int i2, int i3) {
        this.zaa = i2;
        this.zab = i3;
    }

    private static NumberFormatException zaa(String str) {
        throw new NumberFormatException("Invalid Size: \"" + str + "\"");
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.zaa == size.zaa && this.zab == size.zab) {
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return this.zab;
    }

    public int getWidth() {
        return this.zaa;
    }

    public int hashCode() {
        int i2 = this.zaa;
        return ((i2 >>> 16) | (i2 << 16)) ^ this.zab;
    }

    public String toString() {
        return this.zaa + WebViewConfig.LOCATION_X + this.zab;
    }

    public static Size parseSize(String str) throws NumberFormatException {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int indexOf = str.indexOf(42);
        if (indexOf < 0) {
            indexOf = str.indexOf(120);
        }
        if (indexOf >= 0) {
            try {
                return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
            } catch (NumberFormatException unused) {
                throw zaa(str);
            }
        }
        throw zaa(str);
    }
}
