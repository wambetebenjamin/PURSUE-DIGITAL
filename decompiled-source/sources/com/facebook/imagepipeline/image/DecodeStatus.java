package com.facebook.imagepipeline.image;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes8.dex */
public @interface DecodeStatus {
    public static final int NOT_PROGRESSIVE = 0;
    public static final int PROGRESSIVE_END = 3;
    public static final int PROGRESSIVE_INTERMADIATE = 2;
    public static final int PROGRESSIVE_START = 1;
}
