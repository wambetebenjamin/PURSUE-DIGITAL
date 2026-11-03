package com.bytedance.ttgame.module.rn.api.model;

import java.util.Map;
/* loaded from: classes6.dex */
public interface RNAPICallback {
    public static final int BUNDLE_NOTFOUND = 1001;
    public static final int CALLBACK_NULL = 1004;
    public static final int DEGRADE = 1000;
    public static final int ERROR_HAPPENED = 1002;
    public static final int INIT_FAILED = 1003;

    void onFailed(int i2);

    void onSuccess(Map<String, Object> map);
}
