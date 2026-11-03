package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.facebook.internal.security.CertificateUtil;
import com.google.android.gms.measurement.internal.zzje;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzax {
    private static final zzax zza = new zzax(null, 100);
    private final int zzb;
    private final String zzc;
    private final Boolean zzd;
    private final String zze;
    private final EnumMap<zzje.zza, zzjh> zzf;

    public final int zza() {
        return this.zzb;
    }

    public final int hashCode() {
        int i2;
        Boolean bool = this.zzd;
        if (bool == null) {
            i2 = 3;
        } else {
            i2 = bool == Boolean.TRUE ? 7 : 13;
        }
        String str = this.zze;
        return this.zzc.hashCode() + (i2 * 29) + ((str == null ? 17 : str.hashCode()) * 137);
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        for (Map.Entry<zzje.zza, zzjh> entry : this.zzf.entrySet()) {
            String zzb = zzje.zzb(entry.getValue());
            if (zzb != null) {
                bundle.putString(entry.getKey().zze, zzb);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            bundle.putString("is_dma_region", bool.toString());
        }
        String str = this.zze;
        if (str != null) {
            bundle.putString("cps_display_str", str);
        }
        return bundle;
    }

    public static zzax zza(Bundle bundle, int i2) {
        zzje.zza[] zza2;
        if (bundle == null) {
            return new zzax(null, i2);
        }
        EnumMap enumMap = new EnumMap(zzje.zza.class);
        for (zzje.zza zzaVar : zzjg.DMA.zza()) {
            enumMap.put((EnumMap) zzaVar, (zzje.zza) zzje.zza(bundle.getString(zzaVar.zze)));
        }
        return new zzax(enumMap, i2, bundle.containsKey("is_dma_region") ? Boolean.valueOf(bundle.getString("is_dma_region")) : null, bundle.getString("cps_display_str"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzax zza(zzjh zzjhVar, int i2) {
        EnumMap enumMap = new EnumMap(zzje.zza.class);
        enumMap.put((EnumMap) zzje.zza.AD_USER_DATA, (zzje.zza) zzjhVar);
        return new zzax(enumMap, -10, (Boolean) null, (String) null);
    }

    public static zzax zza(String str) {
        if (str == null || str.length() <= 0) {
            return zza;
        }
        String[] split = str.split(CertificateUtil.DELIMITER);
        int parseInt = Integer.parseInt(split[0]);
        EnumMap enumMap = new EnumMap(zzje.zza.class);
        zzje.zza[] zza2 = zzjg.DMA.zza();
        int length = zza2.length;
        int i2 = 1;
        int i3 = 0;
        while (i3 < length) {
            enumMap.put((EnumMap) zza2[i3], (zzje.zza) zzje.zza(split[i2].charAt(0)));
            i3++;
            i2++;
        }
        return new zzax(enumMap, parseInt, (Boolean) null, (String) null);
    }

    public final zzjh zzc() {
        zzjh zzjhVar = this.zzf.get(zzje.zza.AD_USER_DATA);
        return zzjhVar == null ? zzjh.UNINITIALIZED : zzjhVar;
    }

    public static Boolean zza(Bundle bundle) {
        zzjh zza2;
        if (bundle == null || (zza2 = zzje.zza(bundle.getString("ad_personalization"))) == null) {
            return null;
        }
        int i2 = zzba.zza[zza2.ordinal()];
        if (i2 != 3) {
            return i2 != 4 ? null : true;
        }
        return false;
    }

    public final Boolean zzd() {
        return this.zzd;
    }

    private final String zzh() {
        zzje.zza[] zza2;
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzb);
        for (zzje.zza zzaVar : zzjg.DMA.zza()) {
            sb.append(CertificateUtil.DELIMITER);
            sb.append(zzje.zza(this.zzf.get(zzaVar)));
        }
        return sb.toString();
    }

    public final String zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzc;
    }

    public final String toString() {
        zzje.zza[] zza2;
        StringBuilder append = new StringBuilder("source=").append(zzje.zza(this.zzb));
        for (zzje.zza zzaVar : zzjg.DMA.zza()) {
            append.append(",");
            append.append(zzaVar.zze);
            append.append("=");
            zzjh zzjhVar = this.zzf.get(zzaVar);
            if (zzjhVar == null) {
                append.append("uninitialized");
            } else {
                int i2 = zzba.zza[zzjhVar.ordinal()];
                if (i2 == 1) {
                    append.append("uninitialized");
                } else if (i2 == 2) {
                    append.append("eu_consent_policy");
                } else if (i2 == 3) {
                    append.append("denied");
                } else if (i2 == 4) {
                    append.append("granted");
                }
            }
        }
        if (this.zzd != null) {
            append.append(",isDmaRegion=").append(this.zzd);
        }
        if (this.zze != null) {
            append.append(",cpsDisplayStr=").append(this.zze);
        }
        return append.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzax(Boolean bool, int i2) {
        this((Boolean) null, i2, (Boolean) null, (String) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzax(Boolean bool, int i2, Boolean bool2, String str) {
        EnumMap<zzje.zza, zzjh> enumMap = new EnumMap<>(zzje.zza.class);
        this.zzf = enumMap;
        enumMap.put((EnumMap<zzje.zza, zzjh>) zzje.zza.AD_USER_DATA, (zzje.zza) zzje.zza(bool));
        this.zzb = i2;
        this.zzc = zzh();
        this.zzd = bool2;
        this.zze = str;
    }

    private zzax(EnumMap<zzje.zza, zzjh> enumMap, int i2, Boolean bool, String str) {
        EnumMap<zzje.zza, zzjh> enumMap2 = new EnumMap<>(zzje.zza.class);
        this.zzf = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzb = i2;
        this.zzc = zzh();
        this.zzd = bool;
        this.zze = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzax) {
            zzax zzaxVar = (zzax) obj;
            if (this.zzc.equalsIgnoreCase(zzaxVar.zzc) && Objects.equals(this.zzd, zzaxVar.zzd)) {
                return Objects.equals(this.zze, zzaxVar.zze);
            }
            return false;
        }
        return false;
    }

    public final boolean zzg() {
        for (zzjh zzjhVar : this.zzf.values()) {
            if (zzjhVar != zzjh.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }
}
