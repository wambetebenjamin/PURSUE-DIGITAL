package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import java.util.Objects;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzgv extends zzhu {
    private final Context zza;
    @Nullable
    private final Supplier<Optional<zzhh>> zzb;

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        Supplier<Optional<zzhh>> supplier = this.zzb;
        return hashCode ^ (supplier == null ? 0 : supplier.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhu
    public final Context zza() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhu
    @Nullable
    public final Supplier<Optional<zzhh>> zzb() {
        return this.zzb;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        return "FlagsContext{context=" + valueOf + ", hermeticFileOverrides=" + String.valueOf(this.zzb) + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgv(Context context, @Nullable Supplier<Optional<zzhh>> supplier) {
        Objects.requireNonNull(context, "Null context");
        this.zza = context;
        this.zzb = supplier;
    }

    public final boolean equals(Object obj) {
        Supplier<Optional<zzhh>> supplier;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzhu) {
            zzhu zzhuVar = (zzhu) obj;
            if (this.zza.equals(zzhuVar.zza()) && ((supplier = this.zzb) != null ? supplier.equals(zzhuVar.zzb()) : zzhuVar.zzb() == null)) {
                return true;
            }
        }
        return false;
    }
}
