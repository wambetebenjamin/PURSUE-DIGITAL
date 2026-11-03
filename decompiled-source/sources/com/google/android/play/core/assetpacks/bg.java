package com.google.android.play.core.assetpacks;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class bg {
    public static boolean a(int i2) {
        return i2 == 1 || i2 == 7 || i2 == 2 || i2 == 3;
    }

    public static boolean b(int i2) {
        return i2 == 2 || i2 == 7 || i2 == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(int i2, int i3) {
        if (i2 == 5) {
            if (i3 != 5) {
                return true;
            }
            i2 = 5;
        }
        if (i2 == 6) {
            if (i3 != 6 && i3 != 5) {
                return true;
            }
            i2 = 6;
        }
        if (i2 != 4 || i3 == 4) {
            if (i2 == 3 && (i3 == 2 || i3 == 7 || i3 == 1 || i3 == 8)) {
                return true;
            }
            if (i2 == 2) {
                return i3 == 1 || i3 == 8;
            }
            return false;
        }
        return true;
    }

    public static boolean d(int i2) {
        return i2 == 5 || i2 == 6 || i2 == 4;
    }
}
