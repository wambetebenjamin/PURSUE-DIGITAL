package com.google.android.gms.games.playergameevent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.games.internal.zzg;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class PlayerGameEvent extends zzg {
    public static final Parcelable.Creator<PlayerGameEvent> CREATOR = new zza();
    private final long zza;
    private final String zzb;
    private final Bundle zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlayerGameEvent(long j2, String str, Bundle bundle) {
        this.zza = j2;
        this.zzb = str;
        this.zzc = bundle == null ? new Bundle() : bundle;
    }

    public String getEventName() {
        return this.zzb;
    }

    public Bundle getEventProperties() {
        return this.zzc;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        long j2 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, j2);
        SafeParcelWriter.writeString(parcel, 2, getEventName(), false);
        SafeParcelWriter.writeBundle(parcel, 3, getEventProperties(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
    /* loaded from: classes5.dex */
    public static class Builder {
        private final String zza;
        private final Bundle zzb = new Bundle();

        public Builder(String str) {
            Preconditions.checkNotNull(str, "eventName cannot be null");
            Preconditions.checkArgument(!str.isEmpty(), "eventName cannot be empty");
            Preconditions.checkArgument(str.length() <= 100, "eventName cannot be longer than %s characters", 100);
            this.zza = str;
        }

        private final void zza(String str) {
            Preconditions.checkNotNull(str, "Key cannot be null.");
            Preconditions.checkArgument(!str.isEmpty(), "Key cannot be empty.");
            Preconditions.checkArgument(str.length() <= 100, "Key cannot exceed %s characters.", 100);
            Bundle bundle = this.zzb;
            Preconditions.checkState(bundle.containsKey(str) || bundle.size() < 25, "Cannot add more than %s properties.", 25);
        }

        public Builder addProperty(String str, double d2) {
            zza(str);
            this.zzb.putDouble(str, d2);
            return this;
        }

        public PlayerGameEvent build() {
            return new PlayerGameEvent(SystemClock.elapsedRealtime(), this.zza, this.zzb);
        }

        public Builder addProperty(String str, long j2) {
            zza(str);
            this.zzb.putLong(str, j2);
            return this;
        }

        public Builder addProperty(String str, String str2) {
            zza(str);
            Preconditions.checkNotNull(str2, "value cannot be null");
            Preconditions.checkArgument(str2.length() <= 1024, "Property's string value cannot exceed %s characters.", 1024);
            this.zzb.putString(str, str2);
            return this;
        }

        public Builder addProperty(String str, boolean z) {
            zza(str);
            this.zzb.putBoolean(str, z);
            return this;
        }
    }
}
