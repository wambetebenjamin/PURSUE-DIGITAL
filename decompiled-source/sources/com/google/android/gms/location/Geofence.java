package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DefaultClock;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    /* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
    /* loaded from: classes5.dex */
    public static final class Builder {
        private double zze;
        private double zzf;
        private float zzg;
        private String zza = null;
        private int zzb = 0;
        private long zzc = Long.MIN_VALUE;
        private short zzd = -1;
        private int zzh = 0;
        private int zzi = -1;

        public Geofence build() {
            String str = this.zza;
            if (str != null) {
                int i2 = this.zzb;
                if (i2 != 0) {
                    if ((i2 & 4) == 0 || this.zzi >= 0) {
                        long j2 = this.zzc;
                        if (j2 != Long.MIN_VALUE) {
                            if (this.zzd != -1) {
                                int i3 = this.zzh;
                                if (i3 >= 0) {
                                    return new com.google.android.gms.internal.location.zzbe(str, i2, (short) 1, this.zze, this.zzf, this.zzg, j2, i3, this.zzi);
                                }
                                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
                            }
                            throw new IllegalArgumentException("Geofence region not set.");
                        }
                        throw new IllegalArgumentException("Expiration not set.");
                    }
                    throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
                }
                throw new IllegalArgumentException("Transitions types not set.");
            }
            throw new IllegalArgumentException("Request ID not set.");
        }

        public Builder setCircularRegion(double d2, double d3, float f2) {
            boolean z = d2 >= -90.0d && d2 <= 90.0d;
            StringBuilder sb = new StringBuilder(42);
            sb.append("Invalid latitude: ");
            sb.append(d2);
            Preconditions.checkArgument(z, sb.toString());
            boolean z2 = d3 >= -180.0d && d3 <= 180.0d;
            StringBuilder sb2 = new StringBuilder(43);
            sb2.append("Invalid longitude: ");
            sb2.append(d3);
            Preconditions.checkArgument(z2, sb2.toString());
            boolean z3 = f2 > 0.0f;
            StringBuilder sb3 = new StringBuilder(31);
            sb3.append("Invalid radius: ");
            sb3.append(f2);
            Preconditions.checkArgument(z3, sb3.toString());
            this.zzd = (short) 1;
            this.zze = d2;
            this.zzf = d3;
            this.zzg = f2;
            return this;
        }

        public Builder setExpirationDuration(long j2) {
            if (j2 < 0) {
                this.zzc = -1L;
            } else {
                this.zzc = DefaultClock.getInstance().elapsedRealtime() + j2;
            }
            return this;
        }

        public Builder setLoiteringDelay(int i2) {
            this.zzi = i2;
            return this;
        }

        public Builder setNotificationResponsiveness(int i2) {
            this.zzh = i2;
            return this;
        }

        public Builder setRequestId(String str) {
            this.zza = (String) Preconditions.checkNotNull(str, "Request ID can't be set to null");
            return this;
        }

        public Builder setTransitionTypes(int i2) {
            this.zzb = i2;
            return this;
        }
    }

    /* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
    /* loaded from: classes5.dex */
    public @interface GeofenceTransition {
    }

    /* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
    /* loaded from: classes5.dex */
    public @interface TransitionTypes {
    }

    String getRequestId();
}
