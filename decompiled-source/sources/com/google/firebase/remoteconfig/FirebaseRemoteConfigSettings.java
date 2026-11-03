package com.google.firebase.remoteconfig;

import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
/* loaded from: classes9.dex */
public class FirebaseRemoteConfigSettings {
    private final long fetchTimeoutInSeconds;
    private final long minimumFetchInterval;

    private FirebaseRemoteConfigSettings(Builder builder) {
        this.fetchTimeoutInSeconds = builder.fetchTimeoutInSeconds;
        this.minimumFetchInterval = builder.minimumFetchInterval;
    }

    public long getFetchTimeoutInSeconds() {
        return this.fetchTimeoutInSeconds;
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.minimumFetchInterval;
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.setFetchTimeoutInSeconds(getFetchTimeoutInSeconds());
        builder.setMinimumFetchIntervalInSeconds(getMinimumFetchIntervalInSeconds());
        return builder;
    }

    /* loaded from: classes9.dex */
    public static class Builder {
        private long fetchTimeoutInSeconds = 60;
        private long minimumFetchInterval = ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS;

        public Builder setFetchTimeoutInSeconds(long j2) throws IllegalArgumentException {
            if (j2 < 0) {
                throw new IllegalArgumentException(String.format("Fetch connection timeout has to be a non-negative number. %d is an invalid argument", Long.valueOf(j2)));
            }
            this.fetchTimeoutInSeconds = j2;
            return this;
        }

        public Builder setMinimumFetchIntervalInSeconds(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("Minimum interval between fetches has to be a non-negative number. " + j2 + " is an invalid argument");
            }
            this.minimumFetchInterval = j2;
            return this;
        }

        public long getFetchTimeoutInSeconds() {
            return this.fetchTimeoutInSeconds;
        }

        public long getMinimumFetchIntervalInSeconds() {
            return this.minimumFetchInterval;
        }

        public FirebaseRemoteConfigSettings build() {
            return new FirebaseRemoteConfigSettings(this);
        }
    }
}
