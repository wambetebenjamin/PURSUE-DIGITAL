package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.HashCodeUtil;
import com.facebook.imagepipeline.common.ResizeOptions;
import java.io.File;
/* loaded from: classes8.dex */
public class ResizedImageCacheKey implements CacheKey {
    private final String mFileSignature;
    private final int mHash;
    private final ResizeOptions mResizeOptions;
    private final String mSourceUriString;

    public ResizedImageCacheKey(String str, ResizeOptions resizeOptions, File file) {
        String str2 = (String) Preconditions.checkNotNull(str);
        this.mSourceUriString = str2;
        this.mResizeOptions = (ResizeOptions) Preconditions.checkNotNull(resizeOptions);
        this.mFileSignature = Long.toString(((File) Preconditions.checkNotNull(file)).lastModified()) + Long.toString(file.length());
        this.mHash = HashCodeUtil.hashCode(str2.hashCode(), resizeOptions.hashCode(), str2.hashCode());
    }

    @Override // com.facebook.cache.common.CacheKey
    public String toString() {
        return String.format(null, "%s_%s_%s_%d", this.mSourceUriString, this.mResizeOptions, this.mFileSignature, Integer.valueOf(this.mHash));
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ResizedImageCacheKey) {
            ResizedImageCacheKey resizedImageCacheKey = (ResizedImageCacheKey) obj;
            return this.mHash == resizedImageCacheKey.mHash && this.mSourceUriString.equals(resizedImageCacheKey.mSourceUriString) && this.mResizeOptions.equals(resizedImageCacheKey.mResizeOptions) && this.mFileSignature.equals(resizedImageCacheKey.mFileSignature);
        }
        return false;
    }

    @Override // com.facebook.cache.common.CacheKey
    public int hashCode() {
        return this.mHash;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        return this.mSourceUriString.contains(uri.toString());
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return toString();
    }
}
