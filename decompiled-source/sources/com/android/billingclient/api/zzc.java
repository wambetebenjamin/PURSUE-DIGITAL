package com.android.billingclient.api;

import java.util.Objects;
import org.json.JSONObject;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class zzc {
    private final String zza;
    private final String zzb;
    private final String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzc(JSONObject jSONObject, zzd zzdVar) {
        this.zza = jSONObject.optString("productId");
        this.zzb = jSONObject.optString("productType");
        String optString = jSONObject.optString("offerToken");
        this.zzc = true == optString.isEmpty() ? null : optString;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzc) {
            zzc zzcVar = (zzc) obj;
            return this.zza.equals(zzcVar.zza) && this.zzb.equals(zzcVar.zzb) && Objects.equals(this.zzc, zzcVar.zzc);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.zza, this.zzb, this.zzc);
    }

    public final String toString() {
        return String.format("{id: %s, type: %s, offer token: %s}", this.zza, this.zzb, this.zzc);
    }
}
