package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.common.util.HashCodeUtil;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.FrescoCacheMonitorUtil;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class BitmapMemoryCacheKey implements CacheKey {
    private final long mCacheTime;
    private final Object mCallerContext;
    private final int mHash;
    private final ImageDecodeOptions mImageDecodeOptions;
    @Nullable
    private final CacheKey mPostprocessorCacheKey;
    @Nullable
    private final String mPostprocessorName;
    @Nullable
    private final ResizeOptions mResizeOptions;
    private final RotationOptions mRotationOptions;
    private final String mSourceString;

    public BitmapMemoryCacheKey(String str, @Nullable ResizeOptions resizeOptions, RotationOptions rotationOptions, ImageDecodeOptions imageDecodeOptions, @Nullable CacheKey cacheKey, @Nullable String str2, Object obj) {
        this.mSourceString = (String) Preconditions.checkNotNull(str);
        this.mResizeOptions = resizeOptions;
        this.mRotationOptions = rotationOptions;
        this.mImageDecodeOptions = imageDecodeOptions;
        this.mPostprocessorCacheKey = cacheKey;
        this.mPostprocessorName = str2;
        this.mHash = HashCodeUtil.hashCode(Integer.valueOf(str.hashCode()), Integer.valueOf((resizeOptions == null || BitmapCacheUtil.isOptReuseBitmapCache()) ? 0 : resizeOptions.hashCode()), Integer.valueOf(rotationOptions != null ? rotationOptions.hashCode() : 0), FrescoCacheMonitorUtil.isOptBitmapCacheKey() ? imageDecodeOptions.toOptKeyString() : imageDecodeOptions, cacheKey, str2);
        this.mCallerContext = obj;
        this.mCacheTime = RealtimeSinceBootClock.get().now();
    }

    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    public static CacheKey getReuseOptCacheKey(CacheKey cacheKey) {
        ResizeOptions optReuseResizeOption = BitmapCacheUtil.getOptReuseResizeOption(cacheKey);
        if (!(cacheKey instanceof BitmapMemoryCacheKey) || optReuseResizeOption == null) {
            return cacheKey;
        }
        BitmapMemoryCacheKey bitmapMemoryCacheKey = (BitmapMemoryCacheKey) cacheKey;
        return new BitmapMemoryCacheKey(bitmapMemoryCacheKey.mSourceString, optReuseResizeOption, bitmapMemoryCacheKey.mRotationOptions, bitmapMemoryCacheKey.mImageDecodeOptions, bitmapMemoryCacheKey.mPostprocessorCacheKey, bitmapMemoryCacheKey.mPostprocessorName, bitmapMemoryCacheKey.mCallerContext);
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean equals(Object obj) {
        if (obj instanceof BitmapMemoryCacheKey) {
            BitmapMemoryCacheKey bitmapMemoryCacheKey = (BitmapMemoryCacheKey) obj;
            return (this.mHash == bitmapMemoryCacheKey.mHash && this.mSourceString.equals(bitmapMemoryCacheKey.mSourceString) && Objects.equal(this.mResizeOptions, bitmapMemoryCacheKey.mResizeOptions) && Objects.equal(this.mRotationOptions, bitmapMemoryCacheKey.mRotationOptions) && FrescoCacheMonitorUtil.isOptBitmapCacheKey()) ? Objects.equal(this.mImageDecodeOptions.toOptKeyString(), bitmapMemoryCacheKey.mImageDecodeOptions.toOptKeyString()) : Objects.equal(this.mImageDecodeOptions, bitmapMemoryCacheKey.mImageDecodeOptions) && Objects.equal(this.mPostprocessorCacheKey, bitmapMemoryCacheKey.mPostprocessorCacheKey) && Objects.equal(this.mPostprocessorName, bitmapMemoryCacheKey.mPostprocessorName);
        }
        return false;
    }

    @Override // com.facebook.cache.common.CacheKey
    public int hashCode() {
        if (BitmapCacheUtil.isOptReuseBitmapCache()) {
            return HashCodeUtil.hashCode(Integer.valueOf(this.mHash), this.mResizeOptions);
        }
        return this.mHash;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        return getUriString().contains(uri.toString());
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return this.mSourceString;
    }

    @Nullable
    public String getPostprocessorName() {
        return this.mPostprocessorName;
    }

    @Override // com.facebook.cache.common.CacheKey
    public String toString() {
        if (BitmapCacheUtil.isOptReuseBitmapCache()) {
            Object[] objArr = new Object[7];
            objArr[0] = this.mSourceString + "_uri_end";
            objArr[1] = this.mRotationOptions;
            objArr[2] = FrescoCacheMonitorUtil.isOptBitmapCacheKey() ? this.mImageDecodeOptions.toOptKeyString() : this.mImageDecodeOptions;
            objArr[3] = this.mPostprocessorCacheKey;
            objArr[4] = this.mPostprocessorName;
            objArr[5] = Integer.valueOf(this.mHash);
            objArr[6] = "resizeOption:" + this.mResizeOptions;
            return String.format(null, "%s_%s_%s_%s_%s_%d_%s", objArr);
        }
        Object[] objArr2 = new Object[7];
        objArr2[0] = this.mSourceString;
        objArr2[1] = this.mResizeOptions;
        objArr2[2] = this.mRotationOptions;
        objArr2[3] = FrescoCacheMonitorUtil.isOptBitmapCacheKey() ? this.mImageDecodeOptions.toOptKeyString() : this.mImageDecodeOptions;
        objArr2[4] = this.mPostprocessorCacheKey;
        objArr2[5] = this.mPostprocessorName;
        objArr2[6] = Integer.valueOf(this.mHash);
        return String.format(null, "%s_%s_%s_%s_%s_%s_%d", objArr2);
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public long getInBitmapCacheSince() {
        return this.mCacheTime;
    }
}
