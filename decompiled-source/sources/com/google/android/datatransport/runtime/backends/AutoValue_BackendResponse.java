package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.backends.BackendResponse;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_BackendResponse extends BackendResponse {
    private final long nextRequestWaitMillis;
    private final BackendResponse.Status status;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_BackendResponse(BackendResponse.Status status, long j2) {
        Objects.requireNonNull(status, "Null status");
        this.status = status;
        this.nextRequestWaitMillis = j2;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendResponse
    public BackendResponse.Status getStatus() {
        return this.status;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendResponse
    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    public String toString() {
        return "BackendResponse{status=" + this.status + ", nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BackendResponse) {
            BackendResponse backendResponse = (BackendResponse) obj;
            return this.status.equals(backendResponse.getStatus()) && this.nextRequestWaitMillis == backendResponse.getNextRequestWaitMillis();
        }
        return false;
    }

    public int hashCode() {
        long j2 = this.nextRequestWaitMillis;
        return ((this.status.hashCode() ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }
}
