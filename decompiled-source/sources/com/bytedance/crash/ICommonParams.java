package com.bytedance.crash;

import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public interface ICommonParams {
    Map<String, Object> getCommonParams();

    String getDeviceId();

    List<String> getPatchInfo();

    Map<String, Integer> getPluginInfo();

    String getSessionId();

    long getUserId();
}
