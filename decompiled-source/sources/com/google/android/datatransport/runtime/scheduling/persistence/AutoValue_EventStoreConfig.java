package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig;
/* loaded from: classes4.dex */
final class AutoValue_EventStoreConfig extends EventStoreConfig {
    private final int criticalSectionEnterTimeoutMs;
    private final long eventCleanUpAge;
    private final int loadBatchSize;
    private final int maxBlobByteSizePerRow;
    private final long maxStorageSizeInBytes;

    private AutoValue_EventStoreConfig(long j2, int i2, int i3, long j3, int i4) {
        this.maxStorageSizeInBytes = j2;
        this.loadBatchSize = i2;
        this.criticalSectionEnterTimeoutMs = i3;
        this.eventCleanUpAge = j3;
        this.maxBlobByteSizePerRow = i4;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    long getMaxStorageSizeInBytes() {
        return this.maxStorageSizeInBytes;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int getLoadBatchSize() {
        return this.loadBatchSize;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int getCriticalSectionEnterTimeoutMs() {
        return this.criticalSectionEnterTimeoutMs;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    long getEventCleanUpAge() {
        return this.eventCleanUpAge;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int getMaxBlobByteSizePerRow() {
        return this.maxBlobByteSizePerRow;
    }

    public String toString() {
        return "EventStoreConfig{maxStorageSizeInBytes=" + this.maxStorageSizeInBytes + ", loadBatchSize=" + this.loadBatchSize + ", criticalSectionEnterTimeoutMs=" + this.criticalSectionEnterTimeoutMs + ", eventCleanUpAge=" + this.eventCleanUpAge + ", maxBlobByteSizePerRow=" + this.maxBlobByteSizePerRow + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof EventStoreConfig) {
            EventStoreConfig eventStoreConfig = (EventStoreConfig) obj;
            return this.maxStorageSizeInBytes == eventStoreConfig.getMaxStorageSizeInBytes() && this.loadBatchSize == eventStoreConfig.getLoadBatchSize() && this.criticalSectionEnterTimeoutMs == eventStoreConfig.getCriticalSectionEnterTimeoutMs() && this.eventCleanUpAge == eventStoreConfig.getEventCleanUpAge() && this.maxBlobByteSizePerRow == eventStoreConfig.getMaxBlobByteSizePerRow();
        }
        return false;
    }

    public int hashCode() {
        long j2 = this.maxStorageSizeInBytes;
        long j3 = this.eventCleanUpAge;
        return ((((((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ this.loadBatchSize) * 1000003) ^ this.criticalSectionEnterTimeoutMs) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003) ^ this.maxBlobByteSizePerRow;
    }

    /* loaded from: classes4.dex */
    static final class Builder extends EventStoreConfig.Builder {
        private Integer criticalSectionEnterTimeoutMs;
        private Long eventCleanUpAge;
        private Integer loadBatchSize;
        private Integer maxBlobByteSizePerRow;
        private Long maxStorageSizeInBytes;

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder setMaxStorageSizeInBytes(long j2) {
            this.maxStorageSizeInBytes = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder setLoadBatchSize(int i2) {
            this.loadBatchSize = Integer.valueOf(i2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder setCriticalSectionEnterTimeoutMs(int i2) {
            this.criticalSectionEnterTimeoutMs = Integer.valueOf(i2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder setEventCleanUpAge(long j2) {
            this.eventCleanUpAge = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder setMaxBlobByteSizePerRow(int i2) {
            this.maxBlobByteSizePerRow = Integer.valueOf(i2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig build() {
            String str = this.maxStorageSizeInBytes == null ? " maxStorageSizeInBytes" : "";
            if (this.loadBatchSize == null) {
                str = str + " loadBatchSize";
            }
            if (this.criticalSectionEnterTimeoutMs == null) {
                str = str + " criticalSectionEnterTimeoutMs";
            }
            if (this.eventCleanUpAge == null) {
                str = str + " eventCleanUpAge";
            }
            if (this.maxBlobByteSizePerRow == null) {
                str = str + " maxBlobByteSizePerRow";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_EventStoreConfig(this.maxStorageSizeInBytes.longValue(), this.loadBatchSize.intValue(), this.criticalSectionEnterTimeoutMs.intValue(), this.eventCleanUpAge.longValue(), this.maxBlobByteSizePerRow.intValue());
        }
    }
}
