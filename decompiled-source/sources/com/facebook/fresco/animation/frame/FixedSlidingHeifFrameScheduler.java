package com.facebook.fresco.animation.frame;

import com.facebook.fresco.animation.backend.AnimationBackend;
/* loaded from: classes8.dex */
public class FixedSlidingHeifFrameScheduler implements FrameScheduler {
    private static final String TAG = "FixedFrameScheduler";
    private static final int UNSET = -1;
    private boolean isAnimatedStop;
    private final AnimationBackend mAnimationBackend;
    private int mDecodeStatus;
    private int mFrameNumber;
    private long mLastDrawTimeMs;
    private int mLoopCount;
    private long mLoopDurationMs;
    private long mNowAnimationTimeMs;

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public void setStartTime(long j2) {
    }

    public FixedSlidingHeifFrameScheduler(AnimationBackend animationBackend) {
        this(animationBackend, 0);
    }

    public FixedSlidingHeifFrameScheduler(AnimationBackend animationBackend, int i2) {
        this.mLoopDurationMs = -1L;
        this.mLoopCount = 0;
        this.mFrameNumber = -1;
        this.mNowAnimationTimeMs = -1L;
        this.mLastDrawTimeMs = -1L;
        this.mAnimationBackend = animationBackend;
        this.mDecodeStatus = i2;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public int getFrameNumberToRender(long j2, long j3) {
        if (getLoopDurationMs() == 0) {
            return -1;
        }
        if (isInfiniteAnimation() || this.mLoopCount < this.mAnimationBackend.getLoopCount()) {
            int i2 = this.mFrameNumber;
            int i3 = 0;
            if (i2 == -1 || j3 != this.mLastDrawTimeMs) {
                this.mNowAnimationTimeMs = j2;
                this.mLastDrawTimeMs = j2;
                if (i2 == -1 || this.isAnimatedStop) {
                    this.mFrameNumber = 0;
                    this.isAnimatedStop = false;
                }
                this.mLoopCount = 0;
                return this.mFrameNumber;
            }
            this.mLastDrawTimeMs = j2;
            if (this.mNowAnimationTimeMs + this.mAnimationBackend.getFrameDurationMs(i2) > j2) {
                return this.mFrameNumber;
            }
            this.mNowAnimationTimeMs = j2;
            int i4 = this.mFrameNumber + 1;
            if (i4 >= this.mAnimationBackend.getFrameCount()) {
                int i5 = this.mDecodeStatus;
                if (i5 == 0 || i5 == 3) {
                    this.mLoopCount++;
                } else {
                    i3 = i4 - 1;
                }
            } else {
                i3 = i4;
            }
            this.mFrameNumber = i3;
            return i3;
        }
        return -1;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getLoopDurationMs() {
        long j2 = this.mLoopDurationMs;
        if (j2 != -1) {
            return j2;
        }
        this.mLoopDurationMs = 0L;
        int frameCount = this.mAnimationBackend.getFrameCount();
        for (int i2 = 0; i2 < frameCount; i2++) {
            this.mLoopDurationMs += this.mAnimationBackend.getFrameDurationMs(i2);
        }
        return this.mLoopDurationMs;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getTargetRenderTimeMs(int i2) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 += this.mAnimationBackend.getFrameDurationMs(i2);
        }
        return j2;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getTargetRenderTimeForNextFrameMs(long j2) {
        if (getLoopDurationMs() == 0) {
            return -1L;
        }
        if (isInfiniteAnimation() || this.mLoopCount < this.mAnimationBackend.getLoopCount()) {
            long frameDurationMs = this.mAnimationBackend.getFrameDurationMs(this.mFrameNumber);
            long j3 = this.mNowAnimationTimeMs + frameDurationMs;
            return j3 >= j2 ? j3 : j2 + frameDurationMs;
        }
        return -1L;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public boolean isInfiniteAnimation() {
        return this.mAnimationBackend.getLoopCount() == 0;
    }

    int getFrameNumberWithinLoop(long j2) {
        int i2 = 0;
        long j3 = 0;
        do {
            j3 += this.mAnimationBackend.getFrameDurationMs(i2);
            i2++;
        } while (j2 >= j3);
        return i2 - 1;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public FrameScheduler forNewFrameScheduler(FrameScheduler frameScheduler) {
        if (frameScheduler instanceof FixedSlidingHeifFrameScheduler) {
            FixedSlidingHeifFrameScheduler fixedSlidingHeifFrameScheduler = (FixedSlidingHeifFrameScheduler) frameScheduler;
            FixedSlidingHeifFrameScheduler fixedSlidingHeifFrameScheduler2 = new FixedSlidingHeifFrameScheduler(fixedSlidingHeifFrameScheduler.mAnimationBackend, fixedSlidingHeifFrameScheduler.mDecodeStatus);
            fixedSlidingHeifFrameScheduler2.mFrameNumber = this.mFrameNumber;
            fixedSlidingHeifFrameScheduler2.mLastDrawTimeMs = this.mLastDrawTimeMs;
            fixedSlidingHeifFrameScheduler2.mLoopCount = this.mLoopCount;
            fixedSlidingHeifFrameScheduler2.mNowAnimationTimeMs = this.mNowAnimationTimeMs;
            return fixedSlidingHeifFrameScheduler2;
        }
        return null;
    }

    public void setAnimatedStop(boolean z) {
        this.isAnimatedStop = z;
    }
}
