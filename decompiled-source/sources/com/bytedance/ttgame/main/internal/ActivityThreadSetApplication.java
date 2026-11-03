package com.bytedance.ttgame.main.internal;

import android.app.Application;
import android.util.Log;
import com.bytedance.platform.godzilla.common.b;
import java.lang.reflect.Field;
/* loaded from: classes3.dex */
public class ActivityThreadSetApplication {
    public static void fixActivityThreadApplicationNotInitialize(Application application) {
        try {
            Field declaredField = Class.forName("android.app.ActivityThread").getDeclaredField("mInitialApplication");
            declaredField.setAccessible(true);
            declaredField.set(b.currentActivityThread(), application);
        } catch (Exception unused) {
            Log.e("gsdk_init", "fixActivityThreadApplicationNotInitialize fail.");
        }
    }
}
