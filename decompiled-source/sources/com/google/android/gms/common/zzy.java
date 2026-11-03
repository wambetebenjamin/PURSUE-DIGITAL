package com.google.android.gms.common;

import android.util.Log;
import javax.annotation.Nullable;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public class zzy {
    private static final zzy zze = new zzy(true, 3, 1, null, null, -1);
    final boolean zza;
    @Nullable
    final String zzb;
    @Nullable
    final Throwable zzc;
    final int zzd;

    private zzy(boolean z, int i2, int i3, @Nullable String str, @Nullable Throwable th, long j2) {
        this.zza = z;
        this.zzd = i2;
        this.zzb = str;
        this.zzc = th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzy(boolean z, int i2, int i3, String str, Throwable th, long j2, byte[] bArr) {
        this(false, 1, 5, null, null, -1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static zzy zzb() {
        return zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzy zzc(String str) {
        return new zzy(false, 1, 5, str, null, -1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzy zzd(String str, Throwable th) {
        return new zzy(false, 1, 5, str, th, -1L);
    }

    public static zzy zzf(int i2, long j2) {
        return new zzy(true, i2, 1, null, null, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzy zzg(int i2, int i3, String str, @Nullable Throwable th) {
        return new zzy(false, i2, i3, str, th, -1L);
    }

    @Nullable
    String zza() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze() {
        if (this.zza || !Log.isLoggable("GoogleCertificatesRslt", 3)) {
            return;
        }
        Throwable th = this.zzc;
        if (th != null) {
            Log.d("GoogleCertificatesRslt", zza(), th);
        } else {
            Log.d("GoogleCertificatesRslt", zza());
        }
    }
}
