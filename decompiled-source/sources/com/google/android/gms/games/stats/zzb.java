package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Asserts;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzb extends DataBufferRef implements PlayerStats {
    private Bundle zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return PlayerStatsEntity.zzc(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ PlayerStats freeze() {
        return new PlayerStatsEntity(this);
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getAverageSessionLength() {
        return getFloat("ave_session_length_minutes");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getChurnProbability() {
        return getFloat("churn_probability");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getDaysSinceLastPlayed() {
        return getInteger("days_since_last_played");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getHighSpenderProbability() {
        if (hasColumn("high_spender_probability")) {
            return getFloat("high_spender_probability");
        }
        return -1.0f;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getNumberOfPurchases() {
        return getInteger("num_purchases");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final int getNumberOfSessions() {
        return getInteger("num_sessions");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSessionPercentile() {
        return getFloat("num_sessions_percentile");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSpendPercentile() {
        return getFloat("spend_percentile");
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getSpendProbability() {
        if (hasColumn("spend_probability")) {
            return getFloat("spend_probability");
        }
        return -1.0f;
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final float getTotalSpendNext28Days() {
        if (hasColumn("total_spend_next_28_days")) {
            return getFloat("total_spend_next_28_days");
        }
        return -1.0f;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return PlayerStatsEntity.zzb(this);
    }

    public final String toString() {
        return PlayerStatsEntity.zzd(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zza.zza(new PlayerStatsEntity(this), parcel, i2);
    }

    @Override // com.google.android.gms.games.stats.PlayerStats
    public final Bundle zza() {
        Bundle bundle = this.zza;
        if (bundle != null) {
            return bundle;
        }
        this.zza = new Bundle();
        String string = getString("unknown_raw_keys");
        String string2 = getString("unknown_raw_values");
        if (string != null && string2 != null) {
            String[] split = string.split(",");
            String[] split2 = string2.split(",");
            Asserts.checkState(split.length <= split2.length, "Invalid raw arguments!");
            for (int i2 = 0; i2 < split.length; i2++) {
                this.zza.putString(split[i2], split2[i2]);
            }
        }
        return this.zza;
    }
}
