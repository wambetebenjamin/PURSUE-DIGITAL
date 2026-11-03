package com.google.android.gms.internal.play_billing;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzbz implements Map, Serializable {
    private transient zzca zza;
    private transient zzca zzb;
    private transient zzbt zzc;

    public static zzbz zzc(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        zzbr.zza("com.android.vending.billing.PURCHASES_UPDATED", obj2);
        zzbr.zza("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", obj4);
        zzbr.zza("com.android.vending.billing.ALTERNATIVE_BILLING", obj6);
        return zzci.zzg(3, new Object[]{"com.android.vending.billing.PURCHASES_UPDATED", obj2, "com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", obj4, "com.android.vending.billing.ALTERNATIVE_BILLING", obj6}, null);
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzcj.zza(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzca zzcaVar = this.zzb;
        if (zzcaVar == null) {
            zzca zze = zze();
            this.zzb = zze;
            return zze;
        }
        return zzcaVar;
    }

    @Override // java.util.Map
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
            sb.append('{');
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                z = false;
            }
            sb.append('}');
            return sb.toString();
        }
        throw new IllegalArgumentException("size cannot be negative but was: " + size);
    }

    abstract zzbt zza();

    @Override // java.util.Map
    /* renamed from: zzb */
    public final zzbt values() {
        zzbt zzbtVar = this.zzc;
        if (zzbtVar == null) {
            zzbt zza = zza();
            this.zzc = zza;
            return zza;
        }
        return zzbtVar;
    }

    abstract zzca zzd();

    abstract zzca zze();

    @Override // java.util.Map
    /* renamed from: zzf */
    public final zzca entrySet() {
        zzca zzcaVar = this.zza;
        if (zzcaVar == null) {
            zzca zzd = zzd();
            this.zza = zzd;
            return zzd;
        }
        return zzcaVar;
    }
}
