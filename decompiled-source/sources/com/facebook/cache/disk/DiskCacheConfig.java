package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import java.io.File;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class DiskCacheConfig {
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private String mConfigBaseDirectoryName;
    private Supplier<File> mConfigBaseDirectoryPathSupplier;
    private final Context mContext;
    private final long mDefaultSizeLimit;
    private final DiskTrimmableRegistry mDiskTrimmableRegistry;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final float mEvictRatio;
    private final boolean mIndexPopulateAtStartupEnabled;
    private final long mLowDiskSpaceSizeLimit;
    private final long mMinimumSizeLimit;
    private final boolean mNeedEncrypt;
    private final boolean mNeedMD5;
    private final int mVersion;

    private DiskCacheConfig(Builder builder) {
        CacheErrorLogger cacheErrorLogger;
        CacheEventListener cacheEventListener;
        DiskTrimmableRegistry diskTrimmableRegistry;
        this.mVersion = builder.mVersion;
        this.mBaseDirectoryName = (String) Preconditions.checkNotNull(builder.mBaseDirectoryName);
        this.mBaseDirectoryPathSupplier = (Supplier) Preconditions.checkNotNull(builder.mBaseDirectoryPathSupplier);
        this.mDefaultSizeLimit = builder.mMaxCacheSize;
        this.mLowDiskSpaceSizeLimit = builder.mMaxCacheSizeOnLowDiskSpace;
        this.mMinimumSizeLimit = builder.mMaxCacheSizeOnVeryLowDiskSpace;
        this.mEvictRatio = builder.mEvictRatio;
        this.mEntryEvictionComparatorSupplier = (EntryEvictionComparatorSupplier) Preconditions.checkNotNull(builder.mEntryEvictionComparatorSupplier);
        if (builder.mCacheErrorLogger == null) {
            cacheErrorLogger = NoOpCacheErrorLogger.getInstance();
        } else {
            cacheErrorLogger = builder.mCacheErrorLogger;
        }
        this.mCacheErrorLogger = cacheErrorLogger;
        if (builder.mCacheEventListener == null) {
            cacheEventListener = NoOpCacheEventListener.getInstance();
        } else {
            cacheEventListener = builder.mCacheEventListener;
        }
        this.mCacheEventListener = cacheEventListener;
        if (builder.mDiskTrimmableRegistry == null) {
            diskTrimmableRegistry = NoOpDiskTrimmableRegistry.getInstance();
        } else {
            diskTrimmableRegistry = builder.mDiskTrimmableRegistry;
        }
        this.mDiskTrimmableRegistry = diskTrimmableRegistry;
        this.mContext = builder.mContext;
        this.mIndexPopulateAtStartupEnabled = builder.mIndexPopulateAtStartupEnabled;
        this.mNeedMD5 = builder.mNeedMD5;
        this.mNeedEncrypt = builder.mNeedEncrypt;
        this.mConfigBaseDirectoryName = builder.mConfigBaseDirectoryName;
        this.mConfigBaseDirectoryPathSupplier = builder.mConfigBaseDirectoryPathSupplier;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public String getBaseDirectoryName() {
        return this.mBaseDirectoryName;
    }

    public Supplier<File> getBaseDirectoryPathSupplier() {
        return this.mBaseDirectoryPathSupplier;
    }

    public long getDefaultSizeLimit() {
        return this.mDefaultSizeLimit;
    }

    public long getLowDiskSpaceSizeLimit() {
        return this.mLowDiskSpaceSizeLimit;
    }

    public long getMinimumSizeLimit() {
        return this.mMinimumSizeLimit;
    }

    public float getEvictRatio() {
        return this.mEvictRatio;
    }

    public EntryEvictionComparatorSupplier getEntryEvictionComparatorSupplier() {
        return this.mEntryEvictionComparatorSupplier;
    }

    public CacheErrorLogger getCacheErrorLogger() {
        return this.mCacheErrorLogger;
    }

    public CacheEventListener getCacheEventListener() {
        return this.mCacheEventListener;
    }

    public DiskTrimmableRegistry getDiskTrimmableRegistry() {
        return this.mDiskTrimmableRegistry;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean getIndexPopulateAtStartupEnabled() {
        return this.mIndexPopulateAtStartupEnabled;
    }

    public boolean getNeedMD5() {
        return this.mNeedMD5;
    }

    public boolean getNeedEncrypt() {
        return this.mNeedEncrypt;
    }

    public String getConfigBaseDirectoryName() {
        return this.mConfigBaseDirectoryName;
    }

    public Supplier<File> getConfigBaseDirectoryPathSupplier() {
        return this.mConfigBaseDirectoryPathSupplier;
    }

    public static Builder newBuilder(@Nullable Context context) {
        return new Builder(context);
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private String mBaseDirectoryName;
        private Supplier<File> mBaseDirectoryPathSupplier;
        private CacheErrorLogger mCacheErrorLogger;
        private CacheEventListener mCacheEventListener;
        private String mConfigBaseDirectoryName;
        private Supplier<File> mConfigBaseDirectoryPathSupplier;
        @Nullable
        private final Context mContext;
        private DiskTrimmableRegistry mDiskTrimmableRegistry;
        private EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
        private float mEvictRatio;
        private boolean mIndexPopulateAtStartupEnabled;
        private long mMaxCacheSize;
        private long mMaxCacheSizeOnLowDiskSpace;
        private long mMaxCacheSizeOnVeryLowDiskSpace;
        private boolean mNeedEncrypt;
        private boolean mNeedMD5;
        private int mVersion;

        private Builder(@Nullable Context context) {
            this.mVersion = 1;
            this.mBaseDirectoryName = "image_cache";
            this.mMaxCacheSize = 41943040L;
            this.mEvictRatio = 0.1f;
            this.mMaxCacheSizeOnLowDiskSpace = 10485760L;
            this.mMaxCacheSizeOnVeryLowDiskSpace = 2097152L;
            this.mEntryEvictionComparatorSupplier = new DefaultEntryEvictionComparatorSupplier();
            this.mConfigBaseDirectoryName = "image_config";
            this.mContext = context;
        }

        public Builder setVersion(int i2) {
            this.mVersion = i2;
            return this;
        }

        public Builder setBaseDirectoryName(String str) {
            this.mBaseDirectoryName = str;
            return this;
        }

        public Builder setBaseDirectoryPath(File file) {
            this.mBaseDirectoryPathSupplier = Suppliers.of(file);
            return this;
        }

        public Builder setBaseDirectoryPathSupplier(Supplier<File> supplier) {
            this.mBaseDirectoryPathSupplier = supplier;
            return this;
        }

        public Builder setMaxCacheSize(long j2) {
            this.mMaxCacheSize = j2;
            return this;
        }

        public Builder setEvictRatio(float f2) {
            if (f2 > 1.0f) {
                this.mEvictRatio = 1.0f;
            } else if (f2 < 0.0f) {
                this.mEvictRatio = 0.0f;
            } else {
                this.mEvictRatio = f2;
            }
            return this;
        }

        public Builder setMaxCacheSizeOnLowDiskSpace(long j2) {
            this.mMaxCacheSizeOnLowDiskSpace = j2;
            return this;
        }

        public Builder setMaxCacheSizeOnVeryLowDiskSpace(long j2) {
            this.mMaxCacheSizeOnVeryLowDiskSpace = j2;
            return this;
        }

        public Builder setEntryEvictionComparatorSupplier(EntryEvictionComparatorSupplier entryEvictionComparatorSupplier) {
            this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
            return this;
        }

        public Builder setCacheErrorLogger(CacheErrorLogger cacheErrorLogger) {
            this.mCacheErrorLogger = cacheErrorLogger;
            return this;
        }

        public Builder setCacheEventListener(CacheEventListener cacheEventListener) {
            this.mCacheEventListener = cacheEventListener;
            return this;
        }

        public Builder setDiskTrimmableRegistry(DiskTrimmableRegistry diskTrimmableRegistry) {
            this.mDiskTrimmableRegistry = diskTrimmableRegistry;
            return this;
        }

        public Builder setIndexPopulateAtStartupEnabled(boolean z) {
            this.mIndexPopulateAtStartupEnabled = z;
            return this;
        }

        public Builder setNeedMD5(boolean z) {
            this.mNeedMD5 = z;
            return this;
        }

        public Builder setNeedEncrypt(boolean z) {
            this.mNeedEncrypt = z;
            return this;
        }

        public Builder setConfigBaseDirectoryName(String str) {
            this.mConfigBaseDirectoryName = str;
            return this;
        }

        public Builder setConfigBaseDirectoryPath(File file) {
            this.mConfigBaseDirectoryPathSupplier = Suppliers.of(file);
            return this;
        }

        public Builder setConfigBaseDirectoryPathSupplier(Supplier<File> supplier) {
            this.mConfigBaseDirectoryPathSupplier = supplier;
            return this;
        }

        public DiskCacheConfig build() {
            Preconditions.checkState((this.mBaseDirectoryPathSupplier == null && this.mContext == null) ? false : true, "Either a non-null context or a base directory path or supplier must be provided.");
            if (this.mBaseDirectoryPathSupplier == null && this.mContext != null) {
                this.mBaseDirectoryPathSupplier = new Supplier<File>() { // from class: com.facebook.cache.disk.DiskCacheConfig.Builder.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.facebook.common.internal.Supplier
                    public File get() {
                        return Builder.this.mContext.getApplicationContext().getCacheDir();
                    }
                };
            }
            if (this.mConfigBaseDirectoryPathSupplier == null && this.mContext != null) {
                this.mConfigBaseDirectoryPathSupplier = this.mBaseDirectoryPathSupplier;
            }
            return new DiskCacheConfig(this);
        }
    }
}
