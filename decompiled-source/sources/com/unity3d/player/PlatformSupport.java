package com.unity3d.player;

import android.os.Build;
/* loaded from: classes10.dex */
public class PlatformSupport {
    static final boolean MARSHMALLOW_SUPPORT;
    static final boolean NOUGAT_SUPPORT;
    static final boolean PIE_SUPPORT;
    static final boolean RED_VELVET_CAKE_SUPPORT;
    static final boolean SNOW_CONE_SUPPORT;
    static final boolean TIRAMISU_SUPPORT;
    static final boolean UPSIDE_DOWN_CAKE_SUPPORT;
    static final boolean VANILLA_ICE_CREAM_SUPPORT;

    static {
        int i2 = Build.VERSION.SDK_INT;
        MARSHMALLOW_SUPPORT = i2 >= 23;
        NOUGAT_SUPPORT = i2 >= 24;
        PIE_SUPPORT = i2 >= 28;
        RED_VELVET_CAKE_SUPPORT = i2 >= 30;
        SNOW_CONE_SUPPORT = i2 >= 31;
        TIRAMISU_SUPPORT = i2 >= 33;
        UPSIDE_DOWN_CAKE_SUPPORT = i2 >= 34;
        VANILLA_ICE_CREAM_SUPPORT = i2 >= 35;
    }
}
