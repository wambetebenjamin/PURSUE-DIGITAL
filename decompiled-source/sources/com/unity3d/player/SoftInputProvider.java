package com.unity3d.player;
/* loaded from: classes10.dex */
abstract class SoftInputProvider {
    public static int a() {
        int[] b2;
        int nativeGetSoftInputType = nativeGetSoftInputType();
        for (int i2 : com.unity3d.player.a.a.b(3)) {
            if (com.unity3d.player.a.d.a(i2) == nativeGetSoftInputType) {
                return i2;
            }
        }
        return 1;
    }

    private static final native int nativeGetSoftInputType();
}
