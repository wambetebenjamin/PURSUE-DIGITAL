package com.facebook.fresco.animation.frame;

import com.facebook.fresco.animation.backend.AnimationBackend;
/* loaded from: classes8.dex */
public class SmoothSlidingFrameScheduler implements FrameScheduler {
    private static final String TAG = "FrameScheduler";
    private static final int UNSET = -1;
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

    public SmoothSlidingFrameScheduler(AnimationBackend animationBackend) {
        this(animationBackend, 0);
    }

    public SmoothSlidingFrameScheduler(AnimationBackend animationBackend, int i2) {
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
        int i2;
        long loopDurationMs = getLoopDurationMs();
        if (loopDurationMs == 0) {
            return -1;
        }
        if (isInfiniteAnimation() || j2 / loopDurationMs < this.mAnimationBackend.getLoopCount()) {
            int frameNumberWithinLoop = getFrameNumberWithinLoop(j2 % loopDurationMs);
            int i3 = 0;
            if (this.mFrameNumber == -1 || j3 != this.mLastDrawTimeMs) {
                this.mNowAnimationTimeMs = j2;
                this.mLastDrawTimeMs = j2;
                this.mFrameNumber = frameNumberWithinLoop;
                this.mLoopCount = 0;
                return frameNumberWithinLoop;
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
            if (this.mAnimationBackend.hasCacheFrame(i3)) {
                this.mFrameNumber = i3;
                return i3;
            }
            return this.mFrameNumber;
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
        if (frameScheduler instanceof SmoothSlidingFrameScheduler) {
            SmoothSlidingFrameScheduler smoothSlidingFrameScheduler = (SmoothSlidingFrameScheduler) frameScheduler;
            SmoothSlidingFrameScheduler smoothSlidingFrameScheduler2 = new SmoothSlidingFrameScheduler(smoothSlidingFrameScheduler.mAnimationBackend, smoothSlidingFrameScheduler.mDecodeStatus);
            smoothSlidingFrameScheduler2.mFrameNumber = this.mFrameNumber;
            smoothSlidingFrameScheduler2.mLastDrawTimeMs = this.mLastDrawTimeMs;
            smoothSlidingFrameScheduler2.mLoopCount = this.mLoopCount;
            smoothSlidingFrameScheduler2.mNowAnimationTimeMs = this.mNowAnimationTimeMs;
            return smoothSlidingFrameScheduler2;
        }
        return null;
    }
}
