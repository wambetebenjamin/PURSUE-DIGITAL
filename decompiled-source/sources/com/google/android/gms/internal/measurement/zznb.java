package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
interface zznb {
    int zza();

    @Deprecated
    void zza(int i2) throws IOException;

    void zza(int i2, double d2) throws IOException;

    void zza(int i2, float f2) throws IOException;

    void zza(int i2, int i3) throws IOException;

    void zza(int i2, long j2) throws IOException;

    void zza(int i2, zzik zzikVar) throws IOException;

    <K, V> void zza(int i2, zzkt<K, V> zzktVar, Map<K, V> map) throws IOException;

    void zza(int i2, Object obj) throws IOException;

    @Deprecated
    void zza(int i2, Object obj, zzlu zzluVar) throws IOException;

    void zza(int i2, String str) throws IOException;

    void zza(int i2, List<zzik> list) throws IOException;

    @Deprecated
    void zza(int i2, List<?> list, zzlu zzluVar) throws IOException;

    void zza(int i2, List<Boolean> list, boolean z) throws IOException;

    void zza(int i2, boolean z) throws IOException;

    @Deprecated
    void zzb(int i2) throws IOException;

    void zzb(int i2, int i3) throws IOException;

    void zzb(int i2, long j2) throws IOException;

    void zzb(int i2, Object obj, zzlu zzluVar) throws IOException;

    void zzb(int i2, List<String> list) throws IOException;

    void zzb(int i2, List<?> list, zzlu zzluVar) throws IOException;

    void zzb(int i2, List<Double> list, boolean z) throws IOException;

    void zzc(int i2, int i3) throws IOException;

    void zzc(int i2, long j2) throws IOException;

    void zzc(int i2, List<Integer> list, boolean z) throws IOException;

    void zzd(int i2, int i3) throws IOException;

    void zzd(int i2, long j2) throws IOException;

    void zzd(int i2, List<Integer> list, boolean z) throws IOException;

    void zze(int i2, int i3) throws IOException;

    void zze(int i2, long j2) throws IOException;

    void zze(int i2, List<Long> list, boolean z) throws IOException;

    void zzf(int i2, int i3) throws IOException;

    void zzf(int i2, List<Float> list, boolean z) throws IOException;

    void zzg(int i2, List<Integer> list, boolean z) throws IOException;

    void zzh(int i2, List<Long> list, boolean z) throws IOException;

    void zzi(int i2, List<Integer> list, boolean z) throws IOException;

    void zzj(int i2, List<Long> list, boolean z) throws IOException;

    void zzk(int i2, List<Integer> list, boolean z) throws IOException;

    void zzl(int i2, List<Long> list, boolean z) throws IOException;

    void zzm(int i2, List<Integer> list, boolean z) throws IOException;

    void zzn(int i2, List<Long> list, boolean z) throws IOException;
}
