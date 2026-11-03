package com.bytedance.crash;

import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public interface IUserCrashInfoCallback {
    Map<? extends String, ? extends String> a(CrashType crashType, JSONObject jSONObject);
}
