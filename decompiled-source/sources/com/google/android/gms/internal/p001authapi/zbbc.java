package com.google.android.gms.internal.p001authapi;

import android.os.Build;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* compiled from: com.google.android.gms:play-services-auth@@20.5.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zbbc  reason: invalid package */
/* loaded from: classes5.dex */
public final class zbbc {
    public static final int zba;

    static {
        zba = Build.VERSION.SDK_INT >= 31 ? DownloadExpSwitchCode.BACK_CLEAR_DATA : 0;
    }
}
