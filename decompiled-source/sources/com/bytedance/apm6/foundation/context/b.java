package com.bytedance.apm6.foundation.context;

import android.content.Context;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: ApmContextAdapter.java */
/* loaded from: classes.dex */
public interface b {
    int getAid();

    String getAppVersion();

    String getChannel();

    Context getContext();

    String getDeviceId();

    JSONObject getDynamicHeaderExtras();

    int getManifestVersionCode();

    Map<String, String> getParamsExtras();

    String getProcessName();

    String getReleaseBuild();

    String getSessionId();

    JSONObject getStableHeaderExtras();

    long getUid();

    int getUpdateVersionCode();

    int getVersionCode();

    String getVersionName();

    JSONObject m();
}
