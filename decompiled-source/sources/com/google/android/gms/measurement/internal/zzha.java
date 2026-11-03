package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import com.bytedance.apm.constant.k;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzje;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzha extends zzjd {
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    private long zzaa;
    public zzhe zzb;
    public final zzhb zzc;
    public final zzhb zzd;
    public final zzhd zze;
    public final zzhb zzf;
    public final zzgz zzg;
    public final zzhd zzh;
    public final zzhc zzi;
    public final zzgz zzj;
    public final zzhb zzk;
    public final zzhb zzl;
    public boolean zzm;
    public zzgz zzn;
    public zzgz zzo;
    public zzhb zzp;
    public final zzhd zzq;
    public final zzhd zzr;
    public final zzhb zzs;
    public final zzhc zzt;
    private SharedPreferences zzv;
    private Object zzw;
    private SharedPreferences zzx;
    private String zzy;
    private boolean zzz;

    /* JADX INFO: Access modifiers changed from: protected */
    public final SharedPreferences zzc() {
        zzt();
        zzac();
        if (this.zzx == null) {
            synchronized (this.zzw) {
                if (this.zzx == null) {
                    String str = zza().getPackageName() + "_preferences";
                    zzj().zzp().zza("Default prefs file", str);
                    this.zzx = zza().getSharedPreferences(str, 0);
                }
            }
        }
        return this.zzx;
    }

    @Override // com.google.android.gms.measurement.internal.zzjd
    protected final boolean zzh() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final SharedPreferences zzg() {
        zzt();
        zzac();
        Preconditions.checkNotNull(this.zzv);
        return this.zzv;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Pair<String, Boolean> zza(String str) {
        zzt();
        if (!zzo().zza(zzje.zza.AD_STORAGE)) {
            return new Pair<>("", false);
        }
        long elapsedRealtime = zzb().elapsedRealtime();
        if (this.zzy != null && elapsedRealtime < this.zzaa) {
            return new Pair<>(this.zzy, Boolean.valueOf(this.zzz));
        }
        this.zzaa = elapsedRealtime + zze().zzd(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zza());
            this.zzy = "";
            String id = advertisingIdInfo.getId();
            if (id != null) {
                this.zzy = id;
            }
            this.zzz = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Exception e2) {
            zzj().zzc().zza("Unable to get advertising id", e2);
            this.zzy = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzy, Boolean.valueOf(this.zzz));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SparseArray<Long> zzm() {
        Bundle zza2 = this.zzi.zza();
        if (zza2 == null) {
            return new SparseArray<>();
        }
        int[] intArray = zza2.getIntArray("uriSources");
        long[] longArray = zza2.getLongArray("uriTimestamps");
        if (intArray == null || longArray == null) {
            return new SparseArray<>();
        }
        if (intArray.length != longArray.length) {
            zzj().zzg().zza("Trigger URI source and timestamp array lengths do not match");
            return new SparseArray<>();
        }
        SparseArray<Long> sparseArray = new SparseArray<>();
        for (int i2 = 0; i2 < intArray.length; i2++) {
            sparseArray.put(intArray[i2], Long.valueOf(longArray[i2]));
        }
        return sparseArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzax zzn() {
        zzt();
        return zzax.zza(zzg().getString("dma_consent_settings", null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzje zzo() {
        zzt();
        return zzje.zza(zzg().getString("consent_settings", "G1"), zzg().getInt("consent_source", 100));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzp() {
        zzt();
        if (zzg().contains("use_service")) {
            return Boolean.valueOf(zzg().getBoolean("use_service", false));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzu() {
        zzt();
        if (zzg().contains("measurement_enabled_from_api")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled_from_api", true));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzv() {
        zzt();
        if (zzg().contains("measurement_enabled")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String zzw() {
        zzt();
        String string = zzg().getString("previous_os_version", null);
        zzf().zzac();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzg().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzx() {
        zzt();
        return zzg().getString("admob_app_id", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzy() {
        zzt();
        return zzg().getString("gmp_app_id", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzha(zzhy zzhyVar) {
        super(zzhyVar);
        this.zzw = new Object();
        this.zzf = new zzhb(this, "session_timeout", k.LAST_STOP_INTERVAL);
        this.zzg = new zzgz(this, "start_new_session", true);
        this.zzk = new zzhb(this, "last_pause_time", 0L);
        this.zzl = new zzhb(this, "session_id", 0L);
        this.zzh = new zzhd(this, "non_personalized_ads", null);
        this.zzi = new zzhc(this, "last_received_uri_timestamps_by_source", null);
        this.zzj = new zzgz(this, "allow_remote_dynamite", false);
        this.zzc = new zzhb(this, "first_open_time", 0L);
        this.zzd = new zzhb(this, "app_install_time", 0L);
        this.zze = new zzhd(this, "app_instance_id", null);
        this.zzn = new zzgz(this, "app_backgrounded", false);
        this.zzo = new zzgz(this, "deep_link_retrieval_complete", false);
        this.zzp = new zzhb(this, "deep_link_retrieval_attempts", 0L);
        this.zzq = new zzhd(this, "firebase_feature_rollouts", null);
        this.zzr = new zzhd(this, "deferred_attribution_cache", null);
        this.zzs = new zzhb(this, "deferred_attribution_cache_timestamp", 0L);
        this.zzt = new zzhc(this, "default_event_parameters", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzz() {
        zzt();
        Boolean zzv = zzv();
        SharedPreferences.Editor edit = zzg().edit();
        edit.clear();
        edit.apply();
        if (zzv != null) {
            zza(zzv);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzjd
    @EnsuresNonNull.List({@EnsuresNonNull({"this.preferences"}), @EnsuresNonNull({"this.monitoringSample"})})
    protected final void zzaa() {
        SharedPreferences sharedPreferences = zza().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzv = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzm = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzv.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzb = new zzhe(this, "health_monitor", Math.max(0L, zzbh.zzc.zza(null).longValue()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(String str) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(String str) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(Boolean bool) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(Boolean bool) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled_from_api");
        }
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(boolean z) {
        zzt();
        zzj().zzp().zza("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(SparseArray<Long> sparseArray) {
        if (sparseArray == null) {
            this.zzi.zza(null);
            return;
        }
        int[] iArr = new int[sparseArray.size()];
        long[] jArr = new long[sparseArray.size()];
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            iArr[i2] = sparseArray.keyAt(i2);
            jArr[i2] = sparseArray.valueAt(i2).longValue();
        }
        Bundle bundle = new Bundle();
        bundle.putIntArray("uriSources", iArr);
        bundle.putLongArray("uriTimestamps", jArr);
        this.zzi.zza(bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzab() {
        SharedPreferences sharedPreferences = this.zzv;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(long j2) {
        return j2 - this.zzf.zza() > this.zzk.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(zzax zzaxVar) {
        zzt();
        if (zzje.zza(zzaxVar.zza(), zzn().zza())) {
            SharedPreferences.Editor edit = zzg().edit();
            edit.putString("dma_consent_settings", zzaxVar.zzf());
            edit.apply();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(zzje zzjeVar) {
        zzt();
        int zza2 = zzjeVar.zza();
        if (zza(zza2)) {
            SharedPreferences.Editor edit = zzg().edit();
            edit.putString("consent_settings", zzjeVar.zzf());
            edit.putInt("consent_source", zza2);
            edit.apply();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(int i2) {
        return zzje.zza(i2, zzg().getInt("consent_source", 100));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zza(zznm zznmVar) {
        zzt();
        String string = zzg().getString("stored_tcf_param", "");
        String zzc = zznmVar.zzc();
        if (zzc.equals(string)) {
            return false;
        }
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("stored_tcf_param", zzc);
        edit.apply();
        return true;
    }
}
