package com.google.android.gms.games.leaderboard;

import android.util.SparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.games_v2.zzz;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class ScoreSubmissionData {
    private String zza;
    private String zzb;
    private int zzc;
    private SparseArray zzd = new SparseArray();

    /* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
    /* loaded from: classes5.dex */
    public static final class Result {
        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;

        public Result(long j2, String str, String str2, boolean z) {
            this.rawScore = j2;
            this.formattedScore = str;
            this.scoreTag = str2;
            this.newBest = z;
        }

        public String toString() {
            return Objects.toStringHelper(this).add("RawScore", Long.valueOf(this.rawScore)).add("FormattedScore", this.formattedScore).add("ScoreTag", this.scoreTag).add("NewBest", Boolean.valueOf(this.newBest)).toString();
        }
    }

    public ScoreSubmissionData(DataHolder dataHolder) {
        this.zzc = dataHolder.getStatusCode();
        int count = dataHolder.getCount();
        Preconditions.checkArgument(count == 3);
        int i2 = 0;
        while (i2 < count) {
            int windowIndex = dataHolder.getWindowIndex(i2);
            if (i2 == 0) {
                this.zza = dataHolder.getString("leaderboardId", 0, windowIndex);
                this.zzb = dataHolder.getString("playerId", 0, windowIndex);
                i2 = 0;
            }
            if (dataHolder.getBoolean("hasResult", i2, windowIndex)) {
                this.zzd.put(dataHolder.getInteger("timeSpan", i2, windowIndex), new Result(dataHolder.getLong("rawScore", i2, windowIndex), dataHolder.getString("formattedScore", i2, windowIndex), dataHolder.getString("scoreTag", i2, windowIndex), dataHolder.getBoolean("newBest", i2, windowIndex)));
            }
            i2++;
        }
    }

    public String getLeaderboardId() {
        return this.zza;
    }

    public String getPlayerId() {
        return this.zzb;
    }

    public Result getScoreResult(int i2) {
        return (Result) this.zzd.get(i2);
    }

    public String toString() {
        Objects.ToStringHelper add = Objects.toStringHelper(this).add("PlayerId", this.zzb).add("StatusCode", Integer.valueOf(this.zzc));
        for (int i2 = 0; i2 < 3; i2++) {
            Result result = (Result) this.zzd.get(i2);
            add.add("TimesSpan", zzz.zza(i2));
            add.add("Result", result == null ? "null" : result.toString());
        }
        return add.toString();
    }
}
