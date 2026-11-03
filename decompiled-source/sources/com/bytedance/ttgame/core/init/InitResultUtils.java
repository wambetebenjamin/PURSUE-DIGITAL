package com.bytedance.ttgame.core.init;

import com.bytedance.ttgame.rocketapi.callback.InitResult;
/* loaded from: classes3.dex */
public class InitResultUtils {
    public static InitResult getSucInitResult(String str) {
        InitResult initResult = new InitResult();
        initResult.code = 0;
        initResult.did = str;
        return initResult;
    }
}
