package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzhw implements zzhb {
    private static final Map<String, zzhw> zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final Runnable zzc;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzd;
    private final Object zze;
    private volatile Map<String, ?> zzf;
    private final List<zzgz> zzg;

    private static SharedPreferences zza(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzgs.zza()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                return zzcu.zza(context, str.substring(12), 0, zzcq.zza);
            }
            return zzcu.zza(context, str, 0, zzcq.zza);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhw zza(Context context, String str, Runnable runnable) {
        zzhw zzhwVar;
        if ((!zzgs.zza() || str.startsWith("direct_boot:")) ? true : zzgs.zzb(context)) {
            synchronized (zzhw.class) {
                Map<String, zzhw> map = zza;
                zzhwVar = map.get(str);
                if (zzhwVar == null) {
                    zzhwVar = new zzhw(zza(context, str), runnable);
                    map.put(str, zzhwVar);
                }
            }
            return zzhwVar;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzhb
    public final Object zza(String str) {
        Map<String, ?> map = this.zzf;
        if (map == null) {
            synchronized (this.zze) {
                map = this.zzf;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    Map<String, ?> all = this.zzb.getAll();
                    this.zzf = all;
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    map = all;
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    private zzhw(SharedPreferences sharedPreferences, Runnable runnable) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.gms.internal.measurement.zzhv
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                zzhw.this.zza(sharedPreferences2, str);
            }
        };
        this.zzd = onSharedPreferenceChangeListener;
        this.zze = new Object();
        this.zzg = new ArrayList();
        this.zzb = sharedPreferences;
        this.zzc = runnable;
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void zza() {
        synchronized (zzhw.class) {
            for (zzhw zzhwVar : zza.values()) {
                zzhwVar.zzb.unregisterOnSharedPreferenceChangeListener(zzhwVar.zzd);
            }
            zza.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zze) {
            this.zzf = null;
            this.zzc.run();
        }
        synchronized (this) {
            for (zzgz zzgzVar : this.zzg) {
                zzgzVar.zza();
            }
        }
    }
}
