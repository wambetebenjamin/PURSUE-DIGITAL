package com.bytedance.ttgame.rocketapi;

import android.content.Context;
import android.util.Log;
/* loaded from: classes7.dex */
class RocketMiniApplicationPartition {
    RocketMiniApplicationPartition() {
    }

    public static void attachBaseContextAfterMultiDexInstall(Context context) {
        Log.i("gsdk_init", "RocketCnMini perform attachBaseContext after MultiDex install.");
        RocketMini.INSTANCE.initAfterAttachBaseContext(context);
    }
}
