package com.bytedance.sdk.bridge;

import android.util.Log;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Logger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/bytedance/sdk/bridge/Logger;", "", "()V", "d", "", "className", "", "message", com.bytedance.frameworks.baselib.network.e.f7575a, DownloadFileUtils.MODE_WRITE, "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class o {
    public static final o INSTANCE = new o();

    private o() {
    }

    public final void d(String className, String message) {
        Intrinsics.checkParameterIsNotNull(className, "className");
        Intrinsics.checkParameterIsNotNull(message, "message");
        b bridgeConfig = f.INSTANCE.getBridgeConfig();
        if (Intrinsics.areEqual((Object) (bridgeConfig != null ? bridgeConfig.isDebug() : null), (Object) true)) {
            Log.d("bridge", className + " - " + message);
        }
    }

    public final void w(String className, String message) {
        Intrinsics.checkParameterIsNotNull(className, "className");
        Intrinsics.checkParameterIsNotNull(message, "message");
        b bridgeConfig = f.INSTANCE.getBridgeConfig();
        if (Intrinsics.areEqual((Object) (bridgeConfig != null ? bridgeConfig.isDebug() : null), (Object) true)) {
            Log.w("bridge", className + " - " + message);
        }
    }

    public final void e(String className, String message) {
        Intrinsics.checkParameterIsNotNull(className, "className");
        Intrinsics.checkParameterIsNotNull(message, "message");
        b bridgeConfig = f.INSTANCE.getBridgeConfig();
        if (Intrinsics.areEqual((Object) (bridgeConfig != null ? bridgeConfig.isDebug() : null), (Object) true)) {
            Log.e("bridge", className + " - " + message);
        }
    }
}
