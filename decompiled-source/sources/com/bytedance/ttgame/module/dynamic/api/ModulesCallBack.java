package com.bytedance.ttgame.module.dynamic.api;

import java.util.HashMap;
import java.util.List;
/* loaded from: classes5.dex */
public interface ModulesCallBack {
    void onSuccess(List<HashMap<String, Object>> list);

    void onfailed(String str);
}
