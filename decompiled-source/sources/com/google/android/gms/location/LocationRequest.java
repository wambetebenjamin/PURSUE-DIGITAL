package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationRequest> CREATOR = new zzbf();
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    int zza;
    long zzb;
    long zzc;
    boolean zzd;
    long zze;
    int zzf;
    float zzg;
    long zzh;
    boolean zzi;

    @Deprecated
    public LocationRequest() {
        this.zza = 102;
        this.zzb = 3600000L;
        this.zzc = 600000L;
        this.zzd = false;
        this.zze = Long.MAX_VALUE;
        this.zzf = Integer.MAX_VALUE;
        this.zzg = 0.0f;
        this.zzh = 0L;
        this.zzi = false;
    }

    public static LocationRequest create() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setWaitForAccurateLocation(true);
        return locationRequest;
    }

    private static void zza(long j2) {
        if (j2 >= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder(38);
        sb.append("invalid interval: ");
        sb.append(j2);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean equals(Object obj) {
        if (obj instanceof LocationRequest) {
            LocationRequest locationRequest = (LocationRequest) obj;
            if (this.zza == locationRequest.zza && this.zzb == locationRequest.zzb && this.zzc == locationRequest.zzc && this.zzd == locationRequest.zzd && this.zze == locationRequest.zze && this.zzf == locationRequest.zzf && this.zzg == locationRequest.zzg && getMaxWaitTime() == locationRequest.getMaxWaitTime() && this.zzi == locationRequest.zzi) {
                return true;
            }
        }
        return false;
    }

    public long getExpirationTime() {
        return this.zze;
    }

    public long getFastestInterval() {
        return this.zzc;
    }

    public long getInterval() {
        return this.zzb;
    }

    public long getMaxWaitTime() {
        long j2 = this.zzh;
        long j3 = this.zzb;
        return j2 < j3 ? j3 : j2;
    }

    public int getNumUpdates() {
        return this.zzf;
    }

    public int getPriority() {
        return this.zza;
    }

    public float getSmallestDisplacement() {
        return this.zzg;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Long.valueOf(this.zzb), Float.valueOf(this.zzg), Long.valueOf(this.zzh));
    }

    public boolean isFastestIntervalExplicitlySet() {
        return this.zzd;
    }

    public boolean isWaitForAccurateLocation() {
        return this.zzi;
    }

    public LocationRequest setExpirationDuration(long j2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j3 = j2 <= Long.MAX_VALUE - elapsedRealtime ? j2 + elapsedRealtime : Long.MAX_VALUE;
        this.zze = j3;
        if (j3 < 0) {
            this.zze = 0L;
        }
        return this;
    }

    public LocationRequest setExpirationTime(long j2) {
        this.zze = j2;
        if (j2 < 0) {
            this.zze = 0L;
        }
        return this;
    }

    public LocationRequest setFastestInterval(long j2) {
        zza(j2);
        this.zzd = true;
        this.zzc = j2;
        return this;
    }

    public LocationRequest setInterval(long j2) {
        zza(j2);
        this.zzb = j2;
        if (!this.zzd) {
            this.zzc = (long) (j2 / 6.0d);
        }
        return this;
    }

    public LocationRequest setMaxWaitTime(long j2) {
        zza(j2);
        this.zzh = j2;
        return this;
    }

    public LocationRequest setNumUpdates(int i2) {
        if (i2 > 0) {
            this.zzf = i2;
            return this;
        }
        StringBuilder sb = new StringBuilder(31);
        sb.append("invalid numUpdates: ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    public LocationRequest setPriority(int i2) {
        if (i2 == 100 || i2 == 102 || i2 == 104 || i2 == 105) {
            this.zza = i2;
            return this;
        }
        StringBuilder sb = new StringBuilder(28);
        sb.append("invalid quality: ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    public LocationRequest setSmallestDisplacement(float f2) {
        if (f2 >= 0.0f) {
            this.zzg = f2;
            return this;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("invalid displacement: ");
        sb.append(f2);
        throw new IllegalArgumentException(sb.toString());
    }

    public LocationRequest setWaitForAccurateLocation(boolean z) {
        this.zzi = z;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        int i2 = this.zza;
        sb.append(i2 != 100 ? i2 != 102 ? i2 != 104 ? i2 != 105 ? "???" : "PRIORITY_NO_POWER" : "PRIORITY_LOW_POWER" : "PRIORITY_BALANCED_POWER_ACCURACY" : "PRIORITY_HIGH_ACCURACY");
        if (this.zza != 105) {
            sb.append(" requested=");
            sb.append(this.zzb);
            sb.append("ms");
        }
        sb.append(" fastest=");
        sb.append(this.zzc);
        sb.append("ms");
        if (this.zzh > this.zzb) {
            sb.append(" maxWait=");
            sb.append(this.zzh);
            sb.append("ms");
        }
        if (this.zzg > 0.0f) {
            sb.append(" smallestDisplacement=");
            sb.append(this.zzg);
            sb.append("m");
        }
        long j2 = this.zze;
        if (j2 != Long.MAX_VALUE) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(j2 - elapsedRealtime);
            sb.append("ms");
        }
        if (this.zzf != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.zzf);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.writeLong(parcel, 5, this.zze);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzg);
        SafeParcelWriter.writeLong(parcel, 8, this.zzh);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationRequest(int i2, long j2, long j3, boolean z, long j4, int i3, float f2, long j5, boolean z2) {
        this.zza = i2;
        this.zzb = j2;
        this.zzc = j3;
        this.zzd = z;
        this.zze = j4;
        this.zzf = i3;
        this.zzg = f2;
        this.zzh = j5;
        this.zzi = z2;
    }
}
