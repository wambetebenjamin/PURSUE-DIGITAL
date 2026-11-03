package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public abstract class zzj extends com.google.android.gms.common.internal.zzw {
    private final int zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzj(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 25);
        this.zza = Arrays.hashCode(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] zzf(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }

    public final boolean equals(Object obj) {
        IObjectWrapper zzd;
        if (obj instanceof com.google.android.gms.common.internal.zzx) {
            try {
                com.google.android.gms.common.internal.zzx zzxVar = (com.google.android.gms.common.internal.zzx) obj;
                if (zzxVar.zze() == this.zza && (zzd = zzxVar.zzd()) != null) {
                    return Arrays.equals(zzc(), (byte[]) ObjectWrapper.unwrap(zzd));
                }
                return false;
            } catch (RemoteException e2) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
                return false;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] zzc();

    @Override // com.google.android.gms.common.internal.zzx
    public final IObjectWrapper zzd() {
        return ObjectWrapper.wrap(zzc());
    }

    @Override // com.google.android.gms.common.internal.zzx
    public final int zze() {
        return this.zza;
    }
}
