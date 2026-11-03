package com.bytedance.ttgame.module.dynamic.api;

import java.util.HashMap;
/* loaded from: classes5.dex */
public interface IDynamicData {
    void onFailed(String str);

    void onSuccess(HashMap<String, Object> hashMap);
}
