package com.bytedance.ttgame.main.internal.language;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
/* loaded from: classes3.dex */
public class LanguageComponentCallback implements ComponentCallbacks {
    private Context context;

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    public LanguageComponentCallback(Context context) {
        this.context = context;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageManager.changeContextLocale(this.context);
    }
}
