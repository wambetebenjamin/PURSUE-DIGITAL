package com.bytedance.ttgame.rocketapi;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import androidx.multidex.MultiDex;
import com.bytedance.apm.constant.r;
import kotlin.Metadata;
/* compiled from: RocketMiniApplication.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/RocketMiniApplication;", "Landroid/app/Application;", "()V", "attachBaseContext", "", r.STATS_BASE_MODULE, "Landroid/content/Context;", "attachBaseContextAfterMultiDexInstall", "getApplicationContext", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public class RocketMiniApplication extends Application {
    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return this;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
        attachBaseContextAfterMultiDexInstall();
    }

    private final void attachBaseContextAfterMultiDexInstall() {
        try {
            Class.forName("com.bytedance.ttgame.rocketapi.RocketMiniApplicationPartition").getMethod("attachBaseContextAfterMultiDexInstall", Context.class).invoke(null, this);
        } catch (Exception unused) {
            Log.i("gsdk_init", "RocketMini attachBaseContextAfterMultiDexInstall fail.");
        }
    }
}
