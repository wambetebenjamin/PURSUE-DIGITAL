package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.net.RequestFinishedInfo;
import com.ttnet.org.chromium.net.UrlRequest;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public abstract class ExperimentalUrlRequest extends UrlRequest {

    /* loaded from: classes9.dex */
    public static abstract class Builder extends UrlRequest.Builder {
        public static final int DEFAULT_IDEMPOTENCY = 0;
        public static final int IDEMPOTENT = 1;
        public static final int NOT_IDEMPOTENT = 2;

        @Override // com.ttnet.org.chromium.net.UrlRequest.Builder
        public abstract Builder addHeader(String str, String str2);

        public Builder addRequestAnnotation(Object obj) {
            return this;
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Builder
        public abstract Builder allowDirectExecutor();

        public Builder bindToNetwork(long j2) {
            return this;
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Builder
        public abstract ExperimentalUrlRequest build();

        @Override // com.ttnet.org.chromium.net.UrlRequest.Builder
        public abstract Builder disableCache();

        public Builder disableConnectionMigration() {
            return this;
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Builder
        public abstract Builder setHttpMethod(String str);

        public Builder setIdempotency(int i2) {
            return this;
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Builder
        public abstract Builder setPriority(int i2);

        public Builder setRequestFinishedListener(RequestFinishedInfo.Listener listener) {
            return this;
        }

        public Builder setTrafficStatsTag(int i2) {
            return this;
        }

        public Builder setTrafficStatsUid(int i2) {
            return this;
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Builder
        public abstract Builder setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor);
    }
}
