package com.bytedance.ttgame.module.firebase.api;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public interface IFirebaseService extends IModuleApi {
    void getAppInstanceId(Context context, IResult iResult);

    boolean getFirebaseRemoteConfigBooleanValueForKey(String str);

    double getFirebaseRemoteConfigDoubleValueForKey(String str);

    long getFirebaseRemoteConfigLongValueForKey(String str);

    String getFirebaseRemoteConfigStringValueForKey(String str);

    void sendAnalyticsEvent(Context context, String str, Bundle bundle);

    void sendPayAnalyticsEvent(Context context, String str, float f2, String str2);

    void setConsent(Context context, boolean z, boolean z2);

    void setFirebaseRemoteConfigDefaults(JSONObject jSONObject);

    void setFirebaseUserId(Context context, String str);

    void setUserProperty(Context context, String str, String str2);
}
