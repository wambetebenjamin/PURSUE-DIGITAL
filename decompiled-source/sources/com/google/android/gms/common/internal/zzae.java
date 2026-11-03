package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzae {
    private static final Object zza = new Object();
    private static boolean zzb;
    private static String zzc;
    private static int zzd;

    public static String zza(Context context) {
        zzc(context);
        return zzc;
    }

    public static int zzb(Context context) {
        zzc(context);
        return zzd;
    }

    private static void zzc(Context context) {
        Bundle bundle;
        synchronized (zza) {
            if (zzb) {
                return;
            }
            zzb = true;
            try {
                bundle = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.wtf("MetadataValueReader", "This should never happen.", e2);
            }
            if (bundle == null) {
                return;
            }
            zzc = bundle.getString("com.google.app.id");
            zzd = bundle.getInt("com.google.android.gms.version");
        }
    }
}
