package com.oasis.appsflyer;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class AppsFlyerAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static AppsFlyerAgent f17472a = null;
    protected static Context appContext = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17473b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17474c = true;

    public AppsFlyerAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static AppsFlyerAgent createInstance(Context context) {
        if (f17474c || f17472a == null) {
            appContext = context;
            try {
                String string = context.getString(R.string.appsflyer_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("AppsFlyerAgent", "Create the AppsFlyerAgent:" + string);
                    f17472a = (AppsFlyerAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("AppsFlyerAgent", "Create AppsFlyerAgent failed.", e2);
            }
            if (f17472a == null) {
                Logger.i("AppsFlyerAgent", "Create default AppsFlyerAgent.");
                f17472a = new a();
                f17473b = true;
            }
            return f17472a;
        }
        throw new AssertionError();
    }

    public static AppsFlyerAgent getInstance() {
        if (f17474c || f17472a != null) {
            return f17472a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public abstract String getAppsFlyerUID();

    public boolean isUseDefault() {
        return f17473b;
    }

    public abstract void setAFAttributionListener(AFAttributionListener aFAttributionListener);

    public abstract void setAFDeeplinkListener(AFDeeplinkListener aFDeeplinkListener);

    public abstract void setCustomerUID(String str);

    public void start() {
        Logger.i("AppsFlyerAgent", "start");
    }
}
