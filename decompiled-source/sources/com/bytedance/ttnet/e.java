package com.bytedance.ttnet;

import android.content.Context;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: ITTNetDepend.java */
/* loaded from: classes8.dex */
public interface e {
    @Deprecated
    int checkHttpRequestException(Throwable th, String[] strArr);

    @Deprecated
    String executeGet(int i2, String str);

    @Deprecated
    String getApiIHostPrefix();

    int getAppId();

    @Deprecated
    String getCdnHostSuffix();

    String[] getConfigServers();

    Context getContext();

    ArrayList<String> getCookieFlushPathList();

    @Deprecated
    Map<String, String> getHostReverseMap();

    String getHostSuffix();

    int getProviderInt(Context context, String str, int i2);

    String getProviderString(Context context, String str, String str2);

    String getShareCookieMainDomain();

    Map<String, String> getTTNetServiceDomainMap();

    boolean isCronetPluginInstalled();

    @Deprecated
    boolean isPrivateApiAccessEnabled();

    void mobOnEvent(Context context, String str, String str2, JSONObject jSONObject);

    void monitorLogSend(String str, JSONObject jSONObject);

    @Deprecated
    void onAppConfigUpdated(Context context, JSONObject jSONObject);

    void onColdStartFinish();

    @Deprecated
    void onNetConfigUpdate(JSONObject jSONObject, boolean z);

    void onShareCookieConfigUpdated(String str);

    void saveMapToProvider(Context context, Map<String, ?> map);
}
