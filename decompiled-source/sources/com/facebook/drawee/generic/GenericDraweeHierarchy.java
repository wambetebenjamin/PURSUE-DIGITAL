package com.facebook.drawee.generic;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.FadeDrawable;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class GenericDraweeHierarchy implements SettableDraweeHierarchy {
    private static final int ACTUAL_IMAGE_INDEX = 2;
    private static final int BACKGROUND_IMAGE_INDEX = 0;
    private static final int FAILURE_IMAGE_INDEX = 5;
    private static final int OVERLAY_IMAGES_INDEX = 6;
    private static final int PLACEHOLDER_IMAGE_INDEX = 1;
    private static final int PROGRESS_BAR_IMAGE_INDEX = 3;
    private static final int RETRY_IMAGE_INDEX = 4;
    private final ForwardingDrawable mActualImageWrapper;
    private Context mContext;
    private final Drawable mEmptyActualImageDrawable;
    private final FadeDrawable mFadeDrawable;
    private final Resources mResources;
    @Nullable
    private RoundingParams mRoundingParams;
    private final RootDrawable mTopLevelDrawable;

    public GenericDraweeHierarchy(GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder) {
        int i2 = 0;
        ColorDrawable colorDrawable = new ColorDrawable(0);
        this.mEmptyActualImageDrawable = colorDrawable;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeHierarchy()");
        }
        this.mResources = genericDraweeHierarchyBuilder.getResources();
        this.mContext = genericDraweeHierarchyBuilder.getContext();
        this.mRoundingParams = genericDraweeHierarchyBuilder.getRoundingParams();
        ForwardingDrawable forwardingDrawable = new ForwardingDrawable(colorDrawable);
        this.mActualImageWrapper = forwardingDrawable;
        int i3 = 1;
        int size = (genericDraweeHierarchyBuilder.getOverlays() != null ? genericDraweeHierarchyBuilder.getOverlays().size() : 1) + (genericDraweeHierarchyBuilder.getPressedStateOverlay() != null ? 1 : 0);
        Drawable[] drawableArr = new Drawable[size + 6];
        drawableArr[0] = buildBranch(genericDraweeHierarchyBuilder.getBackground(), null);
        drawableArr[1] = buildBranch(genericDraweeHierarchyBuilder.getPlaceholderImage(), genericDraweeHierarchyBuilder.getPlaceholderImageScaleType());
        drawableArr[2] = buildActualImageBranch(forwardingDrawable, genericDraweeHierarchyBuilder.getActualImageScaleType(), genericDraweeHierarchyBuilder.getActualImageFocusPoint(), genericDraweeHierarchyBuilder.getActualImageColorFilter());
        drawableArr[3] = buildBranch(genericDraweeHierarchyBuilder.getProgressBarImage(), genericDraweeHierarchyBuilder.getProgressBarImageScaleType());
        drawableArr[4] = buildBranch(genericDraweeHierarchyBuilder.getRetryImage(), genericDraweeHierarchyBuilder.getRetryImageScaleType());
        drawableArr[5] = buildBranch(genericDraweeHierarchyBuilder.getFailureImage(), genericDraweeHierarchyBuilder.getFailureImageScaleType());
        if (size > 0) {
            if (genericDraweeHierarchyBuilder.getOverlays() != null) {
                for (Drawable drawable : genericDraweeHierarchyBuilder.getOverlays()) {
                    drawableArr[i2 + 6] = buildBranch(drawable, null);
                    i2++;
                }
                i3 = i2;
            }
            if (genericDraweeHierarchyBuilder.getPressedStateOverlay() != null) {
                drawableArr[i3 + 6] = buildBranch(genericDraweeHierarchyBuilder.getPressedStateOverlay(), null);
            }
        }
        FadeDrawable fadeDrawable = new FadeDrawable(drawableArr);
        this.mFadeDrawable = fadeDrawable;
        fadeDrawable.setTransitionDuration(genericDraweeHierarchyBuilder.getFadeDuration());
        RootDrawable rootDrawable = new RootDrawable(WrappingUtils.maybeWrapWithRoundedOverlayColor(fadeDrawable, this.mRoundingParams));
        this.mTopLevelDrawable = rootDrawable;
        rootDrawable.mutate();
        resetFade();
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    @Nullable
    private Drawable buildActualImageBranch(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType, @Nullable PointF pointF, @Nullable ColorFilter colorFilter) {
        drawable.setColorFilter(colorFilter);
        return WrappingUtils.maybeWrapWithScaleType(drawable, scaleType, pointF);
    }

    @Nullable
    private Drawable buildBranch(@Nullable Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        return WrappingUtils.maybeWrapWithScaleType(WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources), scaleType);
    }

    private void resetActualImages() {
        this.mActualImageWrapper.setDrawable(this.mEmptyActualImageDrawable);
    }

    private void resetFade() {
        FadeDrawable fadeDrawable = this.mFadeDrawable;
        if (fadeDrawable != null) {
            fadeDrawable.beginBatchMode();
            this.mFadeDrawable.fadeInAllLayers();
            fadeOutBranches();
            fadeInLayer(1);
            this.mFadeDrawable.finishTransitionImmediately();
            this.mFadeDrawable.endBatchMode();
        }
    }

    private void fadeOutBranches() {
        fadeOutLayer(1);
        fadeOutLayer(2);
        fadeOutLayer(3);
        fadeOutLayer(4);
        fadeOutLayer(5);
    }

    private void fadeInLayer(int i2) {
        if (i2 >= 0) {
            this.mFadeDrawable.fadeInLayer(i2);
        }
    }

    private void fadeOutLayer(int i2) {
        if (i2 >= 0) {
            this.mFadeDrawable.fadeOutLayer(i2);
        }
    }

    private void setProgress(float f2) {
        Drawable drawable = this.mFadeDrawable.getDrawable(3);
        if (drawable == null) {
            return;
        }
        if (f2 >= 0.999f) {
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).stop();
            }
            fadeOutLayer(3);
        } else {
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
            fadeInLayer(3);
        }
        drawable.setLevel(Math.round(f2 * 10000.0f));
    }

    @Override // com.facebook.drawee.interfaces.DraweeHierarchy
    public Drawable getTopLevelDrawable() {
        return this.mTopLevelDrawable;
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void reset() {
        resetActualImages();
        resetFade();
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setImage(Drawable drawable, float f2, boolean z) {
        Drawable maybeApplyLeafRounding = WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources);
        maybeApplyLeafRounding.mutate();
        this.mActualImageWrapper.setDrawable(maybeApplyLeafRounding);
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        fadeInLayer(2);
        setProgress(f2);
        if (z) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setProgress(float f2, boolean z) {
        if (this.mFadeDrawable.getDrawable(3) == null) {
            return;
        }
        this.mFadeDrawable.beginBatchMode();
        setProgress(f2);
        if (z) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setFailure(Throwable th) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(5) != null) {
            fadeInLayer(5);
        } else {
            fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setRetry(Throwable th) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(4) != null) {
            fadeInLayer(4);
        } else {
            fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setControllerOverlay(@Nullable Drawable drawable) {
        this.mTopLevelDrawable.setControllerOverlay(drawable);
    }

    private DrawableParent getParentDrawableAtIndex(int i2) {
        DrawableParent drawableParentForIndex = this.mFadeDrawable.getDrawableParentForIndex(i2);
        if (drawableParentForIndex.getDrawable() instanceof MatrixDrawable) {
            drawableParentForIndex = (MatrixDrawable) drawableParentForIndex.getDrawable();
        }
        return drawableParentForIndex.getDrawable() instanceof ScaleTypeDrawable ? (ScaleTypeDrawable) drawableParentForIndex.getDrawable() : drawableParentForIndex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChildDrawableAtIndex(int i2, @Nullable Drawable drawable) {
        if (drawable == null) {
            this.mFadeDrawable.setDrawable(i2, null);
            return;
        }
        getParentDrawableAtIndex(i2).setDrawable(WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ScaleTypeDrawable getScaleTypeDrawableAtIndex(int i2) {
        DrawableParent parentDrawableAtIndex = getParentDrawableAtIndex(i2);
        if (parentDrawableAtIndex instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable) parentDrawableAtIndex;
        }
        return WrappingUtils.wrapChildWithScaleType(parentDrawableAtIndex, ScalingUtils.ScaleType.FIT_XY);
    }

    private boolean hasScaleTypeDrawableAtIndex(int i2) {
        return getParentDrawableAtIndex(i2) instanceof ScaleTypeDrawable;
    }

    public void setFadeDuration(int i2) {
        this.mFadeDrawable.setTransitionDuration(i2);
    }

    public int getFadeDuration() {
        return this.mFadeDrawable.getTransitionDuration();
    }

    public void setActualImageFocusPoint(PointF pointF) {
        Preconditions.checkNotNull(pointF);
        getScaleTypeDrawableAtIndex(2).setFocusPoint(pointF);
    }

    public void setActualImageScaleType(ScalingUtils.ScaleType scaleType) {
        Preconditions.checkNotNull(scaleType);
        getScaleTypeDrawableAtIndex(2).setScaleType(scaleType);
    }

    @Nullable
    public ScalingUtils.ScaleType getActualImageScaleType() {
        if (hasScaleTypeDrawableAtIndex(2)) {
            return getScaleTypeDrawableAtIndex(2).getScaleType();
        }
        return null;
    }

    public void setActualImageColorFilter(ColorFilter colorFilter) {
        this.mActualImageWrapper.setColorFilter(colorFilter);
    }

    public void getActualImageBounds(RectF rectF) {
        this.mActualImageWrapper.getTransformedBounds(rectF);
    }

    public void setPlaceholderImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(1, drawable);
    }

    public void setPlaceholderImage(final Drawable drawable, final ScalingUtils.ScaleType scaleType) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            Context context = this.mContext;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: com.facebook.drawee.generic.GenericDraweeHierarchy.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GenericDraweeHierarchy.this.setChildDrawableAtIndex(1, drawable);
                        GenericDraweeHierarchy.this.getScaleTypeDrawableAtIndex(1).setScaleType(scaleType);
                    }
                });
                return;
            }
            return;
        }
        setChildDrawableAtIndex(1, drawable);
        getScaleTypeDrawableAtIndex(1).setScaleType(scaleType);
    }

    public boolean hasPlaceholderImage() {
        return this.mFadeDrawable.getDrawable(1) != null;
    }

    public void setPlaceholderImageFocusPoint(PointF pointF) {
        Preconditions.checkNotNull(pointF);
        getScaleTypeDrawableAtIndex(1).setFocusPoint(pointF);
    }

    public void setPlaceholderImage(int i2) {
        setPlaceholderImage(this.mResources.getDrawable(i2));
    }

    public void setPlaceholderImage(int i2, ScalingUtils.ScaleType scaleType) {
        setPlaceholderImage(this.mResources.getDrawable(i2), scaleType);
    }

    public void setFailureImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(5, drawable);
    }

    public void setFailureImage(Drawable drawable, ScalingUtils.ScaleType scaleType) {
        setChildDrawableAtIndex(5, drawable);
        getScaleTypeDrawableAtIndex(5).setScaleType(scaleType);
    }

    public void setFailureImage(int i2) {
        setFailureImage(this.mResources.getDrawable(i2));
    }

    public void setFailureImage(int i2, ScalingUtils.ScaleType scaleType) {
        setFailureImage(this.mResources.getDrawable(i2), scaleType);
    }

    public void setRetryImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(4, drawable);
    }

    public void setRetryImage(Drawable drawable, ScalingUtils.ScaleType scaleType) {
        setChildDrawableAtIndex(4, drawable);
        getScaleTypeDrawableAtIndex(4).setScaleType(scaleType);
    }

    public void setRetryImage(int i2) {
        setRetryImage(this.mResources.getDrawable(i2));
    }

    public void setRetryImage(int i2, ScalingUtils.ScaleType scaleType) {
        setRetryImage(this.mResources.getDrawable(i2), scaleType);
    }

    public void setProgressBarImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(3, drawable);
    }

    public void setProgressBarImage(Drawable drawable, ScalingUtils.ScaleType scaleType) {
        setChildDrawableAtIndex(3, drawable);
        getScaleTypeDrawableAtIndex(3).setScaleType(scaleType);
    }

    public void setProgressBarImage(int i2) {
        setProgressBarImage(this.mResources.getDrawable(i2));
    }

    public void setProgressBarImage(int i2, ScalingUtils.ScaleType scaleType) {
        setProgressBarImage(this.mResources.getDrawable(i2), scaleType);
    }

    public void setBackgroundImage(@Nullable Drawable drawable) {
        setChildDrawableAtIndex(0, drawable);
    }

    public void setOverlayImage(int i2, @Nullable Drawable drawable) {
        Preconditions.checkArgument(i2 >= 0 && i2 + 6 < this.mFadeDrawable.getNumberOfLayers(), "The given index does not correspond to an overlay image.");
        setChildDrawableAtIndex(i2 + 6, drawable);
    }

    public void setOverlayImage(@Nullable Drawable drawable) {
        setOverlayImage(0, drawable);
    }

    public void setRoundingParams(@Nullable RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        WrappingUtils.updateOverlayColorRounding(this.mTopLevelDrawable, roundingParams);
        for (int i2 = 0; i2 < this.mFadeDrawable.getNumberOfLayers(); i2++) {
            WrappingUtils.updateLeafRounding(getParentDrawableAtIndex(i2), this.mRoundingParams, this.mResources);
        }
    }

    @Nullable
    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    public boolean hasImage() {
        return this.mActualImageWrapper.getDrawable() != this.mEmptyActualImageDrawable;
    }
}
