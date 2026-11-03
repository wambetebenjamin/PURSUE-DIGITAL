package com.facebook.fresco.animation.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.drawable.DrawableProperties;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.frame.FixedSlidingHeifFrameScheduler;
import com.facebook.fresco.animation.frame.FrameScheduler;
import com.facebook.fresco.animation.frame.FrameSchedulerFactory;
import com.facebook.fresco.animation.frame.SmoothSlidingFrameScheduler;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class AnimatedDrawable2 extends Drawable implements Animatable, DrawableWithCaches {
    private static final int DEFAULT_FRAME_SCHEDULING_DELAY_MS = 8;
    private static final int DEFAULT_FRAME_SCHEDULING_OFFSET_MS = 0;
    private static FrameSchedulerFactory sFrameSchedulerFactory;
    @Nullable
    private AnimationBackend mAnimationBackend;
    private volatile AnimationListener mAnimationListener;
    @Nullable
    private Object mDecodeContext;
    private int mDecodeStatus;
    @Nullable
    private volatile DrawListener mDrawListener;
    @Nullable
    private DrawableProperties mDrawableProperties;
    private int mDroppedFrames;
    private long mExpectedRenderTimeMs;
    @Nullable
    private BitmapFrameCache mFrameCache;
    @Nullable
    private FrameScheduler mFrameScheduler;
    private long mFrameSchedulingDelayMs;
    private long mFrameSchedulingOffsetMs;
    private boolean mHasChangeLoopCount;
    private ImageFormat mImageFormat;
    private final Runnable mInvalidateRunnable;
    private volatile boolean mIsRunning;
    private int mLastDrawnFrameNumber;
    private long mLastFrameAnimationTimeMs;
    private int mLoopCount;
    private volatile boolean mPaused;
    private int mPausedLastDrawnFrameNumber;
    private long mPausedLastFrameAnimationTimeMsDifference;
    private long mPausedStartTimeMsDifference;
    private long mStartTimeMs;
    private static final Class<?> TAG = AnimatedDrawable2.class;
    private static final AnimationListener NO_OP_LISTENER = new BaseAnimationListener();

    /* loaded from: classes8.dex */
    public interface DrawListener {
        void onDraw(AnimatedDrawable2 animatedDrawable2, FrameScheduler frameScheduler, int i2, boolean z, boolean z2, long j2, long j3, long j4, long j5, long j6, long j7, long j8);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public static void setFrameSchedulerFactory(FrameSchedulerFactory frameSchedulerFactory) {
        sFrameSchedulerFactory = frameSchedulerFactory;
    }

    public void setPrivateFrameSchedulerFactory(FrameSchedulerFactory frameSchedulerFactory) {
        FrameScheduler newPrivateFrameScheduler;
        if (DefaultImageFormats.isHeifFormatAnimated(this.mImageFormat) || (newPrivateFrameScheduler = newPrivateFrameScheduler(this.mAnimationBackend, this.mDecodeContext, this.mDecodeStatus, this.mImageFormat, frameSchedulerFactory)) == null) {
            return;
        }
        this.mFrameScheduler = newPrivateFrameScheduler;
    }

    public void setPrivateFrameScheduler(FrameScheduler frameScheduler) {
        if (DefaultImageFormats.isHeifFormatAnimated(this.mImageFormat) || frameScheduler == null) {
            return;
        }
        this.mFrameScheduler = frameScheduler;
    }

    public AnimatedDrawable2() {
        this(null, null);
    }

    public AnimatedDrawable2(@Nullable AnimationBackend animationBackend, @Nullable Object obj) {
        this.mLoopCount = -1000;
        this.mHasChangeLoopCount = false;
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mFrameSchedulingDelayMs = 8L;
        this.mFrameSchedulingOffsetMs = 0L;
        this.mAnimationListener = NO_OP_LISTENER;
        this.mDrawListener = null;
        this.mInvalidateRunnable = new Runnable() { // from class: com.facebook.fresco.animation.drawable.AnimatedDrawable2.1
            @Override // java.lang.Runnable
            public void run() {
                AnimatedDrawable2 animatedDrawable2 = AnimatedDrawable2.this;
                animatedDrawable2.unscheduleSelf(animatedDrawable2.mInvalidateRunnable);
                AnimatedDrawable2.this.invalidateSelf();
            }
        };
        this.mAnimationBackend = animationBackend;
        this.mFrameScheduler = createSchedulerForBackendAndDelayMethod(animationBackend, obj, 0, this.mImageFormat);
        this.mDecodeContext = obj;
    }

    public AnimatedDrawable2(@Nullable AnimationBackend animationBackend, @Nullable Object obj, @Nullable BitmapFrameCache bitmapFrameCache, int i2, ImageFormat imageFormat) {
        this.mLoopCount = -1000;
        this.mHasChangeLoopCount = false;
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mFrameSchedulingDelayMs = 8L;
        this.mFrameSchedulingOffsetMs = 0L;
        this.mAnimationListener = NO_OP_LISTENER;
        this.mDrawListener = null;
        this.mInvalidateRunnable = new Runnable() { // from class: com.facebook.fresco.animation.drawable.AnimatedDrawable2.1
            @Override // java.lang.Runnable
            public void run() {
                AnimatedDrawable2 animatedDrawable2 = AnimatedDrawable2.this;
                animatedDrawable2.unscheduleSelf(animatedDrawable2.mInvalidateRunnable);
                AnimatedDrawable2.this.invalidateSelf();
            }
        };
        this.mAnimationBackend = animationBackend;
        this.mImageFormat = imageFormat;
        this.mFrameScheduler = createSchedulerForBackendAndDelayMethod(animationBackend, obj, i2, imageFormat);
        this.mFrameCache = bitmapFrameCache;
        this.mDecodeStatus = i2;
        this.mDecodeContext = obj;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend == null) {
            return super.getIntrinsicWidth();
        }
        return animationBackend.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend == null) {
            return super.getIntrinsicHeight();
        }
        return animationBackend.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        AnimationBackend animationBackend;
        if ((!this.mIsRunning || this.mPaused) && (animationBackend = this.mAnimationBackend) != null && animationBackend.getFrameCount() > 1) {
            this.mIsRunning = true;
            long now = now();
            this.mStartTimeMs = now;
            this.mExpectedRenderTimeMs = now;
            this.mLastFrameAnimationTimeMs = -1L;
            this.mLastDrawnFrameNumber = -1;
            if (this.mPaused) {
                long now2 = now();
                long j2 = now2 - this.mPausedStartTimeMsDifference;
                this.mStartTimeMs = j2;
                this.mExpectedRenderTimeMs = j2;
                this.mLastFrameAnimationTimeMs = now2 - this.mPausedLastFrameAnimationTimeMsDifference;
                this.mLastDrawnFrameNumber = this.mPausedLastDrawnFrameNumber;
                this.mPaused = false;
            }
            invalidateSelf();
            this.mAnimationListener.onAnimationStart(this);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            this.mStartTimeMs = 0L;
            this.mExpectedRenderTimeMs = 0L;
            this.mLastFrameAnimationTimeMs = -1L;
            this.mLastDrawnFrameNumber = -1;
            this.mPaused = false;
            FrameScheduler frameScheduler = this.mFrameScheduler;
            if (frameScheduler instanceof FixedSlidingHeifFrameScheduler) {
                ((FixedSlidingHeifFrameScheduler) frameScheduler).setAnimatedStop(true);
            }
            unscheduleSelf(this.mInvalidateRunnable);
            this.mAnimationListener.onAnimationStop(this);
        }
    }

    public void pause() {
        if (this.mPaused || !this.mIsRunning) {
            return;
        }
        this.mIsRunning = false;
        long now = now();
        this.mPausedStartTimeMsDifference = now - this.mStartTimeMs;
        this.mPausedLastFrameAnimationTimeMsDifference = now - this.mLastFrameAnimationTimeMs;
        this.mPausedLastDrawnFrameNumber = this.mLastDrawnFrameNumber;
        this.mPaused = true;
        unscheduleSelf(this.mInvalidateRunnable);
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mIsRunning;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend != null) {
            animationBackend.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        long j2;
        long j3;
        AnimatedDrawable2 animatedDrawable2;
        long j4;
        if (this.mAnimationBackend == null || this.mFrameScheduler == null) {
            return;
        }
        long now = now();
        long max = this.mIsRunning ? (now - this.mStartTimeMs) + this.mFrameSchedulingOffsetMs : Math.max(this.mLastFrameAnimationTimeMs, 0L);
        int frameNumberToRender = this.mFrameScheduler.getFrameNumberToRender(max, this.mLastFrameAnimationTimeMs);
        if (frameNumberToRender == -1) {
            frameNumberToRender = this.mAnimationBackend.getFrameCount() - 1;
            this.mAnimationListener.onAnimationStop(this);
            this.mIsRunning = false;
        } else if (frameNumberToRender == 0 && this.mLastDrawnFrameNumber != -1 && now >= this.mExpectedRenderTimeMs) {
            this.mAnimationListener.onAnimationRepeat(this);
        }
        int i2 = frameNumberToRender;
        boolean drawFrame = this.mAnimationBackend.drawFrame(this, canvas, i2);
        if (drawFrame) {
            this.mAnimationListener.onAnimationFrame(this, i2);
            this.mLastDrawnFrameNumber = i2;
        }
        if (!drawFrame) {
            onFrameDropped();
        }
        long now2 = now();
        if (this.mIsRunning) {
            this.mFrameScheduler.setStartTime(this.mStartTimeMs);
            long targetRenderTimeForNextFrameMs = this.mFrameScheduler.getTargetRenderTimeForNextFrameMs(now2 - this.mStartTimeMs);
            if (targetRenderTimeForNextFrameMs != -1) {
                long j5 = this.mFrameSchedulingDelayMs + targetRenderTimeForNextFrameMs;
                scheduleNextFrame(j5);
                j3 = j5;
            } else {
                j3 = -1;
            }
            j2 = targetRenderTimeForNextFrameMs;
        } else {
            j2 = -1;
            j3 = -1;
        }
        DrawListener drawListener = this.mDrawListener;
        if (drawListener != null) {
            drawListener.onDraw(this, this.mFrameScheduler, i2, drawFrame, this.mIsRunning, this.mStartTimeMs, max, this.mLastFrameAnimationTimeMs, now, now2, j2, j3);
            animatedDrawable2 = this;
            j4 = max;
        } else {
            animatedDrawable2 = this;
            j4 = max;
        }
        animatedDrawable2.mLastFrameAnimationTimeMs = j4;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.mDrawableProperties == null) {
            this.mDrawableProperties = new DrawableProperties();
        }
        this.mDrawableProperties.setAlpha(i2);
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend != null) {
            animationBackend.setAlpha(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mDrawableProperties == null) {
            this.mDrawableProperties = new DrawableProperties();
        }
        this.mDrawableProperties.setColorFilter(colorFilter);
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend != null) {
            animationBackend.setColorFilter(colorFilter);
        }
    }

    public void setAnimationBackend(@Nullable AnimationBackend animationBackend) {
        this.mAnimationBackend = animationBackend;
        if (animationBackend != null) {
            this.mFrameScheduler = new SmoothSlidingFrameScheduler(this.mAnimationBackend);
            this.mAnimationBackend.setBounds(getBounds());
            DrawableProperties drawableProperties = this.mDrawableProperties;
            if (drawableProperties != null) {
                drawableProperties.applyTo(this);
            }
        }
        this.mFrameScheduler = createSchedulerForBackendAndDelayMethod(this.mAnimationBackend, null, 0, this.mImageFormat);
        stop();
    }

    @Nullable
    public AnimationBackend getAnimationBackend() {
        return this.mAnimationBackend;
    }

    public long getDroppedFrames() {
        return this.mDroppedFrames;
    }

    public long getStartTimeMs() {
        return this.mStartTimeMs;
    }

    public boolean isInfiniteAnimation() {
        FrameScheduler frameScheduler = this.mFrameScheduler;
        return frameScheduler != null && frameScheduler.isInfiniteAnimation();
    }

    public void jumpToFrame(int i2) {
        FrameScheduler frameScheduler;
        if (this.mAnimationBackend == null || (frameScheduler = this.mFrameScheduler) == null) {
            return;
        }
        this.mLastFrameAnimationTimeMs = frameScheduler.getTargetRenderTimeMs(i2);
        long now = now() - this.mLastFrameAnimationTimeMs;
        this.mStartTimeMs = now;
        this.mExpectedRenderTimeMs = now;
        invalidateSelf();
    }

    public long getLoopDurationMs() {
        if (this.mAnimationBackend == null) {
            return 0L;
        }
        FrameScheduler frameScheduler = this.mFrameScheduler;
        if (frameScheduler != null) {
            return frameScheduler.getLoopDurationMs();
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAnimationBackend.getFrameCount(); i3++) {
            i2 += this.mAnimationBackend.getFrameDurationMs(i3);
        }
        return i2;
    }

    public int getFrameCount() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend == null) {
            return 0;
        }
        return animationBackend.getFrameCount();
    }

    public int getLoopCount() {
        if (this.mHasChangeLoopCount) {
            return this.mLoopCount;
        }
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend == null) {
            return 0;
        }
        return animationBackend.getLoopCount();
    }

    public void setLoopCount(int i2) {
        this.mLoopCount = i2;
        this.mHasChangeLoopCount = true;
    }

    public void setFrameSchedulingDelayMs(long j2) {
        this.mFrameSchedulingDelayMs = j2;
    }

    public void setFrameSchedulingOffsetMs(long j2) {
        this.mFrameSchedulingOffsetMs = j2;
    }

    public void setAnimationListener(@Nullable AnimationListener animationListener) {
        if (animationListener == null) {
            animationListener = NO_OP_LISTENER;
        }
        this.mAnimationListener = animationListener;
    }

    public void setDrawListener(@Nullable DrawListener drawListener) {
        this.mDrawListener = drawListener;
    }

    private void scheduleNextFrame(long j2) {
        long j3 = this.mStartTimeMs + j2;
        this.mExpectedRenderTimeMs = j3;
        scheduleSelf(this.mInvalidateRunnable, j3);
    }

    private void onFrameDropped() {
        this.mDroppedFrames++;
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "Dropped a frame. Count: %s", Integer.valueOf(this.mDroppedFrames));
        }
    }

    private long now() {
        return SystemClock.uptimeMillis();
    }

    @Nullable
    private static FrameScheduler createSchedulerForBackendAndDelayMethod(@Nullable AnimationBackend animationBackend, @Nullable Object obj, int i2, ImageFormat imageFormat) {
        FrameScheduler build;
        if (animationBackend == null) {
            return null;
        }
        if (DefaultImageFormats.isHeifFormatAnimated(imageFormat) && !ImagePipelineFactory.getInstance().isAnimatedHeifIndividualCacheEnabled() && !ImagePipelineFactory.getInstance().isAnimatedHeifIndividualCacheForImageRequestEnabled()) {
            return new FixedSlidingHeifFrameScheduler(animationBackend, i2);
        }
        FrameSchedulerFactory frameSchedulerFactory = sFrameSchedulerFactory;
        if (frameSchedulerFactory == null || (build = frameSchedulerFactory.build(animationBackend, obj, imageFormat, i2)) == null) {
            if (DefaultImageFormats.isHeifFormatAnimated(imageFormat) && (ImagePipelineFactory.getInstance().isAnimatedHeifIndividualCacheEnabled() || ImagePipelineFactory.getInstance().isAnimatedHeifIndividualCacheForImageRequestEnabled())) {
                return new FixedSlidingHeifFrameScheduler(animationBackend, i2);
            }
            return new SmoothSlidingFrameScheduler(animationBackend, i2);
        }
        return build;
    }

    private FrameScheduler newPrivateFrameScheduler(@Nullable AnimationBackend animationBackend, @Nullable Object obj, int i2, ImageFormat imageFormat, FrameSchedulerFactory frameSchedulerFactory) {
        if (animationBackend == null || frameSchedulerFactory == null) {
            return null;
        }
        return frameSchedulerFactory.build(animationBackend, obj, imageFormat, i2);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        if (this.mIsRunning) {
            return false;
        }
        long j2 = i2;
        if (this.mLastFrameAnimationTimeMs != j2) {
            this.mLastFrameAnimationTimeMs = j2;
            invalidateSelf();
            return true;
        }
        return false;
    }

    @Override // com.facebook.drawable.base.DrawableWithCaches
    public void dropCaches() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend != null) {
            animationBackend.clear();
        }
    }

    @Nullable
    public BitmapFrameCache getBitmapFrameCache() {
        return this.mFrameCache;
    }

    public void updateDrawable(AnimationBackend animationBackend, Object obj, int i2) {
        this.mAnimationBackend = animationBackend;
        if (this.mFrameScheduler != null) {
            FrameScheduler createSchedulerForBackendAndDelayMethod = createSchedulerForBackendAndDelayMethod(animationBackend, obj, i2, this.mImageFormat);
            FrameScheduler forNewFrameScheduler = this.mFrameScheduler.forNewFrameScheduler(createSchedulerForBackendAndDelayMethod);
            if (forNewFrameScheduler != null) {
                createSchedulerForBackendAndDelayMethod = forNewFrameScheduler;
            }
            this.mFrameScheduler = createSchedulerForBackendAndDelayMethod;
        }
    }
}
