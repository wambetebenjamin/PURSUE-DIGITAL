package com.facebook.imagepipeline.request;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.SizeDeterminer;
import com.facebook.imagepipeline.blurhash.BlurHashConfig;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.listener.RequestListener;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class ImageRequest {
    private int mAwebpScanNumber;
    private final List<Uri> mBackupUris;
    @Nullable
    private final BlurHashConfig mBlurHashOptions;
    @Nullable
    private final BytesRange mBytesRange;
    private final CacheChoice mCacheChoice;
    private final String mCustomCacheName;
    private Map<String, String> mCustomParam;
    private boolean mHasDecodedThumb;
    private Map<String, String> mHttpHeader;
    private final ImageDecodeOptions mImageDecodeOptions;
    private final boolean mIsDiskCacheEnabled;
    private final boolean mIsMemoryCacheEnabled;
    private final boolean mIsResizedImageDiskCacheEnabled;
    private final boolean mLoadThumbnailOnly;
    private final boolean mLocalThumbnailPreviewsEnabled;
    private final RequestLevel mLowestPermittedRequestLevel;
    private boolean mMultiplexerEnabled;
    private boolean mNeedReplaceUri;
    @Nullable
    private final Postprocessor mPostprocessor;
    private int mPreviewAlgo;
    private long mPreviewDecodeEnd;
    private long mPreviewDecodeTime;
    private int mPreviewDuration;
    private String mPreviewError;
    private boolean mPreviewUsed;
    private final boolean mProgressiveRenderingAnimatedEnabled;
    private final boolean mProgressiveRenderingEnabled;
    private final boolean mProgressiveRenderingHeicEnabled;
    private String mPublicKey;
    @Nullable
    private final RequestListener mRequestListener;
    private final Priority mRequestPriority;
    @Nullable
    private final ResizeOptions mResizeOptions;
    private final RotationOptions mRotationOptions;
    private SizeDeterminer mSizeDeterminer;
    private File mSourceFile;
    private final Uri mSourceUri;
    private final int mSourceUriType;
    private boolean mThumbDataInFetch;
    private String mUrlKey;

    /* loaded from: classes8.dex */
    public enum CacheChoice {
        CUSTOM,
        SMALL,
        DEFAULT
    }

    public void setPreviewUsed(boolean z) {
        this.mPreviewUsed = z;
    }

    public boolean getPreviewUsed() {
        return this.mPreviewUsed;
    }

    public void setPreviewAlgo(int i2) {
        this.mPreviewAlgo = i2;
    }

    public int getPreviewAlgo() {
        return this.mPreviewAlgo;
    }

    public void setPreviewDecodeTime(long j2) {
        this.mPreviewDecodeTime = j2;
    }

    public long getPreviewDecodeTime() {
        return this.mPreviewDecodeTime;
    }

    public void setPreviewDecodeEnd(long j2) {
        this.mPreviewDecodeEnd = j2;
    }

    public long getPreviewDecodeEnd() {
        return this.mPreviewDecodeEnd;
    }

    public void setPreviewDuration(int i2) {
        this.mPreviewDuration = i2;
    }

    public int getPreviewDuration() {
        return this.mPreviewDuration;
    }

    public void setPreviewError(String str) {
        this.mPreviewError = str;
    }

    public String getPreviewError() {
        return this.mPreviewError;
    }

    public static ImageRequest fromFile(@Nullable File file) {
        if (file == null) {
            return null;
        }
        return fromUri(UriUtil.getUriForFile(file));
    }

    public static ImageRequest fromUri(@Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        return ImageRequestBuilder.newBuilderWithSource(uri).build();
    }

    public static ImageRequest fromUri(@Nullable String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return fromUri(Uri.parse(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ImageRequest(ImageRequestBuilder imageRequestBuilder) {
        RotationOptions rotationOptions;
        this.mCacheChoice = imageRequestBuilder.getCacheChoice();
        Uri sourceUri = imageRequestBuilder.getSourceUri();
        this.mSourceUri = sourceUri;
        this.mBackupUris = imageRequestBuilder.getBackupUris();
        this.mSourceUriType = getSourceUriType(sourceUri, imageRequestBuilder.getMimeType());
        this.mProgressiveRenderingEnabled = imageRequestBuilder.isProgressiveRenderingEnabled();
        this.mProgressiveRenderingAnimatedEnabled = imageRequestBuilder.isProgressiveRenderingAnimatedEnabled();
        this.mProgressiveRenderingHeicEnabled = imageRequestBuilder.isProgressiveRenderingHeicEnabled();
        this.mLocalThumbnailPreviewsEnabled = imageRequestBuilder.isLocalThumbnailPreviewsEnabled();
        this.mLoadThumbnailOnly = imageRequestBuilder.getLoadThumbnailOnly();
        this.mImageDecodeOptions = imageRequestBuilder.getImageDecodeOptions();
        this.mResizeOptions = imageRequestBuilder.getResizeOptions();
        this.mBlurHashOptions = imageRequestBuilder.getBlurHashOptions();
        if (imageRequestBuilder.getRotationOptions() == null) {
            rotationOptions = RotationOptions.autoRotate();
        } else {
            rotationOptions = imageRequestBuilder.getRotationOptions();
        }
        this.mRotationOptions = rotationOptions;
        this.mBytesRange = imageRequestBuilder.getBytesRange();
        this.mRequestPriority = imageRequestBuilder.getRequestPriority();
        this.mLowestPermittedRequestLevel = imageRequestBuilder.getLowestPermittedRequestLevel();
        this.mIsDiskCacheEnabled = imageRequestBuilder.isDiskCacheEnabled();
        this.mIsResizedImageDiskCacheEnabled = imageRequestBuilder.isResizedImageDiskCacheEnabled();
        this.mIsMemoryCacheEnabled = imageRequestBuilder.isMemoryCacheEnabled();
        this.mPostprocessor = imageRequestBuilder.getPostprocessor();
        this.mRequestListener = imageRequestBuilder.getRequestListener();
        this.mCustomCacheName = imageRequestBuilder.getCustomCacheName();
        this.mMultiplexerEnabled = imageRequestBuilder.isMultiplexerEnabled();
        this.mNeedReplaceUri = imageRequestBuilder.isNeedReplaceUri();
        this.mHttpHeader = imageRequestBuilder.getHttpHeader();
        this.mAwebpScanNumber = imageRequestBuilder.getAwebpScanNumber();
        this.mCustomParam = imageRequestBuilder.getCustomParam();
        this.mPublicKey = imageRequestBuilder.getPublicKey();
        this.mUrlKey = imageRequestBuilder.getUrlKey();
    }

    public CacheChoice getCacheChoice() {
        return this.mCacheChoice;
    }

    public Uri getSourceUri() {
        return this.mSourceUri;
    }

    public List<Uri> getBackupUris() {
        return this.mBackupUris;
    }

    public int getSourceUriType() {
        return this.mSourceUriType;
    }

    public int getPreferredWidth() {
        ResizeOptions resizeOptions = this.mResizeOptions;
        if (resizeOptions != null) {
            return resizeOptions.width;
        }
        return 2048;
    }

    public int getPreferredHeight() {
        ResizeOptions resizeOptions = this.mResizeOptions;
        if (resizeOptions != null) {
            return resizeOptions.height;
        }
        return 2048;
    }

    @Nullable
    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    @Nullable
    public BlurHashConfig getBlurHashOptions() {
        return this.mBlurHashOptions;
    }

    public RotationOptions getRotationOptions() {
        return this.mRotationOptions;
    }

    @Deprecated
    public boolean getAutoRotateEnabled() {
        return this.mRotationOptions.useImageMetadata();
    }

    @Nullable
    public BytesRange getBytesRange() {
        return this.mBytesRange;
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }

    public boolean getProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }

    public boolean getProgressiveRenderingAnimatedEnabled() {
        return this.mProgressiveRenderingAnimatedEnabled;
    }

    public boolean getProgressiveRenderingHeicEnabled() {
        return this.mProgressiveRenderingHeicEnabled;
    }

    public boolean getLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public boolean getLoadThumbnailOnlyForAndroidSdkAboveQ() {
        return Build.VERSION.SDK_INT >= 29 && this.mLoadThumbnailOnly;
    }

    public Priority getPriority() {
        return this.mRequestPriority;
    }

    public RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    public String getCustomCacheName() {
        return this.mCustomCacheName;
    }

    public boolean isDiskCacheEnabled() {
        return this.mIsDiskCacheEnabled;
    }

    public boolean isResizedImageDiskCacheEnabled() {
        return this.mIsResizedImageDiskCacheEnabled;
    }

    public boolean isMemoryCacheEnabled() {
        return this.mIsMemoryCacheEnabled;
    }

    public boolean isResizedImageDiskCacheActuallyEnabled() {
        return isResizedImageDiskCacheEnabled() && getResizeOptions() != null;
    }

    public synchronized File getSourceFile() {
        if (this.mSourceFile == null) {
            this.mSourceFile = new File(this.mSourceUri.getPath());
        }
        return this.mSourceFile;
    }

    @Nullable
    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }

    @Nullable
    public RequestListener getRequestListener() {
        return this.mRequestListener;
    }

    public SizeDeterminer getSizeDeterminer() {
        return this.mSizeDeterminer;
    }

    public void setSizeDeterminer(SizeDeterminer sizeDeterminer) {
        this.mSizeDeterminer = sizeDeterminer;
    }

    public boolean isMultiplexerEnabled() {
        return this.mMultiplexerEnabled;
    }

    public void setMultiplexerEnabled(boolean z) {
        this.mMultiplexerEnabled = z;
    }

    public boolean isNeedReplaceUri() {
        return this.mNeedReplaceUri;
    }

    public void setNeedReplaceUri(boolean z) {
        this.mNeedReplaceUri = z;
    }

    public void setHasDecodedThumb(boolean z) {
        this.mHasDecodedThumb = z;
    }

    public boolean getHasDecodedThumb() {
        return this.mHasDecodedThumb;
    }

    public boolean isThumbDataInFetch() {
        return this.mThumbDataInFetch;
    }

    public void setThumbDataInFetch(boolean z) {
        this.mThumbDataInFetch = z;
    }

    public Map<String, String> getHttpHeader() {
        return this.mHttpHeader;
    }

    public int getAwebpScanNumber() {
        return this.mAwebpScanNumber;
    }

    public Map<String, String> getCustomParam() {
        return this.mCustomParam;
    }

    public String getPublicKey() {
        return this.mPublicKey;
    }

    public String getUrlKey() {
        return this.mUrlKey;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageRequest) {
            ImageRequest imageRequest = (ImageRequest) obj;
            if (Objects.equal(this.mSourceUri, imageRequest.mSourceUri) && Objects.equal(this.mCacheChoice, imageRequest.mCacheChoice) && Objects.equal(this.mSourceFile, imageRequest.mSourceFile) && Objects.equal(this.mBytesRange, imageRequest.mBytesRange) && Objects.equal(this.mImageDecodeOptions, imageRequest.mImageDecodeOptions) && Objects.equal(this.mResizeOptions, imageRequest.mResizeOptions) && Objects.equal(this.mRotationOptions, imageRequest.mRotationOptions) && this.mLoadThumbnailOnly == imageRequest.mLoadThumbnailOnly) {
                Postprocessor postprocessor = this.mPostprocessor;
                CacheKey postprocessorCacheKey = postprocessor != null ? postprocessor.getPostprocessorCacheKey() : null;
                Postprocessor postprocessor2 = imageRequest.mPostprocessor;
                return Objects.equal(postprocessorCacheKey, postprocessor2 != null ? postprocessor2.getPostprocessorCacheKey() : null);
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Postprocessor postprocessor = this.mPostprocessor;
        return Objects.hashCode(this.mCacheChoice, this.mSourceUri, this.mSourceFile, this.mBytesRange, this.mImageDecodeOptions, this.mResizeOptions, this.mRotationOptions, postprocessor != null ? postprocessor.getPostprocessorCacheKey() : null, Boolean.valueOf(this.mLoadThumbnailOnly));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("uri", this.mSourceUri).add("cacheChoice", this.mCacheChoice).add("decodeOptions", this.mImageDecodeOptions).add("postprocessor", this.mPostprocessor).add("priority", this.mRequestPriority).add("resizeOptions", this.mResizeOptions).add("rotationOptions", this.mRotationOptions).add("bytesRange", this.mBytesRange).toString();
    }

    /* loaded from: classes8.dex */
    public enum RequestLevel {
        FULL_FETCH(1),
        DISK_CACHE(2),
        ENCODED_MEMORY_CACHE(3),
        BITMAP_MEMORY_CACHE(4);
        
        private int mValue;

        RequestLevel(int i2) {
            this.mValue = i2;
        }

        public int getValue() {
            return this.mValue;
        }

        public static RequestLevel getMax(RequestLevel requestLevel, RequestLevel requestLevel2) {
            return requestLevel.getValue() > requestLevel2.getValue() ? requestLevel : requestLevel2;
        }
    }

    private static int getSourceUriType(Uri uri, String str) {
        if (uri == null) {
            return -1;
        }
        if (UriUtil.isNetworkUri(uri)) {
            return 0;
        }
        if (UriUtil.isLocalFileUri(uri)) {
            return MediaUtils.isVideo(MediaUtils.extractMime(uri.getPath())) ? 2 : 3;
        } else if (UriUtil.isLocalContentUri(uri)) {
            if (TextUtils.isEmpty(str)) {
                return 4;
            }
            if (MediaUtils.isVideo(str)) {
                return 9;
            }
            return MediaUtils.isPhoto(str) ? 10 : 4;
        } else if (UriUtil.isLocalAssetUri(uri)) {
            return 5;
        } else {
            if (UriUtil.isLocalResourceUri(uri)) {
                return 6;
            }
            if (UriUtil.isDataUri(uri)) {
                return 7;
            }
            return UriUtil.isQualifiedResourceUri(uri) ? 8 : -1;
        }
    }

    private static int getSourceUriType(Uri uri) {
        return getSourceUriType(uri, "");
    }
}
