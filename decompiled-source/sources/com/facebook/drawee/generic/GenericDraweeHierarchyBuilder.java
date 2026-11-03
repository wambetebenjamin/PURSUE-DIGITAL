package com.facebook.drawee.generic;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.ScalingUtils;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class GenericDraweeHierarchyBuilder {
    public static final int DEFAULT_FADE_DURATION = 300;
    private ColorFilter mActualImageColorFilter;
    private PointF mActualImageFocusPoint;
    private Matrix mActualImageMatrix;
    private ScalingUtils.ScaleType mActualImageScaleType;
    private Drawable mBackground;
    private Context mContext;
    private float mDesiredAspectRatio;
    private int mFadeDuration;
    private Drawable mFailureImage;
    private ScalingUtils.ScaleType mFailureImageScaleType;
    private List<Drawable> mOverlays;
    private Drawable mPlaceholderImage;
    @Nullable
    private ScalingUtils.ScaleType mPlaceholderImageScaleType;
    private Drawable mPressedStateOverlay;
    private Drawable mProgressBarImage;
    private ScalingUtils.ScaleType mProgressBarImageScaleType;
    private Resources mResources;
    private Drawable mRetryImage;
    private ScalingUtils.ScaleType mRetryImageScaleType;
    private RoundingParams mRoundingParams;
    public static final ScalingUtils.ScaleType DEFAULT_SCALE_TYPE = ScalingUtils.ScaleType.CENTER_INSIDE;
    public static final ScalingUtils.ScaleType DEFAULT_ACTUAL_IMAGE_SCALE_TYPE = ScalingUtils.ScaleType.CENTER_CROP;
    private static int mGlobalFadeDuration = -1;

    public GenericDraweeHierarchyBuilder(Context context, Resources resources) {
        this.mContext = context;
        this.mResources = resources;
        init();
    }

    public GenericDraweeHierarchyBuilder(Resources resources) {
        this.mResources = resources;
        init();
    }

    public static GenericDraweeHierarchyBuilder newInstance(Resources resources) {
        return new GenericDraweeHierarchyBuilder(resources);
    }

    public static void setDefaultFadeDuration(int i2) {
        mGlobalFadeDuration = i2;
    }

    private void init() {
        int i2 = mGlobalFadeDuration;
        if (i2 < 0) {
            i2 = 300;
        }
        this.mFadeDuration = i2;
        this.mDesiredAspectRatio = 0.0f;
        this.mPlaceholderImage = null;
        ScalingUtils.ScaleType scaleType = DEFAULT_SCALE_TYPE;
        this.mPlaceholderImageScaleType = scaleType;
        this.mRetryImage = null;
        this.mRetryImageScaleType = scaleType;
        this.mFailureImage = null;
        this.mFailureImageScaleType = scaleType;
        this.mProgressBarImage = null;
        this.mProgressBarImageScaleType = scaleType;
        this.mActualImageScaleType = DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
        this.mActualImageMatrix = null;
        this.mActualImageFocusPoint = null;
        this.mActualImageColorFilter = null;
        this.mBackground = null;
        this.mOverlays = null;
        this.mPressedStateOverlay = null;
        this.mRoundingParams = null;
    }

    public GenericDraweeHierarchyBuilder reset() {
        init();
        return this;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Resources getResources() {
        return this.mResources;
    }

    public GenericDraweeHierarchyBuilder setFadeDuration(int i2) {
        this.mFadeDuration = i2;
        return this;
    }

    public int getFadeDuration() {
        return this.mFadeDuration;
    }

    public GenericDraweeHierarchyBuilder setDesiredAspectRatio(float f2) {
        this.mDesiredAspectRatio = f2;
        return this;
    }

    public float getDesiredAspectRatio() {
        return this.mDesiredAspectRatio;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(@Nullable Drawable drawable) {
        this.mPlaceholderImage = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(int i2) {
        this.mPlaceholderImage = this.mResources.getDrawable(i2);
        return this;
    }

    @Nullable
    public Drawable getPlaceholderImage() {
        return this.mPlaceholderImage;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mPlaceholderImageScaleType = scaleType;
        return this;
    }

    @Nullable
    public ScalingUtils.ScaleType getPlaceholderImageScaleType() {
        return this.mPlaceholderImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mPlaceholderImage = drawable;
        this.mPlaceholderImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(int i2, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mPlaceholderImage = this.mResources.getDrawable(i2);
        this.mPlaceholderImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(@Nullable Drawable drawable) {
        this.mRetryImage = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(int i2) {
        this.mRetryImage = this.mResources.getDrawable(i2);
        return this;
    }

    @Nullable
    public Drawable getRetryImage() {
        return this.mRetryImage;
    }

    public GenericDraweeHierarchyBuilder setRetryImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mRetryImageScaleType = scaleType;
        return this;
    }

    @Nullable
    public ScalingUtils.ScaleType getRetryImageScaleType() {
        return this.mRetryImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mRetryImage = drawable;
        this.mRetryImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(int i2, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mRetryImage = this.mResources.getDrawable(i2);
        this.mRetryImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(@Nullable Drawable drawable) {
        this.mFailureImage = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(int i2) {
        this.mFailureImage = this.mResources.getDrawable(i2);
        return this;
    }

    @Nullable
    public Drawable getFailureImage() {
        return this.mFailureImage;
    }

    public GenericDraweeHierarchyBuilder setFailureImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mFailureImageScaleType = scaleType;
        return this;
    }

    @Nullable
    public ScalingUtils.ScaleType getFailureImageScaleType() {
        return this.mFailureImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mFailureImage = drawable;
        this.mFailureImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(int i2, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mFailureImage = this.mResources.getDrawable(i2);
        this.mFailureImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(@Nullable Drawable drawable) {
        this.mProgressBarImage = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(int i2) {
        this.mProgressBarImage = this.mResources.getDrawable(i2);
        return this;
    }

    @Nullable
    public Drawable getProgressBarImage() {
        return this.mProgressBarImage;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mProgressBarImageScaleType = scaleType;
        return this;
    }

    @Nullable
    public ScalingUtils.ScaleType getProgressBarImageScaleType() {
        return this.mProgressBarImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mProgressBarImage = drawable;
        this.mProgressBarImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(int i2, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mProgressBarImage = this.mResources.getDrawable(i2);
        this.mProgressBarImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setActualImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mActualImageScaleType = scaleType;
        this.mActualImageMatrix = null;
        return this;
    }

    @Nullable
    public ScalingUtils.ScaleType getActualImageScaleType() {
        return this.mActualImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setActualImageFocusPoint(@Nullable PointF pointF) {
        this.mActualImageFocusPoint = pointF;
        return this;
    }

    @Nullable
    public PointF getActualImageFocusPoint() {
        return this.mActualImageFocusPoint;
    }

    public GenericDraweeHierarchyBuilder setActualImageColorFilter(@Nullable ColorFilter colorFilter) {
        this.mActualImageColorFilter = colorFilter;
        return this;
    }

    @Nullable
    public ColorFilter getActualImageColorFilter() {
        return this.mActualImageColorFilter;
    }

    public GenericDraweeHierarchyBuilder setBackground(@Nullable Drawable drawable) {
        this.mBackground = drawable;
        return this;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    public GenericDraweeHierarchyBuilder setOverlays(@Nullable List<Drawable> list) {
        this.mOverlays = list;
        return this;
    }

    public GenericDraweeHierarchyBuilder setOverlay(@Nullable Drawable drawable) {
        if (drawable == null) {
            this.mOverlays = null;
        } else {
            this.mOverlays = Arrays.asList(drawable);
        }
        return this;
    }

    @Nullable
    public List<Drawable> getOverlays() {
        return this.mOverlays;
    }

    public GenericDraweeHierarchyBuilder setPressedStateOverlay(@Nullable Drawable drawable) {
        if (drawable == null) {
            this.mPressedStateOverlay = null;
        } else {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, drawable);
            this.mPressedStateOverlay = stateListDrawable;
        }
        return this;
    }

    @Nullable
    public Drawable getPressedStateOverlay() {
        return this.mPressedStateOverlay;
    }

    public GenericDraweeHierarchyBuilder setRoundingParams(@Nullable RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        return this;
    }

    @Nullable
    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    private void validate() {
        List<Drawable> list = this.mOverlays;
        if (list != null) {
            for (Drawable drawable : list) {
                Preconditions.checkNotNull(drawable);
            }
        }
    }

    public GenericDraweeHierarchy build() {
        validate();
        return new GenericDraweeHierarchy(this);
    }
}
