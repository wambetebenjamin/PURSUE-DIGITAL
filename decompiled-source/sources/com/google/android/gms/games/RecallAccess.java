package com.google.android.gms.games;

import com.google.android.gms.internal.games_v2.zzam;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class RecallAccess {
    private final String zza;

    private RecallAccess(String str) {
        this.zza = str;
    }

    public static RecallAccess zza(zzam zzamVar) {
        return new RecallAccess(zzamVar.zza());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RecallAccess) {
            return Objects.equals(this.zza, ((RecallAccess) obj).zza);
        }
        return false;
    }

    public String getSessionId() {
        return this.zza;
    }

    public final int hashCode() {
        return Objects.hash(this.zza);
    }
}
