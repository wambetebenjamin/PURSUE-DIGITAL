package com.appsflyer.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.bytedance.apm.constant.i;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFa1bSDK implements AFb1zSDK {
    private Map<String, String> AFInAppEventParameterName = new LinkedHashMap();

    @Override // com.appsflyer.internal.AFb1zSDK
    public final Map<String, String> AFKeystoreWrapper(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        if (this.AFInAppEventParameterName.isEmpty()) {
            Resources resources = context.getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.AFInAppEventParameterName.put("xdp", String.valueOf(displayMetrics.xdpi));
            this.AFInAppEventParameterName.put("ydp", String.valueOf(displayMetrics.ydpi));
            this.AFInAppEventParameterName.put("x_px", String.valueOf(displayMetrics.widthPixels));
            this.AFInAppEventParameterName.put("y_px", String.valueOf(displayMetrics.heightPixels));
            this.AFInAppEventParameterName.put("d_dpi", String.valueOf(displayMetrics.densityDpi));
            this.AFInAppEventParameterName.put(i.PERF_DISK_FILE_SIZE, String.valueOf(resources.getConfiguration().screenLayout & 15));
        }
        return this.AFInAppEventParameterName;
    }
}
