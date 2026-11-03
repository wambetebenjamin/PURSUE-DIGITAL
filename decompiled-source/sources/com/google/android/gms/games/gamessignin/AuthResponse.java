package com.google.android.gms.games.gamessignin;

import java.util.List;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class AuthResponse {
    private final List zza;
    private final String zzb;

    public AuthResponse(String str, List<AuthScope> list) {
        Objects.requireNonNull(list, "Granted scopes list cannot be null");
        this.zzb = str;
        this.zza = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AuthResponse) {
            AuthResponse authResponse = (AuthResponse) obj;
            if (this.zza.equals(authResponse.zza) && this.zzb.equals(authResponse.zzb)) {
                return true;
            }
        }
        return false;
    }

    public String getAuthCode() {
        return this.zzb;
    }

    public List<AuthScope> getGrantedScopes() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hash(this.zza, this.zzb);
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        int length = String.valueOf(valueOf).length();
        String str = this.zzb;
        StringBuilder sb = new StringBuilder(length + 39 + String.valueOf(str).length() + 2);
        sb.append("AuthResponse{grantedScopes=");
        sb.append(valueOf);
        sb.append(", authCode='");
        sb.append(str);
        sb.append("'}");
        return sb.toString();
    }
}
