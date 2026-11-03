package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.facebook.internal.security.CertificateUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzgo extends zzjd {
    private char zza;
    private long zzb;
    private String zzc;
    private final zzgq zzd;
    private final zzgq zze;
    private final zzgq zzf;
    private final zzgq zzg;
    private final zzgq zzh;
    private final zzgq zzi;
    private final zzgq zzj;
    private final zzgq zzk;
    private final zzgq zzl;

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzjd
    protected final boolean zzh() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    public final zzgq zzc() {
        return this.zzk;
    }

    public final zzgq zzg() {
        return this.zzd;
    }

    public final zzgq zzm() {
        return this.zzf;
    }

    public final zzgq zzn() {
        return this.zze;
    }

    public final zzgq zzo() {
        return this.zzj;
    }

    public final zzgq zzp() {
        return this.zzl;
    }

    public final zzgq zzu() {
        return this.zzg;
    }

    public final zzgq zzv() {
        return this.zzi;
    }

    public final zzgq zzw() {
        return this.zzh;
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zza(String str) {
        if (str == null) {
            return null;
        }
        return new zzgp(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        String zza = zza(z, obj);
        String zza2 = zza(z, obj2);
        String zza3 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zza)) {
            sb.append(str2);
            sb.append(zza);
            str2 = ", ";
        }
        if (TextUtils.isEmpty(zza2)) {
            str3 = str2;
        } else {
            sb.append(str2);
            sb.append(zza2);
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str3);
            sb.append(zza3);
        }
        return sb.toString();
    }

    private static String zza(boolean z, Object obj) {
        String str;
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        int i2 = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str2 = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            return str2 + Math.round(Math.pow(10.0d, valueOf.length() - 1)) + "..." + str2 + Math.round(Math.pow(10.0d, valueOf.length()) - 1.0d);
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (obj instanceof Throwable) {
                Throwable th = (Throwable) obj;
                StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzb = zzb(zzhy.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzb(className).equals(zzb)) {
                        sb.append(": ");
                        sb.append(stackTraceElement);
                        break;
                    }
                    i2++;
                }
                return sb.toString();
            } else if (!(obj instanceof zzgp)) {
                return z ? "-" : String.valueOf(obj);
            } else {
                str = ((zzgp) obj).zza;
                return str;
            }
        }
    }

    private static String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return (com.google.android.gms.internal.measurement.zzok.zza() && zzbh.zzcg.zza(null).booleanValue()) ? "" : str;
        }
        return str.substring(0, lastIndexOf);
    }

    public final String zzx() {
        Pair<String, Long> zza;
        if (zzk().zzb == null || (zza = zzk().zzb.zza()) == null || zza == zzha.zza) {
            return null;
        }
        return String.valueOf(zza.second) + CertificateUtil.DELIMITER + ((String) zza.first);
    }

    @EnsuresNonNull({"logTagDoNotUseDirectly"})
    private final String zzy() {
        String str;
        synchronized (this) {
            if (this.zzc == null) {
                this.zzc = this.zzu.zzw() != null ? this.zzu.zzw() : "FA";
            }
            Preconditions.checkNotNull(this.zzc);
            str = this.zzc;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgo(zzhy zzhyVar) {
        super(zzhyVar);
        this.zza = (char) 0;
        this.zzb = -1L;
        this.zzd = new zzgq(this, 6, false, false);
        this.zze = new zzgq(this, 6, true, false);
        this.zzf = new zzgq(this, 6, false, true);
        this.zzg = new zzgq(this, 5, false, false);
        this.zzh = new zzgq(this, 5, true, false);
        this.zzi = new zzgq(this, 5, false, true);
        this.zzj = new zzgq(this, 4, false, false);
        this.zzk = new zzgq(this, 3, false, false);
        this.zzl = new zzgq(this, 2, false, false);
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(int i2, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zza(i2)) {
            zza(i2, zza(false, str, obj, obj2, obj3));
        }
        if (z2 || i2 < 5) {
            return;
        }
        Preconditions.checkNotNull(str);
        zzhv zzo = this.zzu.zzo();
        if (zzo == null) {
            zza(6, "Scheduler not set. Not logging error/warn");
        } else if (!zzo.zzaf()) {
            zza(6, "Scheduler not initialized. Not logging error/warn");
        } else {
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 >= 9) {
                i2 = 8;
            }
            zzo.zzb(new zzgn(this, i2, str, obj, obj2, obj3));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(int i2, String str) {
        Log.println(i2, zzy(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zza(int i2) {
        return Log.isLoggable(zzy(), i2);
    }
}
