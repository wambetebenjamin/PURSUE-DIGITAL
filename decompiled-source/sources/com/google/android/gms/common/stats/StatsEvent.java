package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
@Deprecated
/* loaded from: classes4.dex */
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {

    /* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    /* loaded from: classes4.dex */
    public interface Types {
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
    }

    public final String toString() {
        long zza = zza();
        int zzb = zzb();
        String zzc = zzc();
        int length = String.valueOf(zza).length();
        StringBuilder sb = new StringBuilder(length + 1 + String.valueOf(zzb).length() + 3 + zzc.length());
        sb.append(zza);
        sb.append("\t");
        sb.append(zzb);
        sb.append("\t-1");
        sb.append(zzc);
        return sb.toString();
    }

    public abstract long zza();

    public abstract int zzb();

    public abstract String zzc();
}
