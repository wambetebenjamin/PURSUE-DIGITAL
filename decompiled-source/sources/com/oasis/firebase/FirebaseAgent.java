package com.oasis.firebase;

import android.content.Context;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.oasis.Logger.Logger;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public class FirebaseAgent {
    public static String Tag = null;

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f17530a = true;
    public static FirebaseAgent instance;
    public static boolean isUseDefaultAgent;

    public static FirebaseAgent createInstance(Context context) {
        if (f17530a || instance == null) {
            isUseDefaultAgent = false;
            try {
                String string = context.getString(R.string.firebase_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("FirebaseAgent", "Create the FirebaseAgent:" + string);
                    instance = (FirebaseAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("FirebaseAgent", "Create agent failed.", e2);
            }
            if (instance == null) {
                Logger.i("FirebaseAgent", "Create default FirebaseAgent.");
                instance = new FirebaseAgent();
                isUseDefaultAgent = true;
            }
            Tag = instance.getClass().getName();
            return instance;
        }
        throw new AssertionError();
    }

    public static FirebaseAgent getInstance() {
        if (f17530a || instance != null) {
            return instance;
        }
        throw new AssertionError();
    }

    public void getAppInstanceIdAsync(AppInstanceIdListener appInstanceIdListener) {
        Logger.d(Tag, "getAppInstanceId()");
    }

    public boolean getFirebaseRemoteConfigBooleanValueForKey(String str) {
        Logger.d(Tag, String.format("getFirebaseRemoteConfigBooleanValueForKey(key: %s)", str));
        return false;
    }

    public double getFirebaseRemoteConfigDoubleValueForKey(String str) {
        Logger.d(Tag, String.format("getFirebaseRemoteConfigDoubleValueForKey(key: %s)", str));
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public long getFirebaseRemoteConfigLongValueForKey(String str) {
        Logger.d(Tag, String.format("getFirebaseRemoteConfigLongValueForKey(key: %s)", str));
        return 0L;
    }

    public String getFirebaseRemoteConfigStringValueForKey(String str) {
        Logger.d(Tag, String.format("getFirebaseRemoteConfigStringValueForKey(key: %s)", str));
        return "";
    }

    public boolean sendAnalyticsEvent(String str, String str2) {
        Logger.d(Tag, String.format("sendPayAnalyticsEvent(event: %s, paramsJson: %s)", str, str2));
        return false;
    }

    public boolean sendPayAnalyticsEvent(String str, float f2, String str2) {
        Logger.d(Tag, String.format("sendPayAnalyticsEvent(event: %s, value: %f, currency: %s)", str, Float.valueOf(f2), str2));
        return false;
    }

    public boolean setFirebaseRemoteConfigDefaults(String str) {
        Logger.d(Tag, String.format("setFirebaseRemoteConfigDefaults(paramsJsonString: %s)", str));
        return true;
    }

    public boolean setFirebaseUserId(String str) {
        Logger.d(Tag, String.format("setFirebaseUserId(userId: %s)", str));
        return false;
    }

    public boolean setUserProperty(String str, String str2) {
        Logger.d(Tag, String.format("setUserProperty(key: %s, value: %s)", str, str2));
        return false;
    }
}
