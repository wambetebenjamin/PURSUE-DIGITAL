package com.google.android.gms.internal.measurement;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public enum zzcj implements zzjy {
    UNSPECIFIED_TYPE(0),
    RAW_FILE_IO_TYPE(1),
    MOBSTORE_TYPE(2),
    SQLITE_OPEN_HELPER_TYPE(3),
    LEVEL_DB_TYPE(5),
    ROOM_TYPE(6),
    SHARED_PREFS_TYPE(7),
    PROTO_DATA_STORE_TYPE(8),
    UNRECOGNIZED(-1);
    
    private final int zzk;

    @Override // com.google.android.gms.internal.measurement.zzjy
    public final int zza() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.zzk;
    }

    @Override // java.lang.Enum
    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
        if (this != UNRECOGNIZED) {
            sb.append(" number=").append(zza());
        }
        return sb.append(" name=").append(name()).append('>').toString();
    }

    zzcj(int i2) {
        this.zzk = i2;
    }
}
