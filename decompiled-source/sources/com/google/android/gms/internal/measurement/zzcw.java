package com.google.android.gms.internal.measurement;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzcw {
    public static final int zza;

    public static PendingIntent zza(Context context, int i2, Intent intent, int i3) {
        return PendingIntent.getBroadcast(context, 0, intent, i3);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        zza = Build.VERSION.SDK_INT >= 31 ? DownloadExpSwitchCode.BACK_CLEAR_DATA : 0;
    }
}
