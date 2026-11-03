package gsdk.impl.account.toutiao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.bytedance.ttgame.sdk.module.account.platform.api.Platform;
/* loaded from: classes10.dex */
public interface ab {
    default void a() {
    }

    default void a(int i2, int i3, Intent intent) {
    }

    default void a(Activity activity, Platform platform, as asVar) {
    }

    void a(Activity activity, Platform platform, at atVar, String str, boolean z);

    default void a(Activity activity, Platform platform, au auVar) {
    }

    void a(Context context);

    default boolean a(Activity activity, Platform platform) {
        return false;
    }
}
