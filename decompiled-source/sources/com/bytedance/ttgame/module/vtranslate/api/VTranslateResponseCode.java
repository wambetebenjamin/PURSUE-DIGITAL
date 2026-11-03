package com.bytedance.ttgame.module.vtranslate.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes7.dex */
public @interface VTranslateResponseCode {
    public static final int REASON_ERROR = 6;
    public static final int REASON_LANGUAGE_ERROR = 4;
    public static final int REASON_PARAM_ERROR = 1;
    public static final int REASON_SERVER_ERROR = 5;
    public static final int REASON_SHARK_ERROR = 7;
    public static final int REASON_TOKEN_ERROR = 2;
    public static final int REASON_VOICE_ID_ERROR = 3;
    public static final int SUCCESS = 0;
}
