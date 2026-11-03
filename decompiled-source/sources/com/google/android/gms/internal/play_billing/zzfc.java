package com.google.android.gms.internal.play_billing;

import java.io.IOException;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzfc extends zzem {
    public static final /* synthetic */ int zzb = 0;
    private static final boolean zzc = zzii.zzx();
    Object zza;

    private zzfc() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfc(zzfb zzfbVar) {
    }

    public static int zzx(zzhb zzhbVar) {
        int zzn = zzhbVar.zzn();
        return zzy(zzn) + zzn;
    }

    public static int zzy(int i2) {
        return (352 - (Integer.numberOfLeadingZeros(i2) * 9)) >>> 6;
    }

    public static int zzz(long j2) {
        return (640 - (Long.numberOfLeadingZeros(j2) * 9)) >>> 6;
    }

    public final void zzA() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b2) throws IOException;

    public abstract void zzd(int i2, boolean z) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zze(byte[] bArr, int i2, int i3) throws IOException;

    public abstract void zzf(int i2, zzev zzevVar) throws IOException;

    public abstract void zzg(zzev zzevVar) throws IOException;

    public abstract void zzh(int i2, int i3) throws IOException;

    public abstract void zzi(int i2) throws IOException;

    public abstract void zzj(int i2, long j2) throws IOException;

    public abstract void zzk(long j2) throws IOException;

    public abstract void zzl(int i2, int i3) throws IOException;

    public abstract void zzm(int i2) throws IOException;

    public abstract void zzn(zzhb zzhbVar) throws IOException;

    public abstract void zzo(int i2, zzhb zzhbVar) throws IOException;

    public abstract void zzp(int i2, zzev zzevVar) throws IOException;

    public abstract void zzq(int i2, String str) throws IOException;

    public abstract void zzr(String str) throws IOException;

    public abstract void zzs(int i2, int i3) throws IOException;

    public abstract void zzt(int i2, int i3) throws IOException;

    public abstract void zzu(int i2) throws IOException;

    public abstract void zzv(int i2, long j2) throws IOException;

    public abstract void zzw(long j2) throws IOException;
}
