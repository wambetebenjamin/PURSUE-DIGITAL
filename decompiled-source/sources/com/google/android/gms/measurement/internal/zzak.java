package com.google.android.gms.measurement.internal;
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
enum zzak {
    UNSET('0'),
    REMOTE_DEFAULT('1'),
    REMOTE_DELEGATION('2'),
    MANIFEST('3'),
    INITIALIZATION('4'),
    API('5'),
    CHILD_ACCOUNT('6'),
    TCF('7'),
    REMOTE_ENFORCED_DEFAULT('8'),
    FAILSAFE('9');
    
    private final char zzl;

    public static zzak zza(char c2) {
        zzak[] values;
        for (zzak zzakVar : values()) {
            if (zzakVar.zzl == c2) {
                return zzakVar;
            }
        }
        return UNSET;
    }

    zzak(char c2) {
        this.zzl = c2;
    }
}
