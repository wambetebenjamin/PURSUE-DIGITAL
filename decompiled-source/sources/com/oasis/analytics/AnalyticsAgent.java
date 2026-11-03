package com.oasis.analytics;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public abstract class AnalyticsAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static AnalyticsAgent f17344a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17345b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17346c = true;
    protected AnalyticsAgent nextAgent = null;

    public AnalyticsAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static AnalyticsAgent createInstance(Context context) {
        String[] stringArray;
        AnalyticsAgent analyticsAgent;
        InstantiationException e2;
        IllegalAccessException e3;
        ClassNotFoundException e4;
        StringBuilder sb;
        String str;
        ClassNotFoundException classNotFoundException;
        if (f17346c || f17344a == null) {
            AnalyticsAgent analyticsAgent2 = null;
            for (String str2 : context.getResources().getStringArray(R.array.analytics_agent_class)) {
                Logger.i("AnalyticsAgent", "Start to create the AnalyticsAgent:" + str2);
                if (str2 != null) {
                    try {
                    } catch (ClassNotFoundException e5) {
                        analyticsAgent = analyticsAgent2;
                        e4 = e5;
                    } catch (IllegalAccessException e6) {
                        analyticsAgent = analyticsAgent2;
                        e3 = e6;
                    } catch (InstantiationException e7) {
                        analyticsAgent = analyticsAgent2;
                        e2 = e7;
                    }
                    if (str2.length() > 0) {
                        analyticsAgent = (AnalyticsAgent) Class.forName(str2).newInstance();
                        if (analyticsAgent2 == null) {
                            f17344a = analyticsAgent;
                            try {
                                Logger.i("AnalyticsAgent", str2 + " is instance");
                            } catch (ClassNotFoundException e8) {
                                e4 = e8;
                                sb = new StringBuilder();
                                str = "Throw ClassNotFoundException :";
                                classNotFoundException = e4;
                                Logger.e("AnalyticsAgent", sb.append(str).append(str2).toString(), classNotFoundException);
                                analyticsAgent2 = analyticsAgent;
                                Logger.i("AnalyticsAgent", "Create the AnalyticsAgent:" + str2 + " success");
                            } catch (IllegalAccessException e9) {
                                e3 = e9;
                                sb = new StringBuilder();
                                str = "Throw IllegalAccessException :";
                                classNotFoundException = e3;
                                Logger.e("AnalyticsAgent", sb.append(str).append(str2).toString(), classNotFoundException);
                                analyticsAgent2 = analyticsAgent;
                                Logger.i("AnalyticsAgent", "Create the AnalyticsAgent:" + str2 + " success");
                            } catch (InstantiationException e10) {
                                e2 = e10;
                                sb = new StringBuilder();
                                str = "Throw InstantiationException :";
                                classNotFoundException = e2;
                                Logger.e("AnalyticsAgent", sb.append(str).append(str2).toString(), classNotFoundException);
                                analyticsAgent2 = analyticsAgent;
                                Logger.i("AnalyticsAgent", "Create the AnalyticsAgent:" + str2 + " success");
                            }
                        } else {
                            analyticsAgent2.nextAgent = analyticsAgent;
                        }
                        analyticsAgent2 = analyticsAgent;
                    }
                }
                Logger.i("AnalyticsAgent", "Create the AnalyticsAgent:" + str2 + " success");
            }
            if (f17344a == null) {
                Logger.i("AnalyticsAgent", "Create default AnalyticsAgent.");
                f17344a = new a();
                f17345b = true;
            }
            return f17344a;
        }
        throw new AssertionError();
    }

    public static AnalyticsAgent getInstance() {
        if (f17346c || f17344a != null) {
            return f17344a;
        }
        throw new AssertionError();
    }

    public void closeDebug() {
    }

    public void customizeLaunchEvent(String str) {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean getDebugStatus() {
        return false;
    }

    public void isEmulator(EmulatorListener emulatorListener) {
    }

    public boolean isUseDefault() {
        return f17345b;
    }

    public abstract void netDetect();

    public void onLaunchEvent() {
    }

    public void openDebug() {
    }

    public abstract void reportCreateRole(String str);

    public abstract void reportEnterZone(String str);

    public abstract void reportEvent(String str);

    public void reportEvent(String str, JSONObject jSONObject) {
    }

    public abstract void reportLoginRole(String str);

    public abstract void reportLogoutRole(String str);

    public abstract void reportRoleLevelUp(String str);
}
