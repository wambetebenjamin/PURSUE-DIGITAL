package com.google.android.gms.internal.measurement;
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
public enum zzs {
    DEBUG(3),
    ERROR(6),
    INFO(4),
    VERBOSE(2),
    WARN(5);

    public static zzs zza(int i2) {
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 != 5) {
                    if (i2 == 6) {
                        return ERROR;
                    }
                    return INFO;
                }
                return WARN;
            }
            return DEBUG;
        }
        return VERBOSE;
    }

    zzs(int i2) {
    }
}
