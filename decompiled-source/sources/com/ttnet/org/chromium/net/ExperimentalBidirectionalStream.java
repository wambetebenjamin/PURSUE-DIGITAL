package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.net.BidirectionalStream;
/* loaded from: classes9.dex */
public abstract class ExperimentalBidirectionalStream extends BidirectionalStream {

    /* loaded from: classes9.dex */
    public static abstract class Builder extends BidirectionalStream.Builder {
        @Override // com.ttnet.org.chromium.net.BidirectionalStream.Builder
        public abstract Builder addHeader(String str, String str2);

        public Builder addRequestAnnotation(Object obj) {
            return this;
        }

        public Builder bindToNetwork(long j2) {
            return this;
        }

        @Override // com.ttnet.org.chromium.net.BidirectionalStream.Builder
        public abstract ExperimentalBidirectionalStream build();

        @Override // com.ttnet.org.chromium.net.BidirectionalStream.Builder
        public abstract Builder delayRequestHeadersUntilFirstFlush(boolean z);

        @Override // com.ttnet.org.chromium.net.BidirectionalStream.Builder
        public abstract Builder setHttpMethod(String str);

        @Override // com.ttnet.org.chromium.net.BidirectionalStream.Builder
        public abstract Builder setPriority(int i2);

        public Builder setTrafficStatsTag(int i2) {
            return this;
        }

        public Builder setTrafficStatsUid(int i2) {
            return this;
        }
    }
}
