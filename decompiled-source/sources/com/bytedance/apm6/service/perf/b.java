package com.bytedance.apm6.service.perf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;
/* compiled from: IPerfFilterManager.java */
/* loaded from: classes.dex */
public interface b {
    void addPerfTag(String str, String str2);

    ConcurrentHashMap<String, String> getPerfFilters();

    JSONObject getPerfFiltersJson();

    JSONObject getRealTimeMemInfo();

    CopyOnWriteArraySet<String> getSceneSet();

    String getSceneString();

    void removePerfTag(String str, String str2);

    void startScene(String str);

    void stopScene(String str);
}
