package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class LeaderboardEntity implements Leaderboard {
    private final String zza;
    private final String zzb;
    private final Uri zzc;
    private final int zzd;
    private final ArrayList zze;
    private final String zzf;

    public LeaderboardEntity(Leaderboard leaderboard) {
        this.zza = leaderboard.getLeaderboardId();
        this.zzb = leaderboard.getDisplayName();
        this.zzc = leaderboard.getIconImageUri();
        this.zzf = leaderboard.getIconImageUrl();
        this.zzd = leaderboard.getScoreOrder();
        Game zza = leaderboard.zza();
        if (zza != null) {
            new GameEntity(zza);
        }
        ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        int size = variants.size();
        this.zze = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.zze.add((LeaderboardVariantEntity) variants.get(i2).freeze());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(Leaderboard leaderboard) {
        return Objects.hashCode(leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), Integer.valueOf(leaderboard.getScoreOrder()), leaderboard.getVariants());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc(Leaderboard leaderboard, Object obj) {
        if (obj instanceof Leaderboard) {
            if (leaderboard == obj) {
                return true;
            }
            Leaderboard leaderboard2 = (Leaderboard) obj;
            return Objects.equal(leaderboard2.getLeaderboardId(), leaderboard.getLeaderboardId()) && Objects.equal(leaderboard2.getDisplayName(), leaderboard.getDisplayName()) && Objects.equal(leaderboard2.getIconImageUri(), leaderboard.getIconImageUri()) && Objects.equal(Integer.valueOf(leaderboard2.getScoreOrder()), Integer.valueOf(leaderboard.getScoreOrder())) && Objects.equal(leaderboard2.getVariants(), leaderboard.getVariants());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzd(Leaderboard leaderboard) {
        return Objects.toStringHelper(leaderboard).add("LeaderboardId", leaderboard.getLeaderboardId()).add("DisplayName", leaderboard.getDisplayName()).add("IconImageUri", leaderboard.getIconImageUri()).add("IconImageUrl", leaderboard.getIconImageUrl()).add("ScoreOrder", Integer.valueOf(leaderboard.getScoreOrder())).add("Variants", leaderboard.getVariants()).toString();
    }

    public final boolean equals(Object obj) {
        return zzc(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ Leaderboard freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final String getDisplayName() {
        return this.zzb;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzb, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final Uri getIconImageUri() {
        return this.zzc;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public String getIconImageUrl() {
        return this.zzf;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final String getLeaderboardId() {
        return this.zza;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final int getScoreOrder() {
        return this.zzd;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<>(this.zze);
    }

    public final int hashCode() {
        return zzb(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzd(this);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public final Game zza() {
        throw null;
    }
}
