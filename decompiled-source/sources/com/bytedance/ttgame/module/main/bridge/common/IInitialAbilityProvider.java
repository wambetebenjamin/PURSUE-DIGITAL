package com.bytedance.ttgame.module.main.bridge.common;

import android.util.Pair;
import com.bytedance.ttgame.core.init.ErrorCodeConstants;
import com.bytedance.ttgame.rocketapi.callback.InitCallback;
/* loaded from: classes6.dex */
public interface IInitialAbilityProvider {
    public static final Pair<Integer, String> STATUS_UNINIT = new Pair<>(-2, "sdk is uninit.");
    public static final Pair<Integer, String> STATUS_INITING = new Pair<>(-1, "sdk is initing.");
    public static final Pair<Integer, String> STATUS_INIT_SUCCESS = new Pair<>(0, "sdk init success.");
    public static final Pair<Integer, String> STATUS_INIT_TIMEOUT = new Pair<>(Integer.valueOf((int) ErrorCodeConstants.ERROR_INIT_TIMEOUT), "sdk init failed, because of timeout.");

    Pair<Integer, String> getInitResult();

    void initSdk(InitCallback initCallback);
}
