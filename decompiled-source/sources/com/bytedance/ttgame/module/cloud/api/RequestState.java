package com.bytedance.ttgame.module.cloud.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes4.dex */
public @interface RequestState {
    public static final int NOT_REQUEST = 0;
    public static final int REQUEST_SERVER_WITHOUT_DID = 1;
    public static final int REQUEST_SERVER_WITH_DID = 2;
}
