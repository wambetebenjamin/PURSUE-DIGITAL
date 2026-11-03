package com.bytedance.ttgame.module.rn.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public interface IRNCommonInterface {
    HashMap getBindCode();

    HashMap getCommonInfo();

    Object getConfigByKey(String str);

    List<HashMap<String, Object>> getPrefetchData();

    List<Map<String, Object>> getPrefetchImage();

    String getUrlByIngameID(String str);

    String[] getVersionCode(String str, String str2);
}
