package com.google.android.gms.internal.base;

import android.os.Build;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes5.dex */
public final class zap {
    public static final int zaa;

    static {
        zaa = Build.VERSION.SDK_INT >= 31 ? DownloadExpSwitchCode.BACK_CLEAR_DATA : 0;
    }
}
