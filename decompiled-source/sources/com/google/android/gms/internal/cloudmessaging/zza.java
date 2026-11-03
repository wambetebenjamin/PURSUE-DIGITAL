package com.google.android.gms.internal.cloudmessaging;

import android.os.Build;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
/* loaded from: classes5.dex */
public final class zza {
    public static final int zza;

    static {
        zza = Build.VERSION.SDK_INT >= 31 ? DownloadExpSwitchCode.BACK_CLEAR_DATA : 0;
    }
}
