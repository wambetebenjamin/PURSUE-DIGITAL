package com.facebook.fresco.animation.frame;

import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.imageformat.ImageFormat;
/* loaded from: classes8.dex */
public interface FrameSchedulerFactory {
    FrameScheduler build(AnimationBackend animationBackend, Object obj, ImageFormat imageFormat, int i2);
}
