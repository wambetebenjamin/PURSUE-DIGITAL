package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import java.util.Objects;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzbf {
    private final String zza;
    private final zzbe zzb;
    private zzbe zzc;

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzbe zzbeVar = this.zzb.zzb;
        String str = "";
        while (zzbeVar != null) {
            Object obj = zzbeVar.zza;
            sb.append(str);
            if (obj != null && obj.getClass().isArray()) {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            } else {
                sb.append(obj);
            }
            zzbeVar = zzbeVar.zzb;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzbf zza(Object obj) {
        zzbe zzbeVar = new zzbe();
        this.zzc.zzb = zzbeVar;
        this.zzc = zzbeVar;
        zzbeVar.zza = obj;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbf(String str, zzbg zzbgVar) {
        zzbe zzbeVar = new zzbe();
        this.zzb = zzbeVar;
        this.zzc = zzbeVar;
        Objects.requireNonNull(str);
        this.zza = str;
    }
}
