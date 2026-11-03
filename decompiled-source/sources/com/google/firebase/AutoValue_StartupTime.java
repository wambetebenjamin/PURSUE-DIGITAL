package com.google.firebase;
/* loaded from: classes9.dex */
final class AutoValue_StartupTime extends StartupTime {
    private final long elapsedRealtime;
    private final long epochMillis;
    private final long uptimeMillis;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_StartupTime(long j2, long j3, long j4) {
        this.epochMillis = j2;
        this.elapsedRealtime = j3;
        this.uptimeMillis = j4;
    }

    @Override // com.google.firebase.StartupTime
    public long getEpochMillis() {
        return this.epochMillis;
    }

    @Override // com.google.firebase.StartupTime
    public long getElapsedRealtime() {
        return this.elapsedRealtime;
    }

    @Override // com.google.firebase.StartupTime
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public String toString() {
        return "StartupTime{epochMillis=" + this.epochMillis + ", elapsedRealtime=" + this.elapsedRealtime + ", uptimeMillis=" + this.uptimeMillis + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StartupTime) {
            StartupTime startupTime = (StartupTime) obj;
            return this.epochMillis == startupTime.getEpochMillis() && this.elapsedRealtime == startupTime.getElapsedRealtime() && this.uptimeMillis == startupTime.getUptimeMillis();
        }
        return false;
    }

    public int hashCode() {
        long j2 = this.epochMillis;
        long j3 = this.elapsedRealtime;
        long j4 = this.uptimeMillis;
        return ((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ ((int) ((j4 >>> 32) ^ j4));
    }
}
