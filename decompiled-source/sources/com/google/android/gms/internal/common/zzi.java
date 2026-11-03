package com.google.android.gms.internal.common;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
public class zzi {
    private final Class zza;
    private final Object zzb;

    private zzi(Class cls, Object obj) {
        this.zza = cls;
        this.zzb = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzi(Class cls, Object obj, byte[] bArr) {
        this(cls, obj);
    }

    public static zzi zzb(Class cls, Object obj) {
        return new zzi(cls, obj);
    }

    public final Class zzc() {
        return this.zza;
    }

    public final Object zzd() {
        return this.zzb;
    }
}
