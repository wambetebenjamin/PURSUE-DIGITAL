package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class ImagePerfState {
    @Nullable
    private Object mCallerContext;
    @Nullable
    private String mControllerId;
    @Nullable
    private ImageInfo mImageInfo;
    @Nullable
    private ImageRequest mImageRequest;
    private boolean mIsPrefetch;
    @Nullable
    private String mRequestId;
    private long mControllerSubmitTimeMs = -1;
    private long mControllerIntermediateImageSetTimeMs = -1;
    private long mControllerFinalImageSetTimeMs = -1;
    private long mControllerFailureTimeMs = -1;
    private long mControllerCancelTimeMs = -1;
    private long mImageRequestStartTimeMs = -1;
    private long mImageRequestEndTimeMs = -1;
    private int mImageOrigin = -1;
    private int mOnScreenWidthPx = -1;
    private int mOnScreenHeightPx = -1;
    private int mImageLoadStatus = -1;
    private int mVisibilityState = -1;
    private long mVisibilityEventTimeMs = -1;
    private long mInvisibilityEventTimeMs = -1;

    public void reset() {
        this.mRequestId = null;
        this.mImageRequest = null;
        this.mCallerContext = null;
        this.mImageInfo = null;
        this.mControllerSubmitTimeMs = -1L;
        this.mControllerFinalImageSetTimeMs = -1L;
        this.mControllerFailureTimeMs = -1L;
        this.mControllerCancelTimeMs = -1L;
        this.mImageRequestStartTimeMs = -1L;
        this.mImageRequestEndTimeMs = -1L;
        this.mImageOrigin = 1;
        this.mIsPrefetch = false;
        this.mOnScreenWidthPx = -1;
        this.mOnScreenHeightPx = -1;
        this.mImageLoadStatus = -1;
        this.mVisibilityState = -1;
        this.mVisibilityEventTimeMs = -1L;
        this.mInvisibilityEventTimeMs = -1L;
    }

    public void setImageLoadStatus(int i2) {
        this.mImageLoadStatus = i2;
    }

    public int getImageLoadStatus() {
        return this.mImageLoadStatus;
    }

    public void setControllerId(@Nullable String str) {
        this.mControllerId = str;
    }

    public void setRequestId(@Nullable String str) {
        this.mRequestId = str;
    }

    public void setImageRequest(@Nullable ImageRequest imageRequest) {
        this.mImageRequest = imageRequest;
    }

    public void setCallerContext(@Nullable Object obj) {
        this.mCallerContext = obj;
    }

    public void setControllerSubmitTimeMs(long j2) {
        this.mControllerSubmitTimeMs = j2;
    }

    public void setControllerIntermediateImageSetTimeMs(long j2) {
        this.mControllerIntermediateImageSetTimeMs = j2;
    }

    public void setControllerFinalImageSetTimeMs(long j2) {
        this.mControllerFinalImageSetTimeMs = j2;
    }

    public void setControllerFailureTimeMs(long j2) {
        this.mControllerFailureTimeMs = j2;
    }

    public void setControllerCancelTimeMs(long j2) {
        this.mControllerCancelTimeMs = j2;
    }

    public void setImageRequestStartTimeMs(long j2) {
        this.mImageRequestStartTimeMs = j2;
    }

    public void setImageRequestEndTimeMs(long j2) {
        this.mImageRequestEndTimeMs = j2;
    }

    public void setVisibilityEventTimeMs(long j2) {
        this.mVisibilityEventTimeMs = j2;
    }

    public void setInvisibilityEventTimeMs(long j2) {
        this.mInvisibilityEventTimeMs = j2;
    }

    public void setImageOrigin(int i2) {
        this.mImageOrigin = i2;
    }

    public void setPrefetch(boolean z) {
        this.mIsPrefetch = z;
    }

    public void setImageInfo(@Nullable ImageInfo imageInfo) {
        this.mImageInfo = imageInfo;
    }

    public void setOnScreenWidth(int i2) {
        this.mOnScreenWidthPx = i2;
    }

    public void setOnScreenHeight(int i2) {
        this.mOnScreenHeightPx = i2;
    }

    public void setVisible(boolean z) {
        this.mVisibilityState = z ? 1 : 2;
    }

    public ImagePerfData snapshot() {
        return new ImagePerfData(this.mControllerId, this.mRequestId, this.mImageRequest, this.mCallerContext, this.mImageInfo, this.mControllerSubmitTimeMs, this.mControllerIntermediateImageSetTimeMs, this.mControllerFinalImageSetTimeMs, this.mControllerFailureTimeMs, this.mControllerCancelTimeMs, this.mImageRequestStartTimeMs, this.mImageRequestEndTimeMs, this.mImageOrigin, this.mIsPrefetch, this.mOnScreenWidthPx, this.mOnScreenHeightPx, this.mVisibilityState, this.mVisibilityEventTimeMs, this.mInvisibilityEventTimeMs);
    }
}
