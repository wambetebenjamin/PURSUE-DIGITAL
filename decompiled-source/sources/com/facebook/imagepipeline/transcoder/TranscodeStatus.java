package com.facebook.imagepipeline.transcoder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes8.dex */
public @interface TranscodeStatus {
    public static final int TRANSCODING_ERROR = 2;
    public static final int TRANSCODING_NO_RESIZING = 1;
    public static final int TRANSCODING_SUCCESS = 0;
}
