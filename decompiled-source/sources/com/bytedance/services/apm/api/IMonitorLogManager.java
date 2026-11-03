package com.bytedance.services.apm.api;

import com.bytedance.news.common.service.manager.IService;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface IMonitorLogManager extends IService {
    void deleteLegacyLogByIds(String str, String str2);

    void getLegacyLog(long j2, long j3, String str, i iVar);

    List<JSONObject> getRecentUiActionRecords();
}
