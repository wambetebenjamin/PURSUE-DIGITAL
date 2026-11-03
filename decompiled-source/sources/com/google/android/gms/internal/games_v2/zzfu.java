package com.google.android.gms.internal.games_v2;

import com.google.android.gms.common.internal.GmsLogger;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzfu {
    private static final GmsLogger zza = new GmsLogger("Games");

    public static void zza(String str, String str2) {
        zza.d(zzi(str), str2);
    }

    public static void zzb(String str, String str2, Throwable th) {
        zza.d(zzi("GamesApiManager"), "Authentication task failed", th);
    }

    public static void zzc(String str, String str2) {
        zza.v(zzi(str), str2);
    }

    public static void zzd(String str, String str2, Throwable th) {
        zza.i(zzi("SnapshotContentsEntity"), "Failed to write snapshot data", th);
    }

    public static void zze(String str, String str2) {
        zza.w(zzi(str), str2);
    }

    public static void zzf(String str, String str2, Throwable th) {
        zza.w(zzi(str), str2, th);
    }

    public static void zzg(String str, String str2) {
        zza.e(zzi(str), str2);
    }

    public static void zzh(String str, String str2, Throwable th) {
        zza.e(zzi(str), str2, th);
    }

    private static String zzi(String str) {
        return String.format("%s[%s]", "PlayGamesServices", str);
    }
}
