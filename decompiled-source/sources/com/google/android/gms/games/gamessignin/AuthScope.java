package com.google.android.gms.games.gamessignin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.internal.games_v2.zzgj;
import com.google.android.gms.internal.games_v2.zzgm;
import com.google.android.gms.internal.games_v2.zzgo;
import com.google.android.gms.internal.games_v2.zzgp;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public enum AuthScope {
    EMAIL("email"),
    PROFILE("profile"),
    OPEN_ID("openid"),
    GAMES_LITE(Scopes.GAMES_LITE),
    DRIVE_APP_FOLDER(Scopes.DRIVE_APPFOLDER);
    
    private static final zzgp zzd;
    private final String zzc;

    static {
        AuthScope[] values;
        zzgo zzgoVar = new zzgo();
        for (AuthScope authScope : values()) {
            zzgoVar.zza(authScope.zzc, authScope);
        }
        zzd = zzgoVar.zzb();
    }

    AuthScope(String str) {
        this.zzc = str;
    }

    public static zzgm zza(List list) {
        Objects.requireNonNull(list, "Input values list cannot be null");
        int i2 = zzgm.zzd;
        zzgj zzgjVar = new zzgj();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Objects.requireNonNull(str, "Input list of scope strings must not be null");
            AuthScope authScope = (AuthScope) zzd.get(str);
            if (authScope != null) {
                zzgjVar.zzb(authScope);
            } else {
                String.valueOf(str);
                throw new IllegalArgumentException("Invalid scope: ".concat(String.valueOf(str)));
            }
        }
        return zzgjVar.zzc();
    }

    public String getValue() {
        return this.zzc;
    }
}
