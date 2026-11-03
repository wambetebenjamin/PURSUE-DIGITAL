package com.google.android.gms.internal.games_v2;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzz {
    public static String zza(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return "ALL_TIME";
                }
                StringBuilder sb = new StringBuilder(String.valueOf(i2).length() + 18);
                sb.append("Unknown time span ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            }
            return "WEEKLY";
        }
        return "DAILY";
    }
}
